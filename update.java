import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class update {
	
	private JLabel label_aadhar,res;
	private JTextField aadhar;
	private JButton update,back;
	
	long aadhar_no;
	int age,dose;
	String city,name,vaccine_name;
	
	
	//creating variables for mysql setup
			public static final String driver = "com.mysql.cj.jdbc.Driver";
			public static final String url = "jdbc:mysql://localhost:3306/vaccine_records_db";
			public static final String username = "root";
			public static final String password = "sakshipatil_30";
			
			static Connection con = null;
	
	update()
	{
		Frame f=new JFrame();
		
		 label_aadhar = new JLabel("Aadhar no");
		 label_aadhar.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_aadhar.setSize(100, 20);
		 label_aadhar.setLocation(50, 100);
		 f.add(label_aadhar);

		 aadhar = new JTextField();
		 aadhar.setFont(new Font("Arial", Font.PLAIN, 15));
		 aadhar.setSize(190, 20);
		 aadhar.setLocation(150, 150);
		 f.add(aadhar);
		 
		 update = new JButton("Update");
		 update.setBounds(180,200,80, 40);
		 f.add(update);
		 
		 back=new JButton("back");
		 back.setBounds(10,10,80, 18);//x axis, y axis, width, height  
		 f.add(back);//adding button in JFrame  
			
		 res = new JLabel();
		 label_aadhar.setFont(new Font("Arial", Font.PLAIN, 20));
		 label_aadhar.setSize(100, 20);
		 label_aadhar.setLocation(200, 100);
		 f.add(res);
			          
		f.setSize(400,500);//400 width and 500 height  
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
				
				aadhar_no = Long.parseLong(aadhar.getText());
				
				try {
					Class.forName(driver);
					
					con = DriverManager.getConnection(url,username,password);
					
					PreparedStatement pstmt = con.prepareStatement("select * from vaccine_records where aadhar = ?");
					pstmt.setDouble(1, aadhar_no);
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next())
					{
						if(rs.getString(1)==null)
						{
							System.out.println("No record found");
							
						}else {
							
							CallableStatement get_records = con.prepareCall("{call get_record_procedure(?,?,?,?,?,?)}");
							get_records.setDouble(1, aadhar_no);
							get_records.registerOutParameter(2,java.sql.Types.VARCHAR);
							get_records.registerOutParameter(3,java.sql.Types.INTEGER);
							get_records.registerOutParameter(4,java.sql.Types.VARCHAR);
							get_records.registerOutParameter(5,java.sql.Types.VARCHAR);
							get_records.registerOutParameter(6,java.sql.Types.INTEGER);
							
							get_records.executeUpdate();
							
								name  = get_records.getString(2);
								age = get_records.getInt(3);
								city = get_records.getString(4);
								vaccine_name = get_records.getString(5);
								dose = get_records.getInt(6);
								
								f.dispose();
								new display(aadhar_no ,name,age,city, vaccine_name, dose);
						}
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			});
	}
	

}
