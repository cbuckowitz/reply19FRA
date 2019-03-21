public class IntMap {


    public int[][] mt_map;

    public int mv_width;
    public int mv_heigth;


    public IntMap(int iv_width, int iv_heigt) {

        mv_heigth = iv_heigt;
        mv_width = iv_width;

        mt_map = new int[iv_width][iv_heigt];
    }

    public void setField(int x, int y, int value) throws Exception {
        if (x >= 0 && x < mv_width &&
            y >= 0 && y < mv_heigth) {
            mt_map[x][y] = value;
        }

        throw new Exception();
    }

    public int getField(int x, int y) throws Exception {

        if (x >= 0 && x < mv_width &&
            y >= 0 && y < mv_heigth) {

            if (mt_map[x][y] < 1) {
                throw  new Exception();
            }
            return mt_map[x][y];
        }

        throw new Exception();
    }

}
