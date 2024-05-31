package com.jeesite.modules.yelanyanyu.device_detecter.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@Configuration
public class MqttConfig {
    @Value("${mqtt.broker.url}")
    private String broker;

    @Value("${mqtt.client.clientId}")
    private String clientId;

    @Value("${mqtt.client.topics}")
    private String[] topics;

    @Value("${mqtt.client.username}")
    private String username;

    @Value("${mqtt.client.password}")
    private String password;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{broker});
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        return options;
    }

    @Bean
    public MqttClient mqttClient(MqttConnectOptions mqttConnectOptions) throws MqttException {
        MqttClient mqttClient = new MqttClient(broker, clientId);
        mqttClient.connect(mqttConnectOptions);
        return mqttClient;
    }
}
