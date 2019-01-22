package com.rms.server.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.rms.hardware.entity.HardwareEntity;

/**   
 * @Title: Entity
 * @Description: OS信息
 * @author onlineGenerator
 * @date 2018-09-05 10:49:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_server_jeecg", schema = "")
@SuppressWarnings("serial")
public class ServerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	
	private java.lang.String serverId;
	
	
	
	@Column(name ="SERVER_ID",nullable=true,length=255)
	public java.lang.String getServerId() {
		return serverId;
	}

	public void setServerId(java.lang.String serverId) {
		this.serverId = serverId;
	}

	/**OS名称*/
	@Excel(name="OS名称",width=15)
	private java.lang.String serverName;
	/**ip信息*/
	@Excel(name="ip信息",width=15)
	private java.lang.String ip;
	/**状态*/
	@Excel(name="状态",width=15,dicCode="oststa")
	private java.lang.String status;
	/**类型*/
	@Excel(name="类型",width=15,dicCode="ostype")
	private java.lang.String osType;
	/**虚拟机*/
	@Excel(name="虚拟机",width=15,dicCode="01NY")
	private java.lang.String virtural;
	/**系统版本号*/
	@Excel(name="系统版本号",width=15)
	private java.lang.String osVersion;
	/**配置信息*/
	@Excel(name="配置信息",width=15)
	private java.lang.String configInfo;
	/**管理员*/
	@Excel(name="管理员",width=15)
	private java.lang.String manageIp;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**innerIp*/
	private java.lang.String innerIp;
	/**outerIp*/
	private java.lang.String outerIp;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark1;
	/**备注2*/
	private java.lang.String remark2;
	/**创建日期*/
	private java.util.Date createTime;
	/**最后修改时间*/
	private java.util.Date lastUpdateTime;
	/**上层容器*/
	private java.lang.String vcontainerid;
	/**申请人*/
	private java.lang.String applicant;
	/**创建人*/
	private java.lang.String createdBy;
	/**修改人*/
	private java.lang.String updatedBy;
	/**硬件上层容器*/
	@Excel(name="硬件上层容器",width=15)
	private java.lang.String container;//上层容器,硬件上层容器
	private String vcontainer;//上层容器，即对应的 ESX SERVER,虚拟机
	/**服务器管理员*/
	private java.lang.String administrator;
	/**所在硬件*/
	private java.lang.String hardwareId;
	
	private java.lang.String vcontainer2;//用来接收页面传来的参数
	
	/**虚拟上层容器*/
	/*@Excel(name="虚拟上层容器",width=15)
	private java.lang.String vcontainer;///vcontain
*/	/**
	 * 多对一关联
	 */
	private HardwareEntity hardware;//上层容器，即对应的硬件设备
	
	
  
	@Transient
	public java.lang.String getVcontainer2() {
		return vcontainer2;
	}

	public void setVcontainer2(java.lang.String vcontainer2) {
		this.vcontainer2 = vcontainer2;
	}

	//private Set<ServerEntity> vServerSet;//虚拟机集合

	//@JoinColumn(name="HARDWARE_ID")//注释的是另一个表指向本表的外键。
	/**
	 * 在一对多的时候，多的一方是关系维护端，一的一方是关系被维护端。
	 * 在关系被维护端里面有mappedBy 关系维护方对应的表中应该有外键
	 * @return
	 */
	//@OneToMany(targetEntity = ServerEntity.class,  cascade = { CascadeType.ALL }, mappedBy = "vcontain" )
	
/*	@OneToMany(fetch=FetchType.LAZY,targetEntity=ServerEntity.class,cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="vcontainer")
	public Set<ServerEntity> getvServerSet() {
		return vServerSet;
	}

	public void setvServerSet(Set<ServerEntity> vServerSet) {
		this.vServerSet = vServerSet;
	}*/

	//@ManyToOne(cascade = CascadeType.ALL, optional = false)
	//@JoinColumn(name ="VCONTAINER",referencedColumnName="SERVER_ID",insertable=false,updatable=false)
	
/*	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ServerEntity.class,cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "VCONTAINER",referencedColumnName="SERVER_ID")
*/	
	
	@Column(name ="VCONTAINER",nullable=true,length=255)
	public String getVcontainer() {
		return vcontainer;
	}

	public void setVcontainer(String vcontainer) {
		this.vcontainer = vcontainer;
	}

	/*@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="CONTAINER",referencedColumnName="HARDWARE_ID",insertable=false,updatable=false)//CONTAINER
	public HardwareEntity getHardware() {
		return hardware;
	}

	public void setHardware(HardwareEntity hardware) {
		this.hardware = hardware;
	}*/

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")

	@Column(name ="ID",nullable=false,length=65)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  OS名称
	 */

	@Column(name ="SERVER_NAME",nullable=true,length=255)
	public java.lang.String getServerName(){
		return this.serverName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  OS名称
	 */
	public void setServerName(java.lang.String serverName){
		this.serverName = serverName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ip信息
	 */

	@Column(name ="IP",nullable=true,length=255)
	public java.lang.String getIp(){
		return this.ip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ip信息
	 */
	public void setIp(java.lang.String ip){
		this.ip = ip;
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
	 *@return: java.lang.String  类型
	 */

	@Column(name ="OS_TYPE",nullable=true,length=1)
	public java.lang.String getOsType(){
		return this.osType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setOsType(java.lang.String osType){
		this.osType = osType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  虚拟机
	 */

	@Column(name ="VIRTURAL",nullable=true,length=1)
	public java.lang.String getVirtural(){
		return this.virtural;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  虚拟机
	 */
	public void setVirtural(java.lang.String virtural){
		this.virtural = virtural;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  系统版本号
	 */

	@Column(name ="OS_VERSION",nullable=true,length=255)
	public java.lang.String getOsVersion(){
		return this.osVersion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  系统版本号
	 */
	public void setOsVersion(java.lang.String osVersion){
		this.osVersion = osVersion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  配置信息
	 */

	@Column(name ="CONFIG_INFO",nullable=true,length=255)
	public java.lang.String getConfigInfo(){
		return this.configInfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  配置信息
	 */
	public void setConfigInfo(java.lang.String configInfo){
		this.configInfo = configInfo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  管理员
	 */

	@Column(name ="MANAGE_IP",nullable=true,length=255)
	public java.lang.String getManageIp(){
		return this.manageIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  管理员
	 */
	public void setManageIp(java.lang.String manageIp){
		this.manageIp = manageIp;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=false)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  innerIp
	 */

	@Column(name ="INNER_IP",nullable=true,length=255)
	public java.lang.String getInnerIp(){
		return this.innerIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  innerIp
	 */
	public void setInnerIp(java.lang.String innerIp){
		this.innerIp = innerIp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  outerIp
	 */

	@Column(name ="OUTER_IP",nullable=true,length=255)
	public java.lang.String getOuterIp(){
		return this.outerIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  outerIp
	 */
	public void setOuterIp(java.lang.String outerIp){
		this.outerIp = outerIp;
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

	@Column(name ="LAST_UPDATE_TIME",nullable=false)
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
	 *@return: java.lang.String  上层容器
	 */

	@Column(name ="VCONTAINERID",nullable=true,length=19)
	public java.lang.String getVcontainerid(){
		return this.vcontainerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上层容器
	 */
	public void setVcontainerid(java.lang.String vcontainerid){
		this.vcontainerid = vcontainerid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请人
	 */

	@Column(name ="APPLICANT",nullable=true,length=19)
	public java.lang.String getApplicant(){
		return this.applicant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请人
	 */
	public void setApplicant(java.lang.String applicant){
		this.applicant = applicant;
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
	 *@return: java.lang.String  硬件上层容器
	 */

	@Column(name ="CONTAINER",nullable=true,length=19,insertable=true,updatable=true)
	public java.lang.String getContainer(){
		return this.container;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  硬件上层容器
	 */
	public void setContainer(java.lang.String container){
		this.container = container;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务器管理员
	 */

	@Column(name ="ADMINISTRATOR",nullable=true,length=19)
	public java.lang.String getAdministrator(){
		return this.administrator;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务器管理员
	 */
	public void setAdministrator(java.lang.String administrator){
		this.administrator = administrator;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在硬件
	 */

	@Column(name ="HARDWARE_ID",nullable=true,length=19)
	public java.lang.String getHardwareId(){
		return this.hardwareId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在硬件
	 */
	public void setHardwareId(java.lang.String hardwareId){
		this.hardwareId = hardwareId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  虚拟上层容器
	 */

	/*@Column(name ="VCONTAINER",nullable=true,length=19)
	public java.lang.String getVcontainer(){
		return this.vcontainer;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  虚拟上层容器
	 *//*
	public void setVcontainer(java.lang.String vcontainer){
		this.vcontainer = vcontainer;
	}*/
}
