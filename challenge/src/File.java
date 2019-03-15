import java.io.*;

import static java.lang.Integer.parseInt;

public class File {

    public static int width = 0;
    public static int heigth = 0;
    public static int cust = 0;
    public static int reply = 0;
    public static String[] Customers;

    public File(FileName string){
        BufferedReader br = createReader(FileName);
    }

    public void readFile(){

        int counter = 0;
        int custCounter = 0;

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
                Customers[custCounter] = zeile;
                custCounter ++;
            }
        }
    }
}
