package com.jeecg.pf.entity;

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
 * @Description: 公募基本表
 * @author onlineGenerator
 * @date 2019-01-14 15:54:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pf_base", schema = "")
@SuppressWarnings("serial")
public class PfBaseEntity implements java.io.Serializable {
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
	/**名称*/
	@Excel(name="名称",width=15)
	private java.lang.String name;
	/**基金简称*/
	@Excel(name="基金简称",width=15)
	private java.lang.String shortName;
	/**成立日期*/
	@Excel(name="成立日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date estaDate;
	/**托管人*/
	//@Excel(name="托管人",width=15,dictTable ="tuoGuan",dicCode ="tuoGuan",dicText ="tuoGuan")
	
	@Excel(name="托管人",width=15)
	private java.lang.String tuoguanPerson;
	/**基金代码*/
	@Excel(name="基金代码",width=15)
	private java.lang.String jijinCode;
	/**批复日期*/
	@Excel(name="批复日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date pifuDate;
	/**基金经理*/
	@Excel(name="基金经理",width=15)
	private java.lang.String jijinManager;
	/**产品状态*/
	@Excel(name="产品状态",width=15,dictTable ="pro_state",dicCode ="pro_state",dicText ="pro_state")
	private java.lang.String productState;
	/**历任基金经理*/
	@Excel(name="历任基金经理",width=15)
	private java.lang.String jijinManagerHis;
	/**成立规模*/
	@Excel(name="成立规模",width=15)
	private java.lang.String foundScale;
	/**发行开始日期*/
	@Excel(name="发行开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date faxingDate;
	/**发行截止日期*/
	@Excel(name="发行截止日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date faxingDateto;
	/**律师事务所*/
	@Excel(name="律师事务所",width=15,dictTable ="lvshi",dicCode ="lvshi",dicText ="lvshi")
	private java.lang.String lawOffice;
	/**期货公司*/
	@Excel(name="期货公司",width=15)
	private java.lang.String futuresCompany;
	/**是否为机构定制*/
	@Excel(name="是否为机构定制",width=15)
	private java.lang.String isDingzhi;
	/**风险等级*/
	@Excel(name="风险等级",width=15,dictTable ="risk_level",dicCode ="risk_level",dicText ="risk_level")
	private java.lang.String riskLevel;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remarks;
	
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
	 *@return: java.lang.String  名称
	 */

	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  基金简称
	 */

	@Column(name ="SHORT_NAME",nullable=true,length=32)
	public java.lang.String getShortName(){
		return this.shortName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  基金简称
	 */
	public void setShortName(java.lang.String shortName){
		this.shortName = shortName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  成立日期
	 */

	@Column(name ="ESTA_DATE",nullable=true,length=32)
	public java.util.Date getEstaDate(){
		return this.estaDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  成立日期
	 */
	public void setEstaDate(java.util.Date estaDate){
		this.estaDate = estaDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  托管人
	 */

	@Column(name ="TUOGUAN_PERSON",nullable=true,length=32)
	public java.lang.String getTuoguanPerson(){
		return this.tuoguanPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  托管人
	 */
	public void setTuoguanPerson(java.lang.String tuoguanPerson){
		this.tuoguanPerson = tuoguanPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  基金代码
	 */

	@Column(name ="JIJIN_CODE",nullable=true,length=32)
	public java.lang.String getJijinCode(){
		return this.jijinCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  基金代码
	 */
	public void setJijinCode(java.lang.String jijinCode){
		this.jijinCode = jijinCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  批复日期
	 */

	@Column(name ="PIFU_DATE",nullable=true,length=32)
	public java.util.Date getPifuDate(){
		return this.pifuDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  批复日期
	 */
	public void setPifuDate(java.util.Date pifuDate){
		this.pifuDate = pifuDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  基金经理
	 */

	@Column(name ="JIJIN_MANAGER",nullable=true,length=32)
	public java.lang.String getJijinManager(){
		return this.jijinManager;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  基金经理
	 */
	public void setJijinManager(java.lang.String jijinManager){
		this.jijinManager = jijinManager;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品状态
	 */

	@Column(name ="PRODUCT_STATE",nullable=true,length=32)
	public java.lang.String getProductState(){
		return this.productState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品状态
	 */
	public void setProductState(java.lang.String productState){
		this.productState = productState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  历任基金经理
	 */

	@Column(name ="JIJIN_MANAGER_HIS",nullable=true,length=32)
	public java.lang.String getJijinManagerHis(){
		return this.jijinManagerHis;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  历任基金经理
	 */
	public void setJijinManagerHis(java.lang.String jijinManagerHis){
		this.jijinManagerHis = jijinManagerHis;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成立规模
	 */

	@Column(name ="FOUND_SCALE",nullable=true,length=32)
	public java.lang.String getFoundScale(){
		return this.foundScale;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成立规模
	 */
	public void setFoundScale(java.lang.String foundScale){
		this.foundScale = foundScale;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发行开始日期
	 */

	@Column(name ="FAXING_DATE",nullable=true,length=32)
	public java.util.Date getFaxingDate(){
		return this.faxingDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发行开始日期
	 */
	public void setFaxingDate(java.util.Date faxingDate){
		this.faxingDate = faxingDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发行截止日期
	 */

	@Column(name ="FAXING_DATETO",nullable=true,length=32)
	public java.util.Date getFaxingDateto(){
		return this.faxingDateto;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发行截止日期
	 */
	public void setFaxingDateto(java.util.Date faxingDateto){
		this.faxingDateto = faxingDateto;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  律师事务所
	 */

	@Column(name ="LAW_OFFICE",nullable=true,length=32)
	public java.lang.String getLawOffice(){
		return this.lawOffice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  律师事务所
	 */
	public void setLawOffice(java.lang.String lawOffice){
		this.lawOffice = lawOffice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期货公司
	 */

	@Column(name ="FUTURES_COMPANY",nullable=true,length=32)
	public java.lang.String getFuturesCompany(){
		return this.futuresCompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期货公司
	 */
	public void setFuturesCompany(java.lang.String futuresCompany){
		this.futuresCompany = futuresCompany;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否为机构定制
	 */

	@Column(name ="IS_DINGZHI",nullable=true,length=32)
	public java.lang.String getIsDingzhi(){
		return this.isDingzhi;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否为机构定制
	 */
	public void setIsDingzhi(java.lang.String isDingzhi){
		this.isDingzhi = isDingzhi;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  风险等级
	 */

	@Column(name ="RISK_LEVEL",nullable=true,length=32)
	public java.lang.String getRiskLevel(){
		return this.riskLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  风险等级
	 */
	public void setRiskLevel(java.lang.String riskLevel){
		this.riskLevel = riskLevel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARKS",nullable=true,length=32)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
}
