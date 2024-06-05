package com.jeesite.modules.yelanyanyu.device_detecter.mqtt;

import com.jeesite.modules.yelanyanyu.device_detecter.vo.LEDVO;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public interface MqttService {
    public boolean sendMessage(String topic, String message);

    public boolean subscribeTopic(String topic);
}
