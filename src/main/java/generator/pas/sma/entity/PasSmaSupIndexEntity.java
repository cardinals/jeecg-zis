package generator.pas.sma.entity;

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
 * @Description: 2018年度市场销售支持满意度评价表
 * @author onlineGenerator
 * @date 2018-11-07 13:30:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_sma_sup_index", schema = "")
@SuppressWarnings("serial")
public class PasSmaSupIndexEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**评价人*/
	private java.lang.String appraiser;
	/**被评价人所属部门*/
	@Excel(name="被评价人所属部门",width=15)
	private java.lang.String goalDept;
	/**被评价人*/
	@Excel(name="被评价人",width=15)
	private java.lang.String goalPerson;
	/**市场销售支持满意度*/
	@Excel(name="市场销售支持满意度",width=15,dicCode="pas_s100")
	private java.lang.Integer smaSupIdx;
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
	 *@return: java.lang.String  被评价人所属部门
	 */

	@Column(name ="GOAL_DEPT",nullable=true,length=32)
	public java.lang.String getGoalDept(){
		return this.goalDept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被评价人所属部门
	 */
	public void setGoalDept(java.lang.String goalDept){
		this.goalDept = goalDept;
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
	 *@return: java.lang.Integer  市场销售支持满意度
	 */

	@Column(name ="SMA_SUP_IDX",nullable=true,length=32)
	public java.lang.Integer getSmaSupIdx(){
		return this.smaSupIdx;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  市场销售支持满意度
	 */
	public void setSmaSupIdx(java.lang.Integer smaSupIdx){
		this.smaSupIdx = smaSupIdx;
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
	
	
    private List<PasSmaSupIndexEntity> pasSmaSupIndexList ;
	
    @Transient
	public List<PasSmaSupIndexEntity> getPasSmaSupIndexList() {
		return pasSmaSupIndexList;
	}

	public void setPasSmaSupIndexList(List<PasSmaSupIndexEntity> pasSmaSupIndexList) {
		this.pasSmaSupIndexList = pasSmaSupIndexList;
	}
}
