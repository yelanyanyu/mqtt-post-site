package com.jeesite.modules.yelanyanyu.device_detecter.message;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@Component
public class MqttMessageQueue<T> {
    private final ConcurrentHashMap<String, Deque<T>> map = new ConcurrentHashMap<>();

    public void put(String key, T message) {
        while (this.map.containsKey(key) && !this.map.get(key).isEmpty()) {
            this.popLast(key);
        }
        this.map.computeIfAbsent(key, k -> new ArrayDeque<>(1)).addFirst(message);
    }

    public boolean popLast(String topic) {
        if (map.containsKey(topic)) {
            Deque<T> ts = map.get(topic);
            if (ts.isEmpty()) {
                return false;
            }
            ts.pollLast();
            return true;
        }
        return false;
    }

    public T queryFirst(String topic) {
        if (map.containsKey(topic)) {
            return this.map.get(topic).peekFirst();
        }
        return null;
    }
}
