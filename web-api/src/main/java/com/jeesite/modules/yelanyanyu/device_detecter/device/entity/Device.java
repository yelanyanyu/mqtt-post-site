package com.jeesite.modules.yelanyanyu.device_detecter.device.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * device : 存储设备层次的根表Entity
 *
 * @author yelanyanyu
 * @version 2024-05-20
 */
@Table(name = "device", alias = "a", label = "device : 存储设备层次的根表信息", columns = {
        @Column(name = "parent_code", attrName = "parentCode", label = "父级编号", isParentCode = true),
        @Column(includeEntity = TreeEntity.class),
        @Column(name = "device_id", attrName = "deviceId", label = "device_id ", comment = "device_id : 设备的唯一标识", isPK = true),
        @Column(name = "device_name", attrName = "deviceName", label = "device_name ", comment = "device_name : 设备名称", queryType = QueryType.LIKE, isTreeName = true),
        @Column(name = "device_status", attrName = "deviceStatus", label = "device_status : 设备状态", comment = "device_status : 设备状态(1-active,0-inactive)", isUpdateForce = true),
        @Column(name = "img", attrName = "img", label = "img ", comment = "img : 保存图片路径-通常存在oss中"),
}, orderBy = "a.tree_sorts, a.device_id"
)
public class Device extends TreeEntity<Device> {

    private static final long serialVersionUID = 1L;
    private String deviceId;        // device_id : 设备的唯一标识
    private String deviceName;        // device_name : 设备名称
    private Integer deviceStatus;        // device_status : 设备状态(1-active,0-inactive)
    private String img;        // img : 保存图片路径-通常存在oss中

    public Device() {
        this(null);
    }

    public Device(String id) {
        super(id);
    }

    @Override
    public Device getParent() {
        return parent;
    }

    @Override
    public void setParent(Device parent) {
        this.parent = parent;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @NotBlank(message = "device_name 不能为空")
    @Size(min = 0, max = 255, message = "device_name 长度不能超过 255 个字符")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Size(min = 0, max = 255, message = "img 长度不能超过 255 个字符")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", img='" + img + '\'' +
                '}';
    }
}
