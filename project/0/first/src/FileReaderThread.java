import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileReaderThread extends Thread {
    private List<PCB> jobQueue;
    private String filename;

    public FileReaderThread(List<PCB> jobQueue, String filename) {
        this.jobQueue = jobQueue;
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String processID = scanner.nextLine().trim();
                String[] processDetails = scanner.nextLine().split(",");
                int burstTime = Integer.parseInt(processDetails[0].trim());
                int memoryRequired = Integer.parseInt(processDetails[2].trim());

                PCB pcb = new PCB(processID, "New", burstTime, memoryRequired);
                jobQueue.add(pcb);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}