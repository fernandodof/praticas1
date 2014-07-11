/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.dao;

import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.conexaoBDNC.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Fernando
 */
public class ConcursoDAO {

    private Connection connection;

//    public boolean insert(Concurso concurso) throws SQLException {
//        this.abrirConexao();
//        String sql = "INSERT INTO concurso(dataSorteio, realizado) values(?,?)";
//        try {
//            PreparedStatement stat = connection.prepareStatement(sql);
//            stat.setTimestamp(1, (Timestamp) concurso.getDataHora());
//            stat.setBoolean(2, concurso.isRealizado());
//            stat.executeUpdate();
//            stat.close();
//        } finally {
//            this.fecharConexao();
//        }
//        return true;
//    }
    public boolean insert(Concurso concurso) throws SQLException {
        this.abrirConexao();
        String sql = "INSERT INTO concurso(dataSorteio, realizado) values('" + concurso.getDataHora() + "','" + concurso.isRealizado() + "')";
        try {
            Statement stat = connection.createStatement();
            stat.executeQuery(sql);
            stat.close();
        } finally {
            this.fecharConexao();
        }
        return true;
    }

    public Concurso getProximoConcurso() throws SQLException {
        this.abrirConexao();
        String sql = "SELECT @rid.asString() as id, dataSorteio FROM Concurso WHERE realizado = FALSE ORDER BY dataSorteio asc LIMIT 1";
        Concurso concurso = new Concurso();
        try {
            Statement stat = connection.createStatement();
            ResultSet resultado = stat.executeQuery(sql);
            if (!resultado.next()) {
                concurso = null;
            } else {
                concurso.setId(resultado.getString("id"));
                concurso.setDataHora(resultado.getTimestamp("dataSorteio"));
                concurso.setRealizado(false);
            }
            stat.close();
        } finally {
            this.fecharConexao();
        }
        return concurso;
    }

    public Concurso getById(String id) throws SQLException {
        this.abrirConexao();
        String sql = "SELECT @rid.asString() as id, dataSorteio as data, numerosSorteados as numeros, realizado FROM "+id;
        Concurso concurso = new Concurso();
        try {
            Statement stat = connection.createStatement();
            ResultSet resultado = stat.executeQuery(sql);
            resultado.next();
            concurso.setId(resultado.getString("id"));
            concurso.setDataHora(resultado.getTimestamp("data"));
            concurso.setRealizado(resultado.getBoolean("realizado"));
            if(resultado.getObject("numeros")!=null){
                Set numeros = new TreeSet();
                numeros.addAll((Collection) resultado.getObject("numeros"));
                concurso.setNumeros((SortedSet) numeros);
            }
            stat.close();
        } finally {
            this.fecharConexao();
        }
        return concurso;
    }
    
    public void receberNumerosSorteados(String id, Set numeros) throws SQLException{
        this.abrirConexao();
        String sql =  "UPDATE concurso SET numerosSorteados = ?, realizado = true WHERE @rid = ?";
        try{
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setObject(1, numeros);
            stat.setString(2, id);
            stat.executeUpdate();
        }finally{
            this.fecharConexao();
        }
    }

    private void abrirConexao() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = ConnectionFactory.getInstance().getConnection();
        }
    }

    private void fecharConexao() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
            this.connection = null;
        }
    }
}
