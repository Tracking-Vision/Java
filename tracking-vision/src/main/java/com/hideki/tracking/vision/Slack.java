package com.hideki.tracking.vision;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Slack {
    private static final String URL = "https://hooks.slack.com/services/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";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {
        System.out.println("Enviando mensagem para o slack");
        HttpRequest request = HttpRequest.newBuilder(URI.create(URL)).header("accept", "application/json").POST(HttpRequest.BodyPublishers.ofString(content.toString())).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: "+response.body());
        System.out.println("Request: "+ response.request());
    }
}
