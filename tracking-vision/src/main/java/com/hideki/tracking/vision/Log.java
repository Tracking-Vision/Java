package com.hideki.tracking.vision;

public class Log {
    private Integer idLog;
    private String horarioCapturado;
    private Long janelaPid;
    private String tituloJanela;
    private Double usoCpu;
    private Long usoDisco;
    private Long usoRam;

    public Log(Integer idLog, String horarioCapturado, Long janelaPid, String tituloJanela, Double usoCpu, Long usoDisco, Long usoRam) {
        this.idLog = idLog;
        this.horarioCapturado = horarioCapturado;
        this.janelaPid = janelaPid;
        this.tituloJanela = tituloJanela;
        this.usoCpu = usoCpu;
        this.usoDisco = usoDisco;
        this.usoRam = usoRam;
    }

    public Log() {
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getHorarioCapturado() {
        return horarioCapturado;
    }

    public void setHorarioCapturado(String horarioCapturado) {
        this.horarioCapturado = horarioCapturado;
    }

    public Long getJanelaPid() {
        return janelaPid;
    }

    public void setJanelaPid(Long janelaPid) {
        this.janelaPid = janelaPid;
    }

    public String getTituloJanela() {
        return tituloJanela;
    }

    public void setTituloJanela(String tituloJanela) {
        this.tituloJanela = tituloJanela;
    }

    public Double getUsoCpu() {
        return usoCpu;
    }

    public void setUsoCpu(Double usoCpu) {
        this.usoCpu = usoCpu;
    }

    public Long getUsoDisco() {
        return usoDisco;
    }

    public void setUsoDisco(Long usoDisco) {
        this.usoDisco = usoDisco;
    }

    public Long getUsoRam() {
        return usoRam;
    }

    public void setUsoRam(Long usoRam) {
        this.usoRam = usoRam;
    }
}


