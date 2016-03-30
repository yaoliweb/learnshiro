package com.yaoli.quartzjob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yaoli.beans.Message;
import com.yaoli.beans.SerialBean;
import com.yaoli.service.IMessageService;
import com.yaoli.service.ISysParamService;
import com.yaoli.util.InfoUtil;

@Controller
public class SendMessageTask implements Runnable{
	private static final Logger logger  = Logger.getLogger(SerialBean.class.getName());
	
	private static BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>();
	
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Message> queue) {
		SendMessageTask.queue = queue;
	}
	
	public static void putMessageToQueue(Message message) throws InterruptedException{
		SendMessageTask.queue.put(message);
	}
	
	@Resource
	private IMessageService iMessageService;
	
	@Resource
	private ISysParamService iSysParamService;
	
	@Override
	public void run() {
		try {
			while(true){
				try {
					Message message = queue.take();
					
					//int com = iSysParamService.getComPort();
					
					//SerialBean serialBean = new SerialBean(com);

					//serialBean.initialize();
					
					//InfoUtil.sendMessage(serialBean, message.getTel(), message.getMessagedetail());
					//logger.info(message.toString());
					iMessageService.insertSelective(message);
				} catch (Exception e) {
					
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
