import static java.lang.Integer.parseInt;

class InputTerrainAdapter {

    private TerrainCost mo_cost;
    private InputData mo_input;

    InputTerrainAdapter(TerrainCost io_cost, InputData io_input) {

        mo_cost = io_cost;
        mo_input = io_input;
    }

    void mapTerrain(IntMap io_map) {

        int lv_x = 0;
        int lv_y = 0;

        for (String zeile : mo_input.Map) {

            lv_x = 0;

            if (zeile != null) {
                String[] lt_line = zeile.split("");

                for (String lv_char : lt_line) {
                    try {
                        io_map.setField(lv_x, lv_y, mo_cost.getCost(lv_char));
                    } catch (Exception ignored) {
                    }

                    lv_x++;
                }

                lv_y++;
            }

        }

    }


}
