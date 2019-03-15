public class Main {

    public static void main(String[] args) {


        File mo_file;

        //construct file reader here
        //read file given name here
        mo_file = new File("data/victoria_lake.txt");


        IntMap mo_terrain_map = new IntMap(mo_file.width, mo_file.height);

        TerrainCost mo_cost = new TerrainCost();

        //construct converter here
        TerrainConverter mo_converter = new TerrainConverter(mo_cost);

        // konvertieren der IntMap in Terrain-Cost-Matrix
        mo_converter.convert(mo_file.Map, mo_terrain_map);


        ;
    }





}

// Dennis was here
// Christian was here
// Mikhail was here too ;-)