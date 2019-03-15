import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NaiveOneStep {


    public NaiveOneStep() {

    }


    protected StepPath makePath(IntMap io_terrain_map, int iv_x, int iv_y) throws Exception {

        int lv_cost = io_terrain_map.getField(iv_x, iv_y);

        return new StepPath(iv_x, iv_y);

    }


    public StepPath[] computePaths(Customer[] it_customer, IntMap io_terrain_map) {

        ArrayList<StepPath> lt_steps;

        lt_steps = new ArrayList<StepPath>();

        for (Customer lo_customer : it_customer) {

            int lv_cost = Integer.MAX_VALUE;
            StepPath ro_path;

            int lv_cx = lo_customer.xAxis;
            int lv_cy = lo_customer.yAxis;

            try {

                StepPath lo_path = new StepPath(lv_cx - 1, lv_cy);
                lo_path.mv_cost = io_terrain_map.getField(lo_path.mv_x, lo_path.mv_y);
                lo_path.addStep('R');
                lt_steps.add(lo_path);

            } catch (Exception e) {

                try {

                    StepPath lo_path = new StepPath(lv_cx + 1, lv_cy);
                    lo_path.mv_cost = io_terrain_map.getField(lo_path.mv_x, lo_path.mv_y);
                    lo_path.addStep('L');
                    lt_steps.add(lo_path);

                } catch (Exception e2) {

                    try {

                        StepPath lo_path = new StepPath(lv_cx, lv_cy - 1);
                        lo_path.mv_cost = io_terrain_map.getField(lo_path.mv_x, lo_path.mv_y);
                        lo_path.addStep('D');
                        lt_steps.add(lo_path);

                    } catch (Exception e3) {

                        try {

                            StepPath lo_path = new StepPath(lv_cx, lv_cy + 1);
                            lo_path.mv_cost = io_terrain_map.getField(lo_path.mv_x, lo_path.mv_y);
                            lo_path.addStep('R');
                            lt_steps.add(lo_path);

                        } catch (Exception e4) {

                        }

                    }

                }

            }


        }


        return lt_steps.toArray(new StepPath[0]);
    }


}
