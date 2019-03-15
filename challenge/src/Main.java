import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {


    protected static void outputStepPath(String iv_filename, StepPath[] it_steps) {

        BufferedWriter lo_writer = null;
        try {
            lo_writer = new BufferedWriter(new FileWriter(iv_filename, false));

            for (StepPath lo_step : it_steps) {

                lo_writer.write(lo_step.asString() + "\n");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {

        String lv_filename = "data/1_victoria_lake.txt";


        File mo_file;

        //construct file reader here
        //read file given name here
        mo_file = new File(lv_filename);


        IntMap mo_terrain_map = new IntMap(mo_file.width, mo_file.height);

        TerrainCost mo_cost = new TerrainCost();

        //construct converter here
        TerrainConverter mo_terrain_converter = new TerrainConverter(mo_cost);

        // konvertieren der IntMap in Terrain-Cost-Matrix
        mo_terrain_converter.convert(mo_file.Map, mo_terrain_map);

        CustomerConverter mo_customer_converter = new CustomerConverter(mo_file.Customers);

        Customer[] lt_customer = mo_customer_converter.Customers;

        NaiveOneStep mo_onestep = new NaiveOneStep();
        StepPath[] lt_paths = mo_onestep.computePaths(lt_customer, mo_terrain_map);

        outputStepPath(lv_filename + ".output.txt", lt_paths);
    }


}

// Dennis was here
// Christian was here
// Mikhail was here too ;-)