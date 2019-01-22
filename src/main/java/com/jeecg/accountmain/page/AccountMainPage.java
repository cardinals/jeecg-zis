
package com.jeecg.accountmain.page;
import com.jeecg.accountmain.entity.AccountMainEntity;
import com.jeecg.raiseaccount.entity.RaiseAccountEntity;
import com.jeecg.accountdetail.entity.AccountDetailEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

/**   
 * @Title: Entity
 * @Description: 银行账户信息总表
 * @author onlineGenerator
 * @date 2018-06-19 14:48:51
 * @version V1.0   
 *
 */
public class AccountMainPage implements java.io.Serializable {
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
	/**大额支付号*/
    @Excel(name="大额支付号")
	private java.lang.String bigZhifu;
	/**是否可以提前结息*/
    @Excel(name="是否可以提前结息")
	private java.lang.String isPreEnd;
	/**联系人*/
    @Excel(name="联系人")
	private java.lang.String contact;
	/**联系方式*/
    @Excel(name="联系方式")
	private java.lang.String contactType;
	/**行内联行行号*/
    @Excel(name="行内联行行号")
	private java.lang.String lineHanghao;
	
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
	 *@return: java.lang.String  大额支付号
	 */
	public java.lang.String getBigZhifu(){
		return this.bigZhifu;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大额支付号
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
	 *@return: java.lang.String  联系方式
	 */
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

	/**保存-募集资金*/
    @ExcelCollection(name="募集资金")
	private List<RaiseAccountEntity> raiseAccountList = new ArrayList<RaiseAccountEntity>();
		public List<RaiseAccountEntity> getRaiseAccountList() {
		return raiseAccountList;
		}
		public void setRaiseAccountList(List<RaiseAccountEntity> raiseAccountList) {
		this.raiseAccountList = raiseAccountList;
		}
	/**保存-资金明细*/
    @ExcelCollection(name="资金明细")
	private List<AccountDetailEntity> accountDetailList = new ArrayList<AccountDetailEntity>();
		public List<AccountDetailEntity> getAccountDetailList() {
		return accountDetailList;
		}
		public void setAccountDetailList(List<AccountDetailEntity> accountDetailList) {
		this.accountDetailList = accountDetailList;
		}
}
