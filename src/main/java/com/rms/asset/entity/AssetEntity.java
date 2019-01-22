package com.rms.asset.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: RMS_资产管理
 * @author onlineGenerator
 * @date 2018-11-05 16:03:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_asset_jeecg", schema = "")
@SuppressWarnings("serial")
public class AssetEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**assetId*/
	private java.lang.String assetId;
	/**公司*/
	@Excel(name="公司",width=15,dicCode="company")
	private java.lang.String company;
	/**地区*/
	@Excel(name="地区",width=15,dicCode="area")
	private java.lang.String location;
	/**资产类型*/
	@Excel(name="资产类型",width=15,dicCode="assets")
	private java.lang.String maintype;
	/**资产编号*/
	@Excel(name="资产编号",width=15)
	private java.lang.String assetNo;
	/**使用部门*/
	private java.lang.String departmentId;
	/**使用人*/
	private java.lang.String employeeId;
	/**品牌*/
	@Excel(name="品牌",width=15)
	private java.lang.String vendor;
	/**型号*/
	@Excel(name="型号",width=15)
	private java.lang.String assetType;
	/**设备参数*/
	@Excel(name="设备参数",width=15)
	private java.lang.String config;
	/**电脑型号*/
	@Excel(name="电脑型号",width=15)
	private java.lang.String pcType;
	/**电脑序列号*/
	@Excel(name="电脑序列号",width=15)
	private java.lang.String pcNo;
	/**电源序列号*/
	@Excel(name="电源序列号",width=15)
	private java.lang.String powerNo;
	/**电池序列号*/
	@Excel(name="电池序列号",width=15)
	private java.lang.String batteryNo;
	/**有线Mac*/
	@Excel(name="有线Mac",width=15)
	private java.lang.String mac;
	/**无线Mac*/
	@Excel(name="无线Mac",width=15)
	private java.lang.String wifiMac;
	/**购置日期*/
	@Excel(name="购置日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date purchaseDate;
	/**保修年限*/
	@Excel(name="保修年限",width=15,dicCode="repairTime")
	private java.lang.String warrantyPeriod;
	/**购置单价*/
	@Excel(name="购置单价",width=15)
	private java.lang.String price;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark1;
	/**状态*/
	private java.lang.String status;
	/**备注2*/
	private java.lang.String remark2;
	/**创建时间*/
	private java.util.Date createTime;
	/**最后修改时间*/
	private java.util.Date lastUpdateTime;
	/**创建人*/
	private java.lang.String createdBy;
	/**修改人*/
	private java.lang.String updatedBy;
	/**库房描述*/
	private java.lang.String storehouse;
	/**更新时间*/
	private java.util.Date updateTime;
	/**操作*/
	@Excel(name="操作",width=15)
	private java.lang.String operation;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  assetId
	 */

	@Column(name ="ASSET_ID",nullable=false,scale=30,length=65)
	public java.lang.String getAssetId(){
		return this.assetId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  assetId
	 */
	public void setAssetId(java.lang.String assetId){
		this.assetId = assetId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司
	 */

	@Column(name ="COMPANY",nullable=true,length=1)
	public java.lang.String getCompany(){
		return this.company;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司
	 */
	public void setCompany(java.lang.String company){
		this.company = company;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地区
	 */

	@Column(name ="LOCATION",nullable=true,length=1)
	public java.lang.String getLocation(){
		return this.location;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区
	 */
	public void setLocation(java.lang.String location){
		this.location = location;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产类型
	 */

	@Column(name ="MAINTYPE",nullable=true,length=1)
	public java.lang.String getMaintype(){
		return this.maintype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产类型
	 */
	public void setMaintype(java.lang.String maintype){
		this.maintype = maintype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产编号
	 */

	@Column(name ="ASSET_NO",nullable=true,length=255)
	public java.lang.String getAssetNo(){
		return this.assetNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产编号
	 */
	public void setAssetNo(java.lang.String assetNo){
		this.assetNo = assetNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用部门
	 */

	@Column(name ="DEPARTMENT_ID",nullable=true,length=32)
	public java.lang.String getDepartmentId(){
		return this.departmentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用部门
	 */
	public void setDepartmentId(java.lang.String departmentId){
		this.departmentId = departmentId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用人
	 */

	@Column(name ="EMPLOYEE_ID",nullable=true,length=255)
	public java.lang.String getEmployeeId(){
		return this.employeeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用人
	 */
	public void setEmployeeId(java.lang.String employeeId){
		this.employeeId = employeeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌
	 */

	@Column(name ="VENDOR",nullable=true,length=255)
	public java.lang.String getVendor(){
		return this.vendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌
	 */
	public void setVendor(java.lang.String vendor){
		this.vendor = vendor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  型号
	 */

	@Column(name ="ASSET_TYPE",nullable=true,length=255)
	public java.lang.String getAssetType(){
		return this.assetType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  型号
	 */
	public void setAssetType(java.lang.String assetType){
		this.assetType = assetType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备参数
	 */

	@Column(name ="CONFIG",nullable=true,length=255)
	public java.lang.String getConfig(){
		return this.config;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备参数
	 */
	public void setConfig(java.lang.String config){
		this.config = config;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电脑型号
	 */

	@Column(name ="PC_TYPE",nullable=true,length=255)
	public java.lang.String getPcType(){
		return this.pcType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电脑型号
	 */
	public void setPcType(java.lang.String pcType){
		this.pcType = pcType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电脑序列号
	 */

	@Column(name ="PC_NO",nullable=true,length=255)
	public java.lang.String getPcNo(){
		return this.pcNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电脑序列号
	 */
	public void setPcNo(java.lang.String pcNo){
		this.pcNo = pcNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电源序列号
	 */

	@Column(name ="POWER_NO",nullable=true,length=255)
	public java.lang.String getPowerNo(){
		return this.powerNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电源序列号
	 */
	public void setPowerNo(java.lang.String powerNo){
		this.powerNo = powerNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电池序列号
	 */

	@Column(name ="BATTERY_NO",nullable=true,length=255)
	public java.lang.String getBatteryNo(){
		return this.batteryNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电池序列号
	 */
	public void setBatteryNo(java.lang.String batteryNo){
		this.batteryNo = batteryNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  有线Mac
	 */

	@Column(name ="MAC",nullable=true,length=255)
	public java.lang.String getMac(){
		return this.mac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  有线Mac
	 */
	public void setMac(java.lang.String mac){
		this.mac = mac;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  无线Mac
	 */

	@Column(name ="WIFI_MAC",nullable=true,length=255)
	public java.lang.String getWifiMac(){
		return this.wifiMac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  无线Mac
	 */
	public void setWifiMac(java.lang.String wifiMac){
		this.wifiMac = wifiMac;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  购置日期
	 */

	@Column(name ="PURCHASE_DATE",nullable=true)
	public java.util.Date getPurchaseDate(){
		return this.purchaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  购置日期
	 */
	public void setPurchaseDate(java.util.Date purchaseDate){
		this.purchaseDate = purchaseDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修年限
	 */

	@Column(name ="WARRANTY_PERIOD",nullable=true,length=1)
	public java.lang.String getWarrantyPeriod(){
		return this.warrantyPeriod;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修年限
	 */
	public void setWarrantyPeriod(java.lang.String warrantyPeriod){
		this.warrantyPeriod = warrantyPeriod;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  购置单价
	 */

	@Column(name ="PRICE",nullable=true,scale=30,length=65)
	public java.lang.String getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购置单价
	 */
	public void setPrice(java.lang.String price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK1",nullable=true,length=255)
	public java.lang.String getRemark1(){
		return this.remark1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark1(java.lang.String remark1){
		this.remark1 = remark1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注2
	 */

	@Column(name ="REMARK2",nullable=true,length=255)
	public java.lang.String getRemark2(){
		return this.remark2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注2
	 */
	public void setRemark2(java.lang.String remark2){
		this.remark2 = remark2;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="CREATE_TIME",nullable=false)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最后修改时间
	 */

	@Column(name ="LAST_UPDATE_TIME",nullable=true)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后修改时间
	 */
	public void setLastUpdateTime(java.util.Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */

	@Column(name ="CREATED_BY",nullable=true,length=19)
	public java.lang.String getCreatedBy(){
		return this.createdBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreatedBy(java.lang.String createdBy){
		this.createdBy = createdBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */

	@Column(name ="UPDATED_BY",nullable=true,length=19)
	public java.lang.String getUpdatedBy(){
		return this.updatedBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdatedBy(java.lang.String updatedBy){
		this.updatedBy = updatedBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库房描述
	 */

	@Column(name ="STOREHOUSE",nullable=true,length=1)
	public java.lang.String getStorehouse(){
		return this.storehouse;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库房描述
	 */
	public void setStorehouse(java.lang.String storehouse){
		this.storehouse = storehouse;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */

	@Column(name ="UPDATE_TIME",nullable=true,length=32)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作
	 */

	@Column(name ="OPERATION",nullable=true,length=32)
	public java.lang.String getOperation(){
		return this.operation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作
	 */
	public void setOperation(java.lang.String operation){
		this.operation = operation;
	}
}
