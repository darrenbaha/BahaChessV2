import java.awt.Color;
import java.awt.Font;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ui {
	
		int x,y;
		String title, log;
	
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		static JTextArea console = new JTextArea(5,20);
		JScrollPane scroll_area = new JScrollPane(console);
		
		imageHandler images = new imageHandler();
		
		public ui(int x, int y, String title) {
			this.x = x;
			this.y = y;
			
			this.title = title; 
			
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setSize(x,y);
			frame.setTitle(title);
			frame.setLayout(null);
			frame.getContentPane().setBackground(new Color(218,214,183));
			
			panel.setLayout(null);
			panel.setSize(x, y);
			panel.setOpaque(false);
			panel.setLocation(0, 0);
			
			console.setFont(new Font("Arial Black", Font.BOLD, 20));
			console.setBounds(600,40, 500, 300);
			console.setEditable(false);
			
			scroll_area.setBounds(600,40,500,300);
			scroll_area.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panel.add(scroll_area);
			
			frame.add(panel);
			frame.setResizable(false);
				
		}
		
		public void showWindow() {
			scroll_area.setVisible(true);
			panel.setVisible(true);
			frame.setVisible(true);
			console.setVisible(true);
		}

}
