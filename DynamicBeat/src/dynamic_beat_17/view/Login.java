package dynamic_beat_17.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dynamic_beat_17.Main;
import dynamic_beat_17.model.User;
import dynamic_beat_17.service.impl.UserDAO;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class Login extends JPanel {
	private JTextField IDField;
	private JPasswordField PWField;
	private Main win;
	JButton loginButton;
	JButton signUpButton;
	
	

	public static String userId;
	
	/**
	 * Create the panel.
	 * @return 
	 * 
	 * @throws SQLException
	 */
	

	
	
//	public void paintComponent(Graphics2D g) {
//		super.paintComponent(g);
//		g.drawImage(image, 0, 0, this);
//	}
	
	
	
	public Login(Main win) throws SQLException {
		this.win = win;
		setLayout(null);

		JLabel label = new JLabel("ID:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label.setBounds(530, 300, 81, 30);
		add(label);

		JLabel label_1 = new JLabel("PW:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label_1.setBounds(530, 350, 81, 30);
		add(label_1);

		IDField = new JTextField();
		IDField.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		IDField.setColumns(10);
		IDField.setBounds(630, 300, 116, 30);
		add(IDField);

		PWField = new JPasswordField();
		PWField.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		PWField.setBounds(630, 350, 116, 30);
		add(PWField);

		loginButton = new JButton("Login");
		loginButton.setForeground(Color.BLACK);
		loginButton.setBounds(526, 420, 97, 23);
		add(loginButton);

		signUpButton = new JButton("Sign up");
		signUpButton.setBounds(651, 420, 97, 23);
		add(signUpButton);
		
		//로고
		JLabel TestLabel = new JLabel("");
		Image shadow = new ImageIcon(Main.class.getResource("../images/Logo.png")).getImage();
		TestLabel.setIcon(new ImageIcon(shadow));	
		TestLabel.setBounds(400, 150, 440, 75);
		add(TestLabel);
		
		//배경부분
		JLabel lblNewLabel = new JLabel("");
		Image backgound = new ImageIcon(Main.class.getResource("../images/moveBackground.gif")).getImage();
		lblNewLabel.setIcon(new ImageIcon(backgound));	
		lblNewLabel.setBounds(0, 0, 1280, 720);
		add(lblNewLabel);
		

		
		

		signUpButton.addActionListener(new MyActionListener());

		loginButton.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		User user = new User();
		String id = (IDField.getText());
		char[] pw = (PWField.getPassword());
//		boolean possible;
//		possible = UserDAO.getInstance().login(user);
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == signUpButton) {
				win.change("signUp");
			} else if (e.getSource() == loginButton) {
				// DAO.로그인
				try {
					User user = new User();
					user.setUserid(IDField.getText());
					user.setPasswd(PWField.getPassword());
					UserDAO.getInstance().login(user);
					if (UserDAO.getInstance().login(user) == true) {
						System.out.println("Login Success.");
						JOptionPane.showMessageDialog(null, "로그인 성공!!");
						userId = IDField.getText();
						win.change("dynamicBeat");
					} else {
						System.out.println("Login Failed");
						JOptionPane.showMessageDialog(null, "로그인 에 실패하였습니다.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		}
	}
}
