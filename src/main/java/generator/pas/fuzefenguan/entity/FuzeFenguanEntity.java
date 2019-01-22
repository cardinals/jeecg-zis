package generator.pas.fuzefenguan.entity;

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
 * @Description: 部门及部门的角色列表
 * @author onlineGenerator
 * @date 2018-11-17 13:31:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_fuze_fenguan", schema = "")
@SuppressWarnings("serial")
public class FuzeFenguanEntity implements java.io.Serializable {
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
	/**部门名称*/
	@Excel(name="部门名称",width=15)
	private java.lang.String departName;
	/**部门代码*/
	@Excel(name="部门代码",width=15)
	private java.lang.String departCode;
	/**部门主管*/
	@Excel(name="部门主管",width=15)
	private java.lang.String departZhuguan;
	/**部门分管*/
	@Excel(name="部门分管",width=15)
	private java.lang.String departFenguan;
	/**部门分管领导职位*/
	@Excel(name="部门分管领导职位",width=15)
	private java.lang.String fenguanPosition;
	/**部门协管*/
	@Excel(name="部门协管",width=15)
	private java.lang.String departXieguan;
	
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
	 *@return: java.lang.String  部门名称
	 */

	@Column(name ="DEPART_NAME",nullable=true,length=32)
	public java.lang.String getDepartName(){
		return this.departName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门名称
	 */
	public void setDepartName(java.lang.String departName){
		this.departName = departName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门代码
	 */

	@Column(name ="DEPART_CODE",nullable=true,length=32)
	public java.lang.String getDepartCode(){
		return this.departCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门代码
	 */
	public void setDepartCode(java.lang.String departCode){
		this.departCode = departCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门主管
	 */

	@Column(name ="DEPART_ZHUGUAN",nullable=true,length=32)
	public java.lang.String getDepartZhuguan(){
		return this.departZhuguan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门主管
	 */
	public void setDepartZhuguan(java.lang.String departZhuguan){
		this.departZhuguan = departZhuguan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门分管
	 */

	@Column(name ="DEPART_FENGUAN",nullable=true,length=32)
	public java.lang.String getDepartFenguan(){
		return this.departFenguan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门分管
	 */
	public void setDepartFenguan(java.lang.String departFenguan){
		this.departFenguan = departFenguan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门分管领导职位
	 */

	@Column(name ="FENGUAN_POSITION",nullable=true,length=32)
	public java.lang.String getFenguanPosition(){
		return this.fenguanPosition;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门分管领导职位
	 */
	public void setFenguanPosition(java.lang.String fenguanPosition){
		this.fenguanPosition = fenguanPosition;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门协管
	 */

	@Column(name ="DEPART_XIEGUAN",nullable=true,length=32)
	public java.lang.String getDepartXieguan(){
		return this.departXieguan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门协管
	 */
	public void setDepartXieguan(java.lang.String departXieguan){
		this.departXieguan = departXieguan;
	}
}
