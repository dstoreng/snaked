package src.snaked.Entities;

public class Highscore implements Comparable<Highscore> {
	private int score;
	private String name;
	
	public Highscore(int score, String name){
		this.score = score;
		this.name = name;
	}
	
	public Highscore(int score){
		this.score = score;
		name = "Anon";
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int x){
		this.score = x;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	public String toString(){
		return this.score + " - " + this.name;
	}

	@Override
	public int compareTo(Highscore o) {
		return Integer.compare(this.score, o.score);
	}
}
