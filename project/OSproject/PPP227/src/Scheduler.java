import java.util.*;

public class Scheduler {

	public static LinkedList<PCB> terminatedProcesses = new LinkedList<>();
	static int at = 0;
	static int start = 0;

	public static void Ganttchart(PCB p, int st, int ft) {
		System.out.println("|_________________________|");
		System.out.println("|       ( p" + p.pid + " )            |");
		System.out.println("      "+st + " =====> " + ft);
	}

	public static void Table(Queue<PCB> queue, boolean MLFQ) {
		if (MLFQ) {
			return;
		}
		System.out.println(" _________________________________________________________________________________________________________________");
		System.out.println(
				"| pID  | Arrival Time |   Burst Time | Turnaround Time | Waiting Time | Completion Time | Memory   |   Status     |");
		int totalwaitingTime = 0;
		int totalTurnaroundTime = 0;
		int totalcompletionTime = 0;
		int numOfProcess = 0;
		int size = terminatedProcesses.size();
		for (int i = 0; i < size; i++) {
			numOfProcess += 1;
			totalwaitingTime += terminatedProcesses.peek().waitingTime;
			totalTurnaroundTime += terminatedProcesses.peek().turnAroundTime;
			totalcompletionTime += terminatedProcesses.peek().completionTime;
			System.out.println("   "+terminatedProcesses.peek().pid + "           "
					+ (terminatedProcesses.peek().arrivalTime) + "             " + terminatedProcesses.peek().burstTime
					+ "           " + terminatedProcesses.peek().turnAroundTime + "                   "
					+ terminatedProcesses.peek().waitingTime + "               "
					+ terminatedProcesses.peek().completionTime + "          " + terminatedProcesses.peek().memorySize
					+ "        " + terminatedProcesses.peek().state.toString());
			terminatedProcesses.remove();
		}
		System.out.println("|______|______________|______________|_________________|______________|_________________|__________|______________|\n");
		System.out.println("Average Waiting Time = " + ((double) totalwaitingTime / numOfProcess)+" msec");
		System.out.println("Average Turnaround Time = " + ((double) totalTurnaroundTime / numOfProcess)+" msec");
	}

	public static void FCFS(Queue<PCB> Q, boolean MLFQ) {
		while (!Q.isEmpty()) {
			Ganttchart(Q.peek(), start, start + Q.peek().RemBurstTime);
			Q.peek().state = STATE.Running;

			Q.peek().completionTime = start + Q.peek().RemBurstTime;

			Q.peek().turnAroundTime = Q.peek().completionTime - Q.peek().arrivalTime;

			Q.peek().waitingTime = Q.peek().turnAroundTime - Q.peek().burstTime;

			start += Q.peek().burstTime;
			Q.peek().RemBurstTime = 0;
			System.out.println(" p" + Q.peek().pid + " Memory usage: " + Q.peek().memorySize);

			Q.peek().state = STATE.Terminated;
			Main.memory += Q.peek().memorySize;
			terminatedProcesses.add(Q.remove());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
		Table(terminatedProcesses, MLFQ);
	}

	public static void roundrobin(Queue<PCB> Q, int q, boolean MLFQ) {
		while (!Q.isEmpty()) {
			PCB job = Q.remove();
			job.state = STATE.Running;
			if (job.RemBurstTime > q) {
				job.RemBurstTime -= q;
				Ganttchart(job, start, start + q);
				System.out.println(" p" + job.pid + " Memory usage: " + job.memorySize);
				start += q;
				job.state = STATE.Ready;
				Q.add(job);
			} else {
				Ganttchart(job, start, start + job.RemBurstTime);
				System.out.println(" p" + job.pid + " Memory usage: " + job.memorySize);
				job.completionTime = start + job.RemBurstTime;
				start += job.RemBurstTime;

				job.turnAroundTime = job.completionTime - job.arrivalTime;

				job.waitingTime = job.turnAroundTime - job.burstTime;
				job.RemBurstTime -= job.RemBurstTime;
				job.state = STATE.Terminated;
				Main.memory += job.memorySize;
				terminatedProcesses.add(job);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Table(terminatedProcesses, MLFQ);
	}

	static void executeMLFQ(Queue<PCB> Q) {
		int size = readFileToJobQueue.numberOffProcesses;
		Queue<PCB> ready_queue2 = new LinkedList<>();
		Queue<PCB> ready_queue3 = new LinkedList<>();
		Queue<PCB> temp = new LinkedList<>();
		while (size != temp.size()) {
			while (!Q.isEmpty()) {
				PCB job = Q.remove();
				job.state = STATE.Running;
				if (job.RemBurstTime > 8) {
					job.RemBurstTime -= 8;
					Ganttchart(job, start, start + 8);
					System.out.println(" p" + job.pid + " Memory usage: " + job.memorySize);
					start += 8;
					job.state = STATE.Ready;
					ready_queue2.add(job);
				} else {
					Ganttchart(job, start, start + job.RemBurstTime);
					System.out.println(" p" + job.pid + " Memory usage: " + job.memorySize);
					job.completionTime = start + job.RemBurstTime;
					start += job.RemBurstTime;

					job.turnAroundTime = job.completionTime - job.arrivalTime;

					job.waitingTime = job.turnAroundTime - job.burstTime;
					job.RemBurstTime -= job.RemBurstTime;
					job.state = STATE.Terminated;
					Main.memory += job.memorySize;
					terminatedProcesses.add(job);
					temp.add(job);
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (!ready_queue2.isEmpty()) {
				if (!Q.isEmpty()) {
					break;
				}
				PCB job = ready_queue2.remove();
				job.state = STATE.Running;
				if (job.RemBurstTime > 16) {
					job.RemBurstTime -= 16;
					Ganttchart(job, start, start + 16);
					System.out.println(" p" + job.pid + " Memory usage: " + job.memorySize);
					start += 16;
					job.state = STATE.Ready;
					ready_queue3.add(job);
				} else {
					Ganttchart(job, start, start + job.RemBurstTime);
					System.out.println(" p" + job.pid + " Memory usage: " + job.memorySize);
					job.completionTime = start + job.RemBurstTime;
					start += job.RemBurstTime;

					job.turnAroundTime = job.completionTime - job.arrivalTime;

					job.waitingTime = job.turnAroundTime - job.burstTime;
					job.RemBurstTime -= job.RemBurstTime;
					job.state = STATE.Terminated;
					Main.memory += job.memorySize;
					terminatedProcesses.add(job);
					temp.add(job);
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (!ready_queue3.isEmpty()) {
				if (!Q.isEmpty()) {
					break;
				}
				Ganttchart(ready_queue3.peek(), start, start + ready_queue3.peek().RemBurstTime);
				ready_queue3.peek().state = STATE.Running;

				ready_queue3.peek().completionTime = start + ready_queue3.peek().RemBurstTime;

				ready_queue3.peek().turnAroundTime = ready_queue3.peek().completionTime
						- ready_queue3.peek().arrivalTime;

				ready_queue3.peek().waitingTime = ready_queue3.peek().turnAroundTime - ready_queue3.peek().burstTime;

				start += ready_queue3.peek().RemBurstTime;
				ready_queue3.peek().RemBurstTime = 0;
				System.out.println(" p" + ready_queue3.peek().pid + " Memory usage: " + ready_queue3.peek().memorySize);

				ready_queue3.peek().state = STATE.Terminated;
				Main.memory += ready_queue3.peek().memorySize;
				PCB job = ready_queue3.remove();
				terminatedProcesses.add(job);
				temp.add(job);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		Table(terminatedProcesses, false);
	}

}
