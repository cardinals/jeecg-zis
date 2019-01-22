package com.rms.address.entity;

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
 * @Description: RMS_地址管理
 * @author onlineGenerator
 * @date 2018-10-31 15:18:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rms_address_jeecg", schema = "")
@SuppressWarnings("serial")
public class AddressEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**addressId*/
	private java.lang.String addressId;
	/**全称*/
	@Excel(name="全称",width=15)
	private java.lang.String addrdetails;
	/**简称*/
	@Excel(name="简称",width=15)
	private java.lang.String addrshort;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private java.lang.String contact;
	/**电话*/
	@Excel(name="电话",width=15)
	private java.lang.String phone;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  addressId
	 */

	@Column(name ="ADDRESS_ID",nullable=false,scale=30,length=65)
	public java.lang.String getAddressId(){
		return this.addressId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  addressId
	 */
	public void setAddressId(java.lang.String addressId){
		this.addressId = addressId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  全称
	 */

	@Column(name ="ADDRDETAILS",nullable=true,length=255)
	public java.lang.String getAddrdetails(){
		return this.addrdetails;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  全称
	 */
	public void setAddrdetails(java.lang.String addrdetails){
		this.addrdetails = addrdetails;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简称
	 */

	@Column(name ="ADDRSHORT",nullable=true,length=255)
	public java.lang.String getAddrshort(){
		return this.addrshort;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简称
	 */
	public void setAddrshort(java.lang.String addrshort){
		this.addrshort = addrshort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */

	@Column(name ="CONTACT",nullable=true,length=255)
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
	 *@return: java.lang.String  电话
	 */

	@Column(name ="PHONE",nullable=true,length=255)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=255)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
}
