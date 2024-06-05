package com.jeesite.test;

import com.alibaba.fastjson.JSON;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.modules.yelanyanyu.device_detecter.device.entity.LedData;
import org.junit.Test;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class UnitTest {
    @Test
    public void t1() {
        String json = "{\n" +
                "  \"lux\":\"1.11\",\n" +
                "  \"temprature\":\"1\"\n" +
                "}";
        System.out.println("json: " + JsonMapper.fromJson(JSON.parse(json).toString(), LedData.class));
    }
}
