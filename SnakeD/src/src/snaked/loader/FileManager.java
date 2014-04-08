package src.snaked.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import src.snaked.Entities.Highscore;

public class FileManager{
	private final String FILENAME = "highscores";
	private final String ENDCHAR = "#";
	int madeArchive = 0;
	
	public void writeToArchive(List<Highscore> list){
		
			FileWriter targetFile;
			try{
				targetFile = new FileWriter(FILENAME + ".txt");
				PrintWriter file = new PrintWriter(targetFile);
				file.println("highscores for snaked - do not delete..");
				for(Highscore i: list)
				{
					file.print(i.getName() + ENDCHAR);
					file.print(i.getScore() + ENDCHAR);
					file.println();				
				}
				targetFile.close();
			}catch (IOException e) {
				e.printStackTrace();
				System.out.println("An error occured while writing to archive");
			}
		}
	
		public List<Highscore> readFromArchive(){
			FileReader targetFile;
			List<Highscore> list = new LinkedList<Highscore>();
			try{
				targetFile = new FileReader(FILENAME + ".txt");
				BufferedReader file = new BufferedReader(targetFile);
				String s = file.readLine();
				s = file.readLine();
		
					while(s != null){
						String[] string = s.split(ENDCHAR);
						String name = string[0];
						int score = Integer.parseInt(string[1]);		
						try{
							list.add(new Highscore(score, name));
							s = file.readLine();
						}catch (RuntimeException t){				
							t.printStackTrace();
							System.out.println("An error occured while reading highscores from file.. Archive corrupted.");
						}
					}
					targetFile.close();
			}catch (IOException e) {
				//e.printStackTrace();
				System.out.println("PIAA");
				writeToArchive(new LinkedList<Highscore>());
				madeArchive++;
				
				if(madeArchive <= 1)
					list = readFromArchive();
			}
			
			return list;
		}
}
