package com.zh.utils;

import java.util.List;

public class Pager<T> {
	private List<T> datas;
	private int offset;//该页第1条记录的偏移量，起始为0
	private int total;//总记录数
	private int pageSize = 5;//每页的记录数
	private int currentPage = 1;//当前页为主导
	private int totalPage ;//总页数
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public int getOffset() {
		offset =  (currentPage-1)*pageSize;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage(){
		totalPage = (total % pageSize == 0) ? total/pageSize : total/pageSize+1;
		return totalPage;
	}
	public void setTotalPage(int totalPage){
		
	}
}