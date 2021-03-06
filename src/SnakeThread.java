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

/**
 * 
 */
public class SnakeThread extends Thread {
	Snake snake;

	public SnakeThread(final Snake s) {
		snake = s;
	}

	@Override
	public void run() {
		Snake.run = true;
		while (Snake.run) {
			try {
				snake.move();
				sleep((long) (Speed.speed * 100));
			} catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Snake.run = false;
	}
}
