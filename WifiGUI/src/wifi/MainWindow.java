package wifi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField textSSID;
	private JTextField textPASS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textSSID = new JTextField();
		textSSID.setBounds(295, 43, 86, 20);
		frame.getContentPane().add(textSSID);
		textSSID.setColumns(10);
		
		textPASS = new JTextField();
		textPASS.setBounds(295, 84, 86, 20);
		frame.getContentPane().add(textPASS);
		textPASS.setColumns(10);
		
		JLabel lblSsid = new JLabel("SSID");
		lblSsid.setBounds(22, 46, 46, 14);
		frame.getContentPane().add(lblSsid);
		
		JLabel lblPassword = new JLabel("Password (Must be 8 Characters)");
		lblPassword.setBounds(22, 79, 170, 30);
		frame.getContentPane().add(lblPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				   try 
			        { 
			            String ssid = textSSID.getText();
						String pword = textPASS.getText();
						
						Process p=Runtime.getRuntime().exec("netsh wlan set hostednetwork mode=allow ssid="+ssid+" key="+pword+""); 
			            
			           
			           
			            
			            
			            p.waitFor(); 
			            BufferedReader reader=new BufferedReader(
			                new InputStreamReader(p.getInputStream())
			            ); 
			            String line; 
			            while((line = reader.readLine()) != null) 
			            { 
			                System.out.println(line);
			                
			                String check = "The SSID of the hosted network has been successfully changed. ";

			                if (line.equals(check)){
			                	
			                	
			                	
			                	 Process q=Runtime.getRuntime().exec("netsh wlan start hostednetwork"); 
			                     q.waitFor(); 
			                     BufferedReader reader1=new BufferedReader(
			                         new InputStreamReader(q.getInputStream()));
			                         
			                     
			                     	 String started = "The hosted network started.";
			                     	 String notStarted1 = "The group or resource is not in the correct state to perform the requested operation.";
			                     	 String notStarted = "The hosted network couldn't be started. ";
			                     	 
			                     	 
			                         String line1; 
			                         while((line1 = reader1.readLine()) != null){
			                        	 
			                        	 if(line1.equals(notStarted)){
			                        		 
			                        		 JOptionPane.showMessageDialog(null, "WiFi card not compatable");
			                        		 
			                        		 System.exit(0);
			                        	 }
			                        	 
			                        	 else {
			                        		 
			                        		 JOptionPane.showMessageDialog(null, "Started");
			                        	 }
			                        	 
			                         }
			                      
			                	
			                }
			                
			                
			            } 
			            
			            
			        }
			        catch(IOException e1) {} 
			        catch(InterruptedException e2) {} 
				
				
			}
		});
		btnSubmit.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(237, 227, 89, 23);
		frame.getContentPane().add(btnExit);
	}
}
