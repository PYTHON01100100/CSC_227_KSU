import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LoaderThread extends Thread {
    private List<PCB> jobQueue;
    private List<PCB> readyQueue;
    private int memorySize;

    public LoaderThread(List<PCB> jobQueue, List<PCB> readyQueue, int memorySize) {
        this.jobQueue = jobQueue;
        this.readyQueue = readyQueue;
        this.memorySize = memorySize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (jobQueue) {
                if (jobQueue.isEmpty()) {
                    break;
                }

                PCB pcb = jobQueue.get(0);
                if (pcb.getMemoryRequired() <= memorySize) {
                    readyQueue.add(pcb);
                    jobQueue.remove(0);
                    memorySize -= pcb.getMemoryRequired();
                    System.out.println("Job loaded to ready queue: " + pcb.getProcessID());
                }
            }

            try {
                Thread.sleep(100); // Adjust the delay as per your requirements
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
