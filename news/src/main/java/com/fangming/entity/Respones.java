package com.fangming.entity;

public class Respones<T>{

	private String code;//返回码
	
	private String msg;//返回消息
	
	private T data;//返回数据
	
	private Boolean status;//返回操作状态

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
