package dynamic_beat_17.model;

public class Score {
	private String userid;
	private String music;
	private int highScore;
	private int rank;
	private int totalScore;
	private boolean start;
	
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	@Override
	public String toString() {
		return "Score [userid : " + userid + "\n, music : " + music + "\n, highScore : " + highScore + "\n, rank : "
				+ rank + "\n, totalScore : " + totalScore + "\n, start : " + start + "]";
	}
	
}
