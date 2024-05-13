package com.jeesite.modules.yelanyanyu.device_detecter.iot.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.yelanyanyu.device_detecter.iot.entity.IotDevice;

/**
 * IoT_deviceDAO接口
 * @author yelanyanyu
 * @version 2024-04-25
 */
@MyBatisDao
public interface IotDeviceDao extends CrudDao<IotDevice> {

}
