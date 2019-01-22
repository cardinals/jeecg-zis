package com.jeecg.raiseaccount.entity;
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
 * @Description: 募集资金
 * @author onlineGenerator
 * @date 2018-06-19 14:48:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "raise_account", schema = "")
@SuppressWarnings("serial")
public class RaiseAccountEntity implements java.io.Serializable {
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
	private java.lang.String yinhangAccount;
	/**银行户名*/
    @Excel(name="银行户名",width=15)
	private java.lang.String yinhangName;
	/**开户银行全称*/
    @Excel(name="开户银行全称",width=15)
	private java.lang.String yinhangNamefull;
	/**曾用产品*/
    @Excel(name="曾用产品",width=15)
	private java.lang.String preProject;
	/**结息状态*/
    @Excel(name="结息状态",width=15,dicCode="jiexi")
	private java.lang.String jiexiStatus;
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
	
	@Column(name ="YINHANG_ACCOUNT",nullable=true,length=32)
	public java.lang.String getYinhangAccount(){
		return this.yinhangAccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行账号
	 */
	public void setYinhangAccount(java.lang.String yinhangAccount){
		this.yinhangAccount = yinhangAccount;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行户名
	 */
	
	@Column(name ="YINHANG_NAME",nullable=true,length=32)
	public java.lang.String getYinhangName(){
		return this.yinhangName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行户名
	 */
	public void setYinhangName(java.lang.String yinhangName){
		this.yinhangName = yinhangName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户银行全称
	 */
	
	@Column(name ="YINHANG_NAMEFULL",nullable=true,length=32)
	public java.lang.String getYinhangNamefull(){
		return this.yinhangNamefull;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行全称
	 */
	public void setYinhangNamefull(java.lang.String yinhangNamefull){
		this.yinhangNamefull = yinhangNamefull;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  曾用产品
	 */
	
	@Column(name ="PRE_PROJECT",nullable=true,length=32)
	public java.lang.String getPreProject(){
		return this.preProject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  曾用产品
	 */
	public void setPreProject(java.lang.String preProject){
		this.preProject = preProject;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结息状态
	 */
	
	@Column(name ="JIEXI_STATUS",nullable=true,length=32)
	public java.lang.String getJiexiStatus(){
		return this.jiexiStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结息状态
	 */
	public void setJiexiStatus(java.lang.String jiexiStatus){
		this.jiexiStatus = jiexiStatus;
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
