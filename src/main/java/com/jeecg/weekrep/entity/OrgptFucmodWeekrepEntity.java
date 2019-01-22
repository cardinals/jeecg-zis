package com.jeecg.weekrep.entity;

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
 * @Description: 周报
 * @author onlineGenerator
 * @date 2018-06-08 19:59:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "orgpt_fucmod_weekrep", schema = "")
@SuppressWarnings("serial")
public class OrgptFucmodWeekrepEntity implements java.io.Serializable {
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
	/**员工姓名*/
	@Excel(name="员工姓名",width=15)
	private java.lang.String staffname;
	/**拜访日期*/
	@Excel(name="拜访日期",width=15)
	private java.lang.String visitdate;
	/**本周拜访机构*/
	@Excel(name="本周拜访机构",width=15)
	private java.lang.String targetorginwk;
	/**是否准入*/
	@Excel(name="是否准入",width=15,dicCode="01NY")
	private java.lang.String accessflag;
	/**准入类型*/
	@Excel(name="准入类型",width=15)
	private java.lang.String accesstype;
	/**部门*/
	@Excel(name="部门",width=15)
	private java.lang.String department;
	/**对接人*/
	@Excel(name="对接人",width=15)
	private java.lang.String djperson;
	/**对接人职位*/
	@Excel(name="对接人职位",width=15)
	private java.lang.String djpersonjob;
	/**业务合作点*/
	@Excel(name="业务合作点",width=15,dicCode="cooperapo")
	private java.lang.String cooperationpoint;
	/**跟进情况*/
	@Excel(name="跟进情况",width=15)
	private java.lang.String followsituation;
	/**客户需求*/
	@Excel(name="客户需求",width=15)
	private java.lang.String customerdemand;
	/**投资概况*/
	@Excel(name="投资概况",width=15)
	private java.lang.String investsituation;
	/**邮标*/
	@Excel(name="邮标",width=15)
	private java.lang.String mailsign;
	/**核心人员*/
	@Excel(name="核心人员",width=15)
	private java.lang.String corestaff;
	/**核心人员职位*/
	@Excel(name="核心人员职位",width=15)
	private java.lang.String corestaffpos;
	/**需要支持事项*/
	@Excel(name="需要支持事项",width=15)
	private java.lang.String needsupitems;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remarks;
	/**批注*/
	@Excel(name="批注",width=15)
	private java.lang.String procomment;
	
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
	 *@return: java.lang.String  员工姓名
	 */

	@Column(name ="STAFFNAME",nullable=true,length=32)
	public java.lang.String getStaffname(){
		return this.staffname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工姓名
	 */
	public void setStaffname(java.lang.String staffname){
		this.staffname = staffname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拜访日期
	 */

	@Column(name ="VISITDATE",nullable=true,length=32)
	public java.lang.String getVisitdate(){
		return this.visitdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拜访日期
	 */
	public void setVisitdate(java.lang.String visitdate){
		this.visitdate = visitdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本周拜访机构
	 */

	@Column(name ="TARGETORGINWK",nullable=true,length=32)
	public java.lang.String getTargetorginwk(){
		return this.targetorginwk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本周拜访机构
	 */
	public void setTargetorginwk(java.lang.String targetorginwk){
		this.targetorginwk = targetorginwk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否准入
	 */

	@Column(name ="ACCESSFLAG",nullable=true,length=32)
	public java.lang.String getAccessflag(){
		return this.accessflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否准入
	 */
	public void setAccessflag(java.lang.String accessflag){
		this.accessflag = accessflag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  准入类型
	 */

	@Column(name ="ACCESSTYPE",nullable=true,length=32)
	public java.lang.String getAccesstype(){
		return this.accesstype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  准入类型
	 */
	public void setAccesstype(java.lang.String accesstype){
		this.accesstype = accesstype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */

	@Column(name ="DEPARTMENT",nullable=true,length=32)
	public java.lang.String getDepartment(){
		return this.department;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDepartment(java.lang.String department){
		this.department = department;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对接人
	 */

	@Column(name ="DJPERSON",nullable=true,length=32)
	public java.lang.String getDjperson(){
		return this.djperson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对接人
	 */
	public void setDjperson(java.lang.String djperson){
		this.djperson = djperson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对接人职位
	 */

	@Column(name ="DJPERSONJOB",nullable=true,length=32)
	public java.lang.String getDjpersonjob(){
		return this.djpersonjob;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对接人职位
	 */
	public void setDjpersonjob(java.lang.String djpersonjob){
		this.djpersonjob = djpersonjob;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务合作点
	 */

	@Column(name ="COOPERATIONPOINT",nullable=true,length=32)
	public java.lang.String getCooperationpoint(){
		return this.cooperationpoint;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务合作点
	 */
	public void setCooperationpoint(java.lang.String cooperationpoint){
		this.cooperationpoint = cooperationpoint;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跟进情况
	 */

	@Column(name ="FOLLOWSITUATION",nullable=true,length=32)
	public java.lang.String getFollowsituation(){
		return this.followsituation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跟进情况
	 */
	public void setFollowsituation(java.lang.String followsituation){
		this.followsituation = followsituation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户需求
	 */

	@Column(name ="CUSTOMERDEMAND",nullable=true,length=32)
	public java.lang.String getCustomerdemand(){
		return this.customerdemand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户需求
	 */
	public void setCustomerdemand(java.lang.String customerdemand){
		this.customerdemand = customerdemand;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  投资概况
	 */

	@Column(name ="INVESTSITUATION",nullable=true,length=32)
	public java.lang.String getInvestsituation(){
		return this.investsituation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  投资概况
	 */
	public void setInvestsituation(java.lang.String investsituation){
		this.investsituation = investsituation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮标
	 */

	@Column(name ="MAILSIGN",nullable=true,length=32)
	public java.lang.String getMailsign(){
		return this.mailsign;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮标
	 */
	public void setMailsign(java.lang.String mailsign){
		this.mailsign = mailsign;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  核心人员
	 */

	@Column(name ="CORESTAFF",nullable=true,length=32)
	public java.lang.String getCorestaff(){
		return this.corestaff;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  核心人员
	 */
	public void setCorestaff(java.lang.String corestaff){
		this.corestaff = corestaff;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  核心人员职位
	 */

	@Column(name ="CORESTAFFPOS",nullable=true,length=32)
	public java.lang.String getCorestaffpos(){
		return this.corestaffpos;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  核心人员职位
	 */
	public void setCorestaffpos(java.lang.String corestaffpos){
		this.corestaffpos = corestaffpos;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  需要支持事项
	 */

	@Column(name ="NEEDSUPITEMS",nullable=true,length=32)
	public java.lang.String getNeedsupitems(){
		return this.needsupitems;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  需要支持事项
	 */
	public void setNeedsupitems(java.lang.String needsupitems){
		this.needsupitems = needsupitems;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARKS",nullable=true,length=32)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批注
	 */

	@Column(name ="PROCOMMENT",nullable=true,length=32)
	public java.lang.String getProcomment(){
		return this.procomment;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批注
	 */
	public void setProcomment(java.lang.String procomment){
		this.procomment = procomment;
	}
}
