package com.jeecg.bankaccount.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.accountratechanges.entity.AccountRateChangesEntity;
import com.jeecg.bankaccount.entity.BankAccountEntity;
import com.jeecg.bankaccount.service.BankAccountServiceI;

@Service("updateState")
public class UpdateState extends CommonServiceImpl implements Job{
	@Autowired
	private BankAccountServiceI bankAccountService;
	
	public void run() {
		long start = System.currentTimeMillis();
		org.jeecgframework.core.util.LogUtil.info("===================账户状态自动更新定时任务开始===================");
		try {			
			//tSSmsService.send();
			//1,获取当前系统时间
			 Date dt=new Date();
		     /*SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		     String dateString = matter.format(dt);
		     Date dateFormat = matter.parse(dateString);*/
		     
			//2,遍历bannk_account表，如果到期日>当前系统时间,账户状态自动更新为已注销
			 String hql = "from BankAccountEntity";
			 List<BankAccountEntity> accountRateChangesOldList = bankAccountService.findByQueryString(hql);
			 /**
			  * 如果账户类型是定存账户，是否流水户选择的为是，则当到期日过了之后，账户状态自动更新为已注销
			  */
			 for(BankAccountEntity entity :accountRateChangesOldList){
				
				 if("2".equals(entity.getAcountType())&&"1".equals(entity.getIsLiushui())){
					 if(entity.getTodate()!=null&&entity.getTodate().after(dt)){   //到期日
						 entity.setAcountStatus("0");
						 bankAccountService.saveOrUpdate(entity);
					 }
				 }
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		org.jeecgframework.core.util.LogUtil.info("===================账户状态自动更新定时任务结束===================");
		long end = System.currentTimeMillis();
		long times = end - start;
		org.jeecgframework.core.util.LogUtil.info("总耗时"+times+"毫秒");
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		run();
	}
}
