package com.lzjtedu.txu.domain;

import java.io.Serializable;


/**
 * 
 * @author zhangBin
 * @version 1.7
 *
 */

public class Record implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// id
	private int id;
	//时间戳
	private String times;
	// 事件
	private String matter;
	// 0 代表收入/1代表支出   
	private int types;
	// 费用
	private Double  cost;
	// 备注
	private String  remark;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimestamp() {
		return times;
	}

	public void setTimestamp(String times) {
		this.times = times;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public int getType() {
		return types;
	}

	public void setType(int types) {
		this.types = types;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", times=" + times + ", matter=" + matter + ", types=" + types + ", cost=" + cost
				+ ", remark=" + remark + "]";
	}
	
	
	
	

	
}
