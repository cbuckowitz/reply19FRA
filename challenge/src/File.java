import java.io.*;

import static java.lang.Integer.parseInt;

public class File {

    public static int width = 0;
    public static int heigth = 0;
    public static int cust = 0;
    public static int reply = 0;
    public static String[] Customers;
    public static BufferedReader br = null;

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

        String zeile = " ";

        while( (zeile = br.readLine()) != null ){
            counter ++;
            if ( counter == 1 ){
                String[] Line = zeile.split(" ");
                width =  parseInt(Line[0]);
                heigth =  parseInt(Line[1]);
                cust =  parseInt(Line[2]);
                reply =  parseInt(Line[3]);
            }

            if ( counter <= cust && counter > 1 ){
                Customers[customerCounter] = zeile;
                customerCounter ++;
            }



        }
    }
}
