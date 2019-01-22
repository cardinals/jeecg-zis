
package com.jeecg.bankaccount.page;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import com.jeecg.accountratechanges.entity.AccountRateChangesEntity;
import com.jeecg.raiseaccountuse.entity.RaiseAccountUseEntity;

/**   
 * @Title: Entity
 * @Description: 银行账户信息总表
 * @author onlineGenerator
 * @date 2018-07-14 14:14:42
 * @version V1.0   
 *
 */
public class BankAccountPage implements java.io.Serializable {
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
    @Excel(name="银行账号")
	private java.lang.String accountNumber;
	/**银行户名*/
    @Excel(name="银行户名")
	private java.lang.String acountName;
	/**账户简称*/
    @Excel(name="账户简称")
	private java.lang.String acountAbbreve;
	/**开户银行全称*/
    @Excel(name="开户银行全称")
	private java.lang.String acountFullname;
	/**开户银行简称*/
    @Excel(name="开户银行简称")
	private java.lang.String acountShortname;
	/**账户类型*/
    @Excel(name="账户类型")
	private java.lang.String acountType;
	/**开户日期*/
    @Excel(name="开户日期",format = "yyyy-MM-dd")
	private java.util.Date kaihuDate;
	/**账户状态*/
    @Excel(name="账户状态")
	private java.lang.String acountStatus;
	/**账户利率*/
    @Excel(name="账户利率")
	private java.lang.String acountInterestRate;
	/**利率启用日期*/
    @Excel(name="利率启用日期",format = "yyyy-MM-dd")
	private java.util.Date interestRateDate;
	/**是否流水户*/
    @Excel(name="是否流水户")
	private java.lang.String isLiushui;
	/**在用产品*/
    @Excel(name="在用产品")
	private java.lang.String onlineProduct;
	/**大额支号付*/
    @Excel(name="大额支号付")
	private java.lang.String bigZhifu;
	/**是否可以提前结息*/
    @Excel(name="是否可以提前结息")
	private java.lang.String isPreEnd;
	/**经办人*/
    @Excel(name="经办人")
	private java.lang.String jingban;
	/**联系人*/
    @Excel(name="联系人")
	private java.lang.String contact;
	/**联系地址*/
    @Excel(name="联系地址")
	private java.lang.String contactAddr;
    
    @Excel(name="联系方式-座机",width=15)
	private java.lang.String zuoJi;
    
    @Excel(name="联系方式-手机",width=15)
   	private java.lang.String mobilePhone;
    
    
	/**联系方式*/
   /* @Excel(name="联系方式")
	private java.lang.String contactType;*/
	public java.lang.String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(java.lang.String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public java.lang.String getZuoJi() {
		return zuoJi;
	}

	public void setZuoJi(java.lang.String zuoJi) {
		this.zuoJi = zuoJi;
	}

	/**行内联行行号*/
    @Excel(name="行内联行行号")
	private java.lang.String lineHanghao;
	/**托管行预留印鉴*/
    @Excel(name="托管行预留印鉴")
	private java.lang.String seal;
	/**人名章*/
    @Excel(name="人名章")
	private java.lang.String zhang;
	/**存单金额*/
    @Excel(name="存单金额")
	//private java.lang.Double cundan;//excel导入金额有问题-180912
    private java.lang.String cundan;
	/**到期日*/
    @Excel(name="到期日",format = "yyyy-MM-dd")
	private java.util.Date todate;
	/**备注项*/
    @Excel(name="备注项")
	private java.lang.String remarks;
    
   
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
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
	 *@return: java.lang.String  账户简称
	 */
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
	 *@return: java.lang.String  开户银行全称
	 */
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
	 *@return: java.lang.String  经办人
	 */
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
	 *@return: java.lang.String  联系人
	 */
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
	 *@return: java.lang.String  联系地址
	 */
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
	 *@return: java.lang.String  联系方式
	 */
	/*public java.lang.String getContactType(){
		return this.contactType;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系方式
	 *//*
	public void setContactType(java.lang.String contactType){
		this.contactType = contactType;
	}*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行内联行行号
	 */
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  托管行预留印鉴
	 */
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
	 *@return: java.lang.String  人名章
	 */
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  存单金额
	 */
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */
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
	 *@return: java.lang.String  备注项
	 */
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

	/**账户利率变化明细表*/
    @ExcelCollection(name="账户利率变化明细表")
	private List<AccountRateChangesEntity> accountRateChangesList = new ArrayList<AccountRateChangesEntity>();
		public List<AccountRateChangesEntity> getAccountRateChangesList() {
		return accountRateChangesList;
		}
		public void setAccountRateChangesList(List<AccountRateChangesEntity> accountRateChangesList) {
		this.accountRateChangesList = accountRateChangesList;
		}
	/**账户使用情况*/
    @ExcelCollection(name="账户使用情况")
	private List<RaiseAccountUseEntity> raiseAccountUseList = new ArrayList<RaiseAccountUseEntity>();
		public List<RaiseAccountUseEntity> getRaiseAccountUseList() {
		return raiseAccountUseList;
		}
		public void setRaiseAccountUseList(List<RaiseAccountUseEntity> raiseAccountUseList) {
		this.raiseAccountUseList = raiseAccountUseList;
		}
}
