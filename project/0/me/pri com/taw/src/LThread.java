import java.util.ArrayList;

public class LThread extends Thread{
    private ArrayList<PCB> data;
    private ArrayList<PCB> readyQ;
    private final int MAX_MEMORY = 8192;


    public LThread(ArrayList<PCB> data , ArrayList<PCB> readyQ) {
        this.data = data;
        this.readyQ = readyQ;

    }

    public void run() {
        int cMem = 0;
        for(int i = 0; i < data.size(); i++) {
            cMem += data.get(i).getMemory();
            if(cMem < MAX_MEMORY) {
                readyQ.add(data.get(i));
                readyQ.get(i).setState("ready");

            }
            else {
                cMem -= data.get(i).getMemory();
                break;
            }
        }
    }
}