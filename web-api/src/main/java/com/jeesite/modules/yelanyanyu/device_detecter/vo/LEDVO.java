package com.jeesite.modules.yelanyanyu.device_detecter.vo;

import com.jeesite.modules.yelanyanyu.device_detecter.device.entity.Device;
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
public class LEDVO extends Device {
    private static final long serialVersionUID = 1L;
    private String topic;
    private Integer ledcmd;
}
