package com.hideki.tracking.vision;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class EmpresaService {
    public static Empresa retornarEmpresa(Integer fkEmpresa) {
        Conexao conexao = new Conexao();

        return (Empresa) conexao.getConnection().queryForObject("select * from empresa where idEmpresa = ?", new Object[]{fkEmpresa}, new BeanPropertyRowMapper(Empresa.class));
    }
}
