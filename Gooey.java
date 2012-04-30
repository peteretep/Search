import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Gooey implements ActionListener
{
	JButton searchButton;
	JTextField input;
	JFileChooser fileChooser;
	JButton fileButton;
	JTextArea results;
	static FileIndexer fileIndexer;
	
	public JPanel createContentPane() 
	{
		JPanel totalGUI=new JPanel();
		totalGUI.setLayout(null);
		
		input=new JTextField(30);
		input.setLocation(20,40);
		input.setSize(150,30);
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
		
		results=new JTextArea(5,30);
		results.setSize(300, 100);
		results.setLocation(20,150);
		results.setCaretPosition(results.getDocument().getLength());
		results.setEditable(true);
		totalGUI.add(results);
	//	JScrollPane resultsScrollPane=new JScrollPane(results);
	//	totalGUI.add(resultsScrollPane);
		
		
		
		return totalGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// On click search button
		//finds the key from the input text box
		// and gets the values associated with that key
		// adds them to a list to print out.
		if (e.getSource()==searchButton)
		{
			//creates a list of files with this word in it
			List<String> printlist = fileIndexer.index.get(input.getText());
		
		
			for (int i=0;i<printlist.size();i++)
			{
				
				results.append(printlist.get(i)+"\n");
				
			}
			
		}
		
		// file chooser
		if(e.getSource()==fileButton)
		{
			fileChooser=new JFileChooser();
			//enable multiple selections
			fileChooser.setMultiSelectionEnabled(true);
			fileChooser.showOpenDialog(searchButton);
			
			File[] files=fileChooser.getSelectedFiles();
			fileIndexer=new FileIndexer(files);
		}
		
		
	}
	
	public static void createAndShowGUI()
	{
		JFrame frame=new JFrame("Search");
		Gooey gui=new Gooey();
		frame.setContentPane(gui.createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350,300);
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
	
	public static void checkIfFileContainsWord(String word)
	{
		
	}
}