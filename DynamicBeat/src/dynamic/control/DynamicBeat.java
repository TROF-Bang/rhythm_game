package dynamic.control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dynamic_beat_17.Main;
import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.Score;
import dynamic_beat_17.model.Track;
import dynamic_beat_17.service.impl.ScoreDAO;

public class DynamicBeat extends JFrame /* JFrame */ {

	private Image screenImage; 
	private Graphics screenGraphic;
	private Main win;

	
	static int stage = 0;

	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/button/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/button/startButton.png"));

	private ImageIcon rankButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/button/rankButtonEntered.png"));
	private ImageIcon rankButtonBasicImage = new ImageIcon(Main.class.getResource("../images/button/rankButton.png"));

	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/button/quitButtonEnteted.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/button/quitButton.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	
	private ImageIcon popupImage = new ImageIcon(Main.class.getResource("../images/popupWhite.png"));


	
	private Image background = new ImageIcon(Main.class.getResource("../images/backGround2.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

//	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton rankButton = new JButton(rankButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private JLabel popup = new JLabel(popupImage);
 

	ArrayList<Track> trackList = new ArrayList<Track>(); 
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic; 
	
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; 

	
	public static Game game; 

	
	public static Rank rank;
	
	
	public static GameResult gameresult;


	public DynamicBeat() /* �깮�꽦�옄 */ {

		trackList.add(
				new Track("Mighty Love Title Image.png", "Mighty Love Start Image.jpg", "Mighty Love Game Image.jpg",
						"Mighty Love Selected.mp3", "Joakim Karud - Mighty Love.mp3", "Joakim Karud - Mighty Love"));
		trackList.add(
				new Track("Wild Flower Title Image.png", "Wild Flower Start Image.jpg", "Wild Flower Game Image.jpg",
						"Wild Flower Selected.mp3", "Joakim Karud - Wild Flower.mp3", "Joakim Karud - Wild Flower"));
		trackList.add(new Track("Energy Title Image.png", "Energy Start Image.jpg", "Energy Game Image.jpg",
				"Energy Selected.mp3", "Bensound - Energy.mp3", "Bensound - Energy"));
		trackList.add(new Track("Ask The Wind Title.png", "Ask The Wind Start.jpg", "Ask The Wind Game.jpg",
				"Miya - Ask the wind.mp3", "Miya - Ask the wind.mp3", "Miya - Ask The Wind"));

		
		setUndecorated(true); 
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); 
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true); 
		setBackground(new Color(0, 0, 0, 0)); 

		this.win = win;

		setLayout(null);

		addKeyListener(new KeyListener()); 
		
		introMusic.start(); 

		
		

		startButton.setBounds(40, 200, 400, 100); 
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��
				enterMain();
			}
		});
		add(startButton);

		rankButton.setBounds(40, 330, 400, 100); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		rankButton.setBorderPainted(false);// �젣?�듯�?�뒗 紐⑥?���� �슦?��?�� �썝�븯�뒗 紐⑥?���씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		rankButton.setContentAreaFilled(false);
		rankButton.setFocusPainted(false);
		rankButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rankButton.setIcon(rankButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				rankButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rankButton.setIcon(rankButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				rankButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��
				try {
					Thread.sleep(1000); // �슚?�쇱?���쓣 紐� �뱽?�� ?��?���? 寃쎌?��?���? ��?��꾪빐 �냼?��?�� �굹�삩 �썑 1?���? �썑�뿉 ?��?��즺�?���궎�뒗 寃�
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					System.out.println(ex.getMessage());
				}
				// �옲�겕�떆�뒪��?�吏꾩엯
				rankMain();
			}
		});
		add(rankButton);

		quitButton.setBounds(40, 460, 400, 100); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		quitButton.setBorderPainted(false);// �젣?�듯�?�뒗 紐⑥?���� �슦?��?�� �썝�븯�뒗 紐⑥?���씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��
				try {
					Thread.sleep(1000); // �슚?�쇱?���쓣 紐� �뱽?�� ?��?���? 寃쎌?��?���? ��?��꾪빐 �냼?��?�� �굹�삩 �썑 1?���? �썑�뿉 ?��?��즺�?���궎�뒗 寃�
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					System.out.println(ex.getMessage());
				}
				System.exit(0); // �겢?���? �뻽�쓣�븣 �빐�떦 �옄泥� 寃뚯?���씠 ?��?��즺媛�? �맂�떎.
			}
		});
		add(quitButton);
		
//		//�씛�깋 �뙘�뾽源뚮?��嫄�
		popup.setBounds(55, 200, 360, 360); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		add(popup);

		

		leftButton.setVisible(false); // 留� 泥섏?���� 蹂댁?��吏� �븡�룄濡�
		leftButton.setBounds(140, 310, 60, 60); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		leftButton.setBorderPainted(false);// �젣?�듯�?�뒗 紐⑥?���� �슦?��?�� �썝�븯�뒗 紐⑥?���씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��
				selectLeft(); // �쇊履� 踰꾪?�� �씠踰ㅽ?��
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		rightButton.setBorderPainted(false);// �젣?�듯�?�뒗 紐⑥?���� �슦?��?�� �썝�븯�뒗 紐⑥?���씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��
				selectRight(); // �삤?��몄そ 踰꾪?�� �씠踰ㅽ?��
			}
		});
		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		easyButton.setBorderPainted(false);// �젣?�듯�?�뒗 紐⑥?���� �슦?��?�� �썝�븯�뒗 紐⑥?���씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��
				try {
					gameStart(nowSelected, "Easy");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		hardButton.setBorderPainted(false);// �젣?�듯�?�뒗 紐⑥?���� �슦?��?�� �썝�븯�뒗 紐⑥?���씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					gameStart(nowSelected, "Hard");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60); // 硫붾?��諛붿?�� 媛��옣 �삤?��몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�?, �꽭濡쒗겕湲�?)
		backButton.setBorderPainted(false);// �젣?�듯�?�뒗 紐⑥?���� �슦?��?�� �썝�븯�뒗 紐⑥?���씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �뿏�꽣�씠誘몄�濡�? 蹂�寃�
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉?���뒪媛� �삱�씪媛붿?���븣 ?�ㅼ�? 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �삱�씪媛붿?���븣 �슚?�쇱?��
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 踰좎?��吏곸?��濡� 蹂�寃�
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉?���뒪媛� 踰�?�뼱�궗�쓣�븣 ?�ㅼ�? 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉?���뒪媛� �겢?����릺��?�쓣�븣 �슚?�쇱?��

				if (stage == 3) {
					enterMain(); // 硫붿?�� �솕硫댁?��濡� �룎�븘媛��뒗 �씠踰ㅽ?��
					game.close();
				} else if (stage == 1) {
					introMain();
					introMusic = new Music("introMusic.mp3", true);
					introMusic.start();

				} else if (stage == 2) {
					introMain();

				}
			}
		});
		add(backButton);

//		introMusic = new Music("introMusic.mp3", true);
//		introMusic.start();

//硫붾?��諛붾�遺�? �궘�젣 -> 16李멸??

	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // �겕湲곕쭔��? �뒪�겕?���? �씠誘몄�?
		screenGraphic = screenImage.getGraphics(); // �뒪�겕?���? 洹몃?���뵿�� �뒪�겕?���? �씠誘몄�瑜�? �씠�슜�빐�꽌 洹몃?���뵿 媛앹껜瑜�? �뼸�뼱�샂
		try {
			screenDraw((Graphics2D) screenGraphic);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		g.drawImage(screenImage, 0, 0, null); // �뒪�겕?���? �씠誘몄�瑜�? 0,0�쐞移섏�? 洹몃?��以�

	}

	// 洹몃?�� 洹몃?���뒗 ?���遺�?
	public void screenDraw(Graphics2D g) throws SQLException { // 洹몃?���뵿�뒪2D 留ㅺ컻蹂��?��濡� 蹂��솚
		g.drawImage(background, 0, 0, null); // add �맂 寃껊뱾�?�� �븘�땶 �떒�닚 �씠誘몄��뱾�?�� �솕硫댁�? ?��?��?���빐二쇰?��寃�
		if (stage == 1) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (stage == 2) {
			try {
				rank.screenDraw(g);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stage == 3) {
			game.screenDraw(g);
		}
		
		if (stage == 4) {
			try {
				gameresult.screenDraw(g); // �썝�옒 �궡�슜�� Game�겢�옒�뒪濡� �씠�룞�븳寃�.
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			}
		}
		paintComponents(g); // 硫붾?��諛�, JLabel�벑�쓣 JFrame�븞�뿉 ?��붽��븯硫� 洹멸쾬�?�� 洹몃?��二쇰?�� 寃� (add濡� ?��붽��맂寃껊�? )
		// draw濡� 洹몃?��嫄곕�? paint濡� 洹몃?���뒗�뜲 硫붾?��諛붿?�� 寃쎌?�� �빆�긽 議댁?��, �뿭�룞�쟻�쑝濡� ��吏곸?��吏� �븡�쑝誘�濡� �빆�긽 ?�좎?���씠誘�濡� �럹�씤�듃?���? �궗�슜(�젙�쟻)
		// 諛깃?���씪�슫�뱶 媛숈?�� �떒�닚 �씠誘몄��?�� draw濡� 洹몃?��
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // paint�뒗 JFrame�쓣 �긽�냽諛쏆�? GUI寃뚯?���뿉�꽌 媛��옣 ?��?���? 洹몃?��二쇰?�� 泥ル쾲吏�? �븿�닔

	}

	public void selectTrack(int nowSelected /* �쁽�옱 �꽑�깮�맂 ?�≪?�� 踰덊?��?���? 諛쏅?�� �씤�닔 */) {
		if (selectedMusic != null)
			selectedMusic.close(); // �뼱�뼡 ?�≪?�� �떎�뻾�릺?�� �엳�떎硫� 諛붾�? ?��?���?
		// �쁽�옱 �꽑�깮�맂 ?�≪?�� 媛�吏�?�� �엳�뒗 ���씠�� �씠誘몄�瑜�? 媛�吏�?�� �삤寃좊?���뒗 �쓽誘�
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		// �쁽�옱 �꽑�깮�맂 ?�≪?�� �씠誘몄�瑜�? ?�≪?�� �씠誘몄�濡�? 諛붽?��二쇰?��寃�.
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		// �쓬�븙�뙆�씪 �꽑�깮�맂寃�
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); // true濡� ?��?���? �옱�깮
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0) // �쁽�옱 �꽑�깮�맂 ?�≪?�� 泥ル쾲吏�? ?�≪?���씪硫�
			nowSelected = trackList.size() - 1; // 0踰덉?���씪�븣 �쇊履쎌?�� �늻?��???�� 媛��옣 �삤?��몄そ�쓽 ?�≪?�� �꽑�깮�릺�뼱�빞 �븯湲� �븣?���?
		else
			nowSelected--; // 0踰덉?��媛� �븘�땶寃쎌?��, nowSelected�뿉�꽌 -1�쓣 �빐二쇰?�� �맖.
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) // �쁽�옱 �꽑�깮 ?�≪?�� 留덉�留�? ?�≪?���씪硫�
			nowSelected = 0; // 留덉�留?��?���븣 �삤?��몄そ�쓣 �늻?��???�� 媛��옣 �쇊履쎌?��濡� ?�≪?�� �꽑�깮�릺�뼱�빞 �븯湲� �븣?���?
		else
			nowSelected++; // 留덉�留?��?�� �븘�땶 寃쎌?��, nowSelected�뿉�꽌 +1�쓣 �빐二쇰?�� �맖.
		selectTrack(nowSelected);
	}

	// 硫붿?���솕硫�
	public void introMain() {
		stage = 0;
		popup.setVisible(true);
		backButton.setVisible(false);
		startButton.setVisible(true);
		rankButton.setVisible(true);
		quitButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/backGround2.jpg")).getImage();
		leftButton.setVisible(false);// 硫붿?���뿉�꽌�뒗 ?���?, �슦 �씠�룞 媛��뒫�븳 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		rightButton.setVisible(false);
		easyButton.setVisible(false);// 硫붿?���뿉�꽌�뒗 �궃�씠�룄 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		hardButton.setVisible(false);
		if (selectedMusic != null)
			selectedMusic.close();
		nowSelected = 0;
//		introMusic = new Music("introMusic.mp3", true);
//		introMusic.start();
		/// �겢濡쒖쫰濡�? ���옣�븯硫� �뒪�젅�뱶媛� 吏��썙�졇�꽌 �씪�뱶�굹 �뒳?��?��?��濡� �빐�빞�맖
	}

	// 寃뚯?���꽑�깮李�
	public void enterMain() {
		stage = 1;
		popup.setVisible(false);

		backButton.setVisible(true);
		startButton.setVisible(false);
		rankButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/backGround2.jpg")).getImage();
		leftButton.setVisible(true);// 硫붿?���뿉�꽌�뒗 ?���?, �슦 �씠�룞 媛��뒫�븳 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		rightButton.setVisible(true);
		easyButton.setVisible(true);// 硫붿?���뿉�꽌�뒗 �궃�씠�룄 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		hardButton.setVisible(true);
		selectTrack(nowSelected); // 留� 泥섏?���뿉�뒗 泥ル쾲吏�? ?�≪?�� �떎�뻾
		introMusic.close();
	}

	
	public void rankMain() {
		stage = 2;
		popup.setVisible(false);

		backButton.setVisible(true);
		startButton.setVisible(false);
		rankButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/rankBackground.jpg")).getImage();
		leftButton.setVisible(false);// 硫붿?���뿉�꽌�뒗 ?���?, �슦 �씠�룞 媛��뒫�븳 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		rightButton.setVisible(false);
		easyButton.setVisible(false);// 硫붿?���뿉�꽌�뒗 �궃�씠�룄 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		hardButton.setVisible(false);
		if (selectedMusic != null)
			selectedMusic.close();
		rank = new Rank();
		rank.start();

	}

	// ?�≪꽑��??���遺�?
	public void gameStart(int nowSelected, String difficulty) throws SQLException {
		stage = 3;
		if (selectedMusic != null)
			selectedMusic.close();
		leftButton.setVisible(false); // 硫붿?���솕硫댁?�� �븘�땲誘�濡�, ?�� �꽑�깮 踰꾪?���� 蹂댁뿬吏�硫�? �븞�맂�떎.
		rightButton.setVisible(false);
		easyButton.setVisible(false); // 硫붿?���솕硫댁?�� �븘�땲誘�濡�, �궃�씠�룄 踰꾪?���� 蹂댁뿬吏�硫�? �븞�맂�떎.
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		setFocusable(true); // 硫붿?�� �봽�젅�엫�뿉 �궎蹂�?�? �룷?�ㅼ?��媛� 留욎?��吏�.
		requestFocusInWindow(); // �궎?��?�뒪�꼫 �븘�닔

		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start(); // �윴 �븿�닔 �옄�룞 �떎�뻾, �끂�듃 �깮�꽦�맖.
		}

	
	public void gameResult() {
		
		backButton.setVisible(true);
		startButton.setVisible(false);
		rankButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/rankBackground.jpg")).getImage();
		leftButton.setVisible(false);// 硫붿?���뿉�꽌�뒗 ?���?, �슦 �씠�룞 媛��뒫�븳 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		rightButton.setVisible(false);
		easyButton.setVisible(false);// 硫붿?���뿉�꽌�뒗 �궃�씠�룄 踰꾪?���씠 蹂댁뿬��? �븯誘�濡�
		hardButton.setVisible(false);
		if (selectedMusic != null)
			selectedMusic.close();
		rank = new Rank();
		rank.start();
		
	
	}

}
