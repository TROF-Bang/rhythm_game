package dynamic.control;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;

import dynamic_beat_17.Main;
import javazoom.jl.player.Player;

public class Music extends Thread /* thread?Š” ?”„ë¡œê·¸?¨ ?•ˆ?— ?ˆ?Š” ?•˜?‚˜?˜ ?‘?? ?”„ë¡œê·¸?¨ */ {

	private Player player; // ?—¬ê¸°ì„œ ?”Œ? ˆ?´?–´?Š” JLayer
	private boolean isLoop; // ?˜„?¬ ê³¡ì´ ë¬´í•œë°˜ë³µ?¸ì§? ?•„?‹ˆë©?, ?•œë²ˆë§Œ ?¬?ƒ?˜?–´ êº¼ì??Š”ì§??— ???•œ ?„¤? •
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); // ?•´?‹¹ ?ŒŒ?¼?„ ë²„í¼?— ?‹´?•„?„œ ?½?–´?˜¬ ?ˆ˜ ?ˆ?„ë¡? ?•˜?Š”ê²?
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() /* getTime?? ?˜„?¬ ?‹¤?–‰?˜ê³? ?ˆ?Š” ?Œ?•…?´ ?˜„?¬ ?–´?–¤ ?œ„ì¹˜ì—?„œ ?‹¤?–‰?˜?Š”ì§? ?•Œ? ¤ì£¼ëŠ”ê²?(?‹œê°?). <0.001ì´? ?‹¨?œ„> */ {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() /* close?Š” ?Œ?•…?´ ?–¸? œ ?‹¤?–‰?˜?“ ì§? ?•­?ƒ ì¢…ë£Œ ?•  ?ˆ˜ ?ˆ?„ë¡? ?•´ì£¼ëŠ” ?•¨?ˆ˜. */ {
		isLoop = false;
		player.close();
		this.interrupt(); // ?•´?‹¹ threadë¥? ì¤‘ì? ?ƒ?ƒœë¡? ë§Œë“œ?Š” ê²?. ì¦? ê³? ? •ì§?

	}

	@Override
	public void run() /* thread ?¼?Š” ê²ƒì„ ?ƒ?†ë°›ìœ¼ë©? ë¬´ì¡°ê±? ?‚¬?š©?•´?•¼?˜?Š” ?•¨?ˆ˜ */ {
		try {
			do {
				player.play(); // ê³? ?‹¤?–‰
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); // isLoopê°’ì´ true ?¼ë©? ë¬´í•œ ë°˜ë³µ?´ ?˜ê²Œë” ?•˜?Š”ê²?.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
