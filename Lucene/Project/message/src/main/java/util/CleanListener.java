package util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.IndexService;

public class CleanListener implements ServletContextListener {
	private Timer timer;
	private Timer indexTimer;
	private WebApplicationContext wac = null;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("--------------清理程序已经关闭-------------------");
		if(timer != null) timer.cancel();
		if(indexTimer != null) indexTimer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//可以获取spring中BeanFactory,这个BeanFactory是在系统启动的时候就完成存储了
		wac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		System.out.println("------------------清理的启动程序已经开启（已经获取了"+wac+"）---------------------");
		timer = new Timer();
		timer.schedule(new IndexSave(),60000);
		indexTimer = new Timer();
		indexTimer.scheduleAtFixedRate(new IndexCommit(), 600000, 600000);
	}
	
	private class IndexSave extends TimerTask {
		@Override
		public void run() {
			System.out.println("数据库遗留的索引进行了提交"+new Date());
			IndexService indexService = (IndexService)wac.getBean("indexService");
			try {
				indexService.updateSetIndex();
			} catch (Exception e) {
				System.out.println("数据库遗留的索引提交失败"+new Date());
				e.printStackTrace();
			}
		}
	}
	
	private class IndexCommit extends TimerTask {
		@Override
		public void run() {
			System.out.println("索引进行了提交"+new Date());
			IndexService indexService = (IndexService)wac.getBean("indexService");
			try {
				indexService.updateCommitIndex();
			} catch (Exception e) {
				System.out.println("索引提交失败"+new Date());
				e.printStackTrace();
			}
		}
	}

}
