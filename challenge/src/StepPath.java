public class StepPath {


    public int mv_reward;
    public int mv_x;
    public int mv_y;

    protected String mv_path;


    public StepPath(int iv_x, int iv_y) {

        mv_x = iv_x;
        mv_y = iv_y;


        mv_path = iv_x + " " + iv_y + " ";

    }


    public void addStep(char iv_dir) {

        mv_path += iv_dir;

    }


    public String asString() {

        return mv_path;

    }


}
