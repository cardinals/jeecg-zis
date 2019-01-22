package com.rms.assethistory.entity;

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
 * @Description: RMS_历史资产表
 * @author onlineGenerator
 * @date 2018-11-05 16:11:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_asset_history_jeecg", schema = "")
@SuppressWarnings("serial")
public class AssetHistoryEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**历史记录ID*/
	@Excel(name="历史记录ID",width=15)
	private java.lang.String assetHistoryId;
	/**操作*/
	@Excel(name="操作",width=15,dicCode="assetOpera")
	private java.lang.String operation;
	/**唯一键*/
	@Excel(name="唯一键",width=15)
	private java.lang.String assetId;
	/**公司*/
	@Excel(name="公司",width=15)
	private java.lang.String company;
	/**资产类型*/
	@Excel(name="资产类型",width=15)
	private java.lang.String maintype;
	/**资产编号*/
	@Excel(name="资产编号",width=15)
	private java.lang.String assetNo;
	/**地区*/
	@Excel(name="地区",width=15)
	private java.lang.String location;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private java.lang.String vendor;
	/**品牌型号*/
	@Excel(name="品牌型号",width=15)
	private java.lang.String assetType;
	/**设备参数*/
	@Excel(name="设备参数",width=15)
	private java.lang.String config;
	/**电脑型号*/
	@Excel(name="电脑型号",width=15)
	private java.lang.String pcType;
	/**购置日期*/
	@Excel(name="购置日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date purchaseDate;
	/**购置单价*/
	@Excel(name="购置单价",width=15)
	private java.lang.String price;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createTime;
	/**最后修改时间*/
	@Excel(name="最后修改时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date lastUpdateTime;
	/**使用人*/
	@Excel(name="使用人",width=15)
	private java.lang.String employeeId;
	/**创建人*/
	@Excel(name="创建人",width=15,dicCode="user")
	private java.lang.String createdBy;
	/**修改人*/
	@Excel(name="修改人",width=15,dicCode="user")
	private java.lang.String updatedBy;
	/**操作人*/
	@Excel(name="操作人",width=15)
	private java.lang.String operationBy;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=20)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=32)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=32)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  历史记录ID
	 */

	@Column(name ="ASSET_HISTORY_ID",nullable=true,length=19)
	public java.lang.String getAssetHistoryId(){
		return this.assetHistoryId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  历史记录ID
	 */
	public void setAssetHistoryId(java.lang.String assetHistoryId){
		this.assetHistoryId = assetHistoryId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作
	 */

	@Column(name ="OPERATION",nullable=true,length=1)
	public java.lang.String getOperation(){
		return this.operation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作
	 */
	public void setOperation(java.lang.String operation){
		this.operation = operation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一键
	 */

	@Column(name ="ASSET_ID",nullable=true,length=19)
	public java.lang.String getAssetId(){
		return this.assetId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  唯一键
	 */
	public void setAssetId(java.lang.String assetId){
		this.assetId = assetId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司
	 */

	@Column(name ="COMPANY",nullable=true,length=1)
	public java.lang.String getCompany(){
		return this.company;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司
	 */
	public void setCompany(java.lang.String company){
		this.company = company;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产类型
	 */

	@Column(name ="MAINTYPE",nullable=true,length=1)
	public java.lang.String getMaintype(){
		return this.maintype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产类型
	 */
	public void setMaintype(java.lang.String maintype){
		this.maintype = maintype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产编号
	 */

	@Column(name ="ASSET_NO",nullable=true,length=255)
	public java.lang.String getAssetNo(){
		return this.assetNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产编号
	 */
	public void setAssetNo(java.lang.String assetNo){
		this.assetNo = assetNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地区
	 */

	@Column(name ="LOCATION",nullable=true,length=1)
	public java.lang.String getLocation(){
		return this.location;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区
	 */
	public void setLocation(java.lang.String location){
		this.location = location;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="VENDOR",nullable=true,length=255)
	public java.lang.String getVendor(){
		return this.vendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setVendor(java.lang.String vendor){
		this.vendor = vendor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌型号
	 */

	@Column(name ="ASSET_TYPE",nullable=true,length=255)
	public java.lang.String getAssetType(){
		return this.assetType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌型号
	 */
	public void setAssetType(java.lang.String assetType){
		this.assetType = assetType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备参数
	 */

	@Column(name ="CONFIG",nullable=true,length=255)
	public java.lang.String getConfig(){
		return this.config;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备参数
	 */
	public void setConfig(java.lang.String config){
		this.config = config;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电脑型号
	 */

	@Column(name ="PC_TYPE",nullable=true,length=255)
	public java.lang.String getPcType(){
		return this.pcType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电脑型号
	 */
	public void setPcType(java.lang.String pcType){
		this.pcType = pcType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  购置日期
	 */

	@Column(name ="PURCHASE_DATE",nullable=true)
	public java.util.Date getPurchaseDate(){
		return this.purchaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  购置日期
	 */
	public void setPurchaseDate(java.util.Date purchaseDate){
		this.purchaseDate = purchaseDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  购置单价
	 */

	@Column(name ="PRICE",nullable=true,scale=30,length=65)
	public java.lang.String getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购置单价
	 */
	public void setPrice(java.lang.String price){
		this.price = price;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_TIME",nullable=true)
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
	 *@return: java.util.Date  最后修改时间
	 */

	@Column(name ="LAST_UPDATE_TIME",nullable=true)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后修改时间
	 */
	public void setLastUpdateTime(java.util.Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用人
	 */

	@Column(name ="EMPLOYEE_ID",nullable=true,length=255)
	public java.lang.String getEmployeeId(){
		return this.employeeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用人
	 */
	public void setEmployeeId(java.lang.String employeeId){
		this.employeeId = employeeId;
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
	 *@return: java.lang.String  修改人
	 */

	@Column(name ="UPDATED_BY",nullable=true,length=19)
	public java.lang.String getUpdatedBy(){
		return this.updatedBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdatedBy(java.lang.String updatedBy){
		this.updatedBy = updatedBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作人
	 */

	@Column(name ="OPERATION_BY",nullable=true,length=32)
	public java.lang.String getOperationBy(){
		return this.operationBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作人
	 */
	public void setOperationBy(java.lang.String operationBy){
		this.operationBy = operationBy;
	}
}
