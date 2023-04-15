package com.hideki.tracking.vision;

import org.springframework.jdbc.core.JdbcTemplate;

public class LogService {
    public void salvarLog(Log log) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        con.update("insert into log(horarioCapturado,janelaPid,tituloJanela,usoCpu,usoDisco,usoRam,fkMaquina,fkLimites) values (?, ?, ?, ?, ?, ?,?,?)", log.getHorarioCapturado(), log.getJanelaPid(), log.getTituloJanela(), log.getUsoCpu(), log.getUsoDisco(), log.getUsoRam(), log.getFkMaquina(),null);
    }
}
