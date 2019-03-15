import static java.lang.Integer.parseInt;

public class TerrainConverter {

    Object mo_converter;
    TerrainCost Cost = new TerrainCost();


    public TerrainConverter( TerrainCost io_cost) {
        TerrainCost mo_cost = io_cost;
    }

    public void convert(String[] it_filemap, IntMap io_terrain) {

        int length = it_filemap.length;
        int counter = 0;

        while ( counter < length ){
            String zeile = it_filemap[counter];
            String Line[] = zeile.split(" ");
            int x = parseInt(Line[0]);
            int y = parseInt(Line[1]);
            int value = Cost.getCost(Line[2]);
            io_terrain.setField(x,y,value);
            counter ++;
        }


    }




}
