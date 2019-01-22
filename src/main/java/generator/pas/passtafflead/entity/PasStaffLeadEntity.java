package generator.pas.passtafflead.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import generator.pas.onstaff.entity.PasOnStaffEntity;

/**   
 * @Title: Entity
 * @Description: PAS_员工对部门负责人评分
 * @author onlineGenerator
 * @date 2018-10-23 11:39:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_staff_lead", schema = "")
@SuppressWarnings("serial")
public class PasStaffLeadEntity implements java.io.Serializable {
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
	/**被评价人*/
	@Excel(name="被评价人",width=15)
	private java.lang.String goalPerson;
	/**评价人*/
	private java.lang.String appraiser;
	
	private java.lang.String appraiserDept;//评价人部门
	
	@Column(name ="appraiser_dept",nullable=true,length=32)
	public java.lang.String getAppraiserDept() {
		return appraiserDept;
	}

	public void setAppraiserDept(java.lang.String appraiserDept) {
		this.appraiserDept = appraiserDept;
	}

	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private java.lang.String dept;
	/**专业水平*/
	@Excel(name="专业水平",width=15,dicCode="pas_s25")
	private java.lang.Integer professionalSkill;
	/**团队建设*/
	@Excel(name="团队建设",width=15,dicCode="pas_s25")
	private java.lang.Integer teamBuild;
	
	/**责任意识*/
	//@Excel(name="责任意识",width=15,dicCode="pas_s25")
	private java.lang.Integer zeren;
	/**全局意识*/
//	@Excel(name="全局意识",width=15,dicCode="pas_s25")
	private java.lang.Integer quanju;
	
	@Column(name ="zeren",nullable=true,length=32)
	public java.lang.Integer getZeren() {
		return zeren;
	}

	public void setZeren(java.lang.Integer zeren) {
		this.zeren = zeren;
	}
	@Column(name ="quanju",nullable=true,length=32)
	public java.lang.Integer getQuanju() {
		return quanju;
	}

	public void setQuanju(java.lang.Integer quanju) {
		this.quanju = quanju;
	}

	/**合计*/
	@Excel(name="合计",width=15)
	private java.lang.Integer totalScore;
	
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
	 *@return: java.lang.String  被评价人
	 */

	@Column(name ="GOAL_PERSON",nullable=true,length=32)
	public java.lang.String getGoalPerson(){
		return this.goalPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被评价人
	 */
	public void setGoalPerson(java.lang.String goalPerson){
		this.goalPerson = goalPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评价人
	 */

	@Column(name ="APPRAISER",nullable=true,length=32)
	public java.lang.String getAppraiser(){
		return this.appraiser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评价人
	 */
	public void setAppraiser(java.lang.String appraiser){
		this.appraiser = appraiser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="DEPT",nullable=true,length=32)
	public java.lang.String getDept(){
		return this.dept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setDept(java.lang.String dept){
		this.dept = dept;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  专业水平
	 */

	@Column(name ="PROFESSIONAL_SKILL",nullable=true,length=32)
	public java.lang.Integer getProfessionalSkill(){
		return this.professionalSkill;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  专业水平
	 */
	public void setProfessionalSkill(java.lang.Integer professionalSkill){
		this.professionalSkill = professionalSkill;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  团队建设
	 */

	@Column(name ="TEAM_BUILD",nullable=true,length=32)
	public java.lang.Integer getTeamBuild(){
		return this.teamBuild;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  团队建设
	 */
	public void setTeamBuild(java.lang.Integer teamBuild){
		this.teamBuild = teamBuild;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  合计
	 */

	@Column(name ="TOTAL_SCORE",nullable=true,length=32)
	public java.lang.Integer getTotalScore(){
		return this.totalScore;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  合计
	 */
	public void setTotalScore(java.lang.Integer totalScore){
		this.totalScore = totalScore;
	}
	
	private List<PasStaffLeadEntity> pasStaffLeadList ;
	 
	@Transient
	public List<PasStaffLeadEntity> getPasStaffLeadList() {
		return pasStaffLeadList;
	}

	public void setPasStaffLeadList(List<PasStaffLeadEntity> pasStaffLeadList) {
		this.pasStaffLeadList = pasStaffLeadList;
	}
		
	    
}
