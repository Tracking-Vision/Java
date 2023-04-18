/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hideki.tracking.vision;

/**
 *
 * @author PAULOROBERTODEALMEID
 */
public class Redes {
    private Integer idRede;
    private String nomeRede;
    private String nomeExibicao;
    private String ipv4;
    private String mac;

    public Redes(Integer idRede, String nomeRede, String nomeExibicao, String ipv4, String mac) {
        this.idRede = idRede;
        this.nomeRede = nomeRede;
        this.nomeExibicao = nomeExibicao;
        this.ipv4 = ipv4;
        this.mac = mac;
    }

    public Redes() {
    }

    public Integer getIdRede() {
        return idRede;
    }

    public void setIdRede(Integer idRede) {
        this.idRede = idRede;
    }

    public String getNomeRede() {
        return nomeRede;
    }

    public void setNomeRede(String nomeRede) {
        this.nomeRede = nomeRede;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "Rede{" + "idRede=" + idRede + ", nomeRede=" + nomeRede + ", nomeExibicao=" + nomeExibicao + ", ipv4=" + ipv4 + ", mac=" + mac + '}';
    }

    
    
}
