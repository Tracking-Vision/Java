/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hideki.tracking.vision;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author PAULOROBERTODEALMEID
 */
public class RedeService {
    public void cadastrarRede(Redes rede) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        con.update("insert into rede(nomeRede,nomeExibicao,ipv4,mac) values (?, ?, ?, ?)", rede.getNomeRede(), rede.getNomeExibicao(), rede.getIpv4(), rede.getMac());
    }

    public Integer retornarIdRede (Redes rede) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        return con.queryForObject("select idRede from rede where nomeRede = ? and nomeExibicao = ? and ipv4 = ? and mac = ?", Integer.class, rede.getNomeRede(), rede.getNomeExibicao(), rede.getIpv4(), rede.getMac());
    }
}
