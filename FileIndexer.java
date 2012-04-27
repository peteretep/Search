import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class FileIndexer 
{
	static HashMap<String, List<String>> index=new HashMap<String, List<String>>();
	
	
	
	public FileIndexer()
	{
		/*open the file
		 * read the file word by word
		 * for each word, addWordsToIndex()
		 * 
		
		 */
		String[] files=new String[]{"file1.txt","file2.txt","file3.txt"};
		
		for(int i=0;i<files.length;i++)
		{
		// for each file, open it
			try 
			{
				
				Scanner input = new Scanner(new File(files[i]));
				// iterates through words
				while (input.hasNext())
				{
					String	word=input.next();	
					List<String> fileList=index.get(word);
					if(fileList==null)
					{
						index.put(word, (fileList=new ArrayList<String>()));
					}
					fileList.add(files[i]);
				}
			
			}
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
