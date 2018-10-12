
import logthingy.*;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class UI{
	private JFrame frame;	
	private JFileChooser dialog;
	private JButton btn;
	private JPanel panel;
	private JTextArea console;
	private int height;
	private int width;
	private String logs;
	private String[][] log_split;

	public UI(int width, int height){
		frame = new JFrame("El mediador de la chica");
		this.height = height;
		this.width = width;
		PrepareGUI();	
	}

	private void PrepareGUI(){
		PrepareFileChooser();
		panel = new JPanel(new GridLayout(2, 1));
		panel.add(btn);
		frame.add(panel);
	}	
	
	public void ShowWindow(){
		frame.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent windowEvent ) {
				frame.dispose();
			}
	});
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	private void PrepareFileChooser(){
		dialog = new JFileChooser();
		btn = new JButton("Open");
		btn.addActionListener(new CustomListener());
	}

	private class CustomListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			int retValue = dialog.showOpenDialog(frame);

			if(retValue == JFileChooser.APPROVE_OPTION){		
				System.out.println("You selected: " + dialog.getSelectedFile().getAbsolutePath());
				
				logs = FileReader.ReadLogs(dialog.getSelectedFile().getAbsolutePath());

				System.out.println(logs);	
				

				if(logs != null){
					System.out.println("Proceeding to Parse");	
					log_split = MainParser.Parse_Lines(logs);					
					//System.out.println(log_split);
				}

				CustomWriter.CreateCSV(log_split);
				
			} 
		}
	}

}
