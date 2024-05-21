package com.jeesite.modules.yelanyanyu.device_detecter.device.dao;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.yelanyanyu.device_detecter.device.entity.Device;

/**
 * device : 存储设备层次的根表DAO接口
 * @author yelanyanyu
 * @version 2024-05-20
 */
@MyBatisDao
public interface DeviceDao extends TreeDao<Device> {

}
