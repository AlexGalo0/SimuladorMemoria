package logic.Dynamic;

public class Sector {

	private int size;
	private int freeSpace;
	private boolean isFree=true;
	
	public Sector(int size) {
		this.size = size;
		this.freeSpace = this.size - size;
		this.isFree = false;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(int freeSpace) {
		this.freeSpace = freeSpace;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	@Override
	public String toString() {
		return "Sector [size=" + size + ", freeSpace=" + freeSpace + ", isFree=" + isFree + "]";
	}

	
}
