
import java.util.*;

public class jobToReadyQueue extends Thread {
	public static Queue<PCB> readyQueue = new LinkedList<>();
	

	public void run() {
		try {
			while (!readFileToJobQueue.jobQueue.isEmpty()) {
				int memorySizeOfJob = readFileToJobQueue.jobQueue.peek().memorySize;
				if (Main.memory - memorySizeOfJob >= 0) {
					Main.memory -= memorySizeOfJob;
					readFileToJobQueue.jobQueue.peek().state = STATE.Ready;
					readyQueue.add(readFileToJobQueue.jobQueue.remove());
				}
				if (memorySizeOfJob > 8192) {
					readFileToJobQueue.jobQueue.remove();

				}
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}