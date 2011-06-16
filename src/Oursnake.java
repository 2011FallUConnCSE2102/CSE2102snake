import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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

public class Oursnake extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final Oursnake thisClass = new Oursnake();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setLocation(500, 250);
				thisClass.setVisible(true);
			}
		});
	}

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu1 = null;

	private JMenu jMenu2 = null;
	private JMenu jMenu3 = null;
	private JMenu jMenu4 = null;
	private JMenuItem jMenuItem = null;

	private JMenuItem jMenuItem1 = null;

	/**
	 * This is the default constructor
	 */
	public Oursnake() {
		super();
		initialize();
		final Container c = getContentPane();
		final Game panel = new Game();
		c.add(panel, BorderLayout.CENTER);
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setPreferredSize(new Dimension(20, 25));
			jJMenuBar.add(getJMenu4());
			jJMenuBar.add(getJMenu2());
			jJMenuBar.add(getJMenu1());
			jJMenuBar.add(getJMenu3());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu1
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setPreferredSize(new Dimension(85, 25));
			jMenu1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 12));
			jMenu1.setSize(new Dimension(70, 25));
			jMenu1.setText("Game Explain");
			jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(final java.awt.event.MouseEvent e) {
					Snake.run = false;
					try {
						Game.sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane
							.showMessageDialog(
									null,
									"上,下左,右(w,s,a,d)控制蛇的方向\n回车开始，空格暂停与继续！\nUP,DOWN,LEFT,RIGHT(w,s,a,d) control the snake.\n"
											+ "ENTER starts game,SPACE pause",
									"游戏说明(Game Explain)",
									JOptionPane.INFORMATION_MESSAGE);
					// TODO Auto-generated Event stub mouseClicked()
				}
			});

		}
		return jMenu1;
	}

	/**
	 * This method initializes jMenu2
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setPreferredSize(new Dimension(80, 25));
			jMenu2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 12));
			jMenu2.setText("Show Score");
			jMenu2.setSize(new Dimension(70, 25));
			jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(final java.awt.event.MouseEvent e) {
					Snake.run = false;
					try {
						Game.sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Snake.sumNum >= Snake.maxNum) {
						Snake.maxNum = Snake.sumNum;
					}
					JOptionPane
							.showMessageDialog(null,
									"游戏的最好成绩为：" + "\nYour best score is:"
											+ Snake.maxNum + "\n确定后请按空格继续!"
											+ "\nPress ENTER continue",
									"成绩查询(Show Score)",
									JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return jMenu2;
	}

	/**
	 * This method initializes jMenu3
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu3() {
		if (jMenu3 == null) {
			jMenu3 = new JMenu();
			jMenu3.setPreferredSize(new Dimension(40, 25));
			jMenu3.setText("About");
			jMenu3.setSize(new Dimension(70, 25));
			jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(final java.awt.event.MouseEvent e) {
					Snake.run = false;
					try {
						Game.sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null,
							"版本：贪吃蛇1.0\n版权所有--詹詹、小鬼、猩猩", "关于....",
							JOptionPane.INFORMATION_MESSAGE);
					// TODO Auto-generated Event stub mouseClicked()
				}
			});
		}
		return jMenu3;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu4() {
		if (jMenu4 == null) {
			jMenu4 = new JMenu();
			jMenu4.setPreferredSize(new Dimension(80, 25));
			jMenu4.setText("Set Difficulty");
			jMenu4.add(getJMenuItem());
			jMenu4.add(getJMenuItem1());
			jMenu4.addMenuListener(new javax.swing.event.MenuListener() {
				public void menuCanceled(final javax.swing.event.MenuEvent e) {
				}

				public void menuDeselected(final javax.swing.event.MenuEvent e) {
				}

				public void menuSelected(final javax.swing.event.MenuEvent e) {
					Snake.run = false;
					try {
						Game.sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return jMenu4;
	}

	/**
	 * This method initializes jMenu4
	 * 
	 * @return javax.swing.JMenu
	 */

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Set Speed");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					Snake.run = false;
					try {
						Game.sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					openspeed();
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Set Map");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					Snake.run = false;
					try {
						Game.sleep();
					} catch (final InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					openmap();
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(304, 507);
		setResizable(false);
		setJMenuBar(getJJMenuBar());
		setTitle("贪吃蛇(SuperSnake)");
	}

	public void openmap() {
		final Map mymap = new Map();
		mymap.setModal(true);
		mymap.setVisible(true);
	}

	public void openspeed() {
		final Speed myspeed = new Speed();
		myspeed.setModal(true);
		myspeed.setVisible(true);
	}
} // @jve:decl-index=0:visual-constraint="10,10"
