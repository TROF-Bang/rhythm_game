package dynamic.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) { // ?–´?–¤ ?‚¤ë¥? ?…? ¥ë°›ì•˜?Š”ì§? ê°ì??•˜?Š” ? „ë°˜ì ?¸ ê²?.
		System.out.println(e.getKeyCode());
		if(DynamicBeat.game == null) {
			return; //return ?„ ?„£?–´ì¤Œìœ¼ë¡œì¨ ?˜„?¬ ê²Œì„?´ ì§„í–‰?˜ì§? ?•Š?œ¼ë©? ë°‘ì— ?‘?—…?“¤?? ê±°ì¹˜ì§? ?•Š?„ë¡? ë¦¬í„´?•´ì¤??‹¤.
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { // S?‚¤ ê°ì?
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
	public void keyReleased(KeyEvent e) { // ?‚¤ë¥? ?†“?•˜?„?•Œ ê°ì?
		if(DynamicBeat.game == null) {
			return; //return ?„ ?„£?–´ì¤Œìœ¼ë¡œì¨ ?˜„?¬ ê²Œì„?´ ì§„í–‰?˜ì§? ?•Š?œ¼ë©? ë°‘ì— ?‘?—…?“¤?? ê±°ì¹˜ì§? ?•Š?„ë¡? ë¦¬í„´?•´ì¤??‹¤.
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { // S?‚¤ ê°ì?
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
