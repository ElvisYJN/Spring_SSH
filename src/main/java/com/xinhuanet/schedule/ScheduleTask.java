package com.xinhuanet.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

	private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

	/**
	 * 定时任务
	 */
	@Scheduled(cron = "* * * * * ?")
	public void job2() {
		logger.info("======= DELETEFLAG字段更新成功 ！！！ =======");
	}

}
