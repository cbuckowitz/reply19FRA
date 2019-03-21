import java.io.*;

import static java.lang.Integer.parseInt;

public class OutputData {

    StepPath[] mt_path;


    OutputData(StepPath[] it_path) {

        mt_path = it_path;
    }


    void writeFile(String iv_filename) throws IOException {

        BufferedWriter lo_writer = new BufferedWriter(new FileWriter(iv_filename, false));

        for (StepPath mo_path : mt_path) {

            lo_writer.write(mo_path.asString() + "\n");

        }

        lo_writer.close();

    }

}
