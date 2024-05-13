package com.jeesite.modules.yelanyanyu.device_detecter.iot.service;

import java.util.List;

import com.jeesite.modules.yelanyanyu.device_detecter.iot.dao.IotDeviceDao;
import com.jeesite.modules.yelanyanyu.device_detecter.iot.entity.IotDevice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;

/**
 * IoT_deviceService
 * @author yelanyanyu
 * @version 2024-04-25
 */
@Service
public class IotDeviceService extends CrudService<IotDeviceDao, IotDevice> {

	/**
	 * 获取单条数据
	 * @param iotDevice
	 * @return
	 */
	@Override
	public IotDevice get(IotDevice iotDevice) {
		return super.get(iotDevice);
	}

	/**
	 * 查询分页数据
	 * @param iotDevice 查询条件
	 * @param iotDevice page 分页对象
	 * @return
	 */
	@Override
	public Page<IotDevice> findPage(IotDevice iotDevice) {
		return super.findPage(iotDevice);
	}

	/**
	 * 查询列表数据
	 * @param iotDevice
	 * @return
	 */
	@Override
	public List<IotDevice> findList(IotDevice iotDevice) {
		return super.findList(iotDevice);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param iotDevice
	 */
	@Override
	@Transactional
	public void save(IotDevice iotDevice) {
		super.save(iotDevice);
	}

	/**
	 * 更新状态
	 * @param iotDevice
	 */
	@Override
	@Transactional
	public void updateStatus(IotDevice iotDevice) {
		super.updateStatus(iotDevice);
	}

	/**
	 * 删除数据
	 * @param iotDevice
	 */
	@Override
	@Transactional
	public void delete(IotDevice iotDevice) {
		super.delete(iotDevice);
	}

}
