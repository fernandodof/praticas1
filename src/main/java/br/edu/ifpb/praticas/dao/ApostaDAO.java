/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.dao;

import br.edu.ifpb.praticas.beans.Aposta;
import br.edu.ifpb.praticas.conexaoBDNC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Fernando
 */
public class ApostaDAO {

    private Connection connection;

    public boolean insert(Aposta aposta, String idPessoa, String idConcurso) throws SQLException {
        this.abrirConexao();
        //"INSERT INTO aposta(numerosSorteados) values('" +aposta.getNumeros()+"')"
        String sql = "INSERT INTO aposta SET numerosApostados = " + aposta.getNumeros() + " , feitaPor = " + idPessoa + " , pertence = " + idConcurso;
        System.out.println("Query :" + sql);
        try {
            Statement stat = connection.createStatement();
            stat.executeQuery(sql);
            stat.close();
        } finally {
            this.fecharConexao();
        }
        return true;
    }

//    public void ligarConcurso(String from, String to) throws SQLException{
//        this.abrirConexao();
//        String sql = "CREATE edge pertence FROM "+ from +" TO "+to;
//        try{
//            Statement stat = connection.createStatement();
//            stat.executeQuery(sql);
//            stat.close();
//        }finally{
//            this.fecharConexao();
//        }
//    }
    public List<Aposta> getApostasPessoa(String id) throws SQLException {
        this.abrirConexao();
        String sql = "SELECT @rid.asString() as id, numerosApostados as numeros, pertence.asString() as concurso FROM aposta WHERE feitaPor = "+id;
        List<Aposta> apostas = new ArrayList();
        ConcursoDAO concursoDAO = new ConcursoDAO();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);
            while (resultado.next()) {
                Aposta aposta = new Aposta();
                aposta.setId(resultado.getString("id"));
                Set numeros = new TreeSet();
                numeros.addAll((Collection) resultado.getObject("numeros"));
                aposta.setNumeros((SortedSet) numeros);
                aposta.setConcurso(concursoDAO.getById(resultado.getString("concurso")));
                apostas.add(aposta);
            }
        } finally {
            this.abrirConexao();
        }
        return apostas;

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
