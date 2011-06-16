import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

//*********************************************************
//----------------

//	Developed by Jack Fate,Reice,CrystalDream.
//	Copyright 2008 Jack Fate(ZhanKaijie),Reice(ZhaoChenjun),CrystalDream(DaiJun).
//	Some rights reserved.

//	You aren't allowed to remove the Copyright!
//------------------

//	For the full GPL please read "COPYING.txt"
//------------------

//	This program is free software: you can redistribute it and/or modify
//	it under the terms of the GNU General Public License as published by
//	the Free Software Foundation, either version 3 of the License, or
//	(at your option) any later version.

//	This program is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.

//	You should have received a copy of the GNU General Public License
//	along with this program.  If not, see <http://www.gnu.org/licenses/>.

//------------------
//
//*********************************************************

public class Snake {
	public static boolean run = false;
	Game panel;
	Node n;
	Node food;
	Setdifficulce dif;
	static int array = 5;
	public static int number = 0;
	int x, y, food_x, food_y;
	int dir = 3;
	LinkedList<Node> snakeList = new LinkedList<Node>();
	int maxX, maxY;
	int flag = 0;
	static int sumNum = 0;
	static int maxNum = 0;

	public Snake(final Game p) {
		panel = p;
		maxX = panel.getWidth();
		maxY = panel.getHeight();
		createsnake();
		food = createfood();
		Setdifficulce.all[food.x][food.y] = true;
	}

	public void changedir(final int s_dir, final int c_dir) {
		if (((s_dir == 4) && (c_dir == 2)) || ((c_dir == 4) && (s_dir == 2))) {
		} else {
			if (((s_dir == 3) && (c_dir == 1))
					|| ((c_dir == 3) && (s_dir == 1))) {
			} else {
				dir = c_dir;
			}
		}
	}

	public Node createfood() {
		final Random r = new Random();
		do {
			x = r.nextInt(maxX - 1);
			y = r.nextInt(maxY - 1);
		} while ((Setdifficulce.all[x][y] == true)
				|| (((Math.abs(x - food_x)) % 12 != 0) || ((Math
						.abs(y - food_y)) % 12 != 0)));
		return new Node(x, y);
	}

	public void createsnakeNode(final int x, final int y) {
		Node p;
		if ((x == food.x) && (y == food.y)) {
			n = food;
			snakeList.addFirst(n);
			panel.repaint();
			food = createfood();
			Setdifficulce.all[food.x][food.y] = true;
		} else {
			n = new Node(x, y);
			snakeList.addFirst(n);
		}
		Setdifficulce.all[n.x][n.y] = true;
		p = snakeList.getLast();
		snakeList.removeLast();
		Setdifficulce.all[p.x][p.y] = false;
	}

	public void move() {

		final Node n = snakeList.getFirst();
		int x = n.x;
		int y = n.y;
		if (dir == 3) // 右
		{
			x += 12;
		} else if (dir == 4)// 下
		{
			y += 12;
		} else if (dir == 1)// 左
		{
			x -= 12;
		} else if (dir == 2)// 上
		{
			y -= 12;
		}
		if ((x == food.x) && (y == food.y) && Setdifficulce.all[x][y]) {
			snakeList.addFirst(food);
			panel.repaint();
			food = createfood();
			Setdifficulce.all[food.x][food.y] = true;
			++sumNum;
		}

		else if ((x > maxX - 1) || (y > maxY - 1) || (x < 0) || (y < 0)) {
			switch (dir) {
			case 1:
				x = maxX - 1;
				createsnakeNode(x, y);
				break;
			case 2:
				y = maxY - 1;
				createsnakeNode(x, y);
				break;
			case 3:
				x = 0;
				createsnakeNode(x, y);
				break;
			case 4:
				y = 0;
				createsnakeNode(x, y);
				break;
			}
		} else if ((x != food.x) && (y != food.y) && Setdifficulce.all[x][y]) {
			Snake.number = snakeList.size() - 5;
			if (Snake.number == 0)
				JOptionPane.showMessageDialog(null, "你的蛇真可怜，一个食物都没吃到!"
						+ "\nOh!man! You've got no food!", "游戏结束(Game Over)",
						JOptionPane.DEFAULT_OPTION);
			else
				JOptionPane.showMessageDialog(null, "很不幸！你的蛇撞头了，但是你的蛇一共吃了"
						+ number + "个食物！" + "\n我想它估计已经吃饱了！"
						+ "\nSorry! You are over! You got :" + number + " food"
						+ "\nYour snake doesn't want more!", "游戏结束(Game Over)",
						JOptionPane.DEFAULT_OPTION);
			snakeList.clear();
			Setdifficulce.Walllist.clear();
			dif = new Setdifficulce();
			Setdifficulce.checkmap();
			Setdifficulce.all[food.x][food.y] = true;
			createsnake();
			panel.repaint();
			Snake.run = false;
			panel.start = 0;
			dir = 3;
		} else {
			createsnakeNode(x, y);
		}

		panel.repaint();
	}

	public void createsnake() {
		do {
			flag = 0;
			do {
				x = (int) (Math.random() * (maxX + 1));
			} while ((x >= maxX - array * 24) || ((maxX - 1 - x) % 12 != 0)
					|| (x % 12 != 0));
			food_x = x;
			do {
				y = (int) (Math.random() * (maxY + 1));
			} while (((maxY - 1 - y) % 12 != 0) || (y % 12 != 0));
			food_y = y;
			for (int i = 0; i < array; i++) {
				final int s = x + 12 * (i + 1);
				final int s1 = y;
				if (Setdifficulce.all[s][s1] == true) {
					flag = 1;
					final LinkedList<Node> list = snakeList;
					final Iterator<Node> it = list.iterator();
					Node n1;
					while (it.hasNext()) {
						n1 = it.next();
						Setdifficulce.all[n1.x][n1.y] = false;
					}
					n1 = snakeList.getFirst();
					Setdifficulce.all[n1.x][n1.y] = true;
					snakeList.clear();
					break;
				}
				snakeList.addFirst(new Node(s, s1));
				Setdifficulce.all[s][s1] = true;
			}
		} while (flag == 1);
	}

}
