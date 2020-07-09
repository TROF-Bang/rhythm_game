package dynamic_beat_17.service.impl;



import java.util.Scanner;


import dynamic_beat_17.model.User;
//import oracle.sql.CHAR;


public class UserFrame {
	
	Scanner sc = new Scanner(System.in);

	public UserFrame() {
		boolean ex = true;
		while(ex) {
			printMenu();
			int menu = menuSelect();
		switch(menu) {
		case 1:
			insertUser();
			break;
		case 2:
			insertScore();
			break;
		case 3:
			updateHighScore();
			break;
		case 4:
			searchHighScore();
			break;
		case 5:
			totalScore();
			break;
		case 6:
			searchRank();
			break;
		case 9:
			end();
			ex = false;
			break;
			
		
		}
		}
			
	}
	
	
	private void updateHighScore() {
		
	}
	private void searchHighScore() {
		
	}
	private void totalScore() {
		
	}
	private void searchRank() {
		
	}
	
	
	void end() {
		System.out.println("프로그램을 종료합니다");
		System.exit(0);
	}

	private void printMenu() {
		System.out.println("=======================================================================");
		System.out.println("1.회원가입  2. 점수등록  3.최고 점수 수정  4.최고 점수 조회 5.전체 총점  6. 총점 랭킹 9.종료");
		System.out.println("========================================================================");

	}

	private int menuSelect() {
		int menu = 0;
		try {
		System.out.println("선택>> ");
		menu = sc.nextInt(); sc.nextLine();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	private void insertUser() {

		try {
			User user = new User();
			System.out.println("아이디>");
			user.setUserid(stringput());
			System.out.println("비밀번호>");	
			//string -> char
	        char[] passwd = stringput().toCharArray();		
			user.setPasswd(passwd);
			UserDAO.getInstance().insert(user);
			System.out.println("1건 등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
			sc = new Scanner(System.in);
			System.out.println("잘못 입력하셨습니다");
		}
	}

	int intput() {
		int a = 0;
		while (true) {
			try {
				sc = new Scanner(System.in);
				a = sc.nextInt();
				sc.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("다시 하세요.");
			}
		}
		return a;
	}
	
	String stringput() {
		String x = "";
		while(true) {
			try {
				sc = new Scanner(System.in);
				x = sc.nextLine();
				break;
			}catch (Exception e) {
				System.out.println("다시 하세요.");
			}
		}
		return x;
	}
	
	
	
	void insertScore() {
//		Score score = new Score();
//		System.out.println("점수 등록(최고점수)");
//		int highScore = sc.nextInt(); sc.nextLine();
//		System.out.println("유저 아이디: ");
//		String userId = sc.nextLine();
//		System.out.println("음악: ");
//		String music = sc.nextLine();
//		UserServiceImpl.getInscance().
//		
		
	}
	
	

}

//private void updateUser() {
//
//	try {
//		System.out.println("아이디>");
//		String userId = sc.nextLine();
//		System.out.println("비밀번호>");
//		String userPw = sc.nextLine();
//		System.out.println("User / Admin>");
//		String role = sc.nextLine();
//		System.out.println("생일>");
//		String birth = sc.nextLine();
//
////		User user = new User();
////		user.setUserId(userId);
////		user.setUserPw(userPw);
////		user.setRole(role);
////		user.setBirth(birth);
//
//		UserServiceImpl.getInscance().update(user);
//
//	} catch (Exception e) {
//		sc = new Scanner(System.in);
//		System.out.println("잘못 입력하셨습니다");
//	}
//
//}

//void deleteUser() {
//	System.out.println("아이디>");
//	String userId = sc.nextLine();
//	UserServiceImpl.getInscance().delete(userId);
//
//}

//void selectOn() {
//	System.out.println("아이디>");
//
//	String userId = sc.nextLine();
//	Users user = UserServiceImpl.getInscance().selectOne(userId);
//
//	System.out.printf("검색결과는 아이디:%s  비밀번호:%s  회원분류:%s, 가입일:%s \n", user.getUserId(), user.getUserPw(),
//			user.getRole(), user.getBirth());
//}

//void selectAll() {
//	List<Users> list = UserServiceImpl.getInscance().selectAll();
//	for (Users temp : list) {
//		System.out.printf("%s:  %s  %s  %s \n", temp.getUserId(), temp.getUserPw(), temp.getRole(),
//				temp.getBirth());
//	}
//
//}
