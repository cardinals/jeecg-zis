package com.jeecg.pfhismanager.entity;

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
 * @Description: 历任基金经理
 * @author onlineGenerator
 * @date 2019-01-17 15:51:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pf_his_manager", schema = "")
@SuppressWarnings("serial")
public class PfHisManagerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private java.lang.String name;
	/**中文姓名*/
	@Excel(name="中文姓名",width=15)
	private java.lang.String nameZh;
	/**开始时间*/
	@Excel(name="开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date startTime;
	/**结束时间*/
	@Excel(name="结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date emdTime;
	/**开始*/
	@Excel(name="开始",width=15)
	private java.lang.String startString;
	/**结束*/
	@Excel(name="结束",width=15)
	private java.lang.String endString;
	/**外键*/
	@Excel(name="外键",width=15)
	private java.lang.String foreignKey;
	
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
	 *@return: java.lang.String  姓名
	 */

	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中文姓名
	 */

	@Column(name ="NAME_ZH",nullable=true,length=32)
	public java.lang.String getNameZh(){
		return this.nameZh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中文姓名
	 */
	public void setNameZh(java.lang.String nameZh){
		this.nameZh = nameZh;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */

	@Column(name ="START_TIME",nullable=true,length=32)
	public java.util.Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */

	@Column(name ="EMD_TIME",nullable=true,length=32)
	public java.util.Date getEmdTime(){
		return this.emdTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEmdTime(java.util.Date emdTime){
		this.emdTime = emdTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开始
	 */

	@Column(name ="START_STRING",nullable=true,length=32)
	public java.lang.String getStartString(){
		return this.startString;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开始
	 */
	public void setStartString(java.lang.String startString){
		this.startString = startString;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结束
	 */

	@Column(name ="END_STRING",nullable=true,length=32)
	public java.lang.String getEndString(){
		return this.endString;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结束
	 */
	public void setEndString(java.lang.String endString){
		this.endString = endString;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */

	@Column(name ="FOREIGN_KEY",nullable=true,length=32)
	public java.lang.String getForeignKey(){
		return this.foreignKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setForeignKey(java.lang.String foreignKey){
		this.foreignKey = foreignKey;
	}
}
