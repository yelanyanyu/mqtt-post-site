package com.jeesite.modules.yelanyanyu.device_detecter.mqtt.impl;

import com.alibaba.fastjson.JSON;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.modules.yelanyanyu.device_detecter.device.entity.LedData;
import com.jeesite.modules.yelanyanyu.device_detecter.message.MqttMessageQueue;
import com.jeesite.modules.yelanyanyu.device_detecter.mqtt.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@Service
@Slf4j
public class MqttServiceImpl implements MqttService, MqttCallback {
    @Resource
    private MqttMessageQueue<LedData> messageQueue;
    private final MqttClient mqttClient;

    @Autowired
    public MqttServiceImpl(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
        this.mqttClient.setCallback(this);
    }

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

    @Override
    public boolean subscribeTopic(String topic) {
        try {
            mqttClient.subscribe(topic);
            return true;
        } catch (MqttException e) {
            log.error("error: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.error("mqtt disconnected, try to reconnect...: {}", throwable.getMessage());
        try {
            mqttClient.reconnect();
            log.info("reconnect successfully!");
        } catch (MqttException e) {
            log.error("reconnect failed with error: {}", e.getMessage());
        }
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String payload = new String(mqttMessage.getPayload());
        String json = JsonMapper.toJson(payload);
        log.info("topic: {}, payload-json: {}", s, json);
        messageQueue.put(s, JsonMapper.fromJson(JSON.parse(json).toString(), LedData.class));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
