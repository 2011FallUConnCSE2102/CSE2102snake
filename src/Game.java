import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

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

public class Game extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void begin() {// 线程
		Snake.run = true;
		final SnakeThread thread = new SnakeThread(snake);
		thread.start();
	}

	public static void sleep() throws InterruptedException {// 线程
		Snake.run = false;
		Thread.sleep(100);
	}

	// @jve:decl-index=0:visual-constraint="29,21"
	Node n, m; // @jve:decl-index=0:
	static Snake snake;
	static Setdifficulce dif;
	int rect_x;

	int rect_y;

	int start = 0;

	/**
	 * This method initializes
	 * 
	 */

	// final static BasicStroke stroke = new BasicStroke(2.0f);
	// //@jve:decl-index=0
	// :
	public Game() {
		super();
		initialize();

	}

	public void draw(final Graphics2D g, final int x, final int y) {
		g.setColor(Color.RED);
		drawNode(g, n);
	}

	public void draw1(final Graphics2D g, final int x, final int y) {
		g.setColor(Color.BLACK);
		drawNode(g, n);
	}

	public void drawNode(final Graphics2D g, final Node n) {
		// g.setColor(Color.RED);
		g.fillRect(n.x, n.y, 10, 10);
	}

	/**
	 * This method initializes this
	 * 
	 */

	private void initialize() {

		this.setSize(new Dimension(289, 445));
		setBackground(Color.white);
		setFocusable(true);
		addKeyListener(this);
		dif = new Setdifficulce();
		Setdifficulce.checkmap();
		snake = new Snake(this);

	}

	@Override
	public void keyPressed(final KeyEvent e) {
		if (Snake.run) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				snake.changedir(snake.dir, 4);
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				snake.changedir(snake.dir, 2);
			}

			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				snake.changedir(snake.dir, 1);
			}

			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				snake.changedir(snake.dir, 3);
			}

			else if (e.getKeyCode() == KeyEvent.VK_S) {
				snake.changedir(snake.dir, 4);
			}

			else if (e.getKeyCode() == KeyEvent.VK_W) {
				snake.changedir(snake.dir, 2);
			}

			else if (e.getKeyCode() == KeyEvent.VK_A) {
				snake.changedir(snake.dir, 1);
			}

			else if (e.getKeyCode() == KeyEvent.VK_D) {
				snake.changedir(snake.dir, 3);
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (start == 1) {
				if (!Snake.run) {
					begin();
				} else {
					try {
						sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (start == 0) {
				if (!Snake.run) {
					begin();
					Snake.sumNum = 0;
					start = 1;
				} else {
					try {
						sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(final KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(final KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2 = (Graphics2D) g;
		setBackground(Color.WHITE);
		final LinkedList<Node> list1 = Setdifficulce.Walllist;
		final Iterator<Node> it1 = list1.iterator();
		while (it1.hasNext()) {
			n = it1.next();
			draw1(g2, n.x, n.y);
		}
		final LinkedList<Node> list = snake.snakeList;
		final Iterator<Node> it = list.iterator();
		while (it.hasNext()) {
			n = it.next();
			draw(g2, n.x, n.y);
		}
		g2.setColor(Color.ORANGE);
		final Node f = snake.food;
		drawNode(g2, f);

	}

} // @jve:decl-index=0:visual-constraint="98,45"

