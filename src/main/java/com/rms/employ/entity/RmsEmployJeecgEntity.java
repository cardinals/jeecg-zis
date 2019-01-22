package com.rms.employ.entity;

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
 * @Description: RMS_员工管理
 * @author onlineGenerator
 * @date 2018-10-15 11:30:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_employ_jeecg", schema = "")
@SuppressWarnings("serial")
public class RmsEmployJeecgEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**主键*/
	private java.lang.String employId;
	/**员工编号*/
	@Excel(name="员工编号",width=15)
	private java.lang.String employNo;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private java.lang.String employName;
	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private java.lang.String departmentId;
	/**是否在职*/
	@Excel(name="是否在职",width=15)
	private java.lang.String enable;
	/**邮箱*/
	@Excel(name="邮箱",width=15)
	private java.lang.String emali;
	/**在职状态*/
	private java.lang.String status;
	/**名下资产*/
	private java.lang.String assetId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
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
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
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
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */

	@Column(name ="EMPLOY_ID",nullable=true,length=32)
	public java.lang.String getEmployId(){
		return this.employId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setEmployId(java.lang.String employId){
		this.employId = employId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  员工编号
	 */

	@Column(name ="EMPLOY_NO",nullable=true,length=32)
	public java.lang.String getEmployNo(){
		return this.employNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工编号
	 */
	public void setEmployNo(java.lang.String employNo){
		this.employNo = employNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */

	@Column(name ="EMPLOY_NAME",nullable=true,length=32)
	public java.lang.String getEmployName(){
		return this.employName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setEmployName(java.lang.String employName){
		this.employName = employName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="DEPARTMENT_ID",nullable=true,length=32)
	public java.lang.String getDepartmentId(){
		return this.departmentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setDepartmentId(java.lang.String departmentId){
		this.departmentId = departmentId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否在职
	 */

	@Column(name ="ENABLE",nullable=true,length=32)
	public java.lang.String getEnable(){
		return this.enable;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否在职
	 */
	public void setEnable(java.lang.String enable){
		this.enable = enable;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */

	@Column(name ="EMALI",nullable=true,length=32)
	public java.lang.String getEmali(){
		return this.emali;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setEmali(java.lang.String emali){
		this.emali = emali;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  在职状态
	 */

	@Column(name ="STATUS",nullable=true,length=32)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  在职状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名下资产
	 */

	@Column(name ="ASSET_ID",nullable=true,length=32)
	public java.lang.String getAssetId(){
		return this.assetId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名下资产
	 */
	public void setAssetId(java.lang.String assetId){
		this.assetId = assetId;
	}
}
