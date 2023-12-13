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
			File file = new File("C:\\Users\\d7oom\\Videos\\txt\\testdata1.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
			    String jobName = scan.nextLine().trim();
			    String[] jobData = scan.nextLine().split(",");
			    int arrivalTime = Integer.parseInt(jobData[0].trim());
			    int burstTime = Integer.parseInt(jobData[1].trim());
			    int memorySize = Integer.parseInt(jobData[2].trim());
			    PCB p = new PCB(jobName, arrivalTime, burstTime, memorySize);
			    arrOfProcesses[n++] = 

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