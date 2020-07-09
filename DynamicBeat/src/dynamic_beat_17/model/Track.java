package dynamic_beat_17.model;

public class Track {
	
	private String titleImage; //제목 부분 이미지
	private String startImage; //게임 선택 창 표지 이미지
	private String gameImage; // 해당 곡을 실행했을때 표지 임비지
	private String startMusic; //게임 선택 창 음악
	private String gameMusic; // 해당 곡을 실행했을 때 음악
	private String titleName; //곡 제목
//	private String gameMovie; //게임 영상
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	//생성자 추가(<생성자 필드 생성> track 클래스를 이용해서 새로운 변수를 만들어 줄때 한번에 값들을 초기화 해주는 메소드)
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic, /*String gameMovie,*/ String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
//		this.gameMovie = gameMovie;
		this.titleName = titleName;
	}
	
	
	
}
