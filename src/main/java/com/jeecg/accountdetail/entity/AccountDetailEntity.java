package com.jeecg.accountdetail.entity;
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
 * @Description: 资金明细
 * @author onlineGenerator
 * @date 2018-06-19 14:48:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "account_detail", schema = "")
@SuppressWarnings("serial")
public class AccountDetailEntity implements java.io.Serializable {
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
	/**银行账号*/
    @Excel(name="银行账号",width=15)
	private java.lang.String detailNumber;
	/**银行户名*/
    @Excel(name="银行户名",width=15)
	private java.lang.String detailName;
	/**开户银行全称*/
    @Excel(name="开户银行全称",width=15)
	private java.lang.String detailFullname;
	/**利率*/
    @Excel(name="利率",width=15)
	private java.lang.String detailRate;
	/**开始日期*/
    @Excel(name="开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date startDate;
	/**结束日期*/
    @Excel(name="结束日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date endDate;
	/**外键*/
	private java.lang.String accountMainId;
	
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
	 *@return: java.lang.String  银行账号
	 */
	
	@Column(name ="DETAIL_NUMBER",nullable=true,length=32)
	public java.lang.String getDetailNumber(){
		return this.detailNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行账号
	 */
	public void setDetailNumber(java.lang.String detailNumber){
		this.detailNumber = detailNumber;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行户名
	 */
	
	@Column(name ="DETAIL_NAME",nullable=true,length=32)
	public java.lang.String getDetailName(){
		return this.detailName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行户名
	 */
	public void setDetailName(java.lang.String detailName){
		this.detailName = detailName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户银行全称
	 */
	
	@Column(name ="DETAIL_FULLNAME",nullable=true,length=32)
	public java.lang.String getDetailFullname(){
		return this.detailFullname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行全称
	 */
	public void setDetailFullname(java.lang.String detailFullname){
		this.detailFullname = detailFullname;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率
	 */
	
	@Column(name ="DETAIL_RATE",nullable=true,length=32)
	public java.lang.String getDetailRate(){
		return this.detailRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率
	 */
	public void setDetailRate(java.lang.String detailRate){
		this.detailRate = detailRate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始日期
	 */
	
	@Column(name ="START_DATE",nullable=true,length=32)
	public java.util.Date getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始日期
	 */
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束日期
	 */
	
	@Column(name ="END_DATE",nullable=true,length=32)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束日期
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */
	
	@Column(name ="ACCOUNT_MAIN_ID",nullable=true,length=32)
	public java.lang.String getAccountMainId(){
		return this.accountMainId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setAccountMainId(java.lang.String accountMainId){
		this.accountMainId = accountMainId;
	}
	
}
