package com.jeesite.modules.yelanyanyu.device_detecter.iot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.yelanyanyu.device_detecter.iot.entity.IotDevice;
import com.jeesite.modules.yelanyanyu.device_detecter.iot.service.IotDeviceService;

/**
 * IoT_deviceController
 * @author yelanyanyu
 * @version 2024-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/iot/iotDevice")
@Slf4j
public class IotDeviceController extends BaseController {

	@Autowired
	private IotDeviceService iotDeviceService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IotDevice get(String id, boolean isNewRecord) {
		return iotDeviceService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("iot:iotDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(IotDevice iotDevice, Model model) {
		model.addAttribute("iotDevice", iotDevice);
		return "device_detecter/iot/iotDeviceList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("iot:iotDevice:view")
	@PostMapping(value = "listData")
	@ResponseBody
	public Page<IotDevice> listData(IotDevice iotDevice, HttpServletRequest request, HttpServletResponse response) {
		log.info("====================================");
		iotDevice.setPage(new Page<>(request, response));
		Page<IotDevice> page = iotDeviceService.findPage(iotDevice);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("iot:iotDevice:view")
	@RequestMapping(value = "form")
	public String form(IotDevice iotDevice, Model model) {
		model.addAttribute("iotDevice", iotDevice);
		return "device_detecter/iot/iotDeviceForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("iot:iotDevice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IotDevice iotDevice) {
		iotDeviceService.save(iotDevice);
		return renderResult(Global.TRUE, text("保存IoT_device成功！"));
	}

	/**
	 * 删除数据
	 */
	@RequiresPermissions("iot:iotDevice:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IotDevice iotDevice) {
		iotDeviceService.delete(iotDevice);
		return renderResult(Global.TRUE, text("删除IoT_device成功！"));
	}

}
