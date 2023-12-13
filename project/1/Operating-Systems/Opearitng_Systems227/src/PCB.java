
public class PCB {
	private int processId;
	private ThreadState state;
	private int burstTime;
	private int memoryRequired;
	private int waitingTime;
	private int RemainigTime;

	public int getRemainigTime() {
		return RemainigTime;
	}

	public void Decese_RemainigTime(int Quantim_time) {
		this.RemainigTime = RemainigTime - Quantim_time;
	}

	public PCB(int processId, int burstTime, int memoryRequired) {
		this.processId = processId;
		this.state = ThreadState.New;
		this.burstTime = burstTime;
		this.memoryRequired = memoryRequired;
		this.waitingTime = 0;
		this.RemainigTime = burstTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getProcessId() {
		return processId;
	}

	public ThreadState getState() {
		return state;
	}

	public void setState(ThreadState state) {
		this.state = state;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getMemoryRequired() {
		return memoryRequired;
	}

	public void setMemoryRequired(int memoryRequired) {
		this.memoryRequired = memoryRequired;
	}

	public int compareTo(PCB other) {
		// Compare by priority, and if priorities are equal, compare by insertion order
		if (this.getBurstTime() != other.getBurstTime()) {
			return Integer.compare(this.getBurstTime(), other.getBurstTime()); // Smaller priority first
		} else {
			// Use insertion order as tie-breaker
			return Integer.compare(this.hashCode(), other.hashCode()); // Using hashCode as tie-breaker
		}
	}

	public String toString() {
		return "process ID =      " + processId + "\n--------------------\n" + "stete =   " + state
				+ "\n--------------------\n" + "burst time =      " + burstTime + "\n--------------------\n"
				+ "memory Required = " + memoryRequired + "\n--------------------\n" + "waiting time = " + waitingTime
				+ "\n ============================================\n====================================";

	}
}
