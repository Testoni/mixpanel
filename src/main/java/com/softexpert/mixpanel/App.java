package com.softexpert.mixpanel;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;

public class App {

    // Buscar o token nas configuracoes do Mixpanel web application
    private static final String PROJECT_TOKEN = "a49f9f392fdde63d49e8ca3f185cad2e";

    // ID referenciando o registro
    private static String distinctId = UUID.randomUUID().toString();

    public static void main(String[] args) throws JSONException, IOException {

        MessageBuilder messageBuilder = new MessageBuilder(PROJECT_TOKEN);

        // Cria um evento
        JSONObject sentEvent = messageBuilder.event(distinctId, "Sent Message", null);

        // Monta as propriedades
        JSONObject props = new JSONObject();
        props.put("Gender", "Female");
        props.put("Plan", "Premium");

        JSONObject planEvent = messageBuilder.event(distinctId, "Plan Selected", props);

        // Monta um grupo de mensagens em um unico ClientDelivery, pode ocorrer em uma thread separada ou um processo da chamada MessageBuilder.event()
        ClientDelivery delivery = new ClientDelivery();
        delivery.addMessage(sentEvent);
        delivery.addMessage(planEvent);

        // Usa uma instancia do MixpanelAPI para enviar as mensagens para o servidor do Mixpanel
        MixpanelAPI mixpanel = new MixpanelAPI();
        mixpanel.deliver(delivery);

        System.out.println("Registrado com sucesso!");

    }

}
