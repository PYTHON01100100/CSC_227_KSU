import java.util.*;
import java.util.Comparator;

public class Scheduler {

	public static LinkedList<PCB> terminatedProcesses = new LinkedList<>();
	//static int at = 0;
	static int start = 0;

	public static void Ganttchart(PCB p, int st, int ft) {
		System.out.println("|_________________________|");
		System.out.println("|       ( p" + p.pid + " )            |");
		System.out.println("      "+st + " =====> " + ft);
	}

	public static void Table(Queue<PCB> queue) {
		
		System.out.println(" ________________________________________________________________________________");
		System.out.println(
				"| pID  |   Burst Time | Turnaround Time | Waiting Time | Memory   |   Status     |");
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
					 + terminatedProcesses.peek().burstTime
					+ "           " + terminatedProcesses.peek().turnAroundTime + "                   "
					+ terminatedProcesses.peek().waitingTime + "          "
					 + terminatedProcesses.peek().memorySize
					+ "      " + terminatedProcesses.peek().state.toString());
			terminatedProcesses.remove();
		}
		System.out.println("|______|______________|_________________|______________|__________|______________|\n");
		System.out.println("Average Waiting Time = " + ((double) totalwaitingTime / numOfProcess)+" msec");
		System.out.println("Average Turnaround Time = " + ((double) totalTurnaroundTime / numOfProcess)+" msec");
	}

	public static void FCFS(Queue<PCB> Q) {
		while (!Q.isEmpty()) {
			Ganttchart(Q.peek(), start, start + Q.peek().RemBurstTime);
			Q.peek().state = STATE.Running;

			Q.peek().completionTime = start + Q.peek().RemBurstTime;

			Q.peek().turnAroundTime = Q.peek().completionTime;

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
		Table(terminatedProcesses);
	}

	public static void roundrobin(Queue<PCB> Q, int q) {
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

				job.turnAroundTime = job.completionTime;

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
		Table(terminatedProcesses);
	}
	
	public static void SJF(Queue<PCB> Q) {
        List<PCB> jobList = new ArrayList<>(Q);
        jobList.sort(Comparator.comparingInt(p -> p.burstTime));

        for (PCB job : jobList) {
            Ganttchart(job, start, start + job.burstTime);
            job.state = STATE.Running;

            job.completionTime = start + job.burstTime;
            job.turnAroundTime = job.completionTime;
            job.waitingTime = job.turnAroundTime - job.burstTime;

            start += job.burstTime;
            job.RemBurstTime = 0;
            System.out.println(" p" + job.pid + " Memory usage: " + job.memorySize);

            job.state = STATE.Terminated;
            Main.memory += job.memorySize;
            terminatedProcesses.add(job);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("");
        Table(terminatedProcesses);
    }
	
	

}
