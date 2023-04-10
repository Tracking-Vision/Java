/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hideki.tracking.vision;

import com.github.britooo.looca.api.group.sistema.Sistema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author hidek
 */
public class Teste {

    public static void main(String[] args) {
        API api = new API();
        Conexao conexao = new Conexao();
        Dados dado = new Dados();
        List<Dados> listaDados = new ArrayList();
        
        JdbcTemplate con = conexao.getConnection();

        // API
        // DADOS MONITORADOS
        System.out.println("Bytes de leitura do disco: " + api.getBystesDeLeitura());
        System.out.println("Bytes de Escrita do disco: " + api.getBytesDeEscrita());
        System.out.println("Memoria em uso: " + api.getMemoriaEmUso());
        System.out.println("Processador em uso: " + api.getProcessadorEmUso());
        System.out.println("Bytes Enviados pela rede: " + api.getBytesEnviados());
        System.out.println("Bytes Recebidos pela rede: " + api.getBytesRecebidos());
        System.out.println("ID da Janela: " + api.getJanelaId() + "\n");

        System.out.println("\n" + dado);
        
        // INFORMAÇÕES HARDWARE
        System.out.println("\nINFORMAÇÕES HARDWARE: " + api.getHardware());
        // INFORMAÇÕES PROCESSOS
        System.out.println("\nINFORMAÇÕES PROCESSOS: " + api.getProcesso());

        // BANCO DE DADOS
        con.update("INSERT INTO ---------- VALUES (?, ?);"); //, teste.getNome(), teste.getAnoLancamento());
        listaDados = con.query("SELECT * FROM -------;", new BeanPropertyRowMapper(Dados.class));

        System.out.println(listaDados);

    }

}
