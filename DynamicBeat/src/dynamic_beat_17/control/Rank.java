package dynamic_beat_17.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dynamic_beat_17.Main;
import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.Score;
import dynamic_beat_17.service.impl.ScoreDAO;
import dynamic_beat_17.service.impl.UserDAO;

public class Rank extends Thread {
	List<Score> list;
	Score myRankResult;

	public Rank() { // rank 생성자
		Connection conn = DAO.getConnect();
		try {
			list = ScoreDAO.getInscance().rankList(conn);
			myRankResult = ScoreDAO.getInscance().myRank(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.close(conn);
		}
	}

	private Image screenImage;
	private Graphics screenGraphic;

	private Image rank1 = new ImageIcon(Main.class.getResource("../images/rank/1st.png")).getImage();
	private Image rank2 = new ImageIcon(Main.class.getResource("../images/rank/2rd.png")).getImage();
	private Image rank3 = new ImageIcon(Main.class.getResource("../images/rank/3nd.png")).getImage();

//   String userId;
	int rank;
	String UserPw;

	public String getUserPw() {
		return UserPw;
	}

	public void setUserPw(String userPw) {
		UserPw = userPw;
	}

	public void screenDraw(Graphics2D g) throws SQLException {
		// 총랭킹출력
		for (int i = 0; i < list.size(); i++) {
			String userID = String.format("%20s", list.get(i).getUserid());
			String totalScore = String.format("%10d", list.get(i).getTotalScore());
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.white);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawString(userID, 400, 275 + (100 * i));
			g.drawString(totalScore, 800, 275 + (100 * i)); // 각각 출력
		
			
		
			g.drawString("1st", 270, 275);
			g.drawString("2nd", 270, 275 + (100));
			g.drawString("3rd", 270, 275 + (200));

			g.drawString(". . .", 580, 530);
		}

		
		// 내랭킹출력
		if (myRankResult != null) {	
		String myRank = String.format("%3d", myRankResult.getRank());
		String myID = String.format("%20s", dynamic_beat_17.view.Login.userId);
		String myScore = String.format("%10d", myRankResult.getTotalScore());
		
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.setColor(new Color(118, 255, 237));
		g.drawString(myRank+" th", 250, 600);
		g.drawString(myID, 400, 600);
		g.drawString(myScore, 800, 600);
		} else {
			String myID = String.format("%20s", dynamic_beat_17.view.Login.userId);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(new Color(118, 255, 237));
			g.drawString("999 th", 250, 600);
			g.drawString(myID, 400, 600);
			g.drawString("        0", 800, 600);
			
		}
		

		
//		g.drawImage(rank1, 200, 200, null);
//		g.drawImage(rank2, 200, 300, null);
//		g.drawImage(rank3, 200, 400, null);
		
	
		
	}

	@Override
	public void run() {
	}

	public void close() {
		this.interrupt();
	}

}