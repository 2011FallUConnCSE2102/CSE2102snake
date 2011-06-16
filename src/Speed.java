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
 * @author Jack Fate
 * 
 */
public class Speed extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JButton jButton = null;
	private JRadioButton jRadioButton = null;
	private JRadioButton jRadioButton1 = null;
	private JRadioButton jRadioButton2 = null;
	private final ButtonGroup g = new ButtonGroup(); // @jve:decl-index=0:
	private boolean easy = true;
	private boolean normal = false;
	private boolean hard = false;
	Oursnake oursnake;
	static double speed = 1.5;

	/**
	 * @param mouseAdapter
	 */
	public Speed() {
		super();
		initialize();
	}

	public void getchoice(final boolean e, final boolean n, final boolean h) {
		if (e == true) {
			speed = 1.5;
		}
		if (n == true) {
			speed = 0.8;
		}
		if (h == true) {
			speed = 0.3;
		}
	}

	/**
	 * <<<<<<< Choice.java This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(162, 105, 118, 30));
			jButton.setText("确认(Enter)");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					getchoice(easy, normal, hard);
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
			jLabel2.setBounds(new Rectangle(45, 116, 87, 18));
			jLabel2.setText("高速(Hard)");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(45, 70, 83, 18));
			jLabel1.setText("中速(Normal)");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(45, 24, 82, 18));
			jLabel.setText("慢速(Easy)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJRadioButton(), null);
			jContentPane.add(getJRadioButton1(), null);
			jContentPane.add(getJRadioButton2(), null);
		}
		return jContentPane;
	}

	/**
	 * ======= >>>>>>> 1.2 This method initializes jRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			g.add(jRadioButton);
			if (speed == 1.5) {
				jRadioButton.setSelected(true);
			}
			jRadioButton.setBounds(new Rectangle(14, 24, 21, 21));
			jRadioButton.setSelected(true);
			jRadioButton.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(final java.awt.event.ItemEvent e) {
					easy = true;
					normal = false;
					hard = false;
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
			if (speed == 0.8) {
				jRadioButton1.setSelected(true);
			}
			jRadioButton1.setBounds(new Rectangle(14, 70, 21, 21));
			jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(final java.awt.event.ItemEvent e) {
					easy = false;
					normal = true;
					hard = false;
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
			if (speed == 0.3) {
				jRadioButton2.setSelected(true);
			}
			jRadioButton2.setBounds(new Rectangle(14, 116, 21, 21));
			jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(final java.awt.event.ItemEvent e) {
					easy = false;
					normal = false;
					hard = true;
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
		this.setSize(300, 200);
		setTitle("设置速度(Set Speed)");
		this.setLocation(500, 400);
		setContentPane(getJContentPane());
	}
} // @jve:decl-index=0:visual-constraint="32,-1"
