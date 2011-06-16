import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//*********************************************************
//----------------

//	Developed by Jack Fate.
//	Copyright 2008 Jack Fate(ZhanKaijie).
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

/**
 * 
 */

/**
 * @author Jack Fate
 * 
 */
public class Map extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JRadioButton jRadioButton = null;
	private JRadioButton jRadioButton1 = null;
	private JRadioButton jRadioButton2 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JButton jButton = null;
	private final ButtonGroup g = new ButtonGroup(); // @jve:decl-index=0:
	private boolean noWall = true;
	private boolean wall = false;
	private boolean roadWall = false;
	Oursnake oursnake;
	static int map = 1;

	/**
	 * @param owner
	 */
	public Map() {
		super();
		initialize();
	}

	public void getchoice(final boolean e, final boolean n, final boolean h) {
		if (e == true) {
			map = 1;
		}
		if (n == true) {
			map = 2;
		}
		if (h == true) {
			map = 3;
		}

	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(227, 158, 101, 27));
			jButton.setText("确定(Enter)");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					getchoice(noWall, wall, roadWall);
					Setdifficulce.checkmap();
					Game.begin();
					dispose();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(36, 125, 306, 18));
			jLabel2.setText("障碍物、不可穿墙(RoadBlock And No WallThrough)");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(36, 77, 130, 18));
			jLabel1.setText("可穿墙(WallThrough)");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(36, 34, 269, 18));
			jLabel.setText("障碍物、可穿墙(RoadBlock And WallThrough)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJRadioButton(), null);
			jContentPane.add(getJRadioButton1(), null);
			jContentPane.add(getJRadioButton2(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			g.add(jRadioButton);
			if (map == 1) {
				jRadioButton.setSelected(true);
			}
			jRadioButton.setBounds(new Rectangle(14, 34, 21, 21));
			jRadioButton.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(final java.awt.event.ItemEvent e) {
					noWall = true;
					wall = false;
					roadWall = false;
				}
			});
		}
		return jRadioButton;
	}

	/**
	 * This method initializes jRadioButton1
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			g.add(jRadioButton1);
			if (map == 2) {
				jRadioButton1.setSelected(true);
			}
			jRadioButton1.setBounds(new Rectangle(14, 77, 21, 21));
			jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(final java.awt.event.ItemEvent e) {
					noWall = false;
					wall = true;
					roadWall = false;
				}
			});
		}
		return jRadioButton1;
	}

	/**
	 * This method initializes jRadioButton2
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			g.add(jRadioButton2);
			if (map == 3) {
				jRadioButton2.setSelected(true);
			}
			jRadioButton2.setBounds(new Rectangle(14, 125, 21, 21));
			jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(final java.awt.event.ItemEvent e) {
					noWall = false;
					wall = false;
					roadWall = true;
				}
			});
		}
		return jRadioButton2;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(360, 234);
		setTitle("设置地图(Set Map)");
		this.setLocation(500, 400);
		setContentPane(getJContentPane());
	}
} // @jve:decl-index=0:visual-constraint="10,10"
