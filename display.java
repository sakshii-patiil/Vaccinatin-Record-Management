import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class display {
	
	private JLabel label_aadhar,label_title,label_name,label_gender,label_age,label_city,label_vaccine_name,label_dose;
	private JTextField aadhar,name,city,age,dose,vaccine_name,gender;
	JButton update,back;
	
	long aadhar_no;
	int vage,vdose;
	String vcity,vname,vvaccine_name;
	
	long aadhar_nov;
	int agev,dosev;
	String cityv,namev,vaccine_namev;
	
	
	//creating variables for mysql setup
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/vaccine_records_db";
	public static final String username = "root";
	public static final String password = "sakshipatil_30";
			
	static Connection con = null;
	
	display(long aadhar_no,String vname,int vage,String vcity,String vvaccine_name,int vdose)
	
	{
		JFrame f=new JFrame();//creating instance of JFrame  
		
		label_title = new JLabel("Display Details");
		label_title.setFont(new Font("Times New Toman", Font.BOLD+ Font.ITALIC, 30));
		label_title.setSize(300, 30);
		label_title.setLocation(300, 30);
		f.add(label_title);
		
		label_aadhar = new JLabel("Aadhar no");
		 label_aadhar.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_aadhar.setSize(100, 20);
		 label_aadhar.setLocation(100, 100);
		 f.add(label_aadhar);

		 aadhar = new JTextField();
		 aadhar.setFont(new Font("Arial", Font.PLAIN, 15));
		 aadhar.setSize(190, 20);
		 aadhar.setLocation(200, 100);
		 aadhar.setText(String.valueOf(aadhar_no));
		 f.add(aadhar);

		 label_name = new JLabel("Name");
		 label_name.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_name.setSize(190, 20);
		 label_name.setLocation(100, 160);
		 f.add(label_name);

		 name = new JTextField();
		 name.setFont(new Font("Arial", Font.PLAIN, 15));
		 name.setSize(190, 20);
		 name.setLocation(200, 160);
		 name.setText(vname);
		 f.add(name);

		 
		 label_age = new JLabel("Age");
		 label_age.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_age.setSize(190, 20);
		 label_age.setLocation(100, 220);
		 f.add(label_age);
			  
		 age = new JTextField();
		 age.setFont(new Font("Arial", Font.PLAIN, 15));
		 age.setSize(50, 20);
		 age.setLocation(200, 220);
		 age.setText(String.valueOf(vage));
		 f.add(age);
		 
		 label_vaccine_name = new JLabel("Vaccine Name");
		 label_vaccine_name.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_vaccine_name.setSize(190, 20);
		 label_vaccine_name.setLocation(500, 100);
		 f.add(label_vaccine_name);

			  
		 vaccine_name = new JTextField();
		 vaccine_name.setFont(new Font("Arial", Font.PLAIN, 15));
		 vaccine_name.setSize(150, 20);
		 vaccine_name.setLocation(650, 100);
		 vaccine_name.setText(vvaccine_name);
		 f.add(vaccine_name);
		 
		 label_city = new JLabel("City");
		 label_city.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_city.setSize(190, 20);
		 label_city.setLocation(500, 160);
		 f.add(label_city);

			  
		 city = new JTextField();
		 city.setFont(new Font("Arial", Font.PLAIN, 15));
		 city.setSize(150, 20);
		 city.setLocation(650, 160);
		 city.setText(vcity);
		 f.add(city);
		 
		 label_dose = new JLabel("Dose completed");
		 label_dose.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_dose.setSize(190, 20);
		 label_dose.setLocation(500, 220);
		 f.add(label_dose);
		 
		 dose = new JTextField();
		 dose.setFont(new Font("Arial", Font.PLAIN, 15));
		 dose.setSize(80, 20);
		 dose.setLocation(650, 220);
		 dose.setText(String.valueOf(vdose));
		 f.add(dose);
		 

		 update = new JButton("update");
		 update.setFont(new Font("Arial", Font.PLAIN, 15));
		 update.setBounds(450, 450,150, 40);
		 //reset.addActionListener(this);
		 f.add(update);
       
		back=new JButton("back");

		back.setBounds(10,10,80, 18);//x axis, y axis, width, height  
		          
		f.add(back);//adding button in JFrame  
		
		          
		f.setSize(1000,700);//10000 width and 700 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible 
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				f.dispose();
				new start();
				}
			});
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				
				aadhar_nov = Long.parseLong(aadhar.getText());
				namev = name.getText();
				agev = Integer.parseInt(age.getText());
				cityv = city.getText();
				vaccine_namev = vaccine_name.getText();
				dosev = Integer.parseInt(dose.getText());
				
				try {
					Class.forName(driver);
					
					con = DriverManager.getConnection(url,username,password);
					
					
					CallableStatement store_data = con.prepareCall("{call update_records_procedure(?,?,?,?,?,?)}");
					
					store_data.setLong(1, aadhar_nov);
					store_data.setString(2, namev);
					store_data.setInt(3, agev);
					store_data.setString(4, cityv);
					store_data.setString(5, vaccine_namev);
					store_data.setInt(6, dosev);
					
					store_data.execute();
					
					System.out.print("\n***Successfull***\n");
					
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				f.dispose();
				new start();
				}
			});
	}
}
