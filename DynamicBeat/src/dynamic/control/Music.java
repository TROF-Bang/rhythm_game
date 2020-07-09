package dynamic.control;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;

import dynamic_beat_17.Main;
import javazoom.jl.player.Player;

public class Music extends Thread /* thread?�� ?��로그?�� ?��?�� ?��?�� ?��?��?�� ?��?? ?��로그?�� */ {

	private Player player; // ?��기서 ?��?��?��?��?�� JLayer
	private boolean isLoop; // ?��?�� 곡이 무한반복?���? ?��?���?, ?��번만 ?��?��?��?�� 꺼�??���??�� ???�� ?��?��
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); // ?��?�� ?��?��?�� 버퍼?�� ?��?��?�� ?��?��?�� ?�� ?��?���? ?��?���?
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() /* getTime?? ?��?�� ?��?��?���? ?��?�� ?��?��?�� ?��?�� ?��?�� ?��치에?�� ?��?��?��?���? ?��?��주는�?(?���?). <0.001�? ?��?��> */ {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() /* close?�� ?��?��?�� ?��?�� ?��?��?��?���? ?��?�� 종료 ?�� ?�� ?��?���? ?��주는 ?��?��. */ {
		isLoop = false;
		player.close();
		this.interrupt(); // ?��?�� thread�? 중�? ?��?���? 만드?�� �?. �? �? ?���?

	}

	@Override
	public void run() /* thread ?��?�� 것을 ?��?��받으�? 무조�? ?��?��?��?��?��?�� ?��?�� */ {
		try {
			do {
				player.play(); // �? ?��?��
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); // isLoop값이 true ?���? 무한 반복?�� ?��게끔 ?��?���?.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
