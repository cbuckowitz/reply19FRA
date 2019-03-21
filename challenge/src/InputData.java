import java.io.*;

import static java.lang.Integer.parseInt;
import static processing.core.PApplet.createReader;

class InputData {

    int width = 0;
    int height = 0;
    private int cust = 0;
    int reply = 0;
    String[] Customers;
    String[] Map;

    InputData(String iv_filename) throws IOException {

        this.readFile(iv_filename);
    }

    private void readFile(String iv_filename) throws IOException {

        BufferedReader lo_reader;


        lo_reader = new BufferedReader(new FileReader(iv_filename));

        int lv_line = 0;
        int lv_customer_count = 0;
        int lv_map_count = 0;

        String lv_zeile = " ";

        while ((lv_zeile = lo_reader.readLine()) != null) {
            if (lv_line == 0) {
                String[] Line = lv_zeile.split(" ");
                width = parseInt(Line[0]);
                height = parseInt(Line[1]);
                cust = parseInt(Line[2]);
                reply = parseInt(Line[3]);

                Customers = new String[cust];
                Map = new String[height];

            } else if (lv_customer_count < cust && lv_line >= 1) {
                Customers[lv_customer_count++] = lv_zeile;
            } else if (lv_map_count <= height && lv_line > 1 && lv_line > cust) {
                Map[lv_map_count++] = lv_zeile;
            }

            lv_line++;
        }
    }
}
