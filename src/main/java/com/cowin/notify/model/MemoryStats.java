package com.cowin.notify.model;

public class MemoryStats {
	
	
	Long heapSize ;
	Long heapMaxSize;
	Long heapFreeSize;
	
	public Long getHeapSize() {
		return heapSize;
	}
	public void setHeapSize(Long heapSize) {
		this.heapSize = heapSize;
	}
	public Long getHeapMaxSize() {
		return heapMaxSize;
	}
	public void setHeapMaxSize(Long heapMaxSize) {
		this.heapMaxSize = heapMaxSize;
	}
	public Long getHeapFreeSize() {
		return heapFreeSize;
	}
	public void setHeapFreeSize(Long heapFreeSize) {
		this.heapFreeSize = heapFreeSize;
	}
	@Override
	public String toString() {
		return "MemoryStats [heapSize=" + heapSize + ", heapMaxSize=" + heapMaxSize + ", heapFreeSize=" + heapFreeSize
				+ "]";
	}
	
	
	
	

}
