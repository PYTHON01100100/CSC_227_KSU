
import java.util.Scanner;

public class Main {
	public static int memory = 8192;

	public static void main(String args[]) throws InterruptedException {
		readFileToJobQueue firstThread = new readFileToJobQueue();
		jobToReadyQueue secondThread = new jobToReadyQueue();
		firstThread.start();
		firstThread.join(); 
		secondThread.start();

		Scanner scan = new Scanner(System.in);
		System.out.println("\n=============================================");
		System.out.println("Choose which scheduling algorithm (Exit -1): \n\n1.First-Come-First-Serve (FCFS)       \n2.Shortest-Job-First (SJF)       \n3.Round-Robin with time slice = 3 (RR-3)"
				+ "         \n4.Round-Robin with time slice = 5 (RR-5)");
		System.out.println("\nEnter number from 1 to 4");	
		System.out.println("=============================================");
		String input = scan.next();
		boolean flag = true;
		do {
			switch (input) {
			case "1":
				Scheduler.FCFS(jobToReadyQueue.readyQueue);
				flag = false;
				break;
			case "2":
				Scheduler.SJF(jobToReadyQueue.readyQueue);
				flag = false;
				break;
			case "3":
				Scheduler.roundrobin(jobToReadyQueue.readyQueue, 3);
				flag = false;
				break;
			case "4":
				Scheduler.roundrobin(jobToReadyQueue.readyQueue, 5);
				flag = false;
				break;
			case "-1":
				flag = false;
				System.out.println("DONE");
				break;
			default:
				System.out.println("Enter a number from 1 to 4");
				input = scan.next();
			}
		} while (flag);
		scan.close();
	}

}