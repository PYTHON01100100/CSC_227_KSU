import java.io.*;
import java.util.*;

public class Main {
	// C:\Users\HP\eclipse-workspace\Operating_Systems227\src\Project\TestData.txt
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the path of the file: ");
		String Path = input.next();
		System.out.println(
				" choose the method you want to use\n 1) FCFS Algorithm\n 2) SJF Algorithm\n 3) Round Robin\n 4) Exit the program");
		System.out
				.println(" ------------------------------------------------------------------------------------------");
		int choice = input.nextInt();
		while (choice != 4) {
			ArrayList<PCB> list = new ArrayList<PCB>();
			MemoryLimit mm = new MemoryLimit(8192);
			Flag f = new Flag();
			Flag f2 = new Flag();
			Flag f3 = new Flag();
			f.setFlag(false);
			Queue JobQ = new Queue();
			Queue ReadyQ = new Queue();
			PQueue ReadyPQ = new PQueue();

			if (choice == 1) {
				ReadThread rd = new ReadThread(Path, f, list, JobQ);
				TransportingThread s = new TransportingThread(JobQ, f, mm, ReadyQ, f2, f3);
				ProcessingThread as = new ProcessingThread(ReadyQ, f2, mm, Type_Algorithm.FCFS, list, f3);
				rd.start();
				s.start();
				as.start();
				rd.join();
				s.join();
				as.join();

			} else if (choice == 2) {
				ReadThread rd = new ReadThread(Path, f, list, JobQ);
				TransportingThread s = new TransportingThread(JobQ, f, mm, ReadyPQ, f2, f3);
				ProcessingThread as = new ProcessingThread(ReadyPQ, f2, mm, Type_Algorithm.SJF, list, f3);
				rd.start();
				s.start();
				as.start();
				rd.join();
				s.join();
				as.join();

			} else if (choice == 3) {
				System.out.println("Enter the quantum number you want: ");
				int quantim_number = input.nextInt();
				ReadThread rd = new ReadThread(Path, f, list, JobQ);
				TransportingThread s = new TransportingThread(JobQ, f, mm, ReadyQ, f2, f3);
				ProcessingThread as = new ProcessingThread(ReadyQ, f2, mm, Type_Algorithm.RR, quantim_number, list, f3);
				rd.start();
				s.start();
				as.start();
				rd.join();
				s.join();
				as.join();
			} else if (choice == 4) {
				break;
			} else
				System.out.println("you choose a wrong input, your input should between 1-4");
			System.out.println();
			System.out.println("you want continue ?\n 1)yes otherewise)no");
			if (input.nextInt() == 1) {
				System.out.println(
						" choose the method you want to use\n 1) FCFS Algorithm\n 2) SJFAlgorithm\n 3) Round Robin\n 4) Exit the program");
				System.out.println(
						" ------------------------------------------------------------------------------------------");
				choice = input.nextInt();
			} else
				break;
		}
		System.out.println("Thank you and goodby");
	}
}
