package dynamic_beat_17.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.Score;

public class ScoreDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// singletone
	private static ScoreDAO instance = new ScoreDAO();

	public static ScoreDAO getInscance() {
		return instance;
	}

	// 점수 등록

	public void insert(Score score) throws SQLException {
		Connection conn = DAO.getConnect();
		String sql = "INSERT INTO Music (high_score, ID, Music) values(? , ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, score.getHighScore());
		pstmt.setString(2, score.getUserid());
		pstmt.setString(3, score.getMusic());
		int r = pstmt.executeUpdate();
		System.out.println(r + "건 등록완료. ");
		DAO.close(conn);
	}

	// 최고 점수 수정

	public void update(Score score) throws SQLException {
		Connection conn = DAO.getConnect();
		System.out.println(score);
		String sql = "UPDATE MUSIC SET high_score = ? WHERE ID = ? and music = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, score.getHighScore());
		pstmt.setString(2, score.getUserid());
		pstmt.setString(3, score.getMusic());
		int r = pstmt.executeUpdate();
		System.out.println(r + "건 수정완료");
		DAO.close(conn);
	}

	// 곡에 따른 최고 점수 조회
	public Score selectOne(Score score) {
		Connection conn = null;
		try {
			score.setStart(false);
			conn = DAO.getConnect();
			String sql = "SELECT high_score FROM music WHERE id = ? and music = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, score.getUserid());
			pstmt.setString(2, score.getMusic());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				score.setHighScore(rs.getInt("high_score"));
				score.setStart(false);

			} else {
				score.setHighScore(0);
				score.setStart(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAO.close(conn);
		}
		return score;
	}

	// 전체 총점
	public List<Score>/* util로 import */ selectTotal(Connection conn) throws SQLException {
		List<Score> list = new ArrayList<>();
		Score score = null;
		String sql = "SELECT Id, SUM(HIGH_SCORE) total_score FROM music GROUP BY Id";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			score = new Score();
			score.setHighScore(rs.getInt("high_score"));
//		score.setUserid(rs.getString("id"));
//		score.setMusic(rs.getString("music"));
			score.setTotalScore(rs.getInt("Total_score"));
			list.add(score);
		}
		return list;
	}

	// 전체 총점에 대한 랭킹
	public List<Score> rankList(Connection conn) throws SQLException {
		List<Score> list = new ArrayList<>();
		try {
			Score score = null;
			String sql = "SELECT * FROM (SELECT ID, SUM(HIGH_SCORE) score,  ROW_NUMBER() OVER (ORDER BY SUM(HIGH_SCORE) DESC) as rank FROM MUSIC GROUP BY ID) where  rank<=3";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				score = new Score();
				score.setTotalScore(rs.getInt("score"));
				score.setUserid(rs.getString("id"));
				score.setRank(rs.getInt("rank"));
				list.add(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 본인랭킹 보기

	String id = (dynamic_beat_17.view.Login.userId);

	public Score myRank(Connection conn) {
		Score score = null;
		try {
			String sql = "SELECT * FROM (SELECT id, SUM(HIGH_SCORE) score,  ROW_NUMBER() "
					+ "OVER (ORDER BY SUM(HIGH_SCORE) DESC) as rank FROM MUSIC GROUP BY ID) where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				score = new Score();
				score.setUserid(id);
				score.setTotalScore(rs.getInt("score"));
				score.setRank(rs.getInt("rank"));
			}
			System.out.println(score);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}
}
