import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class FileIndexer 	
{
	HashMap<String, List<String>> index=new HashMap<String, List<String>>();
	
	
	
	public FileIndexer(File [] files)
	{
		long start=System.currentTimeMillis();
		System.out.println(start);
		/*open the file
		 * read the file word by word
		 * for each word, 
		 * 
		
		 */
		
		
		for(int i=0;i<files.length;i++)
		{
		// for each file, open it
			try 
			{
				
				Scanner input = new Scanner((files[i]));
				// iterates through words
				while (input.hasNext())
				{
					String	word=input.next();	
					List<String> fileList=index.get(word);
					if(fileList==null)
					{
						index.put(word, (fileList=new ArrayList<String>()));
					}
					fileList.add(files[i].getAbsolutePath());
				}
				
				
			
			}
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//	System.out.println(index.keySet() + " : " + index.values());

	
		long finished=System.currentTimeMillis();
		System.out.println(finished);
		long indexTime=finished-start;
		System.out.println(indexTime);

	}
	
	
	
}
