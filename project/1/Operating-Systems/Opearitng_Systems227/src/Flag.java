
// This class is for communication
// The boolean flag acts as trigger between two threads
public class Flag {
	private boolean flag;

	public Flag() {
		flag = false;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
