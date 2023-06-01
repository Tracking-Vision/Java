package com.hideki.tracking.vision;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class AlertasSlack {
    public static void mandarAlerta(Log log, List<Limites> limites) throws IOException, InterruptedException {
        String mensagem = "A maquina " + log.getFkMaquina();

        if (log.getUsoCpu() <= limites.get(0).getOk()) {
            mensagem += String.format(" esta com o uso de CPU em %.2f%%, e está com uso OK", log.getUsoCpu());
        }else if(log.getUsoCpu() <= limites.get(0).getAviso()){
            mensagem += String.format(" esta com o uso de CPU em %.2f%%, e está com uso em ALERTA", log.getUsoCpu());
        }else if(log.getUsoCpu() <= limites.get(0).getPerigo()){
            mensagem += String.format(" esta com o uso de CPU em %.2f%%, e está com uso em CRITICO", log.getUsoCpu());
        }else{
            mensagem += String.format(" esta com o uso de CPU em %.2f%%, e está com uso em EMERGENCIA", log.getUsoCpu());
        }

        Slack.sendMessage(new JSONObject().put("text", mensagem));

        mensagem = "A maquina " + log.getFkMaquina();

        if (log.getUsoRam() <= limites.get(0).getOk()) {
            mensagem += String.format(" esta com o uso de RAM em %.2f%%, e está com uso OK", log.getUsoRam());
        } else if(log.getUsoRam() <= limites.get(0).getAviso()){
            mensagem += String.format(" esta com o uso de RAM em %.2f%%, e está com uso em ALERTA", log.getUsoRam());
        } else if(log.getUsoRam() <= limites.get(0).getPerigo()){
            mensagem += String.format(" esta com o uso de RAM em %.2f%%, e está com uso em CRITICO", log.getUsoRam());
        } else{
            mensagem += String.format(" esta com o uso de RAM em %.2f%%, e está com uso em EMERGENCIA", log.getUsoRam());
        }

        Slack.sendMessage(new JSONObject().put("text", mensagem));

        mensagem = "A maquina " + log.getFkMaquina();

        if (log.getUsoDisco() <= limites.get(0).getOk()) {
            mensagem += String.format(" esta com o uso de Disco em %.2f%%, e está com uso OK", log.getUsoDisco());
        }else if(log.getUsoDisco() <= limites.get(0).getAviso()){
            mensagem += String.format(" esta com o uso de Disco em %.2f%%, e está com uso em ALERTA", log.getUsoDisco());
        }else if(log.getUsoDisco() <= limites.get(0).getPerigo()){
            mensagem += String.format(" esta com o uso de Disco em %.2f%%, e está com uso em CRITICO", log.getUsoDisco());
        }else{
            mensagem += String.format(" esta com o uso de Disco em %.2f%%, e está com uso em EMERGENCIA", log.getUsoDisco());
        }

        Slack.sendMessage(new JSONObject().put("text", mensagem));
    }
}

