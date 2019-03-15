import java.util.*;

public class NaiveOneStep {


    public NaiveOneStep() {

    }


    protected StepPath makePath(IntMap io_terrain_map, int iv_x, int iv_y) throws Exception {

        int lv_cost = io_terrain_map.getField(iv_x, iv_y);

        return new StepPath(iv_x, iv_y);

    }


    class PathRewardComparator implements Comparator<StepPath> {
        public int compare(StepPath first, StepPath second) {
            if (first.mv_reward > second.mv_reward)
                return 1;
            if (second.mv_reward > first.mv_reward)
                return -1;
            return 0;
        }
    }

    public StepPath[] prioPaths(StepPath[] it_path, int iv_offices) {

        ArrayList<StepPath> lt_prio;

        lt_prio = new ArrayList<StepPath>();

        Queue<StepPath> lq_path = new PriorityQueue<StepPath>(1000, new PathRewardComparator());


        for (StepPath lo_path : it_path) {
            lq_path.add(lo_path);
        }

        for (int i = 0; i < iv_offices; i++) {

            StepPath lo_path = lq_path.poll();
            if (lo_path != null) {
                if (lo_path.mv_reward > 0) {
                    lt_prio.add(lo_path);

                }
            }
        }

        return lt_prio.toArray(new StepPath[0]);
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
                lo_path.mv_reward = lo_customer.reward + io_terrain_map.getField(lv_cx, lv_cy);
                lo_path.addStep('R');
                lt_steps.add(lo_path);

            } catch (Exception e) {

                try {

                    StepPath lo_path = new StepPath(lv_cx + 1, lv_cy);
                    lo_path.mv_reward = lo_customer.reward + io_terrain_map.getField(lv_cx, lv_cy);
                    lo_path.addStep('L');
                    lt_steps.add(lo_path);

                } catch (Exception e2) {

                    try {

                        StepPath lo_path = new StepPath(lv_cx, lv_cy - 1);
                        lo_path.mv_reward = lo_customer.reward + io_terrain_map.getField(lv_cx, lv_cy);
                        lo_path.addStep('D');
                        lt_steps.add(lo_path);

                    } catch (Exception e3) {

                        try {

                            StepPath lo_path = new StepPath(lv_cx, lv_cy + 1);
                            lo_path.mv_reward = lo_customer.reward + io_terrain_map.getField(lv_cx, lv_cy);
                            lo_path.addStep('U');
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
