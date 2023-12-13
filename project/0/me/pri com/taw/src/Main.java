import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<PCB> arr = new ArrayList<>();
        ArrayList<PCB> readyQ = new ArrayList<PCB>();
        
        // TODO Auto-generated method stub
        RFThread rf = new RFThread(arr);
        rf.start();
        
        try {
			rf.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        /*
        for(int i = 0; i < arr.size(); i++)
            System.out.println(arr.get(i));
         */
        
        LThread l = new LThread(arr , readyQ);
        l.start();
        
        try {
			l.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        
        
        /*
        System.out.println("-----------------------------------------------------");
        
        for(int i = 0; i < r.size(); i++)
            System.out.println(r.get(i));
            
        
        FCFS(readyQ);
        int sumW = 0 , sumT = 0;
        	PCB current = readyQ.get(i);
        	sumW += current.wt;
        	sumT += current.ta;
        }
        
        double avw = (double)sumW / readyQ.size() , avt = (double)sumT / readyQ.size();
        
        System.out.println(avw + "\n" + avt);
        
        SJF(readyQ);
        */
        RR(readyQ , 3);
    }

	private static void FCFS(ArrayList<PCB> readyQ) {
		int index = 0;
        for(int i = 0; i < readyQ.size(); i++) {
        	PCB current = readyQ.get(i);
        	current.setState("running");
        	System.out.println("process " + current.id + " is using the cpu");
        	
        	current.rst = index;
        	
        	index += current.bTime;
        	
        	
        	
        	if(current.rst != 0) {
        		current.wt += current.rst - current.rfn;
        	}
        	
        	current.setState("ready");
        	current.rfn = index;
        }
        
        for(int i = 0; i < readyQ.size(); i++) {
        	PCB current = readyQ.get(i);
        	current.ta = current.rfn;
        }
	}
    
    private static void SJF(ArrayList<PCB> readyQ) {
    	PCB arr[] = new PCB[readyQ.size()];
    	for(int i = 0; i < arr.length; i++) {
    		arr[i] = readyQ.get(i);
    	}
    	int i, j;
    	PCB temp;
        boolean swapped;
        for (i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].bTime > arr[j + 1].bTime) {
                     
                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
 
            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
        
        int index = 0;
        for(int in = 0; in < arr.length; in++) {
        	PCB current = arr[in];
        	current.setState("running");
        	System.out.println("process " + current.id + " is using the cpu");
        	
        	current.rst = index;
        	
        	index += current.bTime;
        	
        	
        	
        	if(current.rst != 0) {
        		current.wt += current.rst - current.rfn;
        	}
        	
        	current.setState("ready");
        	current.rfn = index;
        }
        
        for(int in = 0; in < readyQ.size(); in++) {
        	PCB current = arr[in];
        	current.ta = current.rfn;
        }
    }
    
    private static void RR(ArrayList<PCB> readyQ , int rr){
    	Queue<PCB> q = new LinkedList<>();
    	
    	for(int i = 0; i < readyQ.size(); i++)
    		q.add(readyQ.get(i));
    	
    	
    	int index = 0;
    	int remainingBurstTime;
    	while(!q.isEmpty()) {
    		 
    		if(q.size() == 1)
    			break;
    		
    		 PCB current = q.poll();
    		 
    		 current.rst = index;
    		 if(current.bTime > rr)
    			 index += rr;
    		 else {
    			 index += current.bTime;
    		 }
    		 
    		 
	    	 if(current.rst != 0) {
	         		current.wt += current.rst - current.rfn;
	         }
    		 
    		 
    		 
    		 
    		
    		 current.rfn = index;
    		 
             remainingBurstTime = current.bTime - rr;
             if (remainingBurstTime <= 0) {
                 // Process is completed
                 current.setState("completed");
                 current.bTime = 0;   } 
             
             else {
                 // Process is not completed yet
                 current.bTime = remainingBurstTime;
                 q.offer(current);
             }
             System.out.println("Executing process: " + current.id);
    	}
    	
    	PCB c = q.peek();
    	c.rst = index;
    	c.wt += c.rst - c.rfn;
    	
    	while(!q.isEmpty()) {
    		PCB current = q.poll();
    		
    		remainingBurstTime = current.bTime - rr;
            if (remainingBurstTime <= 0) {
                // Process is completed
                current.setState("completed");
                current.bTime = 0;   } 
            
            else {
                // Process is not completed yet
                current.bTime = remainingBurstTime;
                q.offer(current);
            }
            System.out.println("Executing process: " + current.id);
    		
    	}
    	
    	for(int i = 0; i < readyQ.size(); i++) {
        	PCB current = readyQ.get(i);
        	current.ta = current.rfn;
        }
    	
    	System.out.println(readyQ.get(3).wt);
    }
} 