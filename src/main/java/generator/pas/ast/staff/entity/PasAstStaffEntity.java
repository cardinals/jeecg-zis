package generator.pas.ast.staff.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.util.List;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Transient;
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
 * @Description: 员工互评_资产
 * @author onlineGenerator
 * @date 2018-11-26 14:03:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_ast_staff", schema = "")
@SuppressWarnings("serial")
public class PasAstStaffEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**评价人*/
	private java.lang.String appraiser;
	/**被评价人*/
	@Excel(name="被评价人",width=15)
	private java.lang.String goalPerson;
	/**部门*/
	@Excel(name="部门",width=15)
	private java.lang.String goalPersonDept;
	/**专业水平*/
	@Excel(name="专业水平",width=15,dicCode="pas_30")
	private java.lang.Integer professionalSkill;
	/**合作效率*/
	@Excel(name="合作效率",width=15,dicCode="pas_20")
	private java.lang.Integer coopEff;
	/**合作态度*/
	@Excel(name="合作态度",width=15,dicCode="pas_20")
	private java.lang.Integer coopAtti;
	/**合作结果*/
	@Excel(name="合作结果",width=15,dicCode="pas_30")
	private java.lang.Integer coopOutcome;
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
	 *@return: java.lang.String  部门
	 */

	@Column(name ="GOAL_PERSON_DEPT",nullable=true,length=32)
	public java.lang.String getGoalPersonDept(){
		return this.goalPersonDept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setGoalPersonDept(java.lang.String goalPersonDept){
		this.goalPersonDept = goalPersonDept;
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
	 *@return: java.lang.Integer  合作效率
	 */

	@Column(name ="COOP_EFF",nullable=true,length=32)
	public java.lang.Integer getCoopEff(){
		return this.coopEff;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  合作效率
	 */
	public void setCoopEff(java.lang.Integer coopEff){
		this.coopEff = coopEff;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  合作态度
	 */

	@Column(name ="COOP_ATTI",nullable=true,length=32)
	public java.lang.Integer getCoopAtti(){
		return this.coopAtti;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  合作态度
	 */
	public void setCoopAtti(java.lang.Integer coopAtti){
		this.coopAtti = coopAtti;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  合作结果
	 */

	@Column(name ="COOP_OUTCOME",nullable=true,length=32)
	public java.lang.Integer getCoopOutcome(){
		return this.coopOutcome;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  合作结果
	 */
	public void setCoopOutcome(java.lang.Integer coopOutcome){
		this.coopOutcome = coopOutcome;
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
	
	
    private List<PasAstStaffEntity> pasAstStaffList ;
	
    @Transient
	public List<PasAstStaffEntity> getPasAstStaffList() {
		return pasAstStaffList;
	}

	public void setPasAstStaffList(List<PasAstStaffEntity> pasAstStaffList) {
		this.pasAstStaffList = pasAstStaffList;
	}
}
