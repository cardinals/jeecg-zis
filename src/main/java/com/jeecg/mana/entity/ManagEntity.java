package com.jeecg.mana.entity;

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
 * @Description: 附表
 * @author onlineGenerator
 * @date 2019-01-07 11:49:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "manag", schema = "")
@SuppressWarnings("serial")
public class ManagEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private java.lang.String name;
	/**年纪*/
	@Excel(name="年纪",width=15)
	private java.lang.String age;
	/**性别*/
	@Excel(name="性别",width=15)
	private java.lang.String sex;
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
	 *@return: java.lang.String  年纪
	 */

	@Column(name ="AGE",nullable=true,length=32)
	public java.lang.String getAge(){
		return this.age;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年纪
	 */
	public void setAge(java.lang.String age){
		this.age = age;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */

	@Column(name ="SEX",nullable=true,length=32)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
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
