/*
 * 12.24日,完成了蛇撞到自己的检测.修改方法是在蛇的初始化的时候,用双循环来置FALSE;
 * 在重写代码的时候,我借鉴了别人的思路:将整个游戏的界面定义为一个布尔型的二维数组.
 * 用一个LinkedList来储存蛇.当蛇移动的时候,在蛇的头部增加一个节点,然后删除最后一个节点.
 * 因为只是做练习,所以没做游戏界面,比如菜单栏,计分栏什么的.另外还有一个问题，就是当两次点击回车时,蛇的移动速度会变快
 *
 * 重要说明：
 * 1:增加了菜单功能
 * 2:增加了分数功能
 * 3:增加了最佳成绩功能
 * 4:增加了适当的注释
 * 5:修改了2次点击回车时，蛇的移动速度会变快的BUG,改为选择加速
 * 6 改变了以前游戏结束整个程序就退出的思路，变为重新开始游戏
 * 7:本次修改完全是为了交流学习，我学习了原作者的思维。并无其他商业用途
 * 8:此游戏作者为原作者，本人只是在他作品上有些修改
 * 9:修改后又出现新的BUG 就是快速按 光标键就会出错
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

 class Game {
	Game()
	{
	}
	public static void main(String args[]) {
		GameFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.show();
	}
}

class GameFrame extends JFrame {
    JMenu mnuFile=new JMenu();
    JMenu mnuAbout=new JMenu();
    JMenu mnuHelp=new JMenu();
    JMenuBar jb0=new JMenuBar();
    public GameFrame() {
                mnuFile.setText("最佳成绩");
                mnuAbout.setText("当前版本");
                mnuHelp.setText("游戏帮助");
                mnuHelp.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "上,下左,右控制蛇的方向\n回车开始,S暂停",
                                "贪食蛇1.0(DEMO)", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                mnuAbout.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "共享版本 (c) 2008\n版权为原作者所有",
                                "贪食蛇1.0(DEMO)", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                mnuFile.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "经过您顽强的努力\n最佳成绩为："+Snake.maxnumber+"个",
                                "贪食蛇1.0(DEMO)", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                jb0.add(mnuFile);
                jb0.add(mnuAbout);
                jb0.add(mnuHelp);
		setSize(294, 500);
		setTitle("贪食蛇DEMO");
		this.setLocation(360, 100);
		Container c = getContentPane();
		GamePanel panel = new GamePanel();
                this.setJMenuBar(jb0);
		c.add(panel, BorderLayout.CENTER);
	}
}

class GamePanel extends JPanel implements KeyListener {
	static int panelWidth = 294;

	static int panelHeight = 450;

	int rectX = 15;

	int rectY = 15;

	Snake snake;

	Node n;

	public GamePanel() {
		snake = new Snake(this, panelWidth / rectX, panelHeight / rectY);
		setBackground(Color.WHITE);
		setSize(panelWidth, panelHeight);
		setFocusable(true);
		addKeyListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		LinkedList list = snake.snakeList;
		Iterator it = list.iterator();
		g2.setColor(Color.black);
		while (it.hasNext()) {
			n = (Node) it.next();
			drawNode(g2, n);
		}
		g2.setColor(Color.ORANGE);
		Node f = snake.food;
		drawNode(g2, f);
		snake.drawMap(g2);// 绘制地图
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_ENTER) {
                    if(Snake.run){
                        //Snake.run = false; // 如果暂停，则不可以完成加速
                        int falg=JOptionPane.showConfirmDialog(null,
                                                  "是否加速?",
                                                  "你是否确定加速?",
                                                  JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(falg==0){
                           begin();
                        }
                    }
                    else{
                         begin();
                    }
		} else if (keycode == KeyEvent.VK_UP) {
			snake.changeDirection(Snake.up);
		} else if (keycode == KeyEvent.VK_DOWN) {
			snake.changeDirection(Snake.down);
		} else if (keycode == KeyEvent.VK_LEFT) {
			snake.changeDirection(Snake.left);
		} else if (keycode == KeyEvent.VK_RIGHT) {
			snake.changeDirection(Snake.right);
		} else if (keycode == KeyEvent.VK_S) {
			Snake.run = false;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void drawNode(Graphics2D g, Node n) {
		g.fillRect(n.x * rectX, n.y * rectY, rectX - 2, rectY - 2);
	}

	public void begin() {//线程
		Snake.run = true;
		SnakeThread thread = new SnakeThread(snake);
		thread.start();
	}
}

class Node {//用于增加蛇的长度
	int x;

	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class SnakeThread extends Thread {
	Snake snake;

	public SnakeThread(Snake s) {
		snake = s;
	}

	public void run() {
		Snake.run = true;

		while (Snake.run) {
			try {
				snake.move();
				sleep(200);
			} catch (InterruptedException e) {
			}
		}
		Snake.run = false;
	}
}

class Snake {
	GamePanel panel;

	Node food;

	boolean[][] all;

	public static boolean run;

	int maxX;

	int maxY;
        public static int number=0;

        public static int maxnumber=0;

	public static int left = 1;

	public static int up = 2;

	public static int right = 3;

	public static int down = 4;

	int direction = 4;

	LinkedList snakeList = new LinkedList();

	public Snake(GamePanel p, int maxX, int maxY) {
		panel = p;
		this.maxX = maxX;
		this.maxY = maxY;
		all = new boolean[maxX][maxY];
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				all[i][j] = false;
			}
		}
		int arrayLength = maxX > 20 ? 10 : maxX / 2;
		for (int i = 0; i < arrayLength; i++) {
			int x = maxX / 10 + i;
			int y = maxY / 10;
			snakeList.addFirst(new Node(x, y));
			all[x][y] = true;
		}
		food = createFood();
		all[food.x][food.y] = true;
	}

	// 蛇移动的方法
	public void move() {
		Node n = (Node) snakeList.getFirst();
		int x = n.x;
		int y = n.y;

		if (direction == 3) {
			x++;
		} else if (direction == 4) {
			y++;
		} else if (direction == 1) {
			x--;
		} else if (direction == 2) {
			y--;
		}
		// 实现对蛇撞到自身的检测
		if ((0 <= x && x <= GamePanel.panelWidth / 15 - 1)
				&& (0 <= y && y <= GamePanel.panelHeight / 15 - 1)) {
			if (all[x][y]) {
				if (x == food.x && y == food.y) {
					snakeList.addFirst(food);//吃到食物,加入蛇的一格
					food = createFood();
					all[food.x][food.y] = true;
				} else {
                                    this.number=snakeList.size()-9;
                                        JOptionPane.showMessageDialog(null, "由于撞到自己，你本次成绩"+number+"个","游戏结束",
                                                JOptionPane.DEFAULT_OPTION);
                                        snakeList.clear();
                                        for (int i = 0; i < 9; i++) {
                                                int z = maxX / 10 + i;
                                                int w = maxY / 10;
                                                snakeList.addFirst(new Node(z, w));
                                            }
                                        for (int i = 0; i < maxX; i++) {
                                            for (int j = 0; j < maxY; j++) {
                                                all[i][j]=false;
                                            }
                                            all[food.x][food.y] = true;
                                        }
                                       this.run=false;
				}
			} else {
				snakeList.addFirst(new Node(x, y));//没有吃到食物的变化
				all[x][y] = true;
				Node l = (Node) snakeList.getLast();
				snakeList.removeLast();
				all[l.x][l.y] = false;
			}
		} else {
                    this.number=snakeList.size()-9;
                    JOptionPane.showMessageDialog(null, "由于游戏越界，你本次成绩"+number+"个","游戏结束",
                                                 JOptionPane.DEFAULT_OPTION);
                  snakeList.clear();
                  for (int i = 0; i < 9; i++) {
                      int z = maxX / 10 + i;
                      int w = maxY / 10;
                      snakeList.addFirst(new Node(z, w));
                  }
                  for (int i = 0; i < maxX; i++) {//此处循环为了把以前用了的地图清空
                      for (int j = 0; j < maxY; j++) {
                          all[i][j]=false;
                      }
                      all[food.x][food.y] = true;//必须把产生的食物表示出来
                  }
                  this.run=false;
              }
              this.maxnumber=this.maxnumber>this.number?this.maxnumber:this.number;//确定最佳成绩
              panel.repaint();
	}

	public Node createFood() {
		int x = 0;
		int y = 0;
		do {
			Random r = new Random();//随机产生食物
			x = r.nextInt(maxX - 10);
			y = r.nextInt(maxY - 10);

		} while (all[x][y]);
		return new Node(x, y);
	}
//设置蛇不能回头
	public void changeDirection(int newDirection) {
		if (direction % 2 != newDirection % 2) {
			direction = newDirection;
		}
	}

	public void drawMap(Graphics2D g) {
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				if (all[i][j] == true) {
					g.setColor(Color.red);
					g.fillRect(i, j, 4, 4);
				}
			}
		}
	}
}
