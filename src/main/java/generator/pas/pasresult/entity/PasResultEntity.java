package generator.pas.pasresult.entity;

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
 * @Description: 绩效考核是否打分
 * @author onlineGenerator
 * @date 2018-11-15 15:48:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_result", schema = "")
@SuppressWarnings("serial")
public class PasResultEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**评价人*/
	@Excel(name="评价人",width=15)
	private java.lang.String appraiser;
	/**评价模块*/
	@Excel(name="评价模块",width=15)
	private java.lang.String module;
	
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
	 *@return: java.lang.String  评价模块
	 */

	@Column(name ="MODULE",nullable=true,length=32)
	public java.lang.String getModule(){
		return this.module;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评价模块
	 */
	public void setModule(java.lang.String module){
		this.module = module;
	}
}
