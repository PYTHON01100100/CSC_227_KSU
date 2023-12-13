import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class CPUScheduler {
    private List<PCB> readyQueue;

    public CPUScheduler(List<PCB> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public void executeFCFS() {
        System.out.println("\nExecuting FCFS Scheduling Algorithm");
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (PCB pcb : readyQueue) {
            pcb.setProcessState("Ready");
        }

        for (PCB pcb : readyQueue) {
            System.out.println("\nCurrent Time: " + currentTime);
            System.out.println("Executing Process: " + pcb.getProcessID());

            pcb.setProcessState("Running");
            int startBurstTime = currentTime;
            currentTime += pcb.getBurstTime();
            int endBurstTime = currentTime;

            pcb.setProcessState("Terminated");
            int waitingTime = startBurstTime;
            int turnaroundTime = endBurstTime;

            System.out.println("Process " + pcb.getProcessID() + " executed from " + startBurstTime + " to " + endBurstTime);
            System.out.println("Waiting Time: " + waitingTime);
            System.out.println("Turnaround Time: " + turnaroundTime);

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
        }

        double avgWaitingTime = (double) totalWaitingTime / readyQueue.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / readyQueue.size();

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    public void executeSJF() {
        System.out.println("\nExecuting SJF Scheduling Algorithm");
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (PCB pcb : readyQueue) {
            pcb.setProcessState("Ready");
        }

        readyQueue.sort((pcb1, pcb2) -> {
            if (pcb1.getBurstTime() == pcb2.getBurstTime()) {
                return pcb1.getProcessID().compareTo(pcb2.getProcessID());
            }
            return pcb1.getBurstTime() - pcb2.getBurstTime();
        });

        for (PCB pcb : readyQueue) {
            System.out.println("\nCurrent Time: " + currentTime);
            System.out.println("Executing Process: " + pcb.getProcessID());

            pcb.setProcessState("Running");
            int startBurstTime = currentTime;
            currentTime += pcb.getBurstTime();
            int endBurstTime = currentTime;

            pcb.setProcessState("Terminated");
            int waitingTime = startBurstTime;
            int turnaroundTime = endBurstTime;

            System.out.println("Process " + pcb.getProcessID() + " executed from " + startBurstTime + " to " + endBurstTime);
            System.out.println("Waiting Time: " + waitingTime);
            System.out.println("Turnaround Time: " + turnaroundTime);

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
        }

        double avgWaitingTime = (double) totalWaitingTime / readyQueue.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / readyQueue.size();

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    public void executeRoundRobin(int timeSlice) {
        System.out.println("\nExecuting Round Robin Scheduling Algorithm with Time Slice = " + timeSlice);
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (PCB pcb : readyQueue) {
            pcb.setProcessState("Ready");
        }

        List<PCB> executionQueue = new ArrayList<>(readyQueue);
        int remainingProcesses = executionQueue.size();

        while (remainingProcesses > 0) {
            for (PCB pcb : executionQueue) {
                if (pcb.getBurstTime() > 0) {
                    System.out.println("\nCurrent Time: " + currentTime);
                    System.out.println("Executing Process: " + pcb.getProcessID());

                    pcb.setProcessState("Running");
                    int startBurstTime = currentTime;
                    int executionTime = Math.min(timeSlice, pcb.getBurstTime());
                    currentTime += executionTime;
                    int endBurstTime = currentTime;

                    pcb.setBurstTime(pcb.getBurstTime() - executionTime);
                    if (pcb.getBurstTime() == 0) {
                        pcb.setProcessState("Terminated");
                        remainingProcesses--;
                    } else {
                        pcb.setProcessState("Ready");
                    }

                    int waitingTime = startBurstTime - pcb.getBurstTime();
                    int turnaroundTime = endBurstTime - pcb.getBurstTime();

                    System.out.println("Process " + pcb.getProcessID() + " executed from " + startBurstTime + " to " + endBurstTime);
                    System.out.println("Waiting Time: " + waitingTime);
                    System.out.println("Turnaround Time: " + turnaroundTime);

                    totalWaitingTime += waitingTime;
                    totalTurnaroundTime += turnaroundTime;
                }
            }
        }

        double avgWaitingTime = (double) totalWaitingTime / readyQueue.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / readyQueue.size();

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
