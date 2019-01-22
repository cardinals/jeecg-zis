package com.rms.hardware.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.rms.server.entity.ServerEntity;

/**   
 * @Title: Entity
 * @Description: RMS_设备管理
 * @author onlineGenerator
 * @date 2018-09-04 16:03:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_hardware_jeecg", schema = "")
@SuppressWarnings("serial")
public class HardwareEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**类型*/
	@Excel(name="类型",width=15,dicCode="hardware")
	private java.lang.String maintype;
	/**状态*/
	@Excel(name="状态",width=15,dicCode="hwst")
	private java.lang.String status;
	/**设备名称*/
	@Excel(name="设备名称",width=15)
	private java.lang.String name;
	/**序列号*/
	@Excel(name="序列号",width=15)
	private java.lang.String hardwareNo;
	/**厂商*/
	@Excel(name="厂商",width=15)
	private java.lang.String vendor;
	/**型号*/
	@Excel(name="型号",width=15)
	private java.lang.String hardwareType;
	/**地址*/
	@Excel(name="地址",width=15,dicCode="sbdz")
	private java.lang.String location;
	/**机柜位*/
	@Excel(name="机柜位",width=15)
	private java.lang.String position;
	/**设备申请人*/
	private java.lang.String applicant;
	/**采购日期*/
	@Excel(name="采购日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date purchaseDate;
	/**保修日期*/
	@Excel(name="保修日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date warrantyDate;
	/**最近维修日期*/
	@Excel(name="最近维修日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date maintainDate;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark1;
	/**远程管理IP*/
	@Excel(name="远程管理IP",width=15)
	private java.lang.String remoteIlo;
	/**保修厂商*/
	@Excel(name="保修厂商",width=15)
	private java.lang.String warrantyVendor;
	/**备注2*/
	private java.lang.String remark2;
	/**创建日期*/
	private java.util.Date createTime;
	/**最后保修时间*/
	private java.util.Date lastUpdateTime;
	/**创建人*/
	private java.lang.String createdBy;
	/**更新人*/
	private java.lang.String updatedBy;
	/**代理人*/
	private java.lang.String agent;
	/**设备编号*/
	private java.lang.String hardwareId;

	/**
	 * 服务器
	 */
	private List<ServerEntity>  serverSet;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=true,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	@OneToMany(cascade=CascadeType.ALL) 
	@JoinColumn(name="HARDWARE_ID")//注释的是另一个表指向本表的外键。
	public List<ServerEntity> getServerSet() {
		return serverSet;
	}

	public void setServerSet(List<ServerEntity> serverSet) {
		this.serverSet = serverSet;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */

	@Column(name ="MAINTYPE",nullable=true,length=1)
	public java.lang.String getMaintype(){
		return this.maintype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setMaintype(java.lang.String maintype){
		this.maintype = maintype;
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
	 *@return: java.lang.String  设备名称
	 */

	@Column(name ="NAME",nullable=true,length=255)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  序列号
	 */

	@Column(name ="HARDWARE_NO",nullable=true,length=255)
	public java.lang.String getHardwareNo(){
		return this.hardwareNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  序列号
	 */
	public void setHardwareNo(java.lang.String hardwareNo){
		this.hardwareNo = hardwareNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  厂商
	 */

	@Column(name ="VENDOR",nullable=true,length=255)
	public java.lang.String getVendor(){
		return this.vendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  厂商
	 */
	public void setVendor(java.lang.String vendor){
		this.vendor = vendor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  型号
	 */

	@Column(name ="HARDWARE_TYPE",nullable=true,length=255)
	public java.lang.String getHardwareType(){
		return this.hardwareType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  型号
	 */
	public void setHardwareType(java.lang.String hardwareType){
		this.hardwareType = hardwareType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */

	@Column(name ="LOCATION",nullable=true,length=1)
	public java.lang.String getLocation(){
		return this.location;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setLocation(java.lang.String location){
		this.location = location;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机柜位
	 */

	@Column(name ="POSITION",nullable=true,length=255)
	public java.lang.String getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机柜位
	 */
	public void setPosition(java.lang.String position){
		this.position = position;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备申请人
	 */

	@Column(name ="APPLICANT",nullable=true,length=19)
	public java.lang.String getApplicant(){
		return this.applicant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备申请人
	 */
	public void setApplicant(java.lang.String applicant){
		this.applicant = applicant;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  采购日期
	 */

	@Column(name ="PURCHASE_DATE",nullable=false)
	public java.util.Date getPurchaseDate(){
		return this.purchaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  采购日期
	 */
	public void setPurchaseDate(java.util.Date purchaseDate){
		this.purchaseDate = purchaseDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  保修日期
	 */

	@Column(name ="WARRANTY_DATE",nullable=true)
	public java.util.Date getWarrantyDate(){
		return this.warrantyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  保修日期
	 */
	public void setWarrantyDate(java.util.Date warrantyDate){
		this.warrantyDate = warrantyDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最近维修日期
	 */

	@Column(name ="MAINTAIN_DATE",nullable=true)
	public java.util.Date getMaintainDate(){
		return this.maintainDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最近维修日期
	 */
	public void setMaintainDate(java.util.Date maintainDate){
		this.maintainDate = maintainDate;
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
	 *@return: java.lang.String  远程管理IP
	 */

	@Column(name ="REMOTE_ILO",nullable=true,length=255)
	public java.lang.String getRemoteIlo(){
		return this.remoteIlo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  远程管理IP
	 */
	public void setRemoteIlo(java.lang.String remoteIlo){
		this.remoteIlo = remoteIlo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修厂商
	 */

	@Column(name ="WARRANTY_VENDOR",nullable=true,length=255)
	public java.lang.String getWarrantyVendor(){
		return this.warrantyVendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修厂商
	 */
	public void setWarrantyVendor(java.lang.String warrantyVendor){
		this.warrantyVendor = warrantyVendor;
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
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_TIME",nullable=false)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最后保修时间
	 */

	@Column(name ="LAST_UPDATE_TIME",nullable=false)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后保修时间
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
	 *@return: java.lang.String  更新人
	 */

	@Column(name ="UPDATED_BY",nullable=true,length=19)
	public java.lang.String getUpdatedBy(){
		return this.updatedBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdatedBy(java.lang.String updatedBy){
		this.updatedBy = updatedBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代理人
	 */

	@Column(name ="AGENT",nullable=true,length=19)
	public java.lang.String getAgent(){
		return this.agent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代理人
	 */
	public void setAgent(java.lang.String agent){
		this.agent = agent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备编号
	 */

	@Column(name ="HARDWARE_ID",nullable=false,length=32)
	public java.lang.String getHardwareId(){
		return this.hardwareId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备编号
	 */
	public void setHardwareId(java.lang.String hardwareId){
		this.hardwareId = hardwareId;
	}
}
