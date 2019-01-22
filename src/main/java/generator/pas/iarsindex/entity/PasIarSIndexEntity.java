package generator.pas.iarsindex.entity;

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
 * @Description: 绩效考核系统投研交流评价表
 * @author onlineGenerator
 * @date 2018-10-31 14:43:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_iar_s_index", schema = "")
@SuppressWarnings("serial")
public class PasIarSIndexEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**评分人*/
	private java.lang.String appraiser;
	
	/**评分人部门*/
	private java.lang.String appraiserDept;
	
	/**被评分人*/
	@Excel(name="被评分人",width=15)
	private java.lang.String goalPerson;
	/**专业水平*/
	@Excel(name="专业水平",width=15,dicCode="pas_s50")
	private java.lang.Integer professionalSkill;
	/**合作态度*/
	@Excel(name="合作态度",width=15,dicCode="pas_s50")
	private java.lang.Integer coopAtti;
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
	 *@return: java.lang.String  评分人
	 */

	@Column(name ="APPRAISER",nullable=true,length=40)
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
	 *@return: java.lang.String  评分人部门
	 */

	@Column(name ="APPRAISER_DEPT",nullable=true,length=40)
	public java.lang.String getAppraiserDept(){
		return this.appraiserDept;
	}


	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被评分人
	 */
	public void setAppraiserDept(java.lang.String appraiserDept){
		this.appraiserDept = appraiserDept;
	}
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  被评分人
	 */
	
	@Column(name ="GOAL_PERSON",nullable=true,length=40)
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
	
	
    private List<PasIarSIndexEntity> pasIarSIndexList ;
	
    @Transient
	public List<PasIarSIndexEntity> getPasIarSIndexList() {
		return pasIarSIndexList;
	}

	public void setPasIarSIndexList(List<PasIarSIndexEntity> pasIarSIndexList) {
		this.pasIarSIndexList = pasIarSIndexList;
	}
}
