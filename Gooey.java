import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Gooey implements ActionListener,  FocusListener
{
	JButton searchButton;
	JTextField input;
	JFileChooser fileChooser;
	JButton fileButton;
	JButton clearButton;
	JTextArea results;
	JLabel resultsLabel;
	static FileIndexer fileIndexer;
	
	public JPanel createContentPane() 
	{
		JPanel totalGUI=new JPanel();
		totalGUI.setLayout(null);
		
		input=new JTextField("Insert Search Terms",30);
		input.setLocation(20,40);
		input.setSize(150,30);
		input.addFocusListener(this);
		totalGUI.add(input);
		
		searchButton=new JButton("Search");
		searchButton.setSize(100,30);
		searchButton.setLocation(200, 40);
		searchButton.addActionListener(this);
		totalGUI.add(searchButton);
		
		fileButton=new JButton("Pick Files");
		fileButton.setSize(100,30);
		fileButton.setLocation(200,100);
		fileButton.addActionListener(this);
		totalGUI.add(fileButton);
		
		clearButton=new JButton("Clear");
		clearButton.setSize(100,30);
		clearButton.setLocation(20,100);
		clearButton.addActionListener(this);
		totalGUI.add(clearButton);
		
		resultsLabel=new JLabel("Files Containing Search Terms");
		resultsLabel.setSize(300, 10);
		resultsLabel.setLocation(20, 135);
		totalGUI.add(resultsLabel);
		
		results=new JTextArea(5,30);
		results.setWrapStyleWord(true);
		results.setCaretPosition(results.getDocument().getLength());
		results.setEditable(true);
		//totalGUI.add(results);
		JScrollPane resultsScrollPane=new JScrollPane(results);
		resultsScrollPane.setSize(350, 100);
		resultsScrollPane.setLocation(20,150);
		totalGUI.add(resultsScrollPane);
		
		
		
		return totalGUI;
	}
	 public void focusGained(FocusEvent e) 
	 {
		 
			if (e.getSource()==input)
	        {
				
	        	//input.setText("");
	        }
	    }

	    public void focusLost(FocusEvent e) 
	    {
	    }
	    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getActionCommand());
		// On click search button
		//finds the key from the input text box
		// and gets the values associated with that key
		// adds them to a list to print out.
		if (e.getSource()==searchButton)
		{
			long start = System.currentTimeMillis();
			//Clears the results area
			results.setText("");
			
			// Creates a list to hold the results of the search - files 
			// containing the search terms.
			List<String> resultList=new ArrayList<String>();
			
			Scanner sc=new Scanner(input.getText());
			
			// Iterates over the searchTerms
			while (sc.hasNext())
			{
				String searchTerm=sc.next();
				
				//print the list of files that contain the searchterm to the console
				System.out.println(fileIndexer.index.get(searchTerm));
				
				//add each of the files containing the search term to the results list
				resultList.addAll(fileIndexer.index.get(searchTerm));
			}
			
			Map<String, Integer> unsortedMap=new HashMap<String, Integer>();
			for (String fl : resultList)
			{
				int wordsInFile = Collections.frequency(resultList, fl);
				unsortedMap.put(fl, wordsInFile);
			} 
			
			//Sort Results
			Map<String, Integer> sortedMap= sortByComparator(unsortedMap);
			
			
		    Iterator<String> iterator = sortedMap.keySet().iterator();  
		       
		    while (iterator.hasNext()) {  
		       String key = iterator.next().toString();  
		       String value = sortedMap.get(key).toString();  
		       
		       System.out.println(key + " " + value);
		       results.append(key+"\n");
		    } 
		    long finish=System.currentTimeMillis();
		    long searchTime=finish-start;
		    System.out.println("Search took " + searchTime + "ms");
		}
		
		// file chooser
		if(e.getSource()==fileButton)
		{
			fileChooser=new JFileChooser("/home/peter/workspace/Search/dictionary/");
			//enable multiple selections
			fileChooser.setMultiSelectionEnabled(true);
			fileChooser.showOpenDialog(searchButton);
			
			File[] files=fileChooser.getSelectedFiles();
			fileIndexer=new FileIndexer(files);
			
		}
		// clear the results area
		if(e.getSource()==clearButton)
		{
			results.setText("");
		}
		
	}
	
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortedMap)
	{
		long start = System.nanoTime();
		// This code was adapted from www.mkyong.com/java/how-to-sort-a-map-in-java/
		
		List list = new LinkedList(unsortedMap.entrySet());
		
		Collections.sort(list, new Comparator()
		{
			public int compare (Object o1, Object o2)
			{
				return ((Comparable)((Map.Entry)(o2)).getValue()).compareTo(((Map.Entry)(o1)).getValue());
			}

			
		});
		
		//put list back into a map
		Map sortedMap=new LinkedHashMap();
		for (Iterator it=list.iterator(); it.hasNext();)
		{
			Map.Entry<String, Integer> entry=(Map.Entry<String, Integer>)it.next();
			sortedMap.put(entry.getKey(),entry.getValue());
		}
		long finish= System.nanoTime();
		long sortTime=finish-start;
		System.out.println("Sorting took " + sortTime+ "ns");
		return sortedMap;
		
	}
	
	public static void createAndShowGUI()
	{
		JFrame frame=new JFrame("Search");
		Gooey gui=new Gooey();
		frame.setContentPane(gui.createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args)
	{ 
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	
}