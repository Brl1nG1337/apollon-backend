package de.apollon.backend.service;

import de.apollon.backend.repository.ApollonDeviceFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MqttMessageHandler {

    @Autowired
    private ApollonDeviceFeatureRepository featureRepository;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    @Transactional
    public void handleMessage(Message<?> message) {
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
        String payload = message.getPayload().toString(); // Das JSON vom ESP32

        // Wir suchen in der DB nach dem Feature, das auf diesem 'stateTopic' sendet
        featureRepository.findByStateTopic(topic).ifPresentOrElse(feature -> {

            // Payload speichern
            feature.setLastPayload(payload);
            feature.setLastUpdated(LocalDateTime.now());

            // Speichern
            featureRepository.save(feature);

            System.out.println("Update [" + feature.getType() + "] " + feature.getName() + ": " + payload);

        }, () -> {
            System.out.println("Warnung: Nachricht auf unbekanntem Topic empfangen: " + topic);
        });
    }
}