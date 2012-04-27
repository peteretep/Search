import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Gooey implements ActionListener
{
	JButton search;
	JTextField input;
	JFileChooser chooser;
	JButton fileButton;
	static FileIndexer fileIndexer;
	
	public JPanel createContentPane() 
	{
		JPanel totalGUI=new JPanel();
		totalGUI.setLayout(null);
		
		input=new JTextField(30);
		input.setLocation(20,40);
		input.setSize(150,30);
		totalGUI.add(input);
		
		search=new JButton("Search");
		search.setSize(100,30);
		search.setLocation(200, 40);
		search.addActionListener(this);
		totalGUI.add(search);
		
		fileButton=new JButton("Pick Files");
		fileButton.setSize(100,30);
		fileButton.setLocation(200,100);
		fileButton.addActionListener(this);
		totalGUI.add(fileButton);
		
		return totalGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		if (e.getSource()==search)
		{
			List<String> printlist = fileIndexer.index.get(input.getText());
			
		
			for (int i=0;i<printlist.size();i++)
			{
				System.out.println(printlist.get(i));
			}
			
		}
	}
	
	public static void createAndShowGUI()
	{
		JFrame frame=new JFrame("Search");
		Gooey gui=new Gooey();
		frame.setContentPane(gui.createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350,150);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
		
		fileIndexer=new FileIndexer();
		
	}
}