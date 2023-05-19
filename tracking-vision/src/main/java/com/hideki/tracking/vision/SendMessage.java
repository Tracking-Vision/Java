/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hideki.tracking.vision;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

import java.io.IOException;

/**
 *
 * @author PAULOROBERTODEALMEID
 */
public class SendMessage {

    private String webHookUrl = "https://hooks.slack.com/services/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";
    private String channel = "geral";
    public void sendMessage(String message) throws IOException {
        try {

            Payload payload = Payload.builder()
                    .channel(channel)
                    .username("TrackingVision")
                    .text(message)
                    .build();

            WebhookResponse response = Slack.getInstance().send(webHookUrl, payload);
        }catch(Exception e) {
            System.out.printf("Erro ao enviar mensagem: %s", e.getMessage());
        }

    }
}
