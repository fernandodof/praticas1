/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.dao;

import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.conexaoBDNC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fernando
 */
public class PessoaDAO {

    private Connection connection;

    public PessoaDAO() {

    }

    public boolean insert(Pessoa pessoa) throws SQLException {
        this.abrirConexao();
        String sql = "INSERT INTO pessoa(nome, email, senha, tipo) values('" + pessoa.getNome() + "','"
                + pessoa.getEmail() + "','" + pessoa.getSenha() + "','" + pessoa.isAdm() + "')";
        try {
            Statement stat = connection.createStatement();
            stat.executeQuery(sql);
            stat.close();
        } finally {
            this.fecharConexao();
        }
        return true;
    }

//    public boolean insert(Pessoa pessoa) throws SQLException {
//        this.abrirConexao();
//        String sql = "INSERT INTO pessoa(nome, email, senha, tipo) values(?,?,?,?)";
//        try {
//            PreparedStatement stat = connection.prepareStatement(sql);
//            stat.setString(1, pessoa.getNome());
//            stat.setString(2, pessoa.getEmail());
//            stat.setString(3, pessoa.getSenha());
//            stat.setBoolean(4, pessoa.isAdm());
//            stat.executeUpdate();
//            stat.close();
//        } finally {
//            this.fecharConexao();
//        }
//        return true;
//    }
    public Pessoa logar(String login, String senha) throws SQLException {
        this.abrirConexao();
        String sql = "SELECT @rid.asString() as id, nome, tipo FROM pessoa WHERE email = ? and senha = ?";
        Pessoa pessoa = new Pessoa();
        try {
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, login);
            stat.setString(2, senha);
            ResultSet resultado = stat.executeQuery();
            resultado.next();
            pessoa.setNome(resultado.getString("nome"));
            pessoa.setId(resultado.getString("id"));
            pessoa.setAdm(resultado.getBoolean("tipo"));
            stat.close();
        } finally {
            this.fecharConexao();
        }
        return pessoa;
    }

    public Pessoa getById(String id) throws SQLException {
        this.abrirConexao();
        String sql = "SELECT @rid.asString() as id, nome, tipo FROM " + id;
        Pessoa pessoa = new Pessoa();
        try {
            Statement stat = connection.createStatement();
            ResultSet resultado = stat.executeQuery(sql);
            resultado.next();
            pessoa.setNome(resultado.getString("nome"));
            pessoa.setId(resultado.getString("id"));
            pessoa.setAdm(resultado.getBoolean("tipo"));
            stat.close();
        } finally {
            this.fecharConexao();
        }
        return pessoa;
    }

    public void ligarAposta(String from, String to) throws SQLException {
        this.abrirConexao();
        String sql = "CREATE edge realiza FROM " + from + " TO " + to;
        try {
            Statement stat = connection.createStatement();
            stat.executeQuery(sql);
            stat.close();
        } finally {
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
