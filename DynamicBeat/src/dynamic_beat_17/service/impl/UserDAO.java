package dynamic_beat_17.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.User;

public class UserDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// singletone
	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	// 유저 등록
	public void insert(User user) throws Exception {

		// 1. connect
		Connection conn = DAO.getConnect();
		// 2. 구문
		String sql = "INSERT INTO GAME_USER (id, pw) values(?, ?)";
		pstmt = conn.prepareStatement(sql);

		// 3. 파라미터 셋팅
		String passString = new String(user.getPasswd());

		pstmt.setString(1, user.getUserid());
		pstmt.setString(2, passString);

		// 4. 실행
		int r = pstmt.executeUpdate();
		System.out.println(r + "건이 처리되었습니다.");

		// 5. 연결 해제
		DAO.close(conn);
	}

	// 아이디체크버튼
	public boolean checkId(User user) throws SQLException {
		Connection conn = DAO.getConnect();
		boolean possible = false;

		String sql = "select * from game_user where id = ? ";
		pstmt = conn.prepareStatement(sql);

		try {
			pstmt.setString(1, user.getUserid());
			// 4. 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return possible = true;
			} else {
				return possible = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			DAO.close(conn);
		}
		return possible;
	}

	// 로그인
	public boolean login(User user) throws SQLException {
		// 1. connect
		Connection conn = DAO.getConnect();
		boolean possible = false;
		// 2. 구문
		String sql = "SELECT * FROM GAME_USER WHERE ID = ? AND PW = ?";
		pstmt = conn.prepareStatement(sql);
		// 3. 파라미터 셋팅
		try {
			String passString = new String(user.getPasswd());

			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, passString);
			// 4. 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return possible = true;
			} else {
				return possible = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			DAO.close(conn);
		}
		return possible;

	}

}
