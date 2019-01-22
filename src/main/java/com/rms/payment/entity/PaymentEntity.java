package com.rms.payment.entity;

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
 * @Description: RMS_付款管理
 * @author onlineGenerator
 * @date 2018-11-03 10:12:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_payment_jeecg", schema = "")
@SuppressWarnings("serial")
public class PaymentEntity implements java.io.Serializable {
	/**付款编号*/
	private java.lang.String paymentId;
	/**综合编号*/
	private java.lang.String contractId;
	/**合同名称*/
	private java.lang.String contractName;
	/**报销单号*/
	@Excel(name="报销单号",width=15)
	private java.lang.String reimburseNo;
	/**付款事由*/
	@Excel(name="付款事由",width=15)
	private java.lang.String description;
	/**付款人*/
	@Excel(name="付款人",width=15,dicCode="user")
	private java.lang.String paidBy;
	/**付款金额*/
	@Excel(name="付款金额",width=15)
	private java.lang.String paymentAmount;
	/**付款时间*/
	@Excel(name="付款时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date paymentDate;
	/**创建时间*/
	private java.util.Date createTime;
	/**最后更新时间*/
	private java.util.Date lastUpdateTime;
	/**isreimbursed*/
	private java.lang.String isreimbursed;
	/**报销金额*/
	private java.lang.String reimburseAmount;
	/**创建人*/
	private java.lang.String createdBy;
	/**更新人*/
	private java.lang.String updatedBy;
	/**主键*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款编号
	 */

	@Column(name ="PAYMENT_ID",nullable=true,scale=30,length=65)
	public java.lang.String getPaymentId(){
		return this.paymentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款编号
	 */
	public void setPaymentId(java.lang.String paymentId){
		this.paymentId = paymentId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  综合编号
	 */

	@Column(name ="CONTRACT_ID",nullable=true,length=19)
	public java.lang.String getContractId(){
		return this.contractId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  综合编号
	 */
	public void setContractId(java.lang.String contractId){
		this.contractId = contractId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同名称
	 */

	@Column(name ="CONTRACT_NAME",nullable=true,length=32)
	public java.lang.String getContractName(){
		return this.contractName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同名称
	 */
	public void setContractName(java.lang.String contractName){
		this.contractName = contractName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报销单号
	 */

	@Column(name ="REIMBURSE_NO",nullable=true,length=255)
	public java.lang.String getReimburseNo(){
		return this.reimburseNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报销单号
	 */
	public void setReimburseNo(java.lang.String reimburseNo){
		this.reimburseNo = reimburseNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款事由
	 */

	@Column(name ="DESCRIPTION",nullable=true,length=255)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款事由
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款人
	 */

	@Column(name ="PAID_BY",nullable=true,length=19)
	public java.lang.String getPaidBy(){
		return this.paidBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款人
	 */
	public void setPaidBy(java.lang.String paidBy){
		this.paidBy = paidBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款金额
	 */

	@Column(name ="PAYMENT_AMOUNT",nullable=true,length=65)
	public java.lang.String getPaymentAmount(){
		return this.paymentAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款金额
	 */
	public void setPaymentAmount(java.lang.String paymentAmount){
		this.paymentAmount = paymentAmount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  付款时间
	 */

	@Column(name ="PAYMENT_DATE",nullable=true)
	public java.util.Date getPaymentDate(){
		return this.paymentDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  付款时间
	 */
	public void setPaymentDate(java.util.Date paymentDate){
		this.paymentDate = paymentDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最后更新时间
	 */

	@Column(name ="LAST_UPDATE_TIME",nullable=true)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后更新时间
	 */
	public void setLastUpdateTime(java.util.Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  isreimbursed
	 */

	@Column(name ="ISREIMBURSED",nullable=true,length=1)
	public java.lang.String getIsreimbursed(){
		return this.isreimbursed;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  isreimbursed
	 */
	public void setIsreimbursed(java.lang.String isreimbursed){
		this.isreimbursed = isreimbursed;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报销金额
	 */

	@Column(name ="REIMBURSE_AMOUNT",nullable=true,scale=30,length=65)
	public java.lang.String getReimburseAmount(){
		return this.reimburseAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报销金额
	 */
	public void setReimburseAmount(java.lang.String reimburseAmount){
		this.reimburseAmount = reimburseAmount;
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
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=32)
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
}
