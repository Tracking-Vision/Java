package com.hideki.tracking.vision;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class SlackService {
    public static Slack retornarLink() {
        Conexao conexao = new Conexao();

        return (Slack) conexao.getConnection().queryForObject("select * from slack", new BeanPropertyRowMapper(Slack.class));
    }
}
