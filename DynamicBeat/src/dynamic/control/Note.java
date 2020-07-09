package dynamic.control;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import dynamic_beat_17.Main;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private Image noteSilverImage = new ImageIcon(Main.class.getResource("../images/noteSilver.png")).getImage();
	private Image noteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteSpace.png")).getImage();
	private Image noteEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png")).getImage();

	
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; // ?˜„?¬ ?…¸?Š¸ ?œ„ì¹? ?™•?¸, ?…¸?Š¸ ?ƒ?„±?›„, ? •?™•?ˆ 1ì´? ?’¤?— ?Œ? •?¼?¸?— ?„?‹¬?•œ?‹¤.
	private String noteType;
	private boolean proceeded = true; //?˜„?¬?˜ ?…¸?Š¸?˜ ì§„í–‰ ?—¬ë¶?ë¥? ì²´í¬
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}

	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 228;
		}else if(noteType.equals("D")) {
			x = 332;
		}else if(noteType.equals("F")) {
			x = 436;
		}else if(noteType.equals("Space")) {
			x = 540;
		}else if(noteType.equals("J")) {
			x = 744;
		}else if(noteType.equals("K")) {
			x = 848;
		}else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("Space"))
		{
			g.drawImage(noteSpaceImage, x, y, null);
		}
		else if(noteType.equals("D")||noteType.equals("K"))
		{
			g.drawImage(noteSilverImage, x, y, null);
		}	
		else 
		{
			g.drawImage(noteBasicImage, x, y, null); //Space ?ŒŒ?¼ ì¶”ê?
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED; //?…¸?Š¸ ?Š¤?”¼?“œ ë§Œí¼ ?–¨?–´ì§„ë‹¤. ì¦? ?•„?˜ìª½ìœ¼ë¡? 7ë§Œí¼ ?–¨?–´ì§„ë‹¤.
		if(y > 634) {
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);  //?Š¬ë¦½ì? 0.001ì´? ê¸°ì?, 10?œ¼ë¡? ?–ˆ?œ¼ë¯?ë¡?, 1ì´ˆì— 100?‹¤?–‰?¨. ì¦? 1ì´ˆì— 700?”½??ë§Œí¼ yì¢Œí‘œê°? ?‚´? ¤ê°„ë‹¤ê³? ?ƒê°í•˜ë©? ?¨. 
				}
				else {
					interrupt();
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(y >= 620) {
			System.out.println("Late");
			close();
			return "Late";
		}
		else if(y >= 605) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 592) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y >= 562) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 545) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 529) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}

	public int getY() {
		return y;
	}
}
