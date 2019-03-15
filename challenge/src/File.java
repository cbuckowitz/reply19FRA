import java.io.*;

import static java.lang.Integer.parseInt;

public class File {

    public static BufferedReader br = null;
    public static int width = 0;
    public static int height = 0;
    public static int cust = 0;
    public static int reply = 0;
    public static String[] Customers;
    public static String[] Map;

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
            if ( counter == 1 ){
                String[] Line = zeile.split(" ");
                width =  parseInt(Line[0]);
                height =  parseInt(Line[1]);
                cust =  parseInt(Line[2]);
                reply =  parseInt(Line[3]);
            }
            else if ( counter <= cust && counter > 1 ){
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
