package com.hideki.tracking.vision;

import org.springframework.jdbc.core.JdbcTemplate;

public class LogService {
    public void salvarLog(Log log) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        con.update("insert into log values (?, ?, ?, ?, ?, ?, ?,?)", null,log.getHorarioCapturado(), log.getJanelaPid(), log.getTituloJanela(), log.getUsoCpu(), log.getUsoDisco(), log.getUsoRam(), 1);
    }
}
