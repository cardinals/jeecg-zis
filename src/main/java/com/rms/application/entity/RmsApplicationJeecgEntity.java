package com.rms.application.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.rms.server.entity.ServerEntity;
/**   
 * @Title: Entity
 * @Description: 应用管理
 * @author onlineGenerator
 * @date 2018-10-08 10:26:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_application_jeecg", schema = "")
@SuppressWarnings("serial")
public class RmsApplicationJeecgEntity implements java.io.Serializable {
	/**应用编号*/
	/**主键*/
	private java.lang.Integer id;
	/**中文名称*/
	@Excel(name="中文名称",width=15)
	private java.lang.String applicationNameCn;
	/**英文名称*/
	@Excel(name="英文名称",width=15)
	private java.lang.String applicationName;
	private java.lang.String applicationId;
	@Column(name ="APPLICATION_ID",nullable=true,length=65)
	public java.lang.String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(java.lang.String applicationId) {
		this.applicationId = applicationId;
	}

	/**所在设备*/
	private java.lang.String container;
	/**OS*/
	@Excel(name="OS",width=15)
	private java.lang.String serverId;
	//private java.lang.String serverId2;
	/*public void setServerId2(java.lang.String serverId2) {
		this.serverId2 = serverId2;
	}*/

	/**ip*/
	private java.lang.String ip;
	/**类型*/
	@Excel(name="类型",width=15,dicCode="appliType")
	private java.lang.String appType;
	/**状态*/
	@Excel(name="状态",width=15,dicCode="applstate")
	private java.lang.String status;
	/**应用描述*/
	@Excel(name="应用描述",width=15)
	private java.lang.String appInfo;
	/**管理员*/
	@Excel(name="管理员",width=15)
	private java.lang.String administrator;
	/**上线日期*/
	@Excel(name="上线日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date launchDate;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark1;
	/**备注2*/
	private java.lang.String remark2;
	/**创建日期*/
	private java.util.Date createTime;
	/**最后修改时间*/
	private java.sql.Timestamp  lastUpdateTime;
	/**创建人*/
	private java.lang.String createdBy;
	/**更新人*/
	private java.lang.String updatedBy;
	//上层容器
	//private ServerEntity server;
	

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应用编号
	 */
	/*@Id
	@GeneratedValue(generator = "identity_Generator")
	@GenericGenerator(name = "identity_Generator", strategy = "identity")
	@Column(name ="ID",nullable=false,length=65)
	public java.lang.String getId(){
		return this.id;
	}
*/
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=20)
	public java.lang.Integer getId(){
		return this.id;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应用编号
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中文名称
	 */

	@Column(name ="APPLICATION_NAME_CN",nullable=true,length=255)
	public java.lang.String getApplicationNameCn(){
		return this.applicationNameCn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中文名称
	 */
	public void setApplicationNameCn(java.lang.String applicationNameCn){
		this.applicationNameCn = applicationNameCn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文名称
	 */

	@Column(name ="APPLICATION_NAME",nullable=true,length=255)
	public java.lang.String getApplicationName(){
		return this.applicationName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文名称
	 */
	public void setApplicationName(java.lang.String applicationName){
		this.applicationName = applicationName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在设备
	 */

	/*@Column(name ="CONTAINER",nullable=true,length=19)
	public java.lang.String getContainer(){
		return this.container;
	}*/
	
	/**
	 *   更新版本-1008
	 * @return
	 */
	
	
	/**
	 * 多对一
	 * @return
	 */
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="SERVER_ID")
	public ServerEntity getServer() {
		return this.server;
	}*/
	
	/*public void setServer(ServerEntity server) {
		this.server = server;
	}*/
	
	@Column(name ="CONTAINER",nullable=true,length=19)
	public java.lang.String getContainer(){
		//return getServer().getId();
		return this.container;
	}
	/**	
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在设备
	 */
	public void setContainer(java.lang.String container){
		this.container = container;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  OS
	 */

	//@Column(name ="SERVER_ID",nullable=true,length=19)
	/*@Transient  
	public java.lang.String getServerId2(){
		//return this.serverId;
		return getServer().getId();
		//return server.getId();
	}*/

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  OS
	 */
	@Column(name ="SERVER_ID",nullable=true,length=19)
	public java.lang.String getServerId(){
		return this.serverId;
	}
	public void setServerId(java.lang.String serverId){
		this.serverId = serverId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ip
	 */

	@Column(name ="IP",nullable=true,length=32)
	public java.lang.String getIp(){
		return this.ip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ip
	 */
	public void setIp(java.lang.String ip){
		this.ip = ip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */

	@Column(name ="APP_TYPE",nullable=true,length=1)
	public java.lang.String getAppType(){
		return this.appType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setAppType(java.lang.String appType){
		this.appType = appType;
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
	 *@return: java.lang.String  应用描述
	 */

	@Column(name ="APP_INFO",nullable=true,length=255)
	public java.lang.String getAppInfo(){
		return this.appInfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应用描述
	 */
	public void setAppInfo(java.lang.String appInfo){
		this.appInfo = appInfo;
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
	 *@return: java.util.Date  上线日期
	 */

	@Column(name ="LAUNCH_DATE",nullable=false)
	public java.util.Date getLaunchDate(){
		return this.launchDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上线日期
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
	 *@return: java.util.Date  最后修改时间
	 */

	@Column(name ="LAST_UPDATE_TIME")
	public java.sql.Timestamp getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/** 方法: 设置java.util.Date
	 @param: java.util.Date  最后修改时间
	 */
	public void setLastUpdateTime(java.sql.Timestamp lastUpdateTime){
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
