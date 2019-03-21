import processing.core.PApplet;

import java.io.IOException;

public class Main extends PApplet {

    private InputData mo_input_data;
    private IntMap mo_terrain_map;
    private Customer[] mt_customer;
    private InputTerrainAdapter mo_input_terrain_adapter;
    private StepPath mo_path;
    private MapPainter mo_map_painter;

    private static String gv_input_filename;

    public static void main(String[] args) {
        PApplet.main("Main");

        gv_input_filename = args[0];
    }

    public void settings() {
        size(1200, 1000);

    }

    public void setup() {
//        String lv_input_filename = "data/1_victoria_lake.txt";

        //get the input data
        try {

            // Dateninstanz aus Input Datei erzeugen
            mo_input_data = new InputData(gv_input_filename);

            mo_terrain_map = new IntMap(mo_input_data.width, mo_input_data.height);

            // konvertieren des Input Terrain  in Terrain-Cost-Map
            new InputTerrainAdapter(new TerrainCost(), mo_input_data).mapTerrain(mo_terrain_map);

            //Customer Input Daten in Customer Instanzen umsetzen
            mt_customer = new InputCustomerAdapter(mo_input_data).get_customer();

            NaiveOneStep mo_onestep = new NaiveOneStep(mo_terrain_map, mt_customer);
            mo_onestep.computePaths();
            StepPath[] lt_naive_paths = mo_onestep.getPrioPaths(mo_input_data.reply);

            new OutputData(lt_naive_paths).writeFile(gv_input_filename + ".output.txt");

            mo_map_painter = new MapPainter(this, mo_terrain_map, mt_customer);

            mo_map_painter.drawMap();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw() {
//		draw_triangles();
//		ellipse(width / 2, height / 2, second(), second());

//		draw_grid_line(mv_grid_line += 10);


    }

    protected void read_input(String iv_filename) {

    }

    protected void write_output(String iv_filename) {

    }


}
