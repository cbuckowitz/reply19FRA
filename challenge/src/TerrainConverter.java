import static java.lang.Integer.parseInt;

public class TerrainConverter {

    Object mo_converter;
    TerrainCost mo_cost;


    public TerrainConverter(TerrainCost io_cost) {
        mo_cost = io_cost;
    }

    public void convert(String[] it_filemap, IntMap io_terrain) {

        int lv_x = 0;
        int lv_y = 0;

        for (String zeile : it_filemap) {

            lv_x = 0;

            if (zeile != null) {
                String lt_line[] = zeile.split("");

                for (String lv_char : lt_line) {
                    try {
                        io_terrain.setField(lv_x, lv_y, mo_cost.getCost(lv_char));
                    } catch (Exception e) {
                    }

                    lv_x++;
                }

                lv_y++;
            }

        }
//
//        int length = it_filemap.length;
//        int counter = 0;
//
//        while ( counter < length ){
//            String zeile = it_filemap[counter];
//            int x = parseInt(Line[0]);
//            int y = parseInt(Line[1]);
//
//
//
//            int value = mo_cost.getCost(Line[2]);
//            try {
//                io_terrain.setField(x,y,value);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            counter ++;
//        }
//

    }


}
