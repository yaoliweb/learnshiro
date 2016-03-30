package com.yaoli.vo;

import java.util.Date;

public class EquipRepairRecordVO {
    private Long id;

    private Integer sewageid;

    private Integer deviceid;

    private Date time;

    private String consumetime;

    private String repairreason;

    private String repaircontent;

    private String consumematerial;

    private String repairman;

    private Date completetime;
    
    private String devicename;
    
    private String sewagename;
    
	public String getSewagename() {
		return sewagename;
	}

	public void setSewagename(String sewagename) {
		this.sewagename = sewagename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSewageid() {
		return sewageid;
	}

	public void setSewageid(Integer sewageid) {
		this.sewageid = sewageid;
	}

	public Integer getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getConsumetime() {
		return consumetime;
	}

	public void setConsumetime(String consumetime) {
		this.consumetime = consumetime;
	}

	public String getRepairreason() {
		return repairreason;
	}

	public void setRepairreason(String repairreason) {
		this.repairreason = repairreason;
	}

	public String getRepaircontent() {
		return repaircontent;
	}

	public void setRepaircontent(String repaircontent) {
		this.repaircontent = repaircontent;
	}

	public String getConsumematerial() {
		return consumematerial;
	}

	public void setConsumematerial(String consumematerial) {
		this.consumematerial = consumematerial;
	}

	public String getRepairman() {
		return repairman;
	}

	public void setRepairman(String repairman) {
		this.repairman = repairman;
	}

	public Date getCompletetime() {
		return completetime;
	}

	public void setCompletetime(Date completetime) {
		this.completetime = completetime;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
    
}
