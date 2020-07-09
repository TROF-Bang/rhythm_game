package dynamic_beat_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import javax.swing.JFrame;

import javax.swing.JPanel;
import dynamic_beat_17.control.DynamicBeat;
import dynamic_beat_17.view.Login;
import dynamic_beat_17.view.SignUp;



public class Main extends JFrame{
	public static final int SCREEN_WIDTH = 1280;  //final�� �븳踰� �꽑�뼵�븯硫� �젅�� �븞諛붾��, �긽�닔�뒗 �쟾遺� ��臾몄옄
	public static final int SCREEN_HEIGHT = 750;
	public static final int NOTE_SPEED = 3;  //�끂�듃�쓽 �냽�룄�뒗 7
	public static final int SLEEP_TIME = 5; //�끂�듃媛� 10 二쇨린濡� �뼥�뼱吏�
	public static final int REACH_TIME = 4; //�끂�듃媛� �깮�꽦�맂 �썑 �뙋�젙諛붿뿉 �룄�떖�븯湲� 源뚯��쓽 �떆媛�
	
	public Login login = null;
	public SignUp signUp = null;
	public DynamicBeat dynamicBeat = null;
	
	public Main() throws SQLException {
		this.login = new Login(this);
		this.signUp = new SignUp(this);
		this.dynamicBeat = new DynamicBeat(this);
		
		this.add(this.login);
//		this.add(this.dynamicBeat);

		
		this.setTitle("Dynamic Beat");
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setResizable(false); // �븳踰� 留뚮뱾�뼱吏� 李쎌� �궗�슜�옄媛� �엫�쓽濡� 以꾩씠嫄곕굹 �뒛由� �닔 �뾾�떎.
		this.setLocationRelativeTo(null); // �떎�뻾�떆 寃뚯엫 �솕硫댁씠 �젙 以묒븰�뿉 �쑉寃� �꽕�젙
//		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 李쎈떕�쑝硫� �끂�옒�굹�샂
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �봽濡쒓렇�옩 �쟾泥� 醫낅즺
		this.setVisible(true);
	}
	
	public void change(String panelName) {
		
		
		if(panelName.equals("login")) {
			getContentPane().removeAll();
			getContentPane().add(login);
			revalidate();
			repaint();
		}else if(panelName.equals("signUp")) {
			getContentPane().removeAll();
			getContentPane().add(signUp);
			revalidate();
			repaint();
		}else if(panelName.equals("dynamicBeat")) {
			getContentPane().removeAll();
			getContentPane().add(dynamicBeat);
			revalidate();
			repaint();
		}
	}
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		
//		File file = new File("c:/log.txt");
//		PrintStream printStream = new PrintStream(new FileOutputStream(file));
//		// standard out怨� err�쓣 file濡� 蹂�寃�
//		System.setOut(printStream);
//		System.setErr(printStream);

		
		Main win = new Main();
		
	}

}
