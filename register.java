import java.awt.Container;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;   

public class register{ 
	
	private JLabel label_aadhar,label_title,label_name,label_gender,label_age,label_city,label_vaccine_name,label_dose;
	private JTextField aadhar,name,city;
	private JComboBox age,vaccine_name;
	private JCheckBox dose1,dose2;
	private JButton sub,reset,back;
	
	String age_values[] = new String[82];
	String[] vaccine_name_values = {"Covaxin","Covisheild"};
	
	String name_value,city_value,vaccine_name_value,gender_value;
	int age_value,dose_value;
	long aadhar_value;
	
	//creating variables for mysql setup
		public static final String driver = "com.mysql.cj.jdbc.Driver";
		public static final String url = "jdbc:mysql://localhost:3306/vaccine_records_db";
		public static final String username = "root";
		public static final String password = "sakshipatil_30";
		
		static Connection con = null;
		static Statement get_data = null,create_table=null;
		static CallableStatement store_data;
	
	register()
	{    
		
		JFrame f=new JFrame();//creating instance of JFrame  


		 label_title = new JLabel("Registration Form");
		 label_title.setFont(new Font("Arial", Font.PLAIN, 30));
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
		 f.add(name);
		 
		 label_age = new JLabel("Age");
		 label_age.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_age.setSize(190, 20);
		 label_age.setLocation(100, 220);
		 f.add(label_age);

		 for(int i=0;i<82;i++){
			 age_values[i]=Integer.toString(i+18);
		};
			  
		 age = new JComboBox(age_values);
		 age.setFont(new Font("Arial", Font.PLAIN, 15));
		 age.setSize(50, 20);
		 age.setLocation(200, 220);
		 f.add(age);
		 
		 label_vaccine_name = new JLabel("Vaccine Name");
		 label_vaccine_name.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_vaccine_name.setSize(190, 20);
		 label_vaccine_name.setLocation(500, 100);
		 f.add(label_vaccine_name);

			  
		 vaccine_name = new JComboBox(vaccine_name_values);
		 vaccine_name.setFont(new Font("Arial", Font.PLAIN, 15));
		 vaccine_name.setSize(150, 20);
		 vaccine_name.setLocation(650, 100);
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
		 f.add(city);
		 
		 label_dose = new JLabel("Dose completed");
		 label_dose.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_dose.setSize(190, 20);
		 label_dose.setLocation(500, 220);
		 f.add(label_dose);
		 
		 dose1 = new JCheckBox("Dose 1");
		 dose1.setFont(new Font("Arial", Font.PLAIN, 15));
		 dose1.setSize(250, 20);
		 dose1.setLocation(650, 220);
		 f.add(dose1);
		 
		 dose2 = new JCheckBox("Dose 2");
		 dose2.setFont(new Font("Arial", Font.PLAIN, 15));
		 dose2.setSize(250, 20);
		 dose2.setLocation(650, 250);
		 f.add(dose2);
		 
		 sub = new JButton("Register");
		 sub.setFont(new Font("Arial", Font.PLAIN, 15));
		 sub.setBounds(270, 450,150,40);
		 //sub.addActionListener(this);
		 f.add(sub);

		 reset = new JButton("Reset");
		 reset.setFont(new Font("Arial", Font.PLAIN, 15));
		 reset.setBounds(450, 450,150, 40);
		 //reset.addActionListener(this);
		 f.add(reset);
        
		back=new JButton("back");
		back.setBounds(10,10,80, 18);//x axis, y axis, width, height            
		f.add(back);//adding button in JFrame  
		
		          
		f.setSize(1000,700);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible 
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				f.dispose();
				new start();
				}
			});
		
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					aadhar_value = Long.parseLong(aadhar.getText());
					name_value = name.getText();
					
					age_value = Integer.parseInt((String) age.getSelectedItem());
					vaccine_name_value = (String) vaccine_name.getSelectedItem();
					
					city_value = city.getText();
					if(dose1.isSelected()&&dose2.isSelected())
					{
						dose_value = 2;
					}else if(dose1.isSelected())
					{
						dose_value = 1;
					}else
					{
						dose_value=0;
					}
					try {
						store_to_database();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					f.dispose();
					new start();
				}
			});
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aadhar.setText("");
				city.setText("");
				name.setText("");
				 vaccine_name.setSelectedIndex(0);
				 dose1.setSelected(false);
				 dose2.setSelected(false);
				 age.setSelectedIndex(0);
				}
			});
		
	}
		public void store_to_database() throws ClassNotFoundException {
			try {
				
				Class.forName(driver);
				
				con = DriverManager.getConnection(url,username,password);
				
				store_data = con.prepareCall("{call store_records_procedure(?,?,?,?,?,?)}");
				
				store_data.setLong(1, aadhar_value);
				store_data.setString(2, name_value);
				store_data.setInt(3, age_value);
				store_data.setString(4, city_value);
				store_data.setString(5, vaccine_name_value);
				store_data.setInt(6, dose_value);
				
				store_data.execute();
				
				System.out.print("\n***Successful***\n");
				
		
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
