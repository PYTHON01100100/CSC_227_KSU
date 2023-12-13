
import java.util.*;
import java.io.*;

public class ProcessingThread extends Thread {
	public Queue RR;
	public Queue FCFS;
	public Flag flag2;
	public MemoryLimit limit;
	public PQueue SJF = null;
	public Type_Algorithm ALG;
	public ArrayList<PCB> ListOfProcess;
	public int Quantim_time;
	public Flag ST3;
	String chart = "";

	// When using FCFS
	public ProcessingThread(Queue FCFS, Flag flag, MemoryLimit limit, Type_Algorithm alg, ArrayList<PCB> aa, Flag ST3) {
		this.FCFS = FCFS;
		this.flag2 = flag;
		this.limit = limit;
		this.ALG = alg;
		ListOfProcess = aa;
		this.ST3 = ST3;
	}

	// When we want to use RR
	public ProcessingThread(Queue RR, Flag flag, MemoryLimit limit, Type_Algorithm alg, int qt, ArrayList<PCB> aa,
			Flag ST3) {
		this.RR = RR;
		this.flag2 = flag;
		this.limit = limit;
		this.ALG = alg;
		this.Quantim_time = qt;
		ListOfProcess = aa;
		this.ST3 = ST3;
	}

	// When using SJF
	public ProcessingThread(PQueue SJF, Flag flag, MemoryLimit limit, Type_Algorithm alg, ArrayList<PCB> aa, Flag ST3) {
		this.SJF = SJF;
		this.flag2 = flag;
		this.limit = limit;
		this.ALG = alg;
		ListOfProcess = aa;
		this.ST3 = ST3;
	}

	public void drew_chart(PCB p, int time_spend_in_cpu) {
		chart = chart + " ";
		chart = chart + "P" + p.getProcessId() + " ";
		for (int i = 0; i < time_spend_in_cpu; i++)
			chart = chart + "_ ";
		chart = chart + "|";
	}

	public void printChart() {
		for (int j = 0; j < chart.length(); j++)
			System.out.print("=");
		System.out.println();
		System.out.println(chart);
		for (int j = 0; j < chart.length(); j++)
			System.out.print("=");

	}

	public void calculation(ArrayList<PCB> list, int time) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getState() == ThreadState.Ready || list.get(i).getState() == ThreadState.New)
				list.get(i).setWaitingTime(time + list.get(i).getWaitingTime());

	}

	public void run() {
		while (ST3.isFlag() != true)
			System.out.print(" ");
		if (ALG == Type_Algorithm.FCFS) {

			while (!(FCFS.isEmpty() && flag2.isFlag())) {
				if (FCFS.peek() != null) {
					PCB process = FCFS.dequeue();
					process.setState(ThreadState.Running);

					try {
						Thread.sleep(process.getBurstTime());
					} catch (Exception e) {
					}

					calculation(ListOfProcess, process.getBurstTime());
					limit.setLimit(limit.getLimit() + process.getMemoryRequired());
					drew_chart(process, process.getBurstTime());
					ST3.setFlag(false);
					while (!ST3.isFlag() != true && flag2.isFlag() != true)
						System.out.print("");
					process.setState(ThreadState.Terminated);
				}
			}
		}

		else if (ALG == Type_Algorithm.SJF) {
			while (!(SJF.isEmpty() && flag2.isFlag())) {
				if (SJF.isEmpty() != true) {
					PCB process = SJF.dequeue();
					process.setState(ThreadState.Running);
					try {
						Thread.sleep(process.getBurstTime());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					calculation(ListOfProcess, process.getBurstTime());
					limit.setLimit(limit.getLimit() + process.getMemoryRequired());
					drew_chart(process, process.getBurstTime());
					// Notifies the Transporting Thread that there is a finished process, that is, an empty space
					ST3.setFlag(false);
					while (ST3.isFlag() != true && flag2.isFlag() != true)
						System.out.print("");
					process.setState(ThreadState.Terminated);
				}
			}

		}

		else if (ALG == Type_Algorithm.RR) {

			while (!(RR.isEmpty() && flag2.isFlag())) {
				if (RR.peek() != null) {
					PCB process = RR.dequeue();
					process.setState(ThreadState.Running);

					try {
						if (process.getRemainigTime() >= Quantim_time) {
							Thread.sleep(Quantim_time);
							process.Decese_RemainigTime(Quantim_time);
							calculation(ListOfProcess, Quantim_time);
							drew_chart(process, Quantim_time);
						} else {
							Thread.sleep(process.getRemainigTime());
							calculation(ListOfProcess, process.getRemainigTime());
							drew_chart(process, process.getRemainigTime());
							process.Decese_RemainigTime(process.getRemainigTime());

						}
					} catch (Exception e) {
					}

					if (process.getRemainigTime() > 0) {
						RR.enqueue(process);
						process.setState(ThreadState.Ready);
						limit.setLimit(limit.getLimit() + process.getMemoryRequired());
						while (ST3.isFlag() != true && flag2.isFlag() != true)
							System.out.println("");
					} else
						process.setState(ThreadState.Terminated);

				}
			}

		}
		System.out.println(" Using " + ALG + " Algorithm ");
		if (ALG == Type_Algorithm.RR)
			System.out.println(" the Quantim_tim = " + Quantim_time);
		System.out
				.println("    |       the waiting time         the turnaround  \n    |                              ");
		for (int i = 0; i < ListOfProcess.size(); i++)
			System.out.println("P " + ListOfProcess.get(i).getProcessId() + " |       "
					+ ListOfProcess.get(i).getWaitingTime() + "                           "
					+ (ListOfProcess.get(i).getWaitingTime() + ListOfProcess.get(i).getBurstTime()) + "\n    |");
		System.out.println(
				"============================================================================\n" + " the chart");
		printChart();
		return;

	}
}
