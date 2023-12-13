import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        List<PCB> jobQueue = new ArrayList<>();
        List<PCB> readyQueue = new ArrayList<>();
        int memorySize = 8192; // Total memory size in MB

        FileReaderThread fileReaderThread = new FileReaderThread(jobQueue, "job.txt");
        LoaderThread loaderThread = new LoaderThread(jobQueue, readyQueue, memorySize);

        fileReaderThread.start();
        loaderThread.start();

        try {
            fileReaderThread.join();
            loaderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CPUScheduler cpuScheduler = new CPUScheduler(readyQueue);

        // Execute FCFS
        cpuScheduler.executeFCFS();

        // Execute SJF
        cpuScheduler.executeSJF();

        // Execute Round Robin with time slice = 3
        cpuScheduler.executeRoundRobin(3);

        // Execute Round Robin with time slice = 5
        cpuScheduler.executeRoundRobin(5);
    }
}