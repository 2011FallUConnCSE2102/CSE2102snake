import java.util.Iterator;
import java.util.LinkedList;

public class Setdifficulce {

	int x, y;
	static boolean all[][];
	static LinkedList<Node> Walllist = new LinkedList<Node>();
	static int s = 0, s1 = 0;

	public static void checkmap() {
		if (Map.map == 1) {
			setnormal();
		} else if (Map.map == 3) {
			sethard();
		} else {
			Node n;
			final LinkedList<Node> list1 = Setdifficulce.Walllist;
			final Iterator<Node> it1 = list1.iterator();
			while (it1.hasNext()) {
				n = it1.next();
				all[n.x][n.y] = false;
			}
			Walllist.clear();
		}

	}

	public static void sethard() {
		setnormal();
		s = s1 = 0;
		for (int i = 0; i <= 24; i++) {
			s = 12 * i;
			s1 = 0;
			Walllist.addFirst(new Node(s, s1));
			all[s][s1] = true;
		}
		s = s1 = 0;
		for (int i = 0; i <= 24; i++) {
			s = 12 * i;
			s1 = 444;
			Walllist.addFirst(new Node(s, s1));
			all[s][s1] = true;
		}
		s = s1 = 0;
		for (int i = 1; i <= 36; i++) {
			s = 0;
			s1 = 12 * i;
			Walllist.addFirst(new Node(s, s1));
			all[s][s1] = true;
		}
		s = s1 = 0;
		for (int i = 1; i <= 36; i++) {
			s = 288;
			s1 = 12 * i;
			Walllist.addFirst(new Node(s, s1));
			all[s][s1] = true;
		}
	}

	public static void setnormal() {
		final LinkedList<Node> list = Walllist;
		final Iterator<Node> it = list.iterator();
		Node n1;
		while (it.hasNext()) {
			n1 = it.next();
			if (n1.x == 192 && n1.y == 288)
				break;
			else {
				all[n1.x][n1.y] = false;
				it.remove();
			}
		}
		int wallnum, wallamount;
		wallnum = 9;
		wallamount = 5;
		for (int i = 0; i < wallamount; i++) {
			for (int j = 0; j < wallnum; j++) {
				switch (i) {
				case 0:
					s = 96 + 12 * j;
					s1 = 48;
					break;
				case 1:
					s = 96 + 12 * j;
					s1 = 408;
					break;
				case 2:
					s = 36;
					s1 = 180 + 12 * j;
					break;
				case 3:
					s = 252;
					s1 = 180 + 12 * j;
					break;
				case 4:
					s = 96 + 12 * j;
					s1 = 228;
					break;
				}
				Walllist.addFirst(new Node(s, s1));
				all[s][s1] = true;
			}
		}
	}

	public Setdifficulce() {
		all = new boolean[289][445];
		for (int i = 0; i < 288; ++i) {
			for (int j = 0; j < 444; ++j) {
				all[i][j] = false;
			}
		}
	}
}
