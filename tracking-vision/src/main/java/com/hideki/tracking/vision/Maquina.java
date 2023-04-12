package com.hideki.tracking.vision;

public class Maquina {
    private Integer idMaquina;
    private String marcaMaquina;
    private String nomeModeloCpu;
    private Long clockCpu;
    private String nomeModeloRam;
    private Long capacidadeTotalRam;
    private String nomeModeloDisco;
    private Double capacidadeTotalDisco;
    private Long leituraDisco;
    private Long escritaDisco;

    public Maquina(Integer idMaquina, String marcaMaquina, String nomeModeloCpu, Long clockCpu, String nomeModeloRam, Long capacidadeTotalRam, String nomeModeloDisco, Double capacidadeTotalDisco, Long leiuraDisco, Long escritaDisco) {
        this.idMaquina = idMaquina;
        this.marcaMaquina = marcaMaquina;
        this.nomeModeloCpu = nomeModeloCpu;
        this.clockCpu = clockCpu;
        this.nomeModeloRam = nomeModeloRam;
        this.capacidadeTotalRam = capacidadeTotalRam;
        this.nomeModeloDisco = nomeModeloDisco;
        this.capacidadeTotalDisco = capacidadeTotalDisco;
        this.leituraDisco = leiuraDisco;
        this.escritaDisco = escritaDisco;
    }

    public Maquina() {
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getMarcaMaquina() {
        return marcaMaquina;
    }

    public void setMarcaMaquina(String marcaMaquina) {
        this.marcaMaquina = marcaMaquina;
    }

    public String getNomeModeloCpu() {
        return nomeModeloCpu;
    }

    public void setNomeModeloCpu(String nomeModeloCpu) {
        this.nomeModeloCpu = nomeModeloCpu;
    }

    public Long getClockCpu() {
        return clockCpu;
    }

    public void setClockCpu(Long clockCpu) {
        this.clockCpu = clockCpu;
    }

    public String getNomeModeloRam() {
        return nomeModeloRam;
    }

    public void setNomeModeloRam(String nomeModeloRam) {
        this.nomeModeloRam = nomeModeloRam;
    }

    public Long getCapacidadeTotalRam() {
        return capacidadeTotalRam;
    }

    public void setCapacidadeTotalRam(Long capacidadeTotalRam) {
        this.capacidadeTotalRam = capacidadeTotalRam;
    }

    public String getNomeModeloDisco() {
        return nomeModeloDisco;
    }

    public void setNomeModeloDisco(String nomeModeloDisco) {
        this.nomeModeloDisco = nomeModeloDisco;
    }

    public Double getCapacidadeTotalDisco() {
        return capacidadeTotalDisco;
    }

    public void setCapacidadeTotalDisco(Double capacidadeTotalDisco) {
        this.capacidadeTotalDisco = capacidadeTotalDisco;
    }

    public Long getLeituraDisco() {
        return leituraDisco;
    }

    public void setLeituraDisco(Long leiuraDisco) {
        this.leituraDisco = leiuraDisco;
    }

    public Long getEscritaDisco() {
        return escritaDisco;
    }

    public void setEscritaDisco(Long escritaDisco) {
        this.escritaDisco = escritaDisco;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "idMaquina=" + idMaquina +
                ", marcaMaquina='" + marcaMaquina + '\'' +
                ", nomeModeloCpu='" + nomeModeloCpu + '\'' +
                ", clockCpu=" + clockCpu +
                ", nomeModeloRam='" + nomeModeloRam + '\'' +
                ", capacidadeTotalRam=" + capacidadeTotalRam +
                ", nomeModeloDisco='" + nomeModeloDisco + '\'' +
                ", capacidadeTotalDisco=" + capacidadeTotalDisco +
                ", leiuraDisco=" + leituraDisco +
                ", escritaDisco=" + escritaDisco +
                '}';
    }


}
