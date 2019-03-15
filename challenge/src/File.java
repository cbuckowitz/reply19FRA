import java.io.*;

import static java.lang.Integer.parseInt;

public class File {

    public BufferedReader br = null;
    public int width = 0;
    public int height = 0;
    public int cust = 0;
    public int reply = 0;
    public String[] Customers;
    public String[] Map;

    public File(String FileName){
        FileReader fr = null;
        try {
            fr = new FileReader(FileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(fr);
    }

    public void readFile() throws IOException {

        int counter = 0;
        int customerCounter = 0;
        int MapCounter = 0;

        String zeile = " ";


        while( (zeile = br.readLine()) != null ){
            if ( counter == 0 ){
                String[] Line = zeile.split(" ");
                width =  parseInt(Line[0]);
                height =  parseInt(Line[1]);
                cust =  parseInt(Line[2]);
                reply =  parseInt(Line[3]);

                Customers = new String[cust];
                Map = new String[height];

            }
            else if ( counter < cust && counter >= 1 ){
                Customers[customerCounter] = zeile;
                customerCounter ++;
            }
            else if ( counter <= height && counter > 1 && counter > cust ){
                Map[MapCounter] = zeile;
                MapCounter++;
            }

            counter ++;
        }
    }
}
