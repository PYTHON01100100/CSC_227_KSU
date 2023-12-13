

enum STATE { New, Ready, Running, Waiting, Terminated }

public class PCB {
	//
	public int pid;
	public STATE state = STATE.New;
	public int burstTime;
	public int RemBurstTime;
	public int memorySize;
	public int waitingTime;
	public int turnAroundTime;
	public int completionTime;
	
	public PCB(int id, int burstTime, int memorySize){
		this.pid = id;
		this.burstTime = burstTime;
		this.memorySize = memorySize;
		this.RemBurstTime = burstTime;
	}
	
	
}