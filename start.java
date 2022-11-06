import java.awt.Font;
import java.awt.Frame;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;  

public class start
{
	private JLabel label_title;
	
	//creating variables for mysql setup
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/vaccine_records_db";
	public static final String username = "root";
	public static final String password = "sakshipatil_30";
	
	static Connection con = null;
	static Statement get_data = null;  
	
	start()
	{
		Frame f=new JFrame();//creating instance of JFrame  
		
		//instantiating all the components to be added on to the frame
		
		label_title = new JLabel("Vaccination Records");
		label_title.setFont(new Font("Arial", Font.PLAIN, 30));
		label_title.setSize(300, 30);
		label_title.setLocation(100, 30);
		f.add(label_title);
        
		JButton register=new JButton("Register");
		JButton update=new JButton("Update");
		JButton delete=new JButton("Delete");
		JButton show_records=new JButton("View records");

		register.setBounds(130,100,180, 40);//x axis, y axis, width, height  
		update.setBounds(130,170,180, 40);
		delete.setBounds(130,240,180, 40);
		show_records.setBounds(130,310,180, 40);
          
		//adding button in JFrame  
		f.add(register);
		f.add(update);
		f.add(delete);
		f.add(show_records);
          
		f.setSize(500,500);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				f.dispose();
				new register();
				}
			});
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				f.dispose();
				new update();
				}
			});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				f.dispose();
				new delete();
				}
			});
		show_records.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				get_records();
			}
			});
		
	}
	public static void main(String[] args) 
	{  
		new start();
	}
	static	public void get_records()
	{
		
		try {
		
			//registering driver
			Class.forName(driver);
				
			//opening the connection
			con = DriverManager.getConnection(url,username,password);
			
			System.out.println("Aadhar no\t\tName\t\t\tAge\t\tCity\t\tVaccine name\t\t\tDose");
			
			get_data = con.createStatement();
			
			ResultSet records = get_data.executeQuery("select * from vaccine_records");
			
			//displaying records
			while(records.next())
			{
				System.out.println("\n"+records.getInt(1)+"\t\t"+records.getString(2)+"\t\t"+records.getInt(3)+"\t\t"+records.getString(4)+"\t\t"+records.getString(5)+"\t\t\t"+records.getInt(6));
			}
			
		}catch(Exception ee) {}
		
	}

}  
