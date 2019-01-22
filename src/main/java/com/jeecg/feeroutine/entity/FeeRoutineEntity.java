package com.jeecg.feeroutine.entity;

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
 * @Description: 部门日常费用
 * @author onlineGenerator
 * @date 2018-08-17 17:33:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fee_routine", schema = "")
@SuppressWarnings("serial")
public class FeeRoutineEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**报销日期*/
	@Excel(name="报销日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date feeDate;
	/**金额*/
	@Excel(name="金额",width=15)
	private BigDecimal amount;
	/**收款方*/
	@Excel(name="收款方",width=15)
	private java.lang.String payee;
	/**付款说明明细*/
	@Excel(name="付款说明明细",width=15)
	private java.lang.String description;
	/**费用类型*/
	@Excel(name="费用类型",width=15)
	private java.lang.String feeType;
	/**费用性质*/
	@Excel(name="费用性质",width=15)
	private java.lang.String feeXingzhi;
	/**发票*/
	@Excel(name="发票",width=15,dicCode="FPYN")
	private java.lang.String feeFapiao;
	/**经办人*/
	@Excel(name="经办人",width=15)
	private java.lang.String feeJingbanren;
	/**updateBy*/
	private java.lang.String updateby;
	/**updateDate*/
	private java.lang.String updatedate;
	/**updateName*/
	private java.lang.String updatename;
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  报销日期
	 */

	@Column(name ="FEE_DATE",nullable=true,length=32)
	public java.util.Date getFeeDate(){
		return this.feeDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  报销日期
	 */
	public void setFeeDate(java.util.Date feeDate){
		this.feeDate = feeDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金额
	 */

	@Column(name ="AMOUNT",nullable=true,length=32)
	public BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金额
	 */
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款方
	 */

	@Column(name ="PAYEE",nullable=true,length=32)
	public java.lang.String getPayee(){
		return this.payee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款方
	 */
	public void setPayee(java.lang.String payee){
		this.payee = payee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款说明明细
	 */

	@Column(name ="DESCRIPTION",nullable=true,length=800)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款说明明细
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用类型
	 */

	@Column(name ="FEE_TYPE",nullable=true,length=32)
	public java.lang.String getFeeType(){
		return this.feeType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用类型
	 */
	public void setFeeType(java.lang.String feeType){
		this.feeType = feeType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用性质
	 */

	@Column(name ="FEE_XINGZHI",nullable=true,length=32)
	public java.lang.String getFeeXingzhi(){
		return this.feeXingzhi;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用性质
	 */
	public void setFeeXingzhi(java.lang.String feeXingzhi){
		this.feeXingzhi = feeXingzhi;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票
	 */

	@Column(name ="FEE_FAPIAO",nullable=true,length=32)
	public java.lang.String getFeeFapiao(){
		return this.feeFapiao;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票
	 */
	public void setFeeFapiao(java.lang.String feeFapiao){
		this.feeFapiao = feeFapiao;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经办人
	 */

	@Column(name ="FEE_JINGBANREN",nullable=true,length=32)
	public java.lang.String getFeeJingbanren(){
		return this.feeJingbanren;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经办人
	 */
	public void setFeeJingbanren(java.lang.String feeJingbanren){
		this.feeJingbanren = feeJingbanren;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  updateBy
	 */

	@Column(name ="UPDATEBY",nullable=true,length=32)
	public java.lang.String getUpdateby(){
		return this.updateby;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  updateBy
	 */
	public void setUpdateby(java.lang.String updateby){
		this.updateby = updateby;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  updateDate
	 */

	@Column(name ="UPDATEDATE",nullable=true,length=32)
	public java.lang.String getUpdatedate(){
		return this.updatedate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  updateDate
	 */
	public void setUpdatedate(java.lang.String updatedate){
		this.updatedate = updatedate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  updateName
	 */

	@Column(name ="UPDATENAME",nullable=true,length=32)
	public java.lang.String getUpdatename(){
		return this.updatename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  updateName
	 */
	public void setUpdatename(java.lang.String updatename){
		this.updatename = updatename;
	}
}
