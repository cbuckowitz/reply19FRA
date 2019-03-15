public class IntMap {


    public int[][] mt_map;


    public IntMap(int iv_width, int iv_heigt) {

        mt_map = new int[iv_width][iv_heigt];
    }

    public void setField(int x, int y, int value) {
        mt_map[x][y] = value;
    }

    public int getField(int x, int y) {
        return mt_map[x][y];
    }

}
