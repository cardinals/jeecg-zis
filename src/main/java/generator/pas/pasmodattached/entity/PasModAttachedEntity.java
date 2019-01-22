package generator.pas.pasmodattached.entity;

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
 * @Description: 绩效考评特殊人员附加表
 * @author onlineGenerator
 * @date 2018-11-15 15:48:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pas_mod_attached", schema = "")
@SuppressWarnings("serial")
public class PasModAttachedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**用户名*/
	@Excel(name="用户名",width=15)
	private java.lang.String username;
	/**真实姓名*/
	@Excel(name="真实姓名",width=15)
	private java.lang.String realname;
	/**角色代码*/
	@Excel(name="角色代码",width=15)
	private java.lang.String rolecode;
	/**角色名称*/
	@Excel(name="角色名称",width=15)
	private java.lang.String rolename;
	/**部门代码*/
	@Excel(name="部门代码",width=15)
	private java.lang.String orgCode;
	/**部门名称*/
	@Excel(name="部门名称",width=15)
	private java.lang.String departName;
	
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
	 *@return: java.lang.String  用户名
	 */

	@Column(name ="USERNAME",nullable=true,length=45)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  真实姓名
	 */

	@Column(name ="REALNAME",nullable=true,length=45)
	public java.lang.String getRealname(){
		return this.realname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  真实姓名
	 */
	public void setRealname(java.lang.String realname){
		this.realname = realname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  角色代码
	 */

	@Column(name ="ROLECODE",nullable=true,length=45)
	public java.lang.String getRolecode(){
		return this.rolecode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  角色代码
	 */
	public void setRolecode(java.lang.String rolecode){
		this.rolecode = rolecode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  角色名称
	 */

	@Column(name ="ROLENAME",nullable=true,length=45)
	public java.lang.String getRolename(){
		return this.rolename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  角色名称
	 */
	public void setRolename(java.lang.String rolename){
		this.rolename = rolename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门代码
	 */

	@Column(name ="ORG_CODE",nullable=true,length=45)
	public java.lang.String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门代码
	 */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门名称
	 */

	@Column(name ="DEPART_NAME",nullable=true,length=45)
	public java.lang.String getDepartName(){
		return this.departName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门名称
	 */
	public void setDepartName(java.lang.String departName){
		this.departName = departName;
	}
}
