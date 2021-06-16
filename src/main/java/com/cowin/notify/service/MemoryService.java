package com.cowin.notify.service;

import org.springframework.stereotype.Service;

import com.cowin.notify.model.MemoryStats;

@Service
public class MemoryService {

	public MemoryStats getMemoryStatus() {
		MemoryStats memStats = new MemoryStats();
		memStats.setHeapFreeSize(Runtime.getRuntime().freeMemory());
		memStats.setHeapMaxSize(Runtime.getRuntime().maxMemory());
		memStats.setHeapSize(Runtime.getRuntime().totalMemory());
		memStats.setHeapFreeSize(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
		return memStats;
	}
	
	

}
