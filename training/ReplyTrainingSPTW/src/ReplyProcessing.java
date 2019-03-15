import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import processing.core.PApplet;

class Move {
	public int dx;
	public int dy;
	public double dist;

	public Move(int iv_dx, int iv_dy, double iv_dist) {
		dx = iv_dx;
		dy = iv_dy;
		dist = iv_dist;
	}
}

class Coordinate {

	protected int mv_x;
	protected int mv_y;
	protected String mv_key;
	protected int mv_hash;

	public Coordinate(int x, int y) {
		mv_x = x;
		mv_y = y;
		mv_key = mv_x + "," + mv_y;
		mv_hash = mv_key.hashCode();
	}

	public int x() {
		return mv_x;
	}

	public int y() {
		return mv_y;
	}

	@Override
	public int hashCode() {
		return mv_hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (mv_x != other.mv_x)
			return false;
		if (mv_y != other.mv_y)
			return false;
		return true;
	}
}

class Node {

	protected Coordinate mo_id;

	protected Node mo_parent;

	protected double mv_g;
	protected double mv_h;
	protected double mv_f;
	protected double mv_parent_dist;

	public Node(Coordinate id, Node parent, double parent_dist, double h) {
		mo_id = id;
		mo_parent = parent;
		mv_parent_dist = parent_dist;
		if (parent != null) {
			mv_g = parent.g() + mv_parent_dist;
		} else {
			mv_g = mv_parent_dist;
		}
		mv_h = h;
		mv_f = mv_g + mv_h;
	}

	public Coordinate id() {
		return mo_id;
	}

	public double g() {
		return mv_g;
	}

	public double f() {
		return mv_f;
	}

	public double pathLen() {
		double lv_len = 0;

		for (Node lo_node = this; lo_node != null; lo_node = lo_node.mo_parent) {
			lv_len += lo_node.mv_parent_dist;
		}

		return lv_len;
	}

	public int pathHops() {
		int lv_hops = 0;

		for (Node lo_node = this; lo_node != null; lo_node = lo_node.mo_parent) {
			lv_hops++;
		}

		return lv_hops;
	}

	public Path getPath() {
		Path lo_path = new Path();

		for (Node lo_node = this; lo_node != null; lo_node = lo_node.mo_parent) {
			lo_path.addNode(lo_node);
		}

		return lo_path;
	}

}

class NodeComparator implements Comparator<Node> {
	public int compare(Node first, Node second) {
		if (first.f() > second.f())
			return 1;
		if (second.f() > first.f())
			return -1;
		return 0;
	}
}

class AStar {

	protected Map<Coordinate, Node> mm_closed = new HashMap<Coordinate, Node>();
	protected Map<Coordinate, Node> mm_open = new HashMap<Coordinate, Node>();
	protected Queue<Node> mq_open = new PriorityQueue<Node>(1000, new NodeComparator());

	protected Coordinate mo_target;

//	closedList
//	openQueue

	public AStar(int start_x, int start_y, int target_x, int target_y) {

		mo_target = new Coordinate(target_x, target_y);

		addNode(null, start_x, start_y, 0);
	}

	protected double heuristic(Coordinate id) {

		int dx = mo_target.x() - id.x();
		int dy = mo_target.y() - id.y();

		return Math.sqrt(dx * dx + dy * dy) * 1.5;

//		return Math.abs(dx) + Math.abs(dy);
	}

	public Node addNode(Node parent, int x, int y, double distance) {

		Coordinate id = new Coordinate(x, y);

		if (mm_closed.containsKey(id)) {
			return null;
		}

		Node new_node = new Node(id, parent, distance, heuristic(id));

		if (mm_open.containsKey(id)) {

			Node old_open = mm_open.get(id);

			if (new_node.f() < old_open.f()) {
				// replace old open
				mm_open.replace(id, new_node);
				// remove from queue
				mq_open.remove(old_open);
				mq_open.add(new_node);
			}

		} else {
			mm_open.put(id, new_node);
			mq_open.add(new_node);
		}

		return new_node;
	}

	public Node nextNode() {

		Node node = mq_open.poll();

		if (node != null) {
			mm_open.remove(node.id());
			mm_closed.put(node.id(), node);
		}

		return node;
	}

}

class PathComparator implements Comparator<Node> {
	public int compare(Node first, Node second) {
		if (first.g() > second.g())
			return 1;
		if (second.g() > first.g())
			return -1;
		return 0;
	}
}

class Path {

	protected Queue<Node> mq_node = new PriorityQueue<Node>(15000, new PathComparator());

	public void addNode(Node io_node) {
		mq_node.add(io_node);
	}

	public void compress() {

	}

	public int getLen() {
		return mq_node.size();
	}

	public Coordinate[] getPoints() {

		Coordinate[] lt_point = new Coordinate[mq_node.size()];
		int lv_index = 0;

		for (Node lo_node = mq_node.poll(); lo_node != null; lo_node = mq_node.poll()) {
			lt_point[lv_index++] = lo_node.id();
		}

		return lt_point;

	}

}

class Triangle {
	public int x1, y1, x2, y2, x3, y3;

	public Triangle(int[] it_points) {
		x1 = it_points[0];
		y1 = it_points[1];
		x2 = it_points[2];
		y2 = it_points[3];
		x3 = it_points[4];
		y3 = it_points[5];
	}

	public boolean intersects_point(int x, int y) {

		double ABC = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
		double ABP = Math.abs(x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
		double APC = Math.abs(x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
		double PBC = Math.abs(x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));

		return ABP + APC + PBC == ABC;
	}

	public boolean intersectsLine(int ix1, int iy1, int ix2, int iy2) {

		return Line2D.linesIntersect(x1, y1, x2, y2, ix1, iy1, ix2, iy2)
				|| Line2D.linesIntersect(x1, y1, x3, y3, ix1, iy1, ix2, iy2)
				|| Line2D.linesIntersect(x2, y2, x3, y3, ix1, iy1, ix2, iy2);

	}

	public int min_x() {
		return Math.min(Math.min(x1, x2), x3);
	}

	public int max_x() {
		return Math.max(Math.max(x1, x2), x3);
	}

	public int min_y() {
		return Math.min(Math.min(y1, y2), y3);
	}

	public int max_y() {
		return Math.max(Math.max(y1, y2), y3);
	}

}

public class ReplyProcessing extends PApplet {

//	BufferedReader mo_reader;

	String mv_input_file;

//	Move[] mt_moves = { 
//			new Move(0, -5, 5), new Move(5, 0, 5), new Move(0, 5, 5), new Move(-5, 0, 5),
//
//			new Move(-10, -10, Math.sqrt(200)), new Move(-10, 10, Math.sqrt(200)), new Move(10, -10, Math.sqrt(200)), new Move(10, 10, Math.sqrt(200)),

	Move[] mt_moves = { new Move(0, -1, 1), new Move(-1, 0, 1), new Move(1, 0, 1), new Move(0, 1, 1),
//			new Move(-1, -1, Math.sqrt(2)), new Move(-1, 1, Math.sqrt(2)), new Move(1, -1, Math.sqrt(2)),
//			new Move(1, 1, Math.sqrt(2))

//			 new Move(-5, 0, 5), new Move(0, -5, 5), new Move(5, 0, 5), new Move(0, 5, 5), 
//			 new Move(-10, -10, Math.sqrt(200)), new Move(-10, 10, Math.sqrt(200)),
//			new Move(10, -10, Math.sqrt(200)), new Move(10, 10, Math.sqrt(200)) 			
//			
			new Move(-2, -1, Math.sqrt(5)), new Move(-1, -2, Math.sqrt(5)), new Move(-2, 1, Math.sqrt(5)),
			new Move(-1, 2, Math.sqrt(5)), new Move(2, -1, Math.sqrt(5)), new Move(1, -2, Math.sqrt(5)),
			new Move(2, 1, Math.sqrt(5)), new Move(1, 2, Math.sqrt(5)), };

	int mv_triangles;
	int mv_grid_step = 50;
	int mv_grid_line;

	double mv_path_len = 0;
	int mv_path_hops = 0;

	int[] mt_start = new int[2];
	int[] mt_target = new int[2];

	int[] mt_lim_rect = { 0, 0, 0, 0 };

	Triangle[] mt_triangle;

	AStar mo_astar;

	public static void main(String[] args) {
		PApplet.main("ReplyProcessing");

	}

	public void settings() {
		size(1200, 1000);

	}

	protected void record_lim(int iv_x, int iv_y) {
		if (iv_x < mt_lim_rect[0]) {
			mt_lim_rect[0] = iv_x;
		}
		if (iv_y < mt_lim_rect[1]) {
			mt_lim_rect[1] = iv_y;
		}
		if (iv_x > mt_lim_rect[2]) {
			mt_lim_rect[2] = iv_x;
		}
		if (iv_y > mt_lim_rect[2]) {
			mt_lim_rect[3] = iv_y;
		}
	}

	protected void load_triangles(String iv_filename) {

		mv_input_file = iv_filename;

		BufferedReader lo_reader = createReader(iv_filename);

		String lv_line_start;
		String lv_line_num;
		String lv_line_triangle;

		try {
			lv_line_start = lo_reader.readLine();

			String[] lt_str_start = lv_line_start.split(" ");

			mt_start[0] = parseInt(lt_str_start[0]);
			mt_start[1] = parseInt(lt_str_start[1]);
			mt_target[0] = parseInt(lt_str_start[2]);
			mt_target[1] = parseInt(lt_str_start[3]);
			record_lim(mt_start[0], mt_start[1]);
			record_lim(mt_target[0], mt_target[1]);

			String[] lt_str_triangle;

			lv_line_num = lo_reader.readLine();

			mv_triangles = parseInt(lv_line_num);

			mt_triangle = new Triangle[mv_triangles];

			int[] lt_tpoints = new int[6];

			for (int i = 0; i < mv_triangles; i++) {
				lv_line_triangle = lo_reader.readLine();
				lt_str_triangle = lv_line_triangle.split(" ");

				for (int j = 0; j < 6; j++) {
					lt_tpoints[j] = parseInt(lt_str_triangle[j]);
				}
				for (int j = 0; j < 3; j++) {
					record_lim(lt_tpoints[j * 2], lt_tpoints[j * 2 + 1]);
				}
				mt_triangle[i] = new Triangle(lt_tpoints);

			}

			System.out.println(mv_triangles);
			System.out.println(Arrays.toString(mt_start));
			System.out.println(Arrays.toString(mt_target));
			System.out.println(Arrays.toString(mt_lim_rect));

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	protected int map_x(int iv_x) {
		return (int) map(iv_x, mt_lim_rect[0], mt_lim_rect[2], 0, width);
	}

	protected int map_y(int iv_y) {
		return (int) map(iv_y, mt_lim_rect[1], mt_lim_rect[3], 0, height);
	}

	protected boolean map_blocked(int iv_x, int iv_y) {

		for (int i = 0; i < mv_triangles; i++) {
			if (mt_triangle[i].intersects_point(iv_x, iv_y)) {
				return true;
			}
		}

		return false;
	}

	protected boolean intersectsObstacle(int x1, int y1, int x2, int y2) {

		for (int i = 0; i < mv_triangles; i++) {
			if (mt_triangle[i].intersectsLine(x1, y1, x2, y2)) {
				return true;
			}
		}

		return false;

	}

	protected void draw_triangles() {

//		int[] lt_t = new int[6];
//		int[] lt_mw = { mt_lim_rect[0], mt_lim_rect[2], 0, width };
//		int[] lt_mh = { mt_lim_rect[1], mt_lim_rect[3], 0, height };

		Triangle t;

		for (int i = 0; i < mv_triangles; i++) {
//			for (int j = 0; j < 3; j++) {
////				lt_t[j * 2] = (int) map(mt_triangles[i][j * 2], lt_mw[0], lt_mw[1], lt_mw[2], lt_mw[3]);
////				lt_t[j * 2 + 1] = (int) map(mt_triangles[i][j * 2 + 1], lt_mh[0], lt_mh[1], lt_mh[2], lt_mh[3]);
//				lt_t[j * 2] = map_x(mt_triangles[i][j * 2]);
//				lt_t[j * 2 + 1] = map_x(mt_triangles[i][j * 2 + 1]);
//			}
//			triangle(lt_t[0], lt_t[1], lt_t[2], lt_t[3], lt_t[4], lt_t[5]);
			t = mt_triangle[i];
			triangle(map_x(t.x1), map_y(t.y1), map_x(t.x2), map_y(t.y2), map_x(t.x3), map_y(t.y3));
		}
	}

	protected void draw_startstop() {
		strokeWeight(20);
		stroke(255, 0, 0, 96);
		point(map_x(mt_start[0]), map_y(mt_start[1]));

		stroke(0, 255, 0, 96);
		point(map_x(mt_target[0]), map_y(mt_target[1]));
	}

	protected void draw_grid_line(int y) {
		strokeWeight(1);
		stroke(128, 128, 0);

		for (int x = mt_lim_rect[0]; x < mt_lim_rect[2]; x += mv_grid_step) {
			if (map_blocked(x, y)) {
				stroke(255, 0, 0);
			} else {
				stroke(0, 255, 0);
			}

			point(map_x(x), map_y(y));
		}

		point(map_x(mt_start[0]), map_y(mt_start[1]));
		point(map_x(mt_target[0]), map_y(mt_target[1]));
	}

	protected boolean nodeIsTarget(Node io_node) {
		if (io_node != null) {
			return io_node.id().x() == mt_target[0] && io_node.id().y() == mt_target[1];
		} else {
			return false;
		}
	}

	protected void outputTarget(Node io_node) {

		mv_path_len = io_node.pathLen();
		mv_path_hops = io_node.pathHops();

		textSize(32);
		fill(64, 256, 64);
		text("PATH " + mv_path_hops + " " + mv_path_len, 100, 100);

		Path lo_path = io_node.getPath();

		stroke(255, 128, 128);

		try {
			BufferedWriter lo_writer = new BufferedWriter(new FileWriter(mv_input_file + ".output.txt", false));

			lo_writer.write(lo_path.getLen() + "\n");

			for (Coordinate lo_point : lo_path.getPoints()) {
				point(map_x(lo_point.x()), map_y(lo_point.y()));

				lo_writer.write(lo_point.x() + " " + lo_point.y() + "\n");

			}

			lo_writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void move_astar() {

		strokeWeight(1);

		Node lo_node = mo_astar.nextNode();

		if (lo_node != null) {
			int x = lo_node.id().x();
			int y = lo_node.id().y();

			int nx;
			int ny;

			stroke(0, 0, 255);

			for (Move m : mt_moves) {

				nx = x + m.dx;
				ny = y + m.dy;
				if (!intersectsObstacle(x, y, nx, ny)) {

//				if (!map_blocked(nx, ny)) {
					Node lo_new_node = mo_astar.addNode(lo_node, nx, ny, m.dist);
					if (nodeIsTarget(lo_new_node)) {
						outputTarget(lo_new_node);
					}
					point(map_x(nx), map_y(ny));

				}

			}

			stroke(0, 255, 0);
			point(map_x(x), map_y(y));

		}

	}

	public void setup() {
		load_triangles("data/input_1.txt");

		draw_triangles();

		draw_startstop();

		mo_astar = new AStar(mt_start[0], mt_start[1], mt_target[0], mt_target[1]);

		mv_grid_line = mt_lim_rect[1];

	}

	public void draw() {
//		draw_triangles();
//		ellipse(width / 2, height / 2, second(), second());

//		draw_grid_line(mv_grid_line += 10);

		if (mv_path_len == 0) {
			for (int i = 0; i < 25; i++) {
				move_astar();
			}

		}

	}

}
