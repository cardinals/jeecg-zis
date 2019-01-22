package generator.pas.zongcai.entity;

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

import generator.pas.gaoguanhuping.entity.GaoguanHupingEntity;

/**   
 * @Title: Entity
 * @Description: 副总裁对上级评价
 * @author onlineGenerator
 * @date 2018-12-13 15:11:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_gaoguan_zongcai", schema = "")
@SuppressWarnings("serial")
public class PasGaoguanZongcaiEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	@Excel(name="创建人名称",width=15)
	private java.lang.String createName;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人名称*/
	@Excel(name="更新人名称",width=15)
	private java.lang.String updateName;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name="更新日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name="所属公司",width=15)
	private java.lang.String sysCompanyCode;
	/**职位*/
	@Excel(name="职位",width=15)
	private java.lang.String position;
	/**发展创新力*/
	@Excel(name="发展创新力",width=15)
	private java.lang.Integer deveCreate;
	/**评价人*/
	@Excel(name="评价人",width=15)
	private java.lang.String appraiser;
	/**合计*/
	@Excel(name="合计",width=15)
	private java.lang.String totalScore;
	/**被评价人*/
	@Excel(name="被评价人",width=15)
	private java.lang.String goalPerson;
	/**诚信敬业度*/
	@Excel(name="诚信敬业度",width=15)
	private java.lang.Integer sincerityHardwork;
	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private java.lang.String goalPersonDept;
	/**业绩完成情况*/
	@Excel(name="业绩完成情况",width=15)
	private java.lang.Integer yeji;
	/**团队领导力*/
	@Excel(name="团队领导力",width=15)
	private java.lang.Integer teamLead;
	/**确认状态*/
	@Excel(name="确认状态",width=15)
	private java.lang.String confirmFlag;
	private List<PasGaoguanZongcaiEntity> gghpList ;
	@Transient
	public List<PasGaoguanZongcaiEntity> getGghpList() {
		return gghpList;
	}

	public void setGghpList(List<PasGaoguanZongcaiEntity> gghpList) {
		this.gghpList = gghpList;
	}

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

	@Column(name ="CREATE_DATE",nullable=true)
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

	@Column(name ="UPDATE_DATE",nullable=true)
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
	 *@return: java.lang.String  职位
	 */

	@Column(name ="POSITION",nullable=true,length=32)
	public java.lang.String getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职位
	 */
	public void setPosition(java.lang.String position){
		this.position = position;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发展创新力
	 */

	@Column(name ="DEVE_CREATE",nullable=true,length=10)
	public java.lang.Integer getDeveCreate(){
		return this.deveCreate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发展创新力
	 */
	public void setDeveCreate(java.lang.Integer deveCreate){
		this.deveCreate = deveCreate;
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
	 *@return: java.lang.String  合计
	 */

	@Column(name ="TOTAL_SCORE",nullable=true,length=32)
	public java.lang.String getTotalScore(){
		return this.totalScore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合计
	 */
	public void setTotalScore(java.lang.String totalScore){
		this.totalScore = totalScore;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  诚信敬业度
	 */

	@Column(name ="SINCERITY_HARDWORK",nullable=true,length=10)
	public java.lang.Integer getSincerityHardwork(){
		return this.sincerityHardwork;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  诚信敬业度
	 */
	public void setSincerityHardwork(java.lang.Integer sincerityHardwork){
		this.sincerityHardwork = sincerityHardwork;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="GOAL_PERSON_DEPT",nullable=true,length=32)
	public java.lang.String getGoalPersonDept(){
		return this.goalPersonDept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setGoalPersonDept(java.lang.String goalPersonDept){
		this.goalPersonDept = goalPersonDept;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  业绩完成情况
	 */

	@Column(name ="YEJI",nullable=true,length=10)
	public java.lang.Integer getYeji(){
		return this.yeji;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  业绩完成情况
	 */
	public void setYeji(java.lang.Integer yeji){
		this.yeji = yeji;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  团队领导力
	 */

	@Column(name ="TEAM_LEAD",nullable=true,length=10)
	public java.lang.Integer getTeamLead(){
		return this.teamLead;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  团队领导力
	 */
	public void setTeamLead(java.lang.Integer teamLead){
		this.teamLead = teamLead;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  确认状态
	 */

	@Column(name ="CONFIRM_FLAG",nullable=true,length=32)
	public java.lang.String getConfirmFlag(){
		return this.confirmFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  确认状态
	 */
	public void setConfirmFlag(java.lang.String confirmFlag){
		this.confirmFlag = confirmFlag;
	}
}
