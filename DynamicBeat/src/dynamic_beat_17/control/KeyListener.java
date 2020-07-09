package dynamic_beat_17.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) { // 어떤 키를 입력받았는지 감지하는 전반적인 것.
		System.out.println(e.getKeyCode());
		if(DynamicBeat.game == null) {
			return; //return 을 넣어줌으로써 현재 게임이 진행되지 않으면 밑에 작업들은 거치지 않도록 리턴해준다.
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { // S키 감지
			DynamicBeat.game.pressS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.pressF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.pressSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.pressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.pressK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.pressL();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) { // 키를 놓았을때 감지
		if(DynamicBeat.game == null) {
			return; //return 을 넣어줌으로써 현재 게임이 진행되지 않으면 밑에 작업들은 거치지 않도록 리턴해준다.
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { // S키 감지
			DynamicBeat.game.releaseS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.releaseSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releaseK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releaseL();
		}
	}

}
