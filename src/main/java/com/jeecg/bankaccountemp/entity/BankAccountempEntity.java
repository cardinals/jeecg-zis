package com.jeecg.bankaccountemp.entity;

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
 * @Description: 银行账户信息临时表
 * @author onlineGenerator
 * @date 2018-09-06 16:41:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bank_accountemp", schema = "")
@SuppressWarnings("serial")
public class BankAccountempEntity implements java.io.Serializable {
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
	/**银行账号*/
	@Excel(name="银行账号",width=15)
	private java.lang.String accountNumber;
	/**银行户名*/
	@Excel(name="银行户名",width=15)
	private java.lang.String acountName;
	/**开户银行全称*/
	@Excel(name="开户银行全称",width=15)
	private java.lang.String acountFullname;
	/**开户银行简称*/
	@Excel(name="开户银行简称",width=15)
	private java.lang.String acountShortname;
	/**账户类型*/
	@Excel(name="账户类型",width=15)
	private java.lang.String acountType;
	/**开户日期*/
	@Excel(name="开户日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date kaihuDate;
	/**账户状态*/
	@Excel(name="账户状态",width=15)
	private java.lang.String acountStatus;
	/**账户利率*/
	@Excel(name="账户利率",width=15)
	private java.lang.String acountInterestRate;
	/**利率启用日期*/
	@Excel(name="利率启用日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date interestRateDate;
	/**是否流水户*/
	@Excel(name="是否流水户",width=15)
	private java.lang.String isLiushui;
	/**在用产品*/
	@Excel(name="在用产品",width=15)
	private java.lang.String onlineProduct;
	/**大额支号付*/
	@Excel(name="大额支号付",width=15)
	private java.lang.String bigZhifu;
	/**是否可以提前结息*/
	@Excel(name="是否可以提前结息",width=15)
	private java.lang.String isPreEnd;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private java.lang.String contact;
	/**联系方式*/
	@Excel(name="联系方式",width=15)
	private java.lang.String contactType;
	/**行内联行行号*/
	@Excel(name="行内联行行号",width=15)
	private java.lang.String lineHanghao;
	/**存单金额*/
	@Excel(name="存单金额",width=15)
	private java.lang.String cundan;
	/**备注项*/
	@Excel(name="备注项",width=15)
	private java.lang.String remarks;
	/**到期日*/
	@Excel(name="到期日",width=15,format = "yyyy-MM-dd")
	private java.util.Date todate;
	/**托管行预留印鉴*/
	@Excel(name="托管行预留印鉴",width=15)
	private java.lang.String seal;
	/**账户简称*/
	@Excel(name="账户简称",width=15)
	private java.lang.String acountAbbreve;
	/**人名章*/
	@Excel(name="人名章",width=15)
	private java.lang.String zhang;
	/**经办人*/
	@Excel(name="经办人",width=15)
	private java.lang.String jingban;
	/**联系地址*/
	@Excel(name="联系地址",width=15)
	private java.lang.String contactAddr;
	/**座机*/
	@Excel(name="座机",width=15)
	private java.lang.String zuoJi;
	/**手机*/
	@Excel(name="手机",width=15)
	private java.lang.String mobilePhone;
	/**复核状态*/
	@Excel(name="复核状态",width=15)
	private java.lang.String fuheStatus;
	
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
	 *@return: java.lang.String  银行账号
	 */

	@Column(name ="ACCOUNT_NUMBER",nullable=true,length=32)
	public java.lang.String getAccountNumber(){
		return this.accountNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行账号
	 */
	public void setAccountNumber(java.lang.String accountNumber){
		this.accountNumber = accountNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行户名
	 */

	@Column(name ="ACOUNT_NAME",nullable=true,length=32)
	public java.lang.String getAcountName(){
		return this.acountName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行户名
	 */
	public void setAcountName(java.lang.String acountName){
		this.acountName = acountName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户银行全称
	 */

	@Column(name ="ACOUNT_FULLNAME",nullable=true,length=32)
	public java.lang.String getAcountFullname(){
		return this.acountFullname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行全称
	 */
	public void setAcountFullname(java.lang.String acountFullname){
		this.acountFullname = acountFullname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户银行简称
	 */

	@Column(name ="ACOUNT_SHORTNAME",nullable=true,length=32)
	public java.lang.String getAcountShortname(){
		return this.acountShortname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行简称
	 */
	public void setAcountShortname(java.lang.String acountShortname){
		this.acountShortname = acountShortname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户类型
	 */

	@Column(name ="ACOUNT_TYPE",nullable=true,length=32)
	public java.lang.String getAcountType(){
		return this.acountType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户类型
	 */
	public void setAcountType(java.lang.String acountType){
		this.acountType = acountType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开户日期
	 */

	@Column(name ="KAIHU_DATE",nullable=true)
	public java.util.Date getKaihuDate(){
		return this.kaihuDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开户日期
	 */
	public void setKaihuDate(java.util.Date kaihuDate){
		this.kaihuDate = kaihuDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户状态
	 */

	@Column(name ="ACOUNT_STATUS",nullable=true,length=32)
	public java.lang.String getAcountStatus(){
		return this.acountStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户状态
	 */
	public void setAcountStatus(java.lang.String acountStatus){
		this.acountStatus = acountStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户利率
	 */

	@Column(name ="ACOUNT_INTEREST_RATE",nullable=true,length=32)
	public java.lang.String getAcountInterestRate(){
		return this.acountInterestRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户利率
	 */
	public void setAcountInterestRate(java.lang.String acountInterestRate){
		this.acountInterestRate = acountInterestRate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  利率启用日期
	 */

	@Column(name ="INTEREST_RATE_DATE",nullable=true)
	public java.util.Date getInterestRateDate(){
		return this.interestRateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  利率启用日期
	 */
	public void setInterestRateDate(java.util.Date interestRateDate){
		this.interestRateDate = interestRateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否流水户
	 */

	@Column(name ="IS_LIUSHUI",nullable=true,length=32)
	public java.lang.String getIsLiushui(){
		return this.isLiushui;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否流水户
	 */
	public void setIsLiushui(java.lang.String isLiushui){
		this.isLiushui = isLiushui;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  在用产品
	 */

	@Column(name ="ONLINE_PRODUCT",nullable=true,length=32)
	public java.lang.String getOnlineProduct(){
		return this.onlineProduct;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  在用产品
	 */
	public void setOnlineProduct(java.lang.String onlineProduct){
		this.onlineProduct = onlineProduct;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  大额支号付
	 */

	@Column(name ="BIG_ZHIFU",nullable=true,length=32)
	public java.lang.String getBigZhifu(){
		return this.bigZhifu;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大额支号付
	 */
	public void setBigZhifu(java.lang.String bigZhifu){
		this.bigZhifu = bigZhifu;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否可以提前结息
	 */

	@Column(name ="IS_PRE_END",nullable=true,length=32)
	public java.lang.String getIsPreEnd(){
		return this.isPreEnd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否可以提前结息
	 */
	public void setIsPreEnd(java.lang.String isPreEnd){
		this.isPreEnd = isPreEnd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */

	@Column(name ="CONTACT",nullable=true,length=32)
	public java.lang.String getContact(){
		return this.contact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setContact(java.lang.String contact){
		this.contact = contact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系方式
	 */

	@Column(name ="CONTACT_TYPE",nullable=true,length=32)
	public java.lang.String getContactType(){
		return this.contactType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系方式
	 */
	public void setContactType(java.lang.String contactType){
		this.contactType = contactType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行内联行行号
	 */

	@Column(name ="LINE_HANGHAO",nullable=true,length=32)
	public java.lang.String getLineHanghao(){
		return this.lineHanghao;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行内联行行号
	 */
	public void setLineHanghao(java.lang.String lineHanghao){
		this.lineHanghao = lineHanghao;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  存单金额
	 */

	@Column(name ="CUNDAN",nullable=true,length=32)
	public java.lang.String getCundan(){
		return this.cundan;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  存单金额
	 */
	public void setCundan(java.lang.String cundan){
		this.cundan = cundan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注项
	 */

	@Column(name ="REMARKS",nullable=true,length=32)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注项
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */

	@Column(name ="TODATE",nullable=true)
	public java.util.Date getTodate(){
		return this.todate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日
	 */
	public void setTodate(java.util.Date todate){
		this.todate = todate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  托管行预留印鉴
	 */

	@Column(name ="SEAL",nullable=true,length=32)
	public java.lang.String getSeal(){
		return this.seal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  托管行预留印鉴
	 */
	public void setSeal(java.lang.String seal){
		this.seal = seal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户简称
	 */

	@Column(name ="ACOUNT_ABBREVE",nullable=true,length=32)
	public java.lang.String getAcountAbbreve(){
		return this.acountAbbreve;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户简称
	 */
	public void setAcountAbbreve(java.lang.String acountAbbreve){
		this.acountAbbreve = acountAbbreve;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人名章
	 */

	@Column(name ="ZHANG",nullable=true,length=32)
	public java.lang.String getZhang(){
		return this.zhang;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人名章
	 */
	public void setZhang(java.lang.String zhang){
		this.zhang = zhang;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经办人
	 */

	@Column(name ="JINGBAN",nullable=true,length=32)
	public java.lang.String getJingban(){
		return this.jingban;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经办人
	 */
	public void setJingban(java.lang.String jingban){
		this.jingban = jingban;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系地址
	 */

	@Column(name ="CONTACT_ADDR",nullable=true,length=50)
	public java.lang.String getContactAddr(){
		return this.contactAddr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系地址
	 */
	public void setContactAddr(java.lang.String contactAddr){
		this.contactAddr = contactAddr;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  座机
	 */

	@Column(name ="ZUOJI",nullable=true,length=32)
	public java.lang.String getZuoji(){
		return this.zuoJi;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  座机
	 */
	public void setZuoji(java.lang.String zuoji){
		this.zuoJi = zuoji;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机
	 */

	@Column(name ="MOBILE_PHONE",nullable=true,length=32)
	public java.lang.String getMobilePhone(){
		return this.mobilePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机
	 */
	public void setMobilePhone(java.lang.String mobilePhone){
		this.mobilePhone = mobilePhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  复核状态
	 */

	@Column(name ="FUHE_STATUS",nullable=true,length=32)
	public java.lang.String getFuheStatus(){
		return this.fuheStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  复核状态
	 */
	public void setFuheStatus(java.lang.String fuheStatus){
		this.fuheStatus = fuheStatus;
	}
}
