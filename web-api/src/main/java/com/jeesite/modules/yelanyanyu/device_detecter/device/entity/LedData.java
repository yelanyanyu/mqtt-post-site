package com.jeesite.modules.yelanyanyu.device_detecter.device.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LedData {
    private String lux;
    private String temprature;
}
