package com.jeesite.modules.yelanyanyu.device_detecter.device.web;

import java.util.Map;
import java.util.List;

import com.jeesite.modules.yelanyanyu.device_detecter.device.entity.Device;
import com.jeesite.modules.yelanyanyu.device_detecter.device.service.DeviceService;
import com.jeesite.modules.yelanyanyu.device_detecter.vo.LEDVO;
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
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;

/**
 * device : 存储设备层次的根表Controller
 * @author yelanyanyu
 * @version 2024-05-20
 */
@Controller
@RequestMapping(value = "${adminPath}/device/device")
public class DeviceController extends BaseController {

	@Autowired
	private DeviceService deviceService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Device get(String deviceId, boolean isNewRecord) {
		return deviceService.get(deviceId, isNewRecord);
	}

	/**
	 * 管理主页
	 */
	@RequiresPermissions("device:device:view")
	@RequestMapping(value = "index")
	public String index(Device device, Model model) {
		model.addAttribute("device", device);
		return "iot/device/deviceIndex";
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("device:device:view")
	@RequestMapping(value = {"list", ""})
	public String list(Device device, Model model) {
		model.addAttribute("device", device);
		return "iot/device/deviceList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("device:device:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<Device> listData(Device device) {
		logger.info("device: {}", device);
		if (StringUtils.isBlank(device.getParentCode())) {
			device.setParentCode(Device.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(device.getDeviceName())){
			device.setParentCode(null);
		}
		if (device.getDeviceStatus() != null){
			device.setParentCode(null);
		}
		if (StringUtils.isNotBlank(device.getImg())){
			device.setParentCode(null);
		}
		List<Device> list = deviceService.findList(device);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("device:device:view")
	@RequestMapping(value = "form")
	public String form(Device device, Model model) {
		// 创建并初始化下一个节点信息
		device = createNextNode(device);
		model.addAttribute("device", device);
		return "iot/device/deviceForm";
	}

	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("device:device:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public Device createNextNode(Device device) {
		if (StringUtils.isNotBlank(device.getParentCode())){
			device.setParent(deviceService.get(device.getParentCode()));
		}
		if (device.getIsNewRecord()) {
			Device where = new Device();
			where.setParentCode(device.getParentCode());
			Device last = deviceService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				device.setTreeSort(last.getTreeSort() + 30);
			}
		}
		// 以下设置表单默认数据
		if (device.getTreeSort() == null){
			device.setTreeSort(Device.DEFAULT_TREE_SORT);
		}
		return device;
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("device:device:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Device device) {
		deviceService.save(device);
		return renderResult(Global.TRUE, text("保存device : 存储设备层次的根表成功！"));
	}

	/**
	 * 停用数据
	 */
	@RequiresPermissions("device:device:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Device device) {
		Device where = new Device();
		where.setStatus(Device.STATUS_NORMAL);
		where.setParentCodes("," + device.getId() + ",");
		long count = deviceService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该device : 存储设备层次的根表包含未停用的子device : 存储设备层次的根表！"));
		}
		device.setStatus(Device.STATUS_DISABLE);
		deviceService.updateStatus(device);
		return renderResult(Global.TRUE, text("停用device : 存储设备层次的根表成功"));
	}

	/**
	 * 启用数据
	 */
	@RequiresPermissions("device:device:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Device device) {
		device.setStatus(Device.STATUS_NORMAL);
		deviceService.updateStatus(device);
		return renderResult(Global.TRUE, text("启用device : 存储设备层次的根表成功"));
	}

	/**
	 * 删除数据
	 */
	@RequiresPermissions("device:device:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Device device) {
		deviceService.delete(device);
		return renderResult(Global.TRUE, text("删除device : 存储设备层次的根表成功！"));
	}

	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param parentCode 设置父级编码返回一级
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("device:device:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String parentCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		Device where = new Device();
		where.setStatus(Device.STATUS_NORMAL);
		if (StringUtils.isNotBlank(parentCode)){
			where.setParentCode(parentCode);
		}
		List<Device> list = deviceService.findList(where);
		for (int i=0; i<list.size(); i++){
			Device e = list.get(i);
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", e.getDeviceName());
			map.put("isParent", !e.getIsTreeLeaf());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("device:device:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(Device device){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		deviceService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}

	@RequiresPermissions("device:device:view")
	@RequestMapping(value = "openLight")
	@ResponseBody
	public String openLightForLED(LEDVO ledvo) {
		logger.info("command: {}", ledvo);
		return renderResult(Global.TRUE, "成功");
	}

	@RequiresPermissions("device:device:view")
	@RequestMapping(value = "closeLight")
	@ResponseBody
	public String closeLightForLED(LEDVO ledvo) {
		logger.info("command: {}", ledvo);
		return renderResult(Global.TRUE, "成功");
	}
}
