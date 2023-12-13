
public class PCB {

	private String processID;
	private String processState;
	private int burstTime;
	private int memoryRequired;

	public PCB(String processID, String processState, int burstTime, int memoryRequired) {
		this.processID = processID;
		this.processState = processState;
		this.burstTime = burstTime;
		this.memoryRequired = memoryRequired;
	}

	public String getProcessID() {
		return processID;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public int getMemoryRequired() {
		return memoryRequired;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
		
	}
}

