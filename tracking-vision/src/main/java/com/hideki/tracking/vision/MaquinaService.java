package com.hideki.tracking.vision;

import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaService {
    public void salvarMaquina (Maquina maquina) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConnection();

        con.update("insert into maquina values (null,?,?,?,?,?,?,?,?,?)", maquina.getMarcaMaquina(), maquina.getNomeModeloCpu(), maquina.getClockCpu(), maquina.getNomeModeloRam(), maquina.getCapacidadeTotalRam(), maquina.getNomeModeloDisco(), maquina.getCapacidadeTotalDisco(), maquina.getLeituraDisco(), maquina.getEscritaDisco());

    }
}
