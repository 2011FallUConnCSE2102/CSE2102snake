/*
 * 12.24��,�������ײ���Լ��ļ��.�޸ķ��������ߵĳ�ʼ����ʱ��,��˫ѭ������FALSE;
 * ����д�����ʱ��,�ҽ���˱��˵�˼·:��������Ϸ�Ľ��涨��Ϊһ�������͵Ķ�ά����.
 * ��һ��LinkedList��������.�����ƶ���ʱ��,���ߵ�ͷ������һ���ڵ�,Ȼ��ɾ�����һ���ڵ�.
 * ��Ϊֻ������ϰ,����û����Ϸ����,����˵���,�Ʒ���ʲô��.���⻹��һ�����⣬���ǵ����ε���س�ʱ,�ߵ��ƶ��ٶȻ���
 *
 * ��Ҫ˵����
 * 1:�����˲˵�����
 * 2:�����˷�������
 * 3:��������ѳɼ�����
 * 4:�������ʵ���ע��
 * 5:�޸���2�ε���س�ʱ���ߵ��ƶ��ٶȻ����BUG,��Ϊѡ�����
 * 6 �ı�����ǰ��Ϸ��������������˳���˼·����Ϊ���¿�ʼ��Ϸ
 * 7:�����޸���ȫ��Ϊ�˽���ѧϰ����ѧϰ��ԭ���ߵ�˼ά������������ҵ��;
 * 8:����Ϸ����Ϊԭ���ߣ�����ֻ��������Ʒ����Щ�޸�
 * 9:�޸ĺ��ֳ����µ�BUG ���ǿ��ٰ� �����ͻ����
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
                mnuFile.setText("��ѳɼ�");
                mnuAbout.setText("��ǰ�汾");
                mnuHelp.setText("��Ϸ����");
                mnuHelp.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "��,����,�ҿ����ߵķ���\n�س���ʼ,S��ͣ",
                                "̰ʳ��1.0(DEMO)", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                mnuAbout.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "����汾 (c) 2008\n��ȨΪԭ��������",
                                "̰ʳ��1.0(DEMO)", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                mnuFile.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "��������ǿ��Ŭ��\n��ѳɼ�Ϊ��"+Snake.maxnumber+"��",
                                "̰ʳ��1.0(DEMO)", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                jb0.add(mnuFile);
                jb0.add(mnuAbout);
                jb0.add(mnuHelp);
		setSize(294, 500);
		setTitle("̰ʳ��DEMO");
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
		snake.drawMap(g2);// ���Ƶ�ͼ
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_ENTER) {
                    if(Snake.run){
                        //Snake.run = false; // �����ͣ���򲻿�����ɼ���
                        int falg=JOptionPane.showConfirmDialog(null,
                                                  "�Ƿ����?",
                                                  "���Ƿ�ȷ������?",
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

	public void begin() {//�߳�
		Snake.run = true;
		SnakeThread thread = new SnakeThread(snake);
		thread.start();
	}
}

class Node {//���������ߵĳ���
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

	// ���ƶ��ķ���
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
		// ʵ�ֶ���ײ������ļ��
		if ((0 <= x && x <= GamePanel.panelWidth / 15 - 1)
				&& (0 <= y && y <= GamePanel.panelHeight / 15 - 1)) {
			if (all[x][y]) {
				if (x == food.x && y == food.y) {
					snakeList.addFirst(food);//�Ե�ʳ��,�����ߵ�һ��
					food = createFood();
					all[food.x][food.y] = true;
				} else {
                                    this.number=snakeList.size()-9;
                                        JOptionPane.showMessageDialog(null, "����ײ���Լ����㱾�γɼ�"+number+"��","��Ϸ����",
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
				snakeList.addFirst(new Node(x, y));//û�гԵ�ʳ��ı仯
				all[x][y] = true;
				Node l = (Node) snakeList.getLast();
				snakeList.removeLast();
				all[l.x][l.y] = false;
			}
		} else {
                    this.number=snakeList.size()-9;
                    JOptionPane.showMessageDialog(null, "������ϷԽ�磬�㱾�γɼ�"+number+"��","��Ϸ����",
                                                 JOptionPane.DEFAULT_OPTION);
                  snakeList.clear();
                  for (int i = 0; i < 9; i++) {
                      int z = maxX / 10 + i;
                      int w = maxY / 10;
                      snakeList.addFirst(new Node(z, w));
                  }
                  for (int i = 0; i < maxX; i++) {//�˴�ѭ��Ϊ�˰���ǰ���˵ĵ�ͼ���
                      for (int j = 0; j < maxY; j++) {
                          all[i][j]=false;
                      }
                      all[food.x][food.y] = true;//����Ѳ�����ʳ���ʾ����
                  }
                  this.run=false;
              }
              this.maxnumber=this.maxnumber>this.number?this.maxnumber:this.number;//ȷ����ѳɼ�
              panel.repaint();
	}

	public Node createFood() {
		int x = 0;
		int y = 0;
		do {
			Random r = new Random();//�������ʳ��
			x = r.nextInt(maxX - 10);
			y = r.nextInt(maxY - 10);

		} while (all[x][y]);
		return new Node(x, y);
	}
//�����߲��ܻ�ͷ
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
