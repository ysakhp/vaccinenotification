package com.cowin.notify.model;

public class MemoryStats {
	
	
	Long heapSize ;
	Long heapMaxSize;
	Long heapFreeSize;
	Long usedMemory;
	public Long getUsedMemory() {
		return usedMemory;
	}
	public void setUsedMemory(Long usedMemory) {
		this.usedMemory = usedMemory;
	}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heapFreeSize == null) ? 0 : heapFreeSize.hashCode());
		result = prime * result + ((heapMaxSize == null) ? 0 : heapMaxSize.hashCode());
		result = prime * result + ((heapSize == null) ? 0 : heapSize.hashCode());
		result = prime * result + ((usedMemory == null) ? 0 : usedMemory.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemoryStats other = (MemoryStats) obj;
		if (heapFreeSize == null) {
			if (other.heapFreeSize != null)
				return false;
		} else if (!heapFreeSize.equals(other.heapFreeSize))
			return false;
		if (heapMaxSize == null) {
			if (other.heapMaxSize != null)
				return false;
		} else if (!heapMaxSize.equals(other.heapMaxSize))
			return false;
		if (heapSize == null) {
			if (other.heapSize != null)
				return false;
		} else if (!heapSize.equals(other.heapSize))
			return false;
		if (usedMemory == null) {
			if (other.usedMemory != null)
				return false;
		} else if (!usedMemory.equals(other.usedMemory))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemoryStats [heapSize=" + heapSize + ", heapMaxSize=" + heapMaxSize + ", heapFreeSize=" + heapFreeSize
				+ ", usedMemory=" + usedMemory + "]";
	}
	
	
	
	

}
