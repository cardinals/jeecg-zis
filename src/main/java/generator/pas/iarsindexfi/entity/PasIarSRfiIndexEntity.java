package generator.pas.iarsindexfi.entity;

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
 * @Description: 2018年度投研交流满意度评价表
 * @author onlineGenerator
 * @date 2018-11-06 14:13:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_iar_s_rfi_index", schema = "")
@SuppressWarnings("serial")
public class PasIarSRfiIndexEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**评价人*/
	private java.lang.String appraiser;
	/**被评价人*/
	@Excel(name="被评价人",width=15)
	private java.lang.String goalUsername;
	/**被评价部门*/
	@Excel(name="被评价部门",width=15)
	private java.lang.String goalDept;
	/**投研交流满意度*/
	@Excel(name="投研交流满意度",width=15,dicCode="pas_100")
	private java.lang.Integer iarCmuIdx;
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
	 *@return: java.lang.String  被评价人
	 */

	@Column(name ="GOAL_USERNAME",nullable=true,length=32)
	public java.lang.String getGoalUsername(){
		return this.goalUsername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被评价人
	 */
	public void setGoalUsername(java.lang.String goalUsername){
		this.goalUsername = goalUsername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  被评价部门
	 */

	@Column(name ="GOAL_DEPT",nullable=true,length=32)
	public java.lang.String getGoalDept(){
		return this.goalDept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  被评价部门
	 */
	public void setGoalDept(java.lang.String goalDept){
		this.goalDept = goalDept;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  投研交流满意度
	 */

	@Column(name ="IAR_CMU_IDX",nullable=true,length=32)
	public java.lang.Integer getIarCmuIdx(){
		return this.iarCmuIdx;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  投研交流满意度
	 */
	public void setIarCmuIdx(java.lang.Integer iarCmuIdx){
		this.iarCmuIdx = iarCmuIdx;
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
	
	
    private List<PasIarSRfiIndexEntity> pasIarSRfiIndexList ;
	
    @Transient
	public List<PasIarSRfiIndexEntity> getPasIarSRfiIndexList() {
		return pasIarSRfiIndexList;
	}

	public void setPasIarSRfiIndexList(List<PasIarSRfiIndexEntity> pasIarSRfiIndexList) {
		this.pasIarSRfiIndexList = pasIarSRfiIndexList;
	}
}
