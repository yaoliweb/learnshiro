package com.yaoli.util;

public class ProduceSql {
	public static void main(String[] args) {
		for (int i = 1; i <=21; i++) {
			//System.out.print("@state"+i+"=equipment"+i+"state,");
			//sum(temp1.detection1) as detection1
			System.out.print("sum(temp1.detection"+i+") as detection"+i+",");
		}
/*		for (int i = 6; i <= 24; i++) {
			System.out.println("sum(equip"+i+"normaltime) as equip"+i+"normaltime,");
			System.out.println("sum(equip"+i+"abnormaltime) as equip"+i+"abnormaltime,");
		}*/
		
		//@detection1avg=detection1avg,@detection1min=detection1min,@detection1max=detection1max,
/*		for (int i = 6; i <= 14; i++) {
			System.out.println("detection"+i+"avg=@detection"+i+"avg,");
			System.out.println("detection"+i+"min=@detection"+i+"min,");
			System.out.println("detection"+i+"max=@detection"+i+"max,");
		}*/
/*		if @state1=3
				if not exists(select * from run_data_abnormal where sewageID = @sewageID and equipmentno = 1 and CONVERT(varchar(12),testingtime,111) = CONVERT(varchar(12),GETDATE() ,111) )
				begin
					insert into dbo.run_data_abnormal(sewageid,testingtime,equipmentstate,equipmentno,isrepaired) values(@sewageid,@testtime,@state1,1,0)
				end*/
				
/*		for (int i = 6; i <= 21; i++) {
			System.out.println("		if @state"+i+"=3");
			System.out.println("			if not exists(select * from run_data_abnormal where sewageID = @sewageID and equipmentno = "+i+" and CONVERT(varchar(12),testingtime,111) = CONVERT(varchar(12),GETDATE() ,111) )");
			System.out.println("			begin");
			System.out.println("				insert into dbo.run_data_abnormal(sewageid,testingtime,equipmentstate,equipmentno,isrepaired) values(@sewageid,@testtime,@state"+i+","+i+",0)");
			System.out.println("			end");
		}*/
/*		for (int i = 8; i <= 21; i++) {
			System.out.println("	if @state"+i+" = 1 ");
			System.out.println("		begin");
			System.out.println("			update dbo.statistic_day set equip"+i+"normaltime =equip"+i+"normaltime + @time where sewageID=@sewageid and convert(varchar(11),testingtime,120) = convert(varchar(11),@testtime,120)");
			System.out.println("		end");
			System.out.println("	if @state"+i+" = 3");
			System.out.println("		begin");
			System.out.println("			update dbo.statistic_day set equip"+i+"abnormaltime =equip"+i+"abnormaltime + @time where sewageID=@sewageid and convert(varchar(11),testingtime,120) = convert(varchar(11),@testtime,120)");
			System.out.println("		end");
		}*/
/*		for (int i = 6; i < 21; i++) {
			System.out.print("@state"+i+" int,");
		}*/
		
/*		for (int i = 6; i <= 14; i++) {
			System.out.println("			if @detection"+i+"avg is null");
			System.out.println("				begin");
			System.out.println("					set @detection"+i+"avg = @detection"+i);
			System.out.println("					set @detection"+i+"min = @detection"+i);
			System.out.println("					set @detection"+i+"max = @detection"+i);
			System.out.println("				end");
		}*/
/*		for (int i = 6; i <= 14; i++) {
			System.out.println("			if @detection"+i+" > @detection"+i+"max");
			System.out.println("				set @detection"+i+"max = @detection"+i);
		}*/
		
		//set @detection5avg = (@detection5avg*(@count-1)+@detection5)/@count
/*		for (int i = 6; i <= 14; i++) {
			System.out.println("set @detection"+i+"avg = (@detection"+i+"avg*(@count-1)+@detection"+i+")/@count");
		}*/
	}
	/*System.out.print("@detection"+i+"avg,"+"@detection"+i+"min,"+"@detection"+i+"max,");*/
/*	System.out.println("detection"+i+"avg = @detection"+i+"avg,");
	System.out.println("detection"+i+"min = @detection"+i+"min,");
	System.out.println("detection"+i+"max = @detection"+i+"max,");*/
	/*System.out.println("declare @detection"+i+"avg float,@detection"+i+"min float,@detection"+i+"min float");*/
	
/*	System.out.println("alter table statistic_day add detection"+i+"avg float default 0.00");
	System.out.println("alter table statistic_day add detection"+i+"min float default 0.00");
	System.out.println("alter table statistic_day add detection"+i+"max float default 0.00");*/
}
