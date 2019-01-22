package com.jeecg.rms_hardware.entity;

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
 * @Description: rms_hardware
 * @author onlineGenerator
 * @date 2018-03-28 15:05:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_hardware", schema = "")
@SuppressWarnings("serial")
public class RmsHardwareEntity implements java.io.Serializable {
	/**id*/

	private java.lang.Integer id;
	/**maintype*/
    @Excel(name="maintype",width=15,dicCode="servertype")
		
	private java.lang.String maintype;
	/**status*/
    @Excel(name="status",width=15,dicCode="status")
		
	private java.lang.String status;
	/**location*/
    @Excel(name="location",width=15,dicCode="location")
		
	private java.lang.String location;
	/**position*/
    @Excel(name="position",width=15)
		
	private java.lang.String position;
	/**hardwareType*/
    @Excel(name="hardwareType",width=15)
		
	private java.lang.String hardwareType;
	/**hardwareNo*/
    @Excel(name="hardwareNo",width=15)
		
	private java.lang.String hardwareNo;
	/**remoteIlo*/
    @Excel(name="remoteIlo",width=15)
		
	private java.lang.String remoteIlo;
	/**purchaseDate*/
    @Excel(name="purchaseDate",width=15,format = "yyyy-MM-dd")
		
	private java.util.Date purchaseDate;
	/**vendor*/
    @Excel(name="vendor",width=15)
		
	private java.lang.String vendor;
	/**warrantyDate*/
    @Excel(name="warrantyDate",width=15,format = "yyyy-MM-dd")
		
	private java.util.Date warrantyDate;
	/**maintainDate*/
    @Excel(name="maintainDate",width=15,format = "yyyy-MM-dd")
		
	private java.util.Date maintainDate;
	/**warrantyVendor*/
    @Excel(name="warrantyVendor",width=15)
		
	private java.lang.String warrantyVendor;
	/**remark1*/
    @Excel(name="remark1",width=15)
		
	private java.lang.String remark1;
	/**remark2*/
    @Excel(name="remark2",width=15)
		
	private java.lang.String remark2;
	/**createTime*/
    @Excel(name="createTime",width=15,format = "yyyy-MM-dd")
		
	private java.util.Date createTime;
	/**lastUpdateTime*/
    @Excel(name="lastUpdateTime",width=15,format = "yyyy-MM-dd")
		
	private java.util.Date lastUpdateTime;
	/**applicant*/
    @Excel(name="applicant",width=15,dicCode="user")
		
	private java.lang.String applicant;
	/**createdBy*/
    @Excel(name="createdBy",width=15,dicCode="user")
		
	private java.lang.String createdBy;
	/**updatedBy*/
    @Excel(name="updatedBy",width=15,dicCode="user")
		
	private java.lang.String updatedBy;
	/**agent*/
    @Excel(name="agent",width=15,dicCode="user")
		
	private java.lang.String agent;
	/**name*/
    @Excel(name="name",width=15)
		
	private java.lang.String name;
	
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
	 *@return: java.lang.String  maintype
	 */
	@Column(name ="MAINTYPE",nullable=true,length=1)
	public java.lang.String getMaintype(){
		return this.maintype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  maintype
	 */
	public void setMaintype(java.lang.String maintype){
		this.maintype = maintype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  status
	 */
	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  status
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  location
	 */
	@Column(name ="LOCATION",nullable=true,length=1)
	public java.lang.String getLocation(){
		return this.location;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  location
	 */
	public void setLocation(java.lang.String location){
		this.location = location;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  position
	 */
	@Column(name ="POSITION",nullable=true,length=255)
	public java.lang.String getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  position
	 */
	public void setPosition(java.lang.String position){
		this.position = position;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  hardwareType
	 */
	@Column(name ="HARDWARE_TYPE",nullable=true,length=255)
	public java.lang.String getHardwareType(){
		return this.hardwareType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  hardwareType
	 */
	public void setHardwareType(java.lang.String hardwareType){
		this.hardwareType = hardwareType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  hardwareNo
	 */
	@Column(name ="HARDWARE_NO",nullable=true,length=255)
	public java.lang.String getHardwareNo(){
		return this.hardwareNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  hardwareNo
	 */
	public void setHardwareNo(java.lang.String hardwareNo){
		this.hardwareNo = hardwareNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  remoteIlo
	 */
	@Column(name ="REMOTE_ILO",nullable=true,length=255)
	public java.lang.String getRemoteIlo(){
		return this.remoteIlo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  remoteIlo
	 */
	public void setRemoteIlo(java.lang.String remoteIlo){
		this.remoteIlo = remoteIlo;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  purchaseDate
	 */
	@Column(name ="PURCHASE_DATE",nullable=false)
	public java.util.Date getPurchaseDate(){
		return this.purchaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  purchaseDate
	 */
	public void setPurchaseDate(java.util.Date purchaseDate){
		this.purchaseDate = purchaseDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  vendor
	 */
	@Column(name ="VENDOR",nullable=true,length=255)
	public java.lang.String getVendor(){
		return this.vendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  vendor
	 */
	public void setVendor(java.lang.String vendor){
		this.vendor = vendor;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  warrantyDate
	 */
	@Column(name ="WARRANTY_DATE",nullable=false)
	public java.util.Date getWarrantyDate(){
		return this.warrantyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  warrantyDate
	 */
	public void setWarrantyDate(java.util.Date warrantyDate){
		this.warrantyDate = warrantyDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  maintainDate
	 */
	@Column(name ="MAINTAIN_DATE",nullable=true)
	public java.util.Date getMaintainDate(){
		return this.maintainDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  maintainDate
	 */
	public void setMaintainDate(java.util.Date maintainDate){
		this.maintainDate = maintainDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  warrantyVendor
	 */
	@Column(name ="WARRANTY_VENDOR",nullable=true,length=255)
	public java.lang.String getWarrantyVendor(){
		return this.warrantyVendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  warrantyVendor
	 */
	public void setWarrantyVendor(java.lang.String warrantyVendor){
		this.warrantyVendor = warrantyVendor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  remark1
	 */
	@Column(name ="REMARK1",nullable=true,length=255)
	public java.lang.String getRemark1(){
		return this.remark1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  remark1
	 */
	public void setRemark1(java.lang.String remark1){
		this.remark1 = remark1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  remark2
	 */
	@Column(name ="REMARK2",nullable=true,length=255)
	public java.lang.String getRemark2(){
		return this.remark2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  remark2
	 */
	public void setRemark2(java.lang.String remark2){
		this.remark2 = remark2;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */
	@Column(name ="CREATE_TIME",nullable=false)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  lastUpdateTime
	 */
	@Column(name ="LAST_UPDATE_TIME",nullable=false)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  lastUpdateTime
	 */
	public void setLastUpdateTime(java.util.Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  applicant
	 */
	@Column(name ="APPLICANT",nullable=true,length=19)
	public java.lang.String getApplicant(){
		return this.applicant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  applicant
	 */
	public void setApplicant(java.lang.String applicant){
		this.applicant = applicant;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  createdBy
	 */
	@Column(name ="CREATED_BY",nullable=true,length=19)
	public java.lang.String getCreatedBy(){
		return this.createdBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy){
		this.createdBy = createdBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  updatedBy
	 */
	@Column(name ="UPDATED_BY",nullable=true,length=19)
	public java.lang.String getUpdatedBy(){
		return this.updatedBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy){
		this.updatedBy = updatedBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  agent
	 */
	@Column(name ="AGENT",nullable=true,length=19)
	public java.lang.String getAgent(){
		return this.agent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  agent
	 */
	public void setAgent(java.lang.String agent){
		this.agent = agent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME",nullable=true,length=255)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  name
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
}
