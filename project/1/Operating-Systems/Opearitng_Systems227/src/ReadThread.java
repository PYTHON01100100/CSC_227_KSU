import java.io.*;
import java.util.*;

public class ReadThread extends Thread {
	File file;
	Scanner scanner;
	Flag flag;
	ArrayList<PCB> ListOfProcess;
	Queue JobQueue;

	public ReadThread(String str, Flag flag, ArrayList<PCB> list, Queue queue) {
		file = new File(str); // Creates a file object from the path taken from the main
		this.flag = flag;
		ListOfProcess = list;
		JobQueue = queue;
	}
	
	// Traverses the Whole file
	public void read() {
		try {
			scanner = new Scanner(file); // Scans the whole file
			while (scanner.hasNextLine()) {
				scanner.nextLine(); // Skips the lines that start with: Job#
				readLine(scanner.nextLine()); // Reads the lines that contain data
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Reads and parses lines 
	public void readLine(String str) throws Exception {
		Scanner s = new Scanner(str); // Scans the line given from read()
		String id, time, memory;
		// The comma here separates the data in the TestData file so that why we use it as delimiter
		s.useDelimiter(",");
		// As long as there is tokens(Pieces of data separated by a comma), we enter the while loop
		while (s.hasNext()) { 
			id = s.next(); // First token is for the ID
			time = s.next(); // Second token is for the Burst Time
			memory = s.next(); // Third and final token is for the Memory Required
			PCB pcb = new PCB(Integer.parseInt(id), Integer.parseInt(time), Integer.parseInt(memory));
			ListOfProcess.add(pcb); // Adding the created PCB to the List of all processes
			JobQueue.enqueue(pcb); // Queuing the PCB so it can be processes later
		}
		s.close();
	}

	public void run() {
		read(); // The thread starts by reading the file and filling the data structure
		flag.setFlag(true); // Notifies the Transporting thread that it finished reading
		return;
	}
}
