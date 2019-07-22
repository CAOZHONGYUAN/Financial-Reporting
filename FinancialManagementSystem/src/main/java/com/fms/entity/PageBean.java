package com.fms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.util.Map;

public class PageBean<T> {
	private int pageNum;
	private int totalPage;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private List<T> pageList;
	private int pageSize;
	private int code;
	private String message;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<T> getPageList() {
		return pageList;
	}
	
	public void setPageList(List<Map<String, Object>> list) {
		this.pageList = (List<T>) list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PageBean() {
		super();
	}
	public PageBean(int pageNum, int totalPage, List<T> pageList, int pageSize, int code, String message) {
		super();
		this.pageNum = pageNum;
		this.totalPage = totalPage;
		this.pageList = pageList;
		this.pageSize = pageSize;
		this.code = code;
		this.message = message;
	}

	
}

