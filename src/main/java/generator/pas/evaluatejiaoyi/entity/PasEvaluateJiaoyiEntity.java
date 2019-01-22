package generator.pas.evaluatejiaoyi.entity;

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

import generator.pas.iarsindex.entity.PasIarSIndexEntity;

/**   
 * @Title: Entity
 * @Description: 2018年度交易支持满意度评价表
 * @author onlineGenerator
 * @date 2018-12-07 10:32:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_evaluate_jiaoyi", schema = "")
@SuppressWarnings("serial")
public class PasEvaluateJiaoyiEntity implements java.io.Serializable {
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
	/**评分人*/
	private java.lang.String appraiser;
	/**评分人部门*/
	private java.lang.String appraiserDept;
	/**被评分人*/
	private java.lang.String goalPerson;
	/**被评价部门*/
	@Excel(name="被评价部门",width=15)
	private java.lang.String goalDepart;
	@Column(name ="appraiser_dept",nullable=true,length=50)
	public java.lang.String getAppraiserDept() {
		return appraiserDept;
	}
	
	public void setAppraiserDept(java.lang.String appraiserDept) {
		this.appraiserDept = appraiserDept;
	}

	/**交易指令完成度*/
	@Excel(name="交易指令完成度",width=15,dicCode="pas_s30")
	private java.lang.Integer professionalSkill;
	/**指令执行效率*/
	@Excel(name="指令执行效率",width=15,dicCode="pas_s30")
	private java.lang.Integer coopAtti;
	/**交易技能*/
	@Excel(name="交易技能",width=15,dicCode="pas_s20")
	private java.lang.Integer jiaoyiSkill;
	/**信息沟通*/
	@Excel(name="信息沟通",width=15,dicCode="pas_s20")
	private java.lang.Integer talkMessage;
	/**总分*/
	@Excel(name="总分",width=15)
	private java.lang.Integer totalScore;
	/**评分说明*/
	@Excel(name="评分说明",width=15)
	private java.lang.String comment;
	
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
	 *@return: java.lang.String  评分人
	 */

	@Column(name ="APPRAISER",nullable=true,length=32)
	public java.lang.String getAppraiser(){
		return this.appraiser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评分人
	 */
	public void setAppraiser(java.lang.String appraiser){
		this.appraiser = appraiser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  被评分人
	 */

	@Column(name ="GOAL_PERSON",nullable=true,length=32)
	public java.lang.String getGoalPerson(){
		return this.goalPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被评分人
	 */
	public void setGoalPerson(java.lang.String goalPerson){
		this.goalPerson = goalPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  被评价部门
	 */

	@Column(name ="GOAL_DEPART",nullable=true,length=32)
	public java.lang.String getGoalDepart(){
		return this.goalDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被评价部门
	 */
	public void setGoalDepart(java.lang.String goalDepart){
		this.goalDepart = goalDepart;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  交易指令完成度
	 */

	@Column(name ="PROFESSIONAL_SKILL",nullable=true,length=32)
	public java.lang.Integer getProfessionalSkill(){
		return this.professionalSkill;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  交易指令完成度
	 */
	public void setProfessionalSkill(java.lang.Integer professionalSkill){
		this.professionalSkill = professionalSkill;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  指令执行效率
	 */

	@Column(name ="COOP_ATTI",nullable=true,length=32)
	public java.lang.Integer getCoopAtti(){
		return this.coopAtti;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  指令执行效率
	 */
	public void setCoopAtti(java.lang.Integer coopAtti){
		this.coopAtti = coopAtti;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  交易技能
	 */

	@Column(name ="JIAOYI_SKILL",nullable=true,length=32)
	public java.lang.Integer getJiaoyiSkill(){
		return this.jiaoyiSkill;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  交易技能
	 */
	public void setJiaoyiSkill(java.lang.Integer jiaoyiSkill){
		this.jiaoyiSkill = jiaoyiSkill;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  信息沟通
	 */

	@Column(name ="TALK_MESSAGE",nullable=true,length=32)
	public java.lang.Integer getTalkMessage(){
		return this.talkMessage;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  信息沟通
	 */
	public void setTalkMessage(java.lang.Integer talkMessage){
		this.talkMessage = talkMessage;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总分
	 */

	@Column(name ="TOTAL_SCORE",nullable=true,length=32)
	public java.lang.Integer getTotalScore(){
		return this.totalScore;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总分
	 */
	public void setTotalScore(java.lang.Integer totalScore){
		this.totalScore = totalScore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评分说明
	 */

	@Column(name ="COMMENT",nullable=true,length=1000)
	public java.lang.String getComment(){
		return this.comment;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评分说明
	 */
	public void setComment(java.lang.String comment){
		this.comment = comment;
	}
	
	private List<PasEvaluateJiaoyiEntity> pasIarSIndexList ;
	
	@Transient
	public List<PasEvaluateJiaoyiEntity> getPasIarSIndexList() {
		return pasIarSIndexList;
	}

	public void setPasIarSIndexList(List<PasEvaluateJiaoyiEntity> pasIarSIndexList) {
		this.pasIarSIndexList = pasIarSIndexList;
	}
}
