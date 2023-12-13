public class PCB {
    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    int id;
    int bTime;
    private int memory;
    private String state;

    int rst , rfn , wt , ta;

    public PCB(int id, int bTime, int memory, String state) {
        this.id = id;

        this.bTime = bTime;

        this.memory = memory;

        this.state = state;

        wt = 0;
    }

    public String toString() {
        return "Job " + this.id + " :" + "\nbTime : " + this.bTime + " , memory :" + this.memory + "\nstate = " + this.state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}