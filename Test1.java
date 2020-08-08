
import java.io.*;
import java.util.Scanner;
import java.sql.*;

public class Test1
{
	static Connection con=null;  
		static                                               //called when static method is called
        {
	   
	         try
	            {
	               Class.forName("com.mysql.jdbc.Driver"); //loading driver //this function returns an exception which is checked //so we require try catch
			        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bts","root","Rated!12");//1starg i hostname+dbname
	
	               if(con!=null)
			        {
				     System.out.println("Connection established");
			        }
		            else
			        {
				     System.out.println("Not established");
			        }
                }                                             
	         catch(Exception e)
                    {
		            System.out.println("Can't load driver");
	                }   		   
         }
	public static void main(String[] args) throws Exception
	{
		while(true)
		{
		try
		{
			Blankscreen.clearScreen();
			System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              -----------------------------Log In-----------------------------");
				System.out.println();
			
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader br= new BufferedReader(input);
		
		
			
		
		
	
			PreparedStatement pst=con.prepareStatement("Select Role,empName from Employee where empCode=? and empPassword=?");
			System.out.println("							Enter username( Your Employee Code is your username)");
		
			int userId=Integer.parseInt(br.readLine());
		
			System.out.println("									Enter password");
	    
			String password=new String(System.console().readPassword());
			pst.setInt(1,userId);
			pst.setString(2,password);
			ResultSet res=pst.executeQuery();
        
			if(res.next())
			{
			String role=res.getString(1);
			String name=res.getString(2);
		
				if(role.equals("manager"))
				{
				//Call Manager Module
				System.out.println("									Logged in Succesfuly as a Manager");
				Manager_Panel ob1=new Manager_Panel(userId,name,role);
				ob1.menu();
				}
		
				else if(role.equals("admin"))
				{
				//Call Admin Module
				System.out.println("									Logged in Succesfuly as Admin");
				Admin ob2=new Admin(userId,name,role);
				ob2.menu();
				}
				else if(role.equals("tester")||role.equals("developer"))
				{
				 
				 EmployeePanel1 ob3=new EmployeePanel1(userId,name,role);
				 
				 ob3.menu();
				}
	    
		    }
			else
			{
				System.out.println("									Invalid username or password");
			}
			System.out.println("				              Do you want to continue to the portal ? Press Y to continue and N to exit the systems");
			char ch=(char)br.read();
			if(ch=='N')
			{
				System.exit(0);
			}
		}
		
		catch(Exception e)
		{
			System.out.println("		             Please enter a valid input");
		}
	
	    }
	
	}
}
class Blankscreen
{
	public static void clearScreen()
	{
		try
		{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception e)
		{}
	}
}