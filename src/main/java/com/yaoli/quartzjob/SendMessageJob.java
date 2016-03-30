package com.yaoli.quartzjob;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.yaoli.beans.Message;
import com.yaoli.beans.SysParam;
import com.yaoli.service.IMessageService;
import com.yaoli.service.ISysParamService;

@Controller
public class SendMessageJob {
	private static Logger logger = Logger.getLogger(SendMessageJob.class);
	
	@Resource
	private ISysParamService iSysParamService;
	
	@Resource
	private IMessageService iMessageService;
	

	

	protected void executeInternal() throws InterruptedException{
		List<SysParam> sysParams = iSysParamService.getAbnormalTypeForMessage();
		
		List<Message> runDataAbnormalsForMesages = new ArrayList<Message>();
		List<Message> detectionDataAbnormalsForMessages = new ArrayList<Message>();
		
		for (SysParam sysParam : sysParams) {
			if(sysParam.getValue().equals("设备故障")){
				runDataAbnormalsForMesages = iMessageService.getRunDataAbnormalForMessage();
			}else if(sysParam.getValue().equals("水质异常")){
				detectionDataAbnormalsForMessages = iMessageService.getDetectionAbnormalForMessage();
			}else if(sysParam.getValue().equals("网络故障")){
				//网络故障的定义暂时不清楚
			}
		}
		
		// 注意 Message对象有个sendtime属性，这里sendtime设置为插入数据库的时间，故为数据库表中的sendtime 设置了默认值 getdate()
		for (Message message1 : detectionDataAbnormalsForMessages) {
			if(iMessageService.messageNeedToSend(message1)== 0){
				SendMessageTask.putMessageToQueue(message1);
			}

		}
		
		for (Message message2 : runDataAbnormalsForMesages) {
			if(iMessageService.messageNeedToSend(message2)== 0){
				SendMessageTask.putMessageToQueue(message2);
			}
		}

	}
}
