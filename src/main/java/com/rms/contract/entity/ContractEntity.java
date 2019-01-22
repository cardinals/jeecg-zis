package com.rms.contract.entity;

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
 * @Description: RMS_合同管理
 * @author onlineGenerator
 * @date 2018-11-03 09:53:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_contract_jeecg", schema = "")
@SuppressWarnings("serial")
public class ContractEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**合同编号*/
	private java.lang.String contractId;
	/**综合编号*/
	@Excel(name="综合编号",width=15)
	private java.lang.String contractNo;
	/**合同名称*/
	@Excel(name="合同名称",width=15)
	private java.lang.String name;
	/**合同描述*/
	@Excel(name="合同描述",width=15)
	private java.lang.String description;
	/**合作方*/
	@Excel(name="合作方",width=15,dictTable ="rms_vendor_jeecg",dicCode ="VENDOR_ID",dicText ="vendor_name")
	private java.lang.String vendor;
	/**预算年份*/
	@Excel(name="预算年份",width=15,dicCode="budgetyear")
	private java.lang.String budgetYear;
	/**总额*/
	@Excel(name="总额",width=15)
	private java.lang.String totalAmount;
	/**已付款*/
	private java.lang.String paidAmount;
	/**签订人*/
	@Excel(name="签订人",width=15,dicCode="user")
	private java.lang.String contractedBy;
	/**签订日期*/
	@Excel(name="签订日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date contractDate;
	/**合同到期日*/
	@Excel(name="合同到期日",width=15,format = "yyyy-MM-dd")
	private java.util.Date expireDate;
	/**附件*/
	private java.lang.String attachments;
	/**创建日期*/
	private java.util.Date createTime;
	/**最后更新日期*/
	private java.util.Date lastUpdateTime;
	/**供应商*/
	private java.lang.String vendorName;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark1;
	/**备注2*/
	private java.lang.String remark2;
	/**创建人*/
	private java.lang.String createdBy;
	/**更新人*/
	private java.lang.String updatedBy;
	/**代理人*/
	private java.lang.String agent;
	/**合同文件*/
	@Excel(name="合同文件",width=15)
	private byte[] contractFile;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=true,length=32)
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
	 *@return: java.lang.String  合同编号
	 */

	@Column(name ="CONTRACT_ID",nullable=true,scale=30,length=65)
	public java.lang.String getContractId(){
		return this.contractId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编号
	 */
	public void setContractId(java.lang.String contractId){
		this.contractId = contractId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  综合编号
	 */

	@Column(name ="CONTRACT_NO",nullable=true,length=255)
	public java.lang.String getContractNo(){
		return this.contractNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  综合编号
	 */
	public void setContractNo(java.lang.String contractNo){
		this.contractNo = contractNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同名称
	 */

	@Column(name ="NAME",nullable=true,length=255)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同描述
	 */

	@Column(name ="DESCRIPTION",nullable=true,length=255)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合作方
	 */

	@Column(name ="VENDOR",nullable=true,length=19)
	public java.lang.String getVendor(){
		return this.vendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合作方
	 */
	public void setVendor(java.lang.String vendor){
		this.vendor = vendor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预算年份
	 */

	@Column(name ="BUDGET_YEAR",nullable=true,length=255)
	public java.lang.String getBudgetYear(){
		return this.budgetYear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预算年份
	 */
	public void setBudgetYear(java.lang.String budgetYear){
		this.budgetYear = budgetYear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总额
	 */

	@Column(name ="TOTAL_AMOUNT",nullable=true,scale=30,length=65)
	public java.lang.String getTotalAmount(){
		return this.totalAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总额
	 */
	public void setTotalAmount(java.lang.String totalAmount){
		this.totalAmount = totalAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  已付款
	 */

	@Column(name ="PAID_AMOUNT",nullable=true,scale=30,length=65)
	public java.lang.String getPaidAmount(){
		return this.paidAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  已付款
	 */
	public void setPaidAmount(java.lang.String paidAmount){
		this.paidAmount = paidAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签订人
	 */

	@Column(name ="CONTRACTED_BY",nullable=true,length=19)
	public java.lang.String getContractedBy(){
		return this.contractedBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签订人
	 */
	public void setContractedBy(java.lang.String contractedBy){
		this.contractedBy = contractedBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  签订日期
	 */

	@Column(name ="CONTRACT_DATE",nullable=false)
	public java.util.Date getContractDate(){
		return this.contractDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  签订日期
	 */
	public void setContractDate(java.util.Date contractDate){
		this.contractDate = contractDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同到期日
	 */

	@Column(name ="EXPIRE_DATE",nullable=true)
	public java.util.Date getExpireDate(){
		return this.expireDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同到期日
	 */
	public void setExpireDate(java.util.Date expireDate){
		this.expireDate = expireDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  附件
	 */

	@Column(name ="ATTACHMENTS",nullable=true,length=255)
	public java.lang.String getAttachments(){
		return this.attachments;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  附件
	 */
	public void setAttachments(java.lang.String attachments){
		this.attachments = attachments;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_TIME",nullable=false)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最后更新日期
	 */

	@Column(name ="LAST_UPDATE_TIME",nullable=true)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后更新日期
	 */
	public void setLastUpdateTime(java.util.Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="VENDOR_NAME",nullable=true,length=255)
	public java.lang.String getVendorName(){
		return this.vendorName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setVendorName(java.lang.String vendorName){
		this.vendorName = vendorName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK1",nullable=true,length=255)
	public java.lang.String getRemark1(){
		return this.remark1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark1(java.lang.String remark1){
		this.remark1 = remark1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注2
	 */

	@Column(name ="REMARK2",nullable=true,length=255)
	public java.lang.String getRemark2(){
		return this.remark2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注2
	 */
	public void setRemark2(java.lang.String remark2){
		this.remark2 = remark2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */

	@Column(name ="CREATED_BY",nullable=true,length=19)
	public java.lang.String getCreatedBy(){
		return this.createdBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreatedBy(java.lang.String createdBy){
		this.createdBy = createdBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */

	@Column(name ="UPDATED_BY",nullable=true,length=19)
	public java.lang.String getUpdatedBy(){
		return this.updatedBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdatedBy(java.lang.String updatedBy){
		this.updatedBy = updatedBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代理人
	 */

	@Column(name ="AGENT",nullable=true,length=19)
	public java.lang.String getAgent(){
		return this.agent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代理人
	 */
	public void setAgent(java.lang.String agent){
		this.agent = agent;
	}
	/**
	 *方法: 取得java.sql.Blob
	 *@return: java.sql.Blob  合同文件
	 */

	@Column(name ="CONTRACT_FILE",nullable=true)
	public byte[] getContractFile(){
		return this.contractFile;
	}

	/**
	 *方法: 设置java.sql.Blob
	 *@param: java.sql.Blob  合同文件
	 */
	public void setContractFile(byte[] contractFile){
		this.contractFile = contractFile;
	}
}
