import java.util.ArrayList;


public class Results {
	String fileName;
	ArrayList<String> words=new ArrayList<String>();
	
	
	public Results(String filename)
	{
		this.fileName=filename;
	}
	
	public void addWord(String word)
	{
		words.add(word);
	}
}
