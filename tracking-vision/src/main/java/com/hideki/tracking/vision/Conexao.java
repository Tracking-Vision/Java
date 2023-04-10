/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hideki.tracking.vision;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author hidek
 */
public class Conexao {
    
    private JdbcTemplate connection;

    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        dataSource​.setUrl("jdbc:sqlserver://localhost;encrypt=true;database=BANCO;integratedSecurity=true;");

        dataSource​.setUsername("USUARIO");

        dataSource​.setPassword("SENHA");

        this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }
}
