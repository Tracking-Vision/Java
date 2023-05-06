package com.hideki.tracking.vision;

import org.springframework.jdbc.core.JdbcTemplate;

public class LogService {
    public void salvarLog(Log log) {
        Conexao conexao = new Conexao();
        ConexaoMysql conexaoMysql = new ConexaoMysql();

        JdbcTemplate con = conexao.getConnection();
        JdbcTemplate conMysql = conexaoMysql.getConnection();


        con.update("insert into log(horarioCapturado,janelaPid,tituloJanela,usoCpu,usoDisco,usoRam,bytesRecebidos,bytesEnviados,fkMaquina) values (?, ?, ?, ?, ?,?,?,?,?)", log.getHorarioCapturado(), log.getJanelaPid(), log.getTituloJanela(), log.getUsoCpu(), log.getUsoDisco(), log.getUsoRam(),log.getBytesRecebidos(),log.getBytesEnviados(), log.getFkMaquina());
        conMysql.update("insert into log(idLog,horarioCapturado,janelaPid,tituloJanela,usoCpu,usoDisco,usoRam,bytesRecebidos,bytesEnviados,fkMaquina) values (?,?, ?, ?, ?, ?,?,?,?,?)",retornarIdLog(log), log.getHorarioCapturado(), log.getJanelaPid(), log.getTituloJanela(), log.getUsoCpu(), log.getUsoDisco(), log.getUsoRam(),log.getBytesRecebidos(),log.getBytesEnviados(), log.getFkMaquina());
    }

    public Integer retornarIdLog (Log log) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        return con.queryForObject("select idLog from log where horarioCapturado = ? and janelaPid = ? and tituloJanela = ? and usoCpu = ? and usoDisco = ? and usoRam = ? and bytesRecebidos = ? and bytesEnviados = ? and fkMaquina = ?", Integer.class, log.getHorarioCapturado(), log.getJanelaPid(), log.getTituloJanela(), log.getUsoCpu(), log.getUsoDisco(), log.getUsoRam(),log.getBytesRecebidos(),log.getBytesEnviados(), log.getFkMaquina());
    }

}
