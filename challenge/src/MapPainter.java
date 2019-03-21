import processing.core.PApplet;

public class MapPainter {

    class MapColor {
        int r;
        int g;
        int b;

        MapColor(int iv_r, int iv_g, int iv_b) {
            r = iv_r;
            g = iv_g;
            b = iv_b;
        }

    }


    class MapRect {
        int x1;
        int y1;
        int x2;
        int y2;

        MapRect(int iv_x1, int iv_y1, int iv_x2, int iv_y2) {
            x1 = iv_x1;
            y1 = iv_y1;
            x2 = iv_x2;
            y2 = iv_y2;
        }

    }


    private IntMap mo_terrain;
    private Customer[] mt_customer;
    private PApplet p;

    private int mv_field_size;


    MapPainter(PApplet io_applet, IntMap io_terrain, Customer[] it_customer) {
        mo_terrain = io_terrain;
        mt_customer = it_customer;
        p = io_applet;

        mv_field_size = Math.min(p.width, p.height) / Math.max(io_terrain.mv_width, io_terrain.mv_heigth);

    }


    protected MapRect mapRect(int iv_x, int iv_y) {

        return new MapRect(
                iv_x * mv_field_size,
                iv_y * mv_field_size,
                mv_field_size,
                mv_field_size
        );
    }


    protected MapColor getTerrainColor(int iv_terrain) {

        switch (iv_terrain) {
            case 800:
                return new MapColor(0, 0, 255);
            case 200:
                return new MapColor(0, 0x40, 0);
            case 150:
                return new MapColor(0, 0x80, 0);
            case 120:
                return new MapColor(0, 0xC0, 0);
            case 100:
                return new MapColor(0x20, 0x20, 0x20);
            case 70:
                return new MapColor(0x40, 0x40, 0x40);
            case 50:
                return new MapColor(0x60, 0x60, 0x60);
            default:
                return new MapColor(0, 0, 0);
        }

    }


    void drawMap() {

        MapRect ls_rect;
        MapColor ls_color;

        p.noStroke();

        for (int x = 0; x < mo_terrain.mv_width; x++) {
            for (int y = 0; y < mo_terrain.mv_heigth; y++) {

                try {
                    ls_color = getTerrainColor(mo_terrain.getField(x, y));
                    p.fill(ls_color.r, ls_color.g, ls_color.b);
                } catch (Exception ignored) {
                    p.fill(255, 255, 255);
                }

                ls_rect = mapRect(x, y);
                p.rect(ls_rect.x1, ls_rect.y1, ls_rect.x2, ls_rect.y2);

            }
        }


    }


    void drawCustomers(Customer[] it_customer) {

        MapRect ls_rect;

        int lv_min_reward = Integer.MAX_VALUE;
        int lv_max_reward = 0;

        for (Customer lo_customer : it_customer) {
            if (lo_customer.reward > lv_max_reward) {
                lv_max_reward = lo_customer.reward;
            }
            if (lo_customer.reward < lv_min_reward) {
                lv_min_reward = lo_customer.reward;
            }
        }

        p.stroke(255, 255, 0, 192);

        for (Customer lo_customer : it_customer) {
            ls_rect = mapRect(lo_customer.xAxis, lo_customer.yAxis);
            p.strokeWeight(p.map(lo_customer.reward, lv_min_reward, lv_max_reward, mv_field_size, mv_field_size * 4));
            p.point(ls_rect.x1 + ls_rect.x2 / 2, ls_rect.y1 + ls_rect.y2 / 2);
        }

    }

    void drawPaths(StepPath[] it_path) {

        MapRect ls_rect;

        int lv_min_reward = Integer.MAX_VALUE;
        int lv_max_reward = 0;


        for (StepPath lo_path : it_path) {
            if (lo_path.mv_reward > lv_max_reward) {
                lv_max_reward = lo_path.mv_reward;
            }
            if (lo_path.mv_reward < lv_min_reward) {
                lv_min_reward = lo_path.mv_reward;
            }
        }


        p.stroke(255, 0, 255, 192);
        p.strokeWeight(mv_field_size * 2);

        for (StepPath lo_path : it_path) {

            ls_rect = mapRect(lo_path.mv_x, lo_path.mv_y);
            p.strokeWeight(p.map(lo_path.mv_reward, lv_min_reward, lv_max_reward, mv_field_size, mv_field_size * 4));
            p.point(ls_rect.x1 + ls_rect.x2 / 2, ls_rect.y1 + ls_rect.y2 / 2);

        }

    }

}
