package com.hideki.tracking.vision;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MaquinaService {
    public void salvarMaquina (Maquina maquina) {
        Conexao conexao = new Conexao();
        ConexaoMysql conexaoMysql = new ConexaoMysql();

        JdbcTemplate con = conexao.getConnection();
        JdbcTemplate conMysql = conexaoMysql.getConnection();

        con.update("insert into maquina(status,hostnameMaquina,nomeModeloCpu,clockCpu,nomeModeloRam,capacidadeTotalRam,nomeModeloDisco,capacidadeTotalDisco,leituraDisco,escritaDisco,fkEmpresa,fkJanelasBloqueadas) values (?,?,?,?,?,?,?,?,?,?,?,?)", 1,maquina.getHostnameMaquina(), maquina.getNomeModeloCpu(), maquina.getClockCpu(), maquina.getNomeModeloRam(), maquina.getCapacidadeTotalRam(), maquina.getNomeModeloDisco(), maquina.getCapacidadeTotalDisco(), maquina.getLeituraDisco(), maquina.getEscritaDisco(),maquina.getFkEmpresa(),null);
        conMysql.update("insert into maquina(idMaquina,status,hostnameMaquina,nomeModeloCpu,clockCpu,nomeModeloRam,capacidadeTotalRam,nomeModeloDisco,capacidadeTotalDisco,leituraDisco,escritaDisco,fkEmpresa,fkJanelasBloqueadas) values (?,?,?,?,?,?,?,?,?,?,?,?,?)",idMaquinaAtual(maquina.getHostnameMaquina(), maquina.getFkEmpresa()), 1,maquina.getHostnameMaquina(), maquina.getNomeModeloCpu(), maquina.getClockCpu(), maquina.getNomeModeloRam(), maquina.getCapacidadeTotalRam(), maquina.getNomeModeloDisco(), maquina.getCapacidadeTotalDisco(), maquina.getLeituraDisco(), maquina.getEscritaDisco(),null,null);
    }

    public List<Maquina> buscarPeloHostname(String hostname) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        return con.query("select * from maquina where hostnameMaquina = ?", new BeanPropertyRowMapper(Maquina.class), hostname);
    }

    public List<Maquina> buscarPeloHostname(String hostname, Integer fkEmpresa) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        return con.query("select * from maquina where hostnameMaquina = ? and fkEmpresa = ?", new BeanPropertyRowMapper(Maquina.class), hostname, fkEmpresa);
    }

    public Integer idMaquinaAtual(String hostname, Integer fkEmpresa) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        return con.queryForObject("select idMaquina from maquina where hostnameMaquina = ? and fkEmpresa = ?", Integer.class, hostname, fkEmpresa);
    }




}
