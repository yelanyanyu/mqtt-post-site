package com.jeesite.modules.yelanyanyu.device_detecter.iot.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * IoT_deviceEntity
 * @author yelanyanyu
 * @version 2024-04-25
 */
@Table(name="iot_device", alias="a", label="IoT_device信息", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="device_type", attrName="deviceType", label="设备类型"),
		@Column(name="location", attrName="location", label="设备位置"),
		@Column(name="ip", attrName="ip", label="ip"),
		@Column(name="mac_address", attrName="macAddress", label="mac_address"),
		@Column(name="last_update_date", attrName="lastUpdateDate", label="更新时间", isUpdateForce=true),
	}, orderBy="a.id DESC"
)
public class IotDevice extends DataEntity<IotDevice> {

	private static final long serialVersionUID = 1L;
	private String deviceName;		// 设备名称
	private String deviceType;		// 设备类型
	private String location;		// 设备位置
	private String ip;		// ip
	private String macAddress;		// mac_address
	private Date lastUpdateDate;		// 更新时间

	public IotDevice() {
		this(null);
	}

	public IotDevice(String id){
		super(id);
	}

	@NotBlank(message="设备名称不能为空")
	@Size(min=0, max=64, message="设备名称长度不能超过 64 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@NotBlank(message="设备类型不能为空")
	@Size(min=0, max=255, message="设备类型长度不能超过 255 个字符")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Size(min=0, max=255, message="设备位置长度不能超过 255 个字符")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Size(min=0, max=64, message="ip长度不能超过 64 个字符")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Size(min=0, max=64, message="mac_address长度不能超过 64 个字符")
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
