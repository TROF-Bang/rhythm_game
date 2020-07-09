package dynamic_beat_17.control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;

import dynamic_beat_17.Main;
import javazoom.jl.player.Player;

public class Movie extends Thread {

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Movie(String name, boolean isLoop) {
		this.isLoop = isLoop;
		try {
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
