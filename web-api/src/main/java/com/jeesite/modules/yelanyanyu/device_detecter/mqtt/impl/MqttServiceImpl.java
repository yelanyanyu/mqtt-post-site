package com.jeesite.modules.yelanyanyu.device_detecter.mqtt.impl;

import com.jeesite.modules.yelanyanyu.device_detecter.mqtt.MqttService;
import com.jeesite.modules.yelanyanyu.device_detecter.vo.LEDVO;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@Service
@Slf4j
public class MqttServiceImpl implements MqttService {
    @Resource
    private MqttClient mqttClient;

    @Override
    public boolean sendMessage(String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        try {
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            log.error("Failed to send message to topic: " + topic, e);
            return false;
        }
        return true;
    }
}
