package dynamic_beat_17.control;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

import dynamic_beat_17.Main;
import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.Score;
import dynamic_beat_17.model.Track;
import dynamic_beat_17.service.impl.ScoreDAO;

public class DynamicBeat extends JPanel /* JFrame */ {

	private Image screenImage; // �뜑釉붾쾭�띁留곸쓣 �쐞�빐 �쟾泥� �솕硫댁뿉 ���빐�꽌 �씠誘몄�瑜� �떞�뒗 �몢 �씤�뒪�꽩�뒪
	private Graphics screenGraphic;
	private Main win;

	// Main�겢�옒�뒪�쓽 �쐞移섎�� 湲곕컲�쑝濡� �빐�꽌 �씤�듃濡쒖씠誘몄� �뙆�씪�쓣 �뼸�뼱�삩 �썑 洹멸쾬�쓽 �씠誘몄� �씤�뒪�꽩�뒪瑜� background �씪�뒗 �씠誘몄�
	// 蹂��닔�뿉 珥덇린�솕 �빐二쇰뒗寃�.
	// background瑜� �쐞履쎌뿉�꽌 諛붾줈 珥덇린�솕 �븯�룄濡� �꽕�젙

	// �솕硫댁쟾�솚�슜 蹂��닔 0:�씤�듃濡쒕찓�씤 1:�뿏�꽣硫붿씤(怨≪꽑�깮) 2:�옲�궧李� 3:�씤寃뚯엫
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


	// introBackground => background 濡� �븳 �씠�쑀�뒗 �떆�옉�솕硫댁뿉�꽌 硫붿씤�솕硫댁쑝濡� �쟾�솚�릺�뿀�쓣 寃쎌슦 �떒�닚�엳 蹂��닔�뿉 �씠誘몄�留�
	// 蹂�寃쏀븯湲� �쐞�빐�꽌
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
 

	ArrayList<Track> trackList = new ArrayList<Track>(); // ArrayList �궗�슜, �씤�뜳�뒪 0遺��꽣 �궗�슜.

	// 泥섏쓬�뿉�뒗 �꽑�뼵留� �븷 �닔 �엳�룄濡� ,珥덇린�솕 �떆�궗 �븘�슂 �뾾�룄濡�
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic; // 蹂��닔 �꽕�젙
	// �씤�듃濡� 裕ㅼ쭅�씠 臾댄븳�젙�쑝濡� �옱�깮�릺�룄濡� �빐二쇰뒗寃�
	private Music introMusic = new Music("introMusic.mp3", true/* 吏곸젒 醫낅즺 �쟾�뿉�뒗 怨꾩냽�빐�꽌 諛섎났 �옱�깮 */);
	private int nowSelected = 0; // �쁽�옱 �꽑�깮�맂 怨≪쓣 �쓽誘�, 留� 泥섏쓬�뿉�뒗 0�쑝濡� 泥ル쾲吏� 怨≪쓣 �쓽誘�

	// �븯�굹�쓽 寃뚯엫�씠�씪�뒗 寃껋� �븯�굹�쓽 �봽濡쒓렇�옩�씠 �떎�뻾�릺�뿀�쓣�븣 �떒 �븯�굹�쓽 寃뚯엫留� 吏꾪뻾 媛��뒫, 利� �룞�떆�뿉 �뿬�윭 寃뚯엫�쓣 �떎�뻾 �떆�궗 �닔 �뾾湲곕븣臾몄뿉,
	// �봽濡쒓렇�옩 �쟾泥댁뿉�꽌 �넻�슜�씠 媛��뒫�븯�떎.
	public static Game game; // �뵲�씪�꽌, public static�쑝濡� �꽑�뼵�빐以��떎. �씠�젣 寃뚯엫�씠�씪�뒗 蹂��닔�뒗 �봽濡쒖젥�듃 �쟾泥댁뿉�꽌 �궗�슜 媛��뒫�븳 蹂��닔媛� �맂�떎.

	// �옲�겕�꽑�뼵
	public static Rank rank;
	
	//寃뚯엫寃곌낵李�
	public static GameResult gameresult;


	public DynamicBeat(Main win) /* �깮�꽦�옄 */ {
		// �닚�꽌�뿉 留욊쾶 �꽔�뼱以뚯쑝濡쒖뜥 蹂��닔瑜� �닚�떇媛꾩뿉 珥덇린�솕, 珥덇린�솕 �맂 蹂��닔瑜� �듃�옓由ъ뒪�듃�뿉 �꽔�뼱以뚯쑝濡쒖뜥 怨〓뱾�쓽 由ъ뒪�듃瑜� 愿�由ы븷 �닔 �엳寃� �맖
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

//		setUndecorated(true); // �떎�뻾 �떆 湲곕낯�쟻�쑝濡� 蹂댁씠�뒗 硫붾돱諛붽� 蹂댁씠吏� �븡寃뚮맖
//		setTitle("Dynamic Beat");
//		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
//		setResizable(false); // �븳踰� 留뚮뱾�뼱吏� 李쎌� �궗�슜�옄媛� �엫�쓽濡� 以꾩씠嫄곕굹 �뒛由� �닔 �뾾�떎.
//		setLocationRelativeTo(null); // �떎�뻾�떆 寃뚯엫 �솕硫댁씠 �젙 以묒븰�뿉 �쑉寃� �꽕�젙
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 寃뚯엫 李쎌쓣 �떕�쓣�븣 �봽濡쒓렇�옩 �쟾泥닿� 醫낅즺�릺�뒗寃�
//		setVisible(true); // 寃뚯엫 �솕硫댁씠 �젙�긽�쟻�쑝濡� 異쒕젰�릺�룄濡�(蹂댁씠寃� �븯�룄濡�)
		setBackground(new Color(0, 0, 0, 0)); // �럹�씤�듃 而щ윭�떆 諛곌꼍�쓣 �쉶�깋�씠 �븘�땲�씪 �쟾遺� �씛�깋�쑝濡� 蹂��솚

		this.win = win;

		setLayout(null); // 踰꾪듉�씠�굹 �떎瑜멸쾬�뱾�쓣 �꽔�뿀�쓣�븣 洹� �쐞移섏뿉 洹몃�濡� 苑귦엳�룄濡� �꽕�젙

		addKeyListener(new KeyListener()); // �궎由ъ뒪�꼫瑜� �궗�슜
		
		introMusic.start(); // 寃뚯엫�쓣 �떎�뻾�븯硫댁꽌 �룞�떆�뿉 �쓬�븙�씠 �떆�옉�맖.

		// exit踰꾪듉 �궘�젣 ->16李멸퀬
		

		startButton.setBounds(40, 200, 400, 100); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		startButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬
				enterMain();
			}
		});
		add(startButton);

		rankButton.setBounds(40, 330, 400, 100); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		rankButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		rankButton.setContentAreaFilled(false);
		rankButton.setFocusPainted(false);
		rankButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rankButton.setIcon(rankButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				rankButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rankButton.setIcon(rankButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				rankButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬
				try {
					Thread.sleep(1000); // �슚怨쇱쓬�쓣 紐� �뱽怨� 爰쇱쭏 寃쎌슦瑜� ��鍮꾪빐 �냼由ш� �굹�삩 �썑 1珥� �썑�뿉 醫낅즺�떆�궎�뒗 寃�
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					System.out.println(ex.getMessage());
				}
				// �옲�겕�떆�뒪�뀥吏꾩엯
				rankMain();
			}
		});
		add(rankButton);

		quitButton.setBounds(40, 460, 400, 100); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		quitButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬
				try {
					Thread.sleep(1000); // �슚怨쇱쓬�쓣 紐� �뱽怨� 爰쇱쭏 寃쎌슦瑜� ��鍮꾪빐 �냼由ш� �굹�삩 �썑 1珥� �썑�뿉 醫낅즺�떆�궎�뒗 寃�
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					System.out.println(ex.getMessage());
				}
				System.exit(0); // �겢由� �뻽�쓣�븣 �빐�떦 �옄泥� 寃뚯엫�씠 醫낅즺媛� �맂�떎.
			}
		});
		add(quitButton);
		
//		//�씛�깋 �뙘�뾽源뚮뒗嫄�
		popup.setBounds(55, 200, 360, 360); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		add(popup);

		

		leftButton.setVisible(false); // 留� 泥섏쓬�� 蹂댁씠吏� �븡�룄濡�
		leftButton.setBounds(140, 310, 60, 60); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		leftButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬
				selectLeft(); // �쇊履� 踰꾪듉 �씠踰ㅽ듃
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		rightButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬
				selectRight(); // �삤瑜몄そ 踰꾪듉 �씠踰ㅽ듃
			}
		});
		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		easyButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬
				try {
					gameStart(nowSelected, "Easy");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		hardButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					gameStart(nowSelected, "Hard");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60); // 硫붾돱諛붿쓽 媛��옣 �삤瑜몄そ�뿉 �쐞移� (x, y, 媛�濡쒗겕湲�, �꽭濡쒗겕湲�)
		backButton.setBorderPainted(false);// �젣怨듯븯�뒗 紐⑥뒿�� �슦由ш� �썝�븯�뒗 紐⑥뒿�씠 �븘�땲誘�濡�, �닔�젙�빐以��떎.
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �뿏�꽣�씠誘몄�濡� 蹂�寃�
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 而ㅼ꽌 蹂�寃�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �삱�씪媛붿쓣�븣 �슚怨쇱쓬
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 踰좎씠吏곸쑝濡� 蹂�寃�
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 留덉슦�뒪媛� 踰쀬뼱�궗�쓣�븣 而ㅼ꽌 蹂�寃�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 留덉슦�뒪媛� �겢由��릺�뿀�쓣�븣 �슚怨쇱쓬

				if (stage == 3) {
					enterMain(); // 硫붿씤 �솕硫댁쑝濡� �룎�븘媛��뒗 �씠踰ㅽ듃
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

//硫붾돱諛붾�遺� �궘�젣 -> 16李멸퀬

	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // �겕湲곕쭔�겮 �뒪�겕由� �씠誘몄�
		screenGraphic = screenImage.getGraphics(); // �뒪�겕由� 洹몃옒�뵿�� �뒪�겕由� �씠誘몄�瑜� �씠�슜�빐�꽌 洹몃옒�뵿 媛앹껜瑜� �뼸�뼱�샂
		try {
			screenDraw((Graphics2D) screenGraphic);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		g.drawImage(screenImage, 0, 0, null); // �뒪�겕由� �씠誘몄�瑜� 0,0�쐞移섏뿉 洹몃젮以�

	}

	// 洹몃┝ 洹몃━�뒗 遺�遺�
	public void screenDraw(Graphics2D g) throws SQLException { // 洹몃옒�뵿�뒪2D 留ㅺ컻蹂��닔濡� 蹂��솚
		g.drawImage(background, 0, 0, null); // add �맂 寃껊뱾�씠 �븘�땶 �떒�닚 �씠誘몄��뱾�쓣 �솕硫댁뿉 異쒕젰�빐二쇰뒗寃�
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
		paintComponents(g); // 硫붾돱諛�, JLabel�벑�쓣 JFrame�븞�뿉 異붽��븯硫� 洹멸쾬�쓣 洹몃젮二쇰뒗 寃� (add濡� 異붽��맂寃껊뱾 )
		// draw濡� 洹몃━嫄곕굹 paint濡� 洹몃━�뒗�뜲 硫붾돱諛붿쓽 寃쎌슦 �빆�긽 議댁옱, �뿭�룞�쟻�쑝濡� ��吏곸씠吏� �븡�쑝誘�濡� �빆�긽 怨좎젙�씠誘�濡� �럹�씤�듃瑜� �궗�슜(�젙�쟻)
		// 諛깃렇�씪�슫�뱶 媛숈씠 �떒�닚 �씠誘몄��뒗 draw濡� 洹몃┝
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // paint�뒗 JFrame�쓣 �긽�냽諛쏆� GUI寃뚯엫�뿉�꽌 媛��옣 癒쇱� 洹몃젮二쇰뒗 泥ル쾲吏� �븿�닔

	}

	public void selectTrack(int nowSelected /* �쁽�옱 �꽑�깮�맂 怨≪쓽 踰덊샇瑜� 諛쏅뒗 �씤�닔 */) {
		if (selectedMusic != null)
			selectedMusic.close(); // �뼱�뼡 怨≪씠 �떎�뻾�릺怨� �엳�떎硫� 諛붾줈 醫낅즺
		// �쁽�옱 �꽑�깮�맂 怨≪씠 媛�吏�怨� �엳�뒗 ���씠�� �씠誘몄�瑜� 媛�吏�怨� �삤寃좊떎�뒗 �쓽誘�
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		// �쁽�옱 �꽑�깮�맂 怨≪쓽 �씠誘몄�瑜� 怨≪쓽 �씠誘몄�濡� 諛붽퓭二쇰뒗寃�.
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		// �쓬�븙�뙆�씪 �꽑�깮�맂寃�
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); // true濡� 臾댄븳 �옱�깮
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0) // �쁽�옱 �꽑�깮�맂 怨≪씠 泥ル쾲吏� 怨≪씠�씪硫�
			nowSelected = trackList.size() - 1; // 0踰덉㎏�씪�븣 �쇊履쎌쓣 �늻瑜대㈃ 媛��옣 �삤瑜몄そ�쓽 怨≪씠 �꽑�깮�릺�뼱�빞 �븯湲� �븣臾�
		else
			nowSelected--; // 0踰덉㎏媛� �븘�땶寃쎌슦, nowSelected�뿉�꽌 -1�쓣 �빐二쇰㈃ �맖.
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) // �쁽�옱 �꽑�깮 怨≪씠 留덉�留� 怨≪씠�씪硫�
			nowSelected = 0; // 留덉�留됱씪�븣 �삤瑜몄そ�쓣 �늻瑜대㈃ 媛��옣 �쇊履쎌쑝濡� 怨≪씠 �꽑�깮�릺�뼱�빞 �븯湲� �븣臾�
		else
			nowSelected++; // 留덉�留됱씠 �븘�땶 寃쎌슦, nowSelected�뿉�꽌 +1�쓣 �빐二쇰㈃ �맖.
		selectTrack(nowSelected);
	}

	// 硫붿씤�솕硫�
	public void introMain() {
		stage = 0;
		popup.setVisible(true);
		backButton.setVisible(false);
		startButton.setVisible(true);
		rankButton.setVisible(true);
		quitButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/backGround2.jpg")).getImage();
		leftButton.setVisible(false);// 硫붿씤�뿉�꽌�뒗 醫�, �슦 �씠�룞 媛��뒫�븳 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		rightButton.setVisible(false);
		easyButton.setVisible(false);// 硫붿씤�뿉�꽌�뒗 �궃�씠�룄 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		hardButton.setVisible(false);
		if (selectedMusic != null)
			selectedMusic.close();
		nowSelected = 0;
//		introMusic = new Music("introMusic.mp3", true);
//		introMusic.start();
		/// �겢濡쒖쫰濡� ���옣�븯硫� �뒪�젅�뱶媛� 吏��썙�졇�꽌 �씪�뱶�굹 �뒳由쎌쑝濡� �빐�빞�맖
	}

	// 寃뚯엫�꽑�깮李�
	public void enterMain() {
		stage = 1;
		popup.setVisible(false);

		backButton.setVisible(true);
		startButton.setVisible(false);
		rankButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/backGround2.jpg")).getImage();
		leftButton.setVisible(true);// 硫붿씤�뿉�꽌�뒗 醫�, �슦 �씠�룞 媛��뒫�븳 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		rightButton.setVisible(true);
		easyButton.setVisible(true);// 硫붿씤�뿉�꽌�뒗 �궃�씠�룄 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		hardButton.setVisible(true);
		selectTrack(nowSelected); // 留� 泥섏쓬�뿉�뒗 泥ル쾲吏� 怨≪쓣 �떎�뻾
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
		leftButton.setVisible(false);// 硫붿씤�뿉�꽌�뒗 醫�, �슦 �씠�룞 媛��뒫�븳 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		rightButton.setVisible(false);
		easyButton.setVisible(false);// 硫붿씤�뿉�꽌�뒗 �궃�씠�룄 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		hardButton.setVisible(false);
		if (selectedMusic != null)
			selectedMusic.close();
		rank = new Rank();
		rank.start();

	}

	// 怨≪꽑�깮遺�遺�
	public void gameStart(int nowSelected, String difficulty) throws SQLException {
		stage = 3;
		if (selectedMusic != null)
			selectedMusic.close();
		leftButton.setVisible(false); // 硫붿씤�솕硫댁씠 �븘�땲誘�濡�, 怨� �꽑�깮 踰꾪듉�� 蹂댁뿬吏�硫� �븞�맂�떎.
		rightButton.setVisible(false);
		easyButton.setVisible(false); // 硫붿씤�솕硫댁씠 �븘�땲誘�濡�, �궃�씠�룄 踰꾪듉�� 蹂댁뿬吏�硫� �븞�맂�떎.
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		setFocusable(true); // 硫붿씤 �봽�젅�엫�뿉 �궎蹂대뱶 �룷而ㅼ뒪媛� 留욎떠吏�.
		requestFocusInWindow(); // �궎由ъ뒪�꼫 �븘�닔

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
		leftButton.setVisible(false);// 硫붿씤�뿉�꽌�뒗 醫�, �슦 �씠�룞 媛��뒫�븳 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		rightButton.setVisible(false);
		easyButton.setVisible(false);// 硫붿씤�뿉�꽌�뒗 �궃�씠�룄 踰꾪듉�씠 蹂댁뿬�빞 �븯誘�濡�
		hardButton.setVisible(false);
		if (selectedMusic != null)
			selectedMusic.close();
		rank = new Rank();
		rank.start();
		
	
	}

}
