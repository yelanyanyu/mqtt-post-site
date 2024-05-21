package com.jeesite.modules.yelanyanyu.device_detecter.device.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.yelanyanyu.device_detecter.device.entity.Device;
import com.jeesite.modules.yelanyanyu.device_detecter.device.dao.DeviceDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * device : 存储设备层次的根表Service
 * @author yelanyanyu
 * @version 2024-05-20
 */
@Service
public class DeviceService extends TreeService<DeviceDao, Device> {

	/**
	 * 获取单条数据
	 * @param device
	 * @return
	 */
	@Override
	public Device get(Device device) {
		return super.get(device);
	}

	/**
	 * 查询分页数据
	 * @param device 查询条件
	 * @param device page 分页对象
	 * @return
	 */
	@Override
	public Page<Device> findPage(Device device) {
		return super.findPage(device);
	}

	/**
	 * 查询列表数据
	 * @param device
	 * @return
	 */
	@Override
	public List<Device> findList(Device device) {
		return super.findList(device);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param device
	 */
	@Override
	@Transactional
	public void save(Device device) {
		super.save(device);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(device, device.getId(), "device_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(device, device.getId(), "device_file");
	}

	/**
	 * 更新状态
	 * @param device
	 */
	@Override
	@Transactional
	public void updateStatus(Device device) {
		super.updateStatus(device);
	}

	/**
	 * 删除数据
	 * @param device
	 */
	@Override
	@Transactional
	public void delete(Device device) {
		super.delete(device);
	}

}
