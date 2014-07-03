/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.conexaoBDNC;

import com.orientechnologies.orient.jdbc.OrientJdbcConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class ConnectionFactory {

    private static ConnectionFactory instance;

    private ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = (ConnectionFactory) new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Properties info = new Properties();
        info.put("user", "admin");
        info.put("password", "admin");
        try {
            Class.forName("com.orientechnologies.orient.jdbc.OrientJdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        Connection connection = (OrientJdbcConnection) DriverManager.getConnection("jdbc:orient:remote:localhost/loteria", info);
        return connection;
    }
}
