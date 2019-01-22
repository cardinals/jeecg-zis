package com.rms.database.entity;

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
 * @Description: RMS_数据库管理
 * @author onlineGenerator
 * @date 2018-11-01 16:26:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_database_jeecg", schema = "")
@SuppressWarnings("serial")
public class DatabaseEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer id;
	/**databaseId*/
	private java.lang.String databaseId;
	/**所在设备*/
	private java.lang.String serverId;
	/**OS*/
	@Excel(name="OS",width=15)
	private java.lang.String os;
	/**IP*/
	private java.lang.String ip;
	/**类型*/
	@Excel(name="类型",width=15,dicCode="dbTypes")
	private java.lang.String dbType;
	/**版本号*/
	@Excel(name="版本号",width=15)
	private java.lang.String version;
	/**状态*/
	@Excel(name="状态",width=15,dicCode="applstate")
	private java.lang.String status;
	/**名称*/
	@Excel(name="名称",width=15)
	private java.lang.String databaseName;
	/**SID*/
	@Excel(name="SID",width=15)
	private java.lang.String sid;
	/**RACHA*/
	@Excel(name="RACHA",width=15,dicCode="racha")
	private java.lang.String racorha;
	/**管理员*/
	@Excel(name="管理员",width=15,dicCode="user")
	private java.lang.String administrator;
	/**上线时间*/
	@Excel(name="上线时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date launchDate;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark1;
	/**备注2*/
	private java.lang.String remark2;
	/**创建时间*/
	private java.util.Date createTime;
	/**最后更改时间 */
	private java.util.Date lastUpdateTime;
	/**创建人*/
	private java.lang.String createdBy;
	/**更新人*/
	private java.lang.String updatedBy;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
	//@GeneratedValue(generator = "paymentableGenerator")
	//@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=32)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  databaseId
	 */

	@Column(name ="DATABASE_ID",nullable=false,scale=30,length=65)
	public java.lang.String getDatabaseId(){
		return this.databaseId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  databaseId
	 */
	public void setDatabaseId(java.lang.String databaseId){
		this.databaseId = databaseId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在设备
	 */

	@Column(name ="SERVER_ID",nullable=true,length=19)
	public java.lang.String getServerId(){
		return this.serverId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在设备
	 */
	public void setServerId(java.lang.String serverId){
		this.serverId = serverId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  OS
	 */

	@Column(name ="OS",nullable=true,length=32)
	public java.lang.String getOs(){
		return this.os;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  OS
	 */
	public void setOs(java.lang.String os){
		this.os = os;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  IP
	 */

	@Column(name ="IP",nullable=true,length=32)
	public java.lang.String getIp(){
		return this.ip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  IP
	 */
	public void setIp(java.lang.String ip){
		this.ip = ip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */

	@Column(name ="DB_TYPE",nullable=true,length=1)
	public java.lang.String getDbType(){
		return this.dbType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setDbType(java.lang.String dbType){
		this.dbType = dbType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本号
	 */

	@Column(name ="VERSION",nullable=true,length=255)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本号
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
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
	 *@return: java.lang.String  名称
	 */

	@Column(name ="DATABASE_NAME",nullable=true,length=255)
	public java.lang.String getDatabaseName(){
		return this.databaseName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setDatabaseName(java.lang.String databaseName){
		this.databaseName = databaseName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  SID
	 */

	@Column(name ="SID",nullable=true,length=255)
	public java.lang.String getSid(){
		return this.sid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  SID
	 */
	public void setSid(java.lang.String sid){
		this.sid = sid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  RACHA
	 */

	@Column(name ="RACORHA",nullable=true,length=1)
	public java.lang.String getRacorha(){
		return this.racorha;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  RACHA
	 */
	public void setRacorha(java.lang.String racorha){
		this.racorha = racorha;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  管理员
	 */

	@Column(name ="ADMINISTRATOR",nullable=true,length=19)
	public java.lang.String getAdministrator(){
		return this.administrator;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  管理员
	 */
	public void setAdministrator(java.lang.String administrator){
		this.administrator = administrator;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上线时间
	 */

	@Column(name ="LAUNCH_DATE",nullable=false)
	public java.util.Date getLaunchDate(){
		return this.launchDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上线时间
	 */
	public void setLaunchDate(java.util.Date launchDate){
		this.launchDate = launchDate;
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
	 *@return: java.util.Date  最后更改时间 
	 */

	@Column(name ="LAST_UPDATE_TIME",nullable=false)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后更改时间 
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
}
