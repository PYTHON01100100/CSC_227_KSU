import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RFThread extends Thread{
    private ArrayList<PCB> data;

    public RFThread(ArrayList<PCB> data) {
        this.data = data;
    }
    public void run() {
        String fn = "C:\\Users\\d7oom\\Videos\\txt\\testdata1.txt";
        try {
        Scanner s = new Scanner(new File(fn));
          // Read data from the file
        int c = 1;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if(c % 2 == 0) {
                String[] info = line.split(",");
                int [] inf = new int[info.length];
                for(int i = 0; i < info.length; i++) {
                    inf[i] = Integer.parseInt(info[i].trim());

                }
                data.add(new PCB(inf[0] , inf[1] , inf[2] , "new"));
            }




            c++;
        }

        // Close the scanner
        s.close();
        }
        catch(Exception e) {

        }
    }
}
