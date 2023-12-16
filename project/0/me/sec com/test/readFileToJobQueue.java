import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class readFileToJobQueue extends Thread {
	static PCB[] arrOfProcesses = new PCB[30];
	static Queue<PCB> jobQueue = new LinkedList<>();
	static int n = 0;
	static int numberOffProcesses = 0;

	public void run() {
		try {
			File file = new File("C:\\Users\\d7oom\\Videos\\txt\\testdata2.txt");
			//File file = new File("C:\\Users\\d7oom\\Videos\\txt\\testdata2.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				scan.nextLine();
				String[] numbers = scan.nextLine().trim().split(", ");
				int processID = Integer.parseInt(numbers[0]);
				int burstTime = Integer.parseInt(numbers[1]);
				int memorySize = Integer.parseInt(numbers[2]);
				PCB p = new PCB(processID, burstTime, memorySize);
				arrOfProcesses[n++] = p;

			}
			for (int i = 0; i < n; i++) {
				jobQueue.add(arrOfProcesses[i]);
				numberOffProcesses++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("invalid file path");
		} catch (NumberFormatException e) {
			System.out.println("invalid file format");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("invalid process must be less than or equal 30");
		}
	}
}