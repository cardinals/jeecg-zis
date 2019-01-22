package com.jeecg.sysparams.entity;

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
 * @Description: 系统参数
 * @author onlineGenerator
 * @date 2018-05-31 17:32:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_sys_params", schema = "")
@SuppressWarnings("serial")
public class TSSysParamsEntity implements java.io.Serializable {
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
	/**模块名称*/
	@Excel(name="模块名称",width=15)
	private java.lang.String cClass;
	/**参数名称*/
	@Excel(name="参数名称",width=15)
	private java.lang.String citem;
	/**参数值*/
	@Excel(name="参数值",width=15)
	private java.lang.String cValue;
	/**是否修改*/
	@Excel(name="是否修改",width=15)
	private java.lang.String cModify;
	/**参数描述*/
	@Excel(name="参数描述",width=15)
	private java.lang.String cDescribe;
	/**标志*/
	@Excel(name="标志",width=15)
	private java.lang.String cCryptflag;
	/**标志位*/
	@Excel(name="标志位",width=15)
	private java.lang.String cType;
	/**取值范围*/
	@Excel(name="取值范围",width=15)
	private java.lang.String cValuebound;
	/**类别*/
	@Excel(name="类别",width=15)
	private java.lang.String cShowclass;
	/**子类*/
	@Excel(name="子类",width=15)
	private java.lang.String cShowsubclass;
	
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
	 *@return: java.lang.String  模块名称
	 */

	@Column(name ="C_CLASS",nullable=true,length=32)
	public java.lang.String getCClass(){
		return this.cClass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块名称
	 */
	public void setCClass(java.lang.String cClass){
		this.cClass = cClass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数名称
	 */

	@Column(name ="CITEM",nullable=true,length=32)
	public java.lang.String getCitem(){
		return this.citem;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数名称
	 */
	public void setCitem(java.lang.String citem){
		this.citem = citem;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数值
	 */

	@Column(name ="C_VALUE",nullable=true,length=32)
	public java.lang.String getCValue(){
		return this.cValue;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数值
	 */
	public void setCValue(java.lang.String cValue){
		this.cValue = cValue;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否修改
	 */

	@Column(name ="C_MODIFY",nullable=true,length=32)
	public java.lang.String getCModify(){
		return this.cModify;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否修改
	 */
	public void setCModify(java.lang.String cModify){
		this.cModify = cModify;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数描述
	 */

	@Column(name ="C_DESCRIBE",nullable=true,length=32)
	public java.lang.String getCDescribe(){
		return this.cDescribe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数描述
	 */
	public void setCDescribe(java.lang.String cDescribe){
		this.cDescribe = cDescribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标志
	 */

	@Column(name ="C_CRYPTFLAG",nullable=true,length=32)
	public java.lang.String getCCryptflag(){
		return this.cCryptflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标志
	 */
	public void setCCryptflag(java.lang.String cCryptflag){
		this.cCryptflag = cCryptflag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标志位
	 */

	@Column(name ="C_TYPE",nullable=true,length=32)
	public java.lang.String getCType(){
		return this.cType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标志位
	 */
	public void setCType(java.lang.String cType){
		this.cType = cType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  取值范围
	 */

	@Column(name ="C_VALUEBOUND",nullable=true,length=32)
	public java.lang.String getCValuebound(){
		return this.cValuebound;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  取值范围
	 */
	public void setCValuebound(java.lang.String cValuebound){
		this.cValuebound = cValuebound;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类别
	 */

	@Column(name ="C_SHOWCLASS",nullable=true,length=32)
	public java.lang.String getCShowclass(){
		return this.cShowclass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类别
	 */
	public void setCShowclass(java.lang.String cShowclass){
		this.cShowclass = cShowclass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  子类
	 */

	@Column(name ="C_SHOWSUBCLASS",nullable=true,length=32)
	public java.lang.String getCShowsubclass(){
		return this.cShowsubclass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  子类
	 */
	public void setCShowsubclass(java.lang.String cShowsubclass){
		this.cShowsubclass = cShowsubclass;
	}
}
