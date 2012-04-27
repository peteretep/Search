import java.util.ArrayList;


public class WordIndex 
{
	String wordInFile;
	ArrayList <String> filesThatContainThisWord=new ArrayList<String>();
	
	public WordIndex(String word)
	{
		this.wordInFile=word;
		
	}
	
	public void addFileToListIfContainsWord(String fileLoc)
	{
		filesThatContainThisWord.add(fileLoc);
	}
	
	public void printFilesThatContainThisWord()
	{
		System.out.println("word = " +wordInFile);
		for (int i=0;i<filesThatContainThisWord.size();i++)
		{
			System.out.println(filesThatContainThisWord.get(i));
		}
	}
	
	public String getWord()
	{
		return wordInFile;
	}
	
	
}
