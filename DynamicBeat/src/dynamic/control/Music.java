package dynamic.control;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;

import dynamic_beat_17.Main;
import javazoom.jl.player.Player;

public class Music extends Thread /* thread? ?λ‘κ·Έ?¨ ?? ?? ??? ??? ?λ‘κ·Έ?¨ */ {

	private Player player; // ?¬κΈ°μ ?? ?΄?΄? JLayer
	private boolean isLoop; // ??¬ κ³‘μ΄ λ¬΄νλ°λ³΅?Έμ§? ??λ©?, ?λ²λ§ ?¬???΄ κΊΌμ??μ§?? ??? ?€? 
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); // ?΄?Ή ??Ό? λ²νΌ? ?΄?? ?½?΄?¬ ? ??λ‘? ??κ²?
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() /* getTime?? ??¬ ?€??κ³? ?? ???΄ ??¬ ?΄?€ ?μΉμ? ?€???μ§? ?? €μ£Όλκ²?(?κ°?). <0.001μ΄? ?¨?> */ {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() /* close? ???΄ ?Έ?  ?€??? μ§? ?­? μ’λ£ ?  ? ??λ‘? ?΄μ£Όλ ?¨?. */ {
		isLoop = false;
		player.close();
		this.interrupt(); // ?΄?Ή threadλ₯? μ€μ? ??λ‘? λ§λ? κ²?. μ¦? κ³? ? μ§?

	}

	@Override
	public void run() /* thread ?Ό? κ²μ ??λ°μΌλ©? λ¬΄μ‘°κ±? ?¬?©?΄?Ό?? ?¨? */ {
		try {
			do {
				player.play(); // κ³? ?€?
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); // isLoopκ°μ΄ true ?Όλ©? λ¬΄ν λ°λ³΅?΄ ?κ²λ ??κ²?.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
