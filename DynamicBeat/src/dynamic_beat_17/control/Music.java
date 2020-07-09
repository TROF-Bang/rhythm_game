package dynamic_beat_17.control;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;

import dynamic_beat_17.Main;
import javazoom.jl.player.Player;

public class Music extends Thread /* thread는 프로그램 안에 있는 하나의 작은 프로그램 */ {

	private Player player; // 여기서 플레이어는 JLayer
	private boolean isLoop; // 현재 곡이 무한반복인지 아니면, 한번만 재생되어 꺼지는지에 대한 설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); // 해당 파일을 버퍼에 담아서 읽어올 수 있도록 하는것
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() /* getTime은 현재 실행되고 있는 음악이 현재 어떤 위치에서 실행되는지 알려주는것(시간). <0.001초 단위> */ {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() /* close는 음악이 언제 실행되든지 항상 종료 할 수 있도록 해주는 함수. */ {
		isLoop = false;
		player.close();
		this.interrupt(); // 해당 thread를 중지 상태로 만드는 것. 즉 곡 정지

	}

	@Override
	public void run() /* thread 라는 것을 상속받으면 무조건 사용해야되는 함수 */ {
		try {
			do {
				player.play(); // 곡 실행
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); // isLoop값이 true 라면 무한 반복이 되게끔 하는것.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
