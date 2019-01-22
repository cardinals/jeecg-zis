package org.jeecgframework.web.system.job;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.web.cgform.util.DataHandlerUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.bankaccount.controller.BankAccountController;
import com.jeecg.dianzihu.entity.DianzihuEntity;
import com.jeecg.dianzihu.service.DianzihuServiceI;
import com.jeecg.reportemail.entity.ReportEmailEntity;
import com.jeecg.reportemail.service.ReportEmailServiceI;
@Service("cushionFunds")
public class CushionFunds extends CommonServiceImpl implements Job {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BankAccountController.class);

	private final String dbKey = "ctptadb";
	@Autowired
	private DianzihuServiceI DianzihuService;
	@Autowired
	private ReportEmailServiceI reportEmailService;
	/**
	 * 正式环境sql
	 */
	
   private final String qurenSql="select t.c_value from  hsord.tsysparameter@dbl_tadb t where t.c_item ='SYSDATE'";
		//private final String jiaoyiSql="select t.c_value from  hsord.tsysparameter@dbl_tadb t where t.c_item ='LASTSYSDATE'";
 //上一个交易日
 	private final String lastBuyDaySql ="select t.d_date,t.l_workflag  from   ta4.topenday@dbl_tadb  t where t.d_date = ? ";
 	
		private final String totalAmountSql = " select sum(t.f_confirmbalance) "
					+"   from ta4.tconfirm@dbl_tadb t "
					+"  where t.d_cdate = ? "
					+"   and t.c_tradeacco in ('27811','27833','35822','35823','35824','35825','35826','35827','35828','35829','35830','35831','35832','35833','35834','35835','35836') "
					+"    and t.c_outbusinflag = '024' "
					+"    and t.c_status = '1' "
					+"  group by t.d_cdate ";
		
private final String principalMoneySql = "select sum(t.en_occurbala) "
		       +"  from hsord.tyebpayoutrequest@dbl_tadb t "
		       +"   where t.vc_requestdate= ? "
				+" 	   and t.vc_dzhtradeacco in ('27811','27833','35822','35823','35824','35825','35826','35827','35828','35829','35830','35831','35832','35833','35834','35835','35836') "
				+" 	   and t.c_state = '2' "
				+" 	 group by t.vc_requestdate";

	/**
	 * 测试环境sql
	 */
	/*private final String qurenSql="select t.c_value from  zrdd11.tsysparameter t where t.c_item ='SYSDATE'";
	//上一个交易日
	private final String lastBuyDaySql ="select t.d_date,t.l_workflag  from   ta4.topenday  t where t.d_date = ? ";
	
	private final String jiaoyiSql="select t.c_value from  zrdd11.tsysparameter t where t.c_item ='LASTSYSDATE'";
	private final String totalAmountSql = " select sum(t.f_confirmbalance) "
					 +" from ta00.tconfirm t "
					 +" where t.d_cdate = ? "
					 +"  and t.c_tradeacco in ('27833', '27811') "
					 +"  and t.c_outbusinflag = '024' "
					 +"  and t.c_status = '1' "
					 +" group by t.d_cdate ";
	private final String principalMoneySql = "select sum(t.en_occurbala) "
					 +"  from zrdd11.tyebpayoutrequest t "
					 +" 	where t.vc_requestdate = ? "
					 +" 	and t.vc_dzhtradeacco in ('27833', '27811') "
					 +" 	and t.c_state = '2' "
					 +" 	group by t.vc_requestdate;";*/
	
	
	/**
	 *      测试专用
	 */
	public void run_bak() {
		logger.info("-------------开始-------------------");
		Map<String,Object> queRen = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, qurenSql, null);//确认日
		String queRenDay = (String)queRen.get("C_VALUE");//确认日
		logger.info(queRenDay);
		DynamicDBUtil.closeDBkey(dbKey);
		logger.info("-------------结束------------------");
	}
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	public void run() {
		try {
			long start = System.currentTimeMillis();
			logger.info("===================垫资户本金收益文件定时发送任务开始===================");
			//逻辑代码开始...
			//DynamicDBUtil.findList
			Map<String,Object> queRen = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, qurenSql, null);//确认日
			String queRenDay = (String)queRen.get("C_VALUE");//确认日
			
			
			//上一个交易日根据确认日 获取
			String lastBuyDay = getLastBuyDay(queRenDay);
			//上上个交易日根据上一个交易日获取
			String lalastBuyDay = getLastBuyDay(lastBuyDay);//只是为了落库，便于发送excel的时候显示出来，不做其他使用
			
			//如果确认日==当前系统时间，就执行以下代码的业务逻辑，否则什么也不做
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
			String	currentTime = simpleDateFormat.format(new Date());//当前系统时间
			if(StringUtils.isNotEmpty(queRenDay)&&currentTime.equals(queRenDay)){
				//String jiaoYiDay = (String)jiaoYi.get("C_VALUE");//上一个交易日
				String totalAmount = "0.0000"; //总金额，根据确认日获取
				String principalMoney = "0.0000"; //本金，根据上一个交易日获取
				boolean flag = true;
				try{
					Map<String,Object> totalAmountObject = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, totalAmountSql, queRenDay);
					Map<String,Object> principalMoneyObject = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, principalMoneySql, lastBuyDay);
					totalAmount = totalAmountObject.get("SUM(T.F_CONFIRMBALANCE)").toString();
					principalMoney = principalMoneyObject.get("SUM(T.EN_OCCURBALA)").toString();
				}catch(Exception e){
					flag = false;
					e.printStackTrace();
				}
				BigDecimal totalAmountDecima = new BigDecimal(totalAmount);//总金额
				BigDecimal principalMoneyDecima = new BigDecimal(principalMoney);//本金
				//利息
				BigDecimal interest = totalAmountDecima.subtract(principalMoneyDecima).setScale(4,BigDecimal.ROUND_DOWN);//直接删除多余的小数位，如2.35356会变成2.3535 
			
				/**
				 * 落库 
				 *     字段： 上个交易日、确认日、总结额、本金、利息
				 */
				if(flag){
					
					try {
						//如果该条记录已经存在的话，就不存进去了;该条记录存在的标志就是确认日已经存在数据库了
						DianzihuEntity entity = DianzihuService.findUniqueByProperty(DianzihuEntity.class, "qurenday", queRenDay);
						if(entity!=null){
							entity.setQurenday(queRenDay);
							entity.setJiaoyiday(lastBuyDay);//上一个交易日
							entity.setLaJiaoyiday(lalastBuyDay);//上上一个交易日
							entity.setTotalAmount(totalAmountDecima);
							entity.setPrincipalMoney(principalMoneyDecima);
							entity.setInterest(interest);
							DianzihuService.saveOrUpdate(entity);
						}else{
							DianzihuEntity dianzihuEntity = new DianzihuEntity();
							dianzihuEntity.setQurenday(queRenDay);
							dianzihuEntity.setJiaoyiday(lastBuyDay);//上一个交易日
							dianzihuEntity.setLaJiaoyiday(lalastBuyDay);//上上一个交易日
							dianzihuEntity.setTotalAmount(totalAmountDecima);
							dianzihuEntity.setPrincipalMoney(principalMoneyDecima);
							dianzihuEntity.setInterest(interest);
							DianzihuService.save(dianzihuEntity);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				String filePath = "C:\\raqsoft\\exportFiles\\";//C:\\raqsoft\\exportFiles\\
			    //String fileName = "基金快赎业务还款明细表(中融基金).xls";
			    String fileName = "report_zrfunds.xls";
			    
				List<DianzihuEntity> dianZihuList = DianzihuService.loadAll(DianzihuEntity.class);//插入excel的数据
			    createExcel(filePath, fileName, dianZihuList); 
			   String  mailBubject = "基金快赎业务还款明细表(中融基金)";
			   String  mailBody = "邮件发送内容-测试";
			    //收件人、抄送人 配置到数据库中
			    ReportEmailEntity reportEmail = reportEmailService.findUniqueByProperty(ReportEmailEntity.class, "reportName", "dianzijin");
			    String sendto = reportEmail!=null?reportEmail.getSentTo():"";
			    String sendcc = reportEmail!=null?reportEmail.getSentCc():"";
			    String sendExp = reportEmail!=null?reportEmail.getSentExcep():"";
				try {
					sendMail(filePath,fileName,mailBubject,mailBody,sendto,sendcc);
				} catch (Exception e) {
					//当邮件发送失败的时候，发送异常收件人
					try {
						sendMail(filePath,fileName,"邮件提醒","垫资户本金收益表发送失败",sendExp,"");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			    
				//...逻辑代码结束
			}else{
				//...
				logger.info("当前日期不等于确认日,不发送邮件！");
			}
			
			logger.info("===================垫资户本金收益文件定时发送任务结束===================");
			long end = System.currentTimeMillis();
			long times = end - start;
			logger.info("总耗时"+times+"毫秒");
		} catch (Exception e) {
			//2018-10-16
			e.printStackTrace();
			String filePath = "C:\\raqsoft\\exportFiles\\";
		    String fileName = "report_zrfunds.xls";
		    String sendto = "majianchao@zrfunds.com.cn";
		    String sendcc = "wangxiangyu@zrfunds.com.cn,liangqingyang@zrfunds.com.cn";
			try {
				sendMail(filePath,fileName,"浦发定时邮件发送失败",e.getMessage(),sendto,sendcc);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally {
			//关闭数据源
			DynamicDBUtil.closeDBkey(dbKey);
		}
	}
	private String getLastBuyDay(String currentDay) {
		String lastBuyDay = currentDay;
		String workFlag = "0";
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		while(!"1".equals(workFlag)){
			try {
				lastBuyDay = dateFormat.format(new Date(dateFormat.parse(lastBuyDay).getTime() - 1 * 24 * 60 * 60 * 1000));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Map<String,Object> lastDayObject = (Map<String,Object>)DynamicDBUtil.findOne(dbKey, lastBuyDaySql, lastBuyDay);
			workFlag = lastDayObject.get("L_WORKFLAG").toString();
		}
		return lastBuyDay;
	}
	/**
	 * 生成excel
	 */
	private void createExcel(String filePath, String fileName, List<DianzihuEntity> dianZihuList) {
		int sheetRows = dianZihuList.size();
		//创建HSSFWorkbook对象(excel的文档对象)  
	    HSSFWorkbook workbook = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet = workbook.createSheet("垫资户本金收益表"); 
		HSSFCellStyle  style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 单元格样式
		// 边框，居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
		
		//标题行
		HSSFRow row = sheet.createRow(0); 
		Cell cellTitle = row.createCell(0); 
		cellTitle.setCellValue("中融基金公司还款明细表"); 
		cellTitle.setCellStyle(cellStyle);
		// 合并单元格
		CellRangeAddress cra =new CellRangeAddress(0, 0, 0, 5); // 起始行, 终止行, 起始列, 终止列
		sheet.addMergedRegion(cra);

				
		HSSFRow titleRow = sheet.createRow(1);//字段行
		Cell cell0 = titleRow.createCell(0);
		cell0.setCellValue("还款日期");//确认日, 还款日期就是确认日
		cell0.setCellStyle(style);
		
		Cell cell = titleRow.createCell(1);
		cell.setCellValue("垫资发生时间段");//本金
		cell.setCellStyle(style);
		
		Cell cell2 = titleRow.createCell(2);
		cell2.setCellValue("垫资本金(包含差错资金)");//利息
		cell2.setCellStyle(style);
		
		Cell cell3 = titleRow.createCell(3);
		cell3.setCellValue("可结算收益");
		cell3.setCellStyle(style);
		
		Cell cell4 = titleRow.createCell(4);
		cell4.setCellValue("当日新增未结算收益");
		cell4.setCellStyle(style);
		
		Cell cell5 = titleRow.createCell(5);
		cell5.setCellValue("当日产生的差错资金");
		cell5.setCellStyle(style);
		
		//titleRow.setRowStyle(style);
		/**
		 * 数据行
		 */
		for (int i = 2; i < sheetRows+2; i++) {
			HSSFRow currentRow = sheet.createRow(i);//当前行
			String qurenday = dianZihuList.get(i-2).getQurenday();//确认日
			String lalajiaoyiDay = dianZihuList.get(i-2).getLaJiaoyiday();////上上一个交易日
			String lajiaoyiDay = dianZihuList.get(i-2).getJiaoyiday();//上一个交易日
			
			currentRow.createCell(0).setCellValue(qurenday);//还款日期
			//垫资发生时间段
			String dzTimes = lalajiaoyiDay+"日15:00--"+lajiaoyiDay+"日15:00";
			currentRow.createCell(1).setCellValue(dzTimes);
			currentRow.createCell(2).setCellValue(dianZihuList.get(i-2).getPrincipalMoney().setScale(2,BigDecimal.ROUND_DOWN).toString());
			currentRow.createCell(3).setCellValue(dianZihuList.get(i-2).getInterest().setScale(2,BigDecimal.ROUND_DOWN).toString());
			currentRow.createCell(4).setCellValue("-");
			currentRow.createCell(5).setCellValue("-");
			//sheet.setColumnWidth(i, 30 * 256);
			
		}
		sheet.autoSizeColumn(0); 
		sheet.autoSizeColumn(0, true);
		sheet.autoSizeColumn(1); 
		sheet.autoSizeColumn(1, true);
		sheet.autoSizeColumn(2); 
		sheet.autoSizeColumn(2, true);
		sheet.autoSizeColumn(3); 
		sheet.autoSizeColumn(3, true);
		sheet.autoSizeColumn(4); 
		sheet.autoSizeColumn(4, true);
		sheet.autoSizeColumn(5); 
		sheet.autoSizeColumn(5, true);
		
		sheet.setColumnWidth(2, 30 * 256);
		sheet.setColumnWidth(3, 30 * 256);
		sheet.setColumnWidth(4, 30 * 256);
		sheet.setColumnWidth(5, 30 * 256);
		
		try {
			FileOutputStream output=new FileOutputStream(filePath+fileName);  //   "d:\\垫资户本金收益表.xls"
			workbook.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 发送邮件
	 */
	private void sendMail(String filePath,String fileName,String mailBubject,String mailBody,String sendto,String sendcc) throws Exception{
		//调用发送邮件开始...2018-08-09
         PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
         Properties properties = ftpConfig.getProperties();
         //DataHandlerUtil  dataHandlerUtil =new DataHandlerUtil();
         mailBubject = "基金快赎业务还款明细表(中融基金)";
         mailBody = "附件包含中融基金公司还款明细表";
         DataHandlerUtil.sendMailForExcel(mailBody,mailBubject,filePath,fileName,properties,sendto,sendcc);
        //...调用发送邮件结束
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		run();
	}

}
