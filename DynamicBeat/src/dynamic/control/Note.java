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

	
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; // ?��?�� ?��?�� ?���? ?��?��, ?��?�� ?��?��?��, ?��?��?�� 1�? ?��?�� ?��?��?��?��?�� ?��?��?��?��.
	private String noteType;
	private boolean proceeded = true; //?��?��?�� ?��?��?�� 진행 ?���?�? 체크
	
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
			g.drawImage(noteBasicImage, x, y, null); //Space ?��?�� 추�?
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED; //?��?�� ?��?��?�� 만큼 ?��?��진다. �? ?��?��쪽으�? 7만큼 ?��?��진다.
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
					Thread.sleep(Main.SLEEP_TIME);  //?��립�? 0.001�? 기�?, 10?���? ?��?���?�?, 1초에 100?��?��?��. �? 1초에 700?��??만큼 y좌표�? ?��?��간다�? ?��각하�? ?��. 
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
