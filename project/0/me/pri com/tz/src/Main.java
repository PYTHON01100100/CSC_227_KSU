
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
		System.out.println("Choose which scheduling algorithm (Exit -1): \n\n1.First Come-First Serve (FCFS)       \n2.Round Robin(RR) with q=3ms"
				+ "         \n2.Round Robin(RR) with q=5ms ");
		System.out.println("\nEnter number from 1 to 3");	
		System.out.println("=============================================");
		String input = scan.next();
		boolean flag = true;
		do {
			switch (input) {
			case "1":
				Scheduler.FCFS(jobToReadyQueue.readyQueue, false);
				flag = false;
				break;
			case "2":
				Scheduler.roundrobin(jobToReadyQueue.readyQueue, 3, false);
				flag = false;
				break;
			case "3":
				Scheduler.roundrobin(jobToReadyQueue.readyQueue, 5, false);
				break;
			//case "4":
				//Scheduler.SJF(jobToReadyQueue.readyQueue);;
				//break;
			case "-1":
				flag = false;
				System.out.println("DONE");
				break;
			default:
				System.out.println("Enter a number from 1 to 3");
				input = scan.next();
			}
		} while (flag);
		scan.close();
	}

}