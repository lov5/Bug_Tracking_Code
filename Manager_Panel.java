
import java.io.*;
import java.util.Scanner;
import java.sql.*;
public class Manager_Panel
{	
	
	static Connection con=null;
    static
	{
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");  //this function return exception driverNotFound i.e checked ecxeption //load driver
		//below line will establish connection to database
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bts","root","Rated!12");  //driver manager class havd method get connection method that returns object 1st arg host+database name
		if(con!=null)
		{
			System.out.println("connection established");
		}
		else
		{
			System.out.println("connection not established");
		}
		}
		catch(Exception ex)
		{
			System.out.println("cannot load the driver ");
		}
	}
	int id;
	String name;
	String role;
	
	Manager_Panel(int id,String name,String role)
	{
		this.id=id;
		this.name=name;
		this.role=role;
	}
	
	
	InputStreamReader input=new InputStreamReader(System.in);
	BufferedReader br=new BufferedReader(input);
	public void addProject() throws Exception
		{
			
			System.out.println("Enter Project id ");
			try
			{
			int projectid1=Integer.parseInt(br.readLine());
			
			PreparedStatement pst=con.prepareStatement("select * from project where projectid=?");
			pst.setInt(1,projectid1);
			ResultSet res=pst.executeQuery();
		   if(res.next())
		   {
			
		     System.out.println("data with this project id already present");
		    }
		   else
		   {
		    pst=con.prepareStatement("insert into Project values(?,?,?,?,?)");  //parameterised query    setInt(int pos ,int value) or setString(int pos,String value)
		    pst.setInt(1,projectid1);
		    
			System.out.println("enter Project name");
			String projectName=br.readLine();
			pst.setString(2,projectName);
			
			System.out.println("Enter SDate");
			String startDate=br.readLine();
			pst.setString(3,startDate);
			
			System.out.println("enter EDate");
			String endDate=br.readLine();
			pst.setString(4,endDate);
			
			System.out.println("Enter Project Description");
			String projectDes=br.readLine();
			pst.setString(5,projectDes);    
			
			int res1=pst.executeUpdate();
			if(res1>0)
			System.out.println("Record inserted....");
			else
			System.out.println("Error in inserting record");
			
		}
		}
			catch(Exception e)
			{
				System.out.println("you entered wrong input");
			}
		//con.close(); in finally block it will close connection
		}
	
		public void UpdateBug() throws Exception
		{
		System.out.println("Enter bugcode to be updated ");
		try
		{
		int bugcode1=Integer.parseInt(br.readLine());
		PreparedStatement pst=con.prepareStatement("select * from BugType where bugCode=?");
		pst.setInt(1,bugcode1);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
			
		pst=con.prepareStatement("update BugType set bugCatgory=?,bugSeverty=? where bugCode=?");  //parameterised query    setInt(int pos ,int value) or setString(int pos,String value)
		
		System.out.println("enter UPDATED bug category");
		pst.setString(1,br.readLine());
		
		System.out.println("Enter UPDATED bug severty");
		String s=br.readLine();
		pst.setString(2,s); 
		
		pst.setInt(3,bugcode1);
		int res1=pst.executeUpdate();
		
		if(res1>0)
			System.out.println("record successfully updated");
		else
			System.out.println("error in updating recoerd");
			
		}
		else
		{
			System.out.println("NO BUGTYPE PRESENT WITH THIS BUG CODE SO YOU CAN'T UPDATE IT");
		}
		
		}
			catch(Exception e)
			{
				System.out.println("you entered wrong input");
			}
		
		}
		public void View_All_Projects() throws Exception
		{
			PreparedStatement pst=con.prepareStatement("select * from Project");
			ResultSet res= pst.executeQuery();
			while(res.next())  
			{
				System.out.println(res.getInt(1)+"\t"+res.getString(2)+"\t"+res.getString(3)+"\t"+res.getString(4)+"\t"+res.getString(5));
			}	
		}
		public void Delete_Projects() throws Exception
		{
			try
			{
		PreparedStatement pst=con.prepareStatement("delete from Project where projectID=?");
			System.out.println("enter projectcode you want to delete ");
			pst.setInt(1,Integer.parseInt(br.readLine()));
			int res=pst.executeUpdate();
			if(res>0)
				System.out.println("record successfully deleted");
			else
				System.out.println("record to delete is not found");	
				}
			catch(Exception e)
			{
				System.out.println("you entered wrong input");
			}
		}
		
	
		public void UpdateProject() throws Exception
		{
		try
		{
		System.out.println("Enter project id to be updated ");
		int projectcode1=Integer.parseInt(br.readLine());
		PreparedStatement pst=con.prepareStatement("select * from Project where projectID=?");
		pst.setInt(1,projectcode1);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{

		pst=con.prepareStatement("update Project set projectName=?,SDate=?,EDate=?,projectDec=? where projectID=?");  
		System.out.println("enter UPDATED PROJECT NAME");
		pst.setString(1,br.readLine());
		System.out.println("Enter UPDATED SDATE");
		String s=br.readLine();
		pst.setString(2,s);
		System.out.println("ENTER UPDATED EDATE");
		pst.setString(3,br.readLine());
		System.out.println("Enter UPDATED PROJECT DESCRIPTION");
		pst.setString(4,br.readLine());
		pst.setInt(5,projectcode1);
		int res1=pst.executeUpdate();

		if(res1>0)
		System.out.println("record successfully updated");
		else
		System.out.println("error in updating recoerd");

		}
		else
		{
		System.out.println("NO Project with this projectcode exist SO YOU CAN'T UPDATE IT");

		}
			}
			catch(Exception e)
			{
				System.out.println("you entered wrong input");
			}
		}
		public void Add_New_Bug() throws Exception
		{
			try{
		System.out.println("Enter bug code ");
		int bugid1=Integer.parseInt(br.readLine());
		PreparedStatement pst=con.prepareStatement("select * from BugType where bugCode=?");
		pst.setInt(1,bugid1);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
			
		System.out.println("bug with this id already present");
		}
		else
		{
		pst=con.prepareStatement("insert into BugType values(?,?,?)");  //parameterised query    setInt(int pos ,int value) or setString(int pos,String value)
		pst.setInt(1,bugid1);
		System.out.println("enter bug category");
		pst.setString(2,br.readLine());
		System.out.println("Enter bug severty");
		pst.setString(3,br.readLine());  
		int res1=pst.executeUpdate();
		if(res1>0)
			System.out.println("record inserted....");
		else
			System.out.println("error in inserting recoerd");
			
		}
		}
			catch(Exception e)
			{
				System.out.println("you entered wrong input");
			}
		}
		public void View_All_Bugs() throws Exception
		{
		PreparedStatement pst=con.prepareStatement("select * from BugType");
			ResultSet res= pst.executeQuery();
			while(res.next())  
			{
				System.out.println(res.getInt(1)+"\t"+res.getString(2)+"\t"+res.getString(3));
			}	
		}
		
		public void DeleteBug() throws Exception
		{
			try{
		PreparedStatement pst=con.prepareStatement("delete from bugType where bugCode=?");
			System.out.println("enter bugcode you want to delete ");
			pst.setInt(1,Integer.parseInt(br.readLine()));
			int res=pst.executeUpdate();
			if(res>0)
				System.out.println("record successfully deleted");
			else
				System.out.println("record to delete is not found");	
			}
			catch(Exception e)
			{
				System.out.println("you entered wrong input");
			}
		}
		public void Update() throws Exception
		{
		/*System.out.println("Enter Employee id to be updated ");
		int empcode1=Integer.parseInt(br.readLine());*/
		PreparedStatement pst=con.prepareStatement("select * from Employee where empCode=?");
		pst.setInt(1,id);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
			
		pst=con.prepareStatement("update Employee set empName=?,empEmail=?,empPassword=?,gender=?,DOB=?,mobileNo=? where empCode=?");  //parameterised query    setInt(int pos ,int value) or setString(int pos,String value)
		System.out.println("enter UPDATED Name");
		pst.setString(1,br.readLine());
		System.out.println("Enter UPDATED Email");
		String s=br.readLine();
		pst.setString(2,s);
		System.out.println("Enter updated password");
		pst.setString(3,br.readLine());
		System.out.println("Enter updated Gender");
		pst.setString(4,br.readLine());
		System.out.println("Enter updated DOB");
		pst.setString(5,br.readLine());
		System.out.println("enter updated mobile number");
		pst.setLong(6,Long.parseLong(br.readLine()));
		//String s1="manager";
		//pst.setString(7,s1);
		pst.setInt(7,id);
		int res1=pst.executeUpdate();
		
		if(res1>0)
			System.out.println("record successfully updated");
		else
			System.out.println("error in updating record");
			
		}
		else
		{
			System.out.println("NO DATA WITH GIVEN ID EXIST SO YOU CANT UPDATE IT");
		}
		
		}
	public void AssignProject() throws Exception
	{
		try
		{
	    System.out.println(" Enter Project id ");
		int projectid1=Integer.parseInt(br.readLine());
		PreparedStatement pst=con.prepareStatement("select * from Project where projectID=?");
		pst.setInt(1,projectid1);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
		int res1=0;
		pst=con.prepareStatement("insert into AssignProject values(?,?)");  //parameterised query    setInt(int pos ,int value) or setString(int pos,String value)
		pst.setInt(1,projectid1);
		System.out.println("enter Emp Code you want to assign project");
		pst.setInt(2,Integer.parseInt(br.readLine()));  
		System.out.println(res1);		
		res1=pst.executeUpdate();
		System.out.println(res1);
		if(res1>0)
			System.out.println("record inserted....");
		else
			System.out.println("error in inserting record");	
		
		}
		else
		{
		System.out.println("data with this projec is not present");	
			
		}	
		}
			catch(Exception e)
			{
				System.out.println("you entered wrong input");
			}
	}	
		
		

public void menu()
{
Scanner sc=new Scanner(System.in);
System.out.println("--------Welcome "+name+"---------");
while(true)
{
	int ch1=0,ch2=0,ch3=0;
try{
System.out.println("1.Enter 1 to Update Profile");
System.out.println("2.Enter 2 to Manage Project");
System.out.println("3.Enter 3 to view Bug related Information");
System.out.println("4.Enter 4 to Assign project");
System.out.println("5.Enter 5 to logout");
System.out.println("Enter choice");
ch1 =sc.nextInt();
}
catch(Exception e)
{
	System.out.println("you entered wrong choice");
}
switch (ch1)
{
	case 1:
		try{
			
		Update();
		}
		catch(Exception e)
		{
		}
		break;
	case 2:
		
		
		do
		{
			try{
		System.out.println("Enter your choice");
		System.out.println("1. Add project");
		System.out.println("2. view all project");
		System.out.println("3. delete project");
		System.out.println("4. update project");
		System.out.println("5. Back");
		ch2=sc.nextInt();
			}
			catch(Exception e)
			{
			System.out.println("you entered wrong choice");	
			}
		switch (ch2)
		{
			case 1:
			    try{
				addProject();
				}
				catch(Exception e)
				{
				}
				break;
			case 2:
				try{
					View_All_Projects();
				}
				catch(Exception e)
				{
				}
				break;
			case 3:
			
				try{
					Delete_Projects();
				}
				catch(Exception e)
				{}
				break;
			case 4:
				try{
					UpdateProject();
				}
				catch(Exception e)
				{}
				break;
			case 5:
				ch2=5;
				break;
			default:
				System.out.println("you entered wrong choice");
			    break;
			
			
		}
		}while(ch2!=5);
		
		break;
	case 3:
	
	do
	{
		try{
		System.out.println("Enter your choice");
		System.out.println("1. Add new Bug");
		System.out.println("2. view all Bug's");
		System.out.println("3. update Bug");
		System.out.println("4. Delete Bug");
		System.out.println("5. Back");
		ch3=sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("you entered wrong choice");
		}
		switch (ch3)
		{
			case 1:
			    try{
				Add_New_Bug();
				}
				catch(Exception e)
				{
				}
				break;
			case 2:
				try{
					View_All_Bugs();
				}
				catch(Exception e)
				{
				}
				break;
			case 3:
			
				try{
					UpdateBug();
				}
				catch(Exception e)
				{}
				break;
			case 4:
				try
				{
					DeleteBug();
				}
				catch(Exception e)
				{}
				break;
			case 5:
				ch3=5;
				break;
			default:
				System.out.println("you entered wrong choice");
			
			
			
	}
	}while(ch3!=5);	
		
	break;	
	case 4:
		
		System.out.println("You selected to Assign Project");
		try{
			
			AssignProject();
			
		}
		catch(Exception e)
		{
		}
		break;
	
	case 5:
		System.out.println("Successfully Loged out");
		return;
	default:
		System.out.println("You entered wrong choice");
		break;	
}
}
/*
System.out.println("Press 0 to logout and 1 to continue");

int i=sc.nextInt();
if(i==0)
{
	System.out.println("Logged out successfully.   Redirecting to Home Page....");
	return;
}
*/
}
}

////////////////////////////////////////////////////////////////////////

	/*public void updateBugStatus()
	{}
	public void viewBug()
	{}
	public void bugDetails()
    {}
	public void exit()
	{}*/







/*class Main
{
	static Connection con=null;  
		static                                               //called when static method is called
        {
	   
	         try
	            {
	               //Class.forName("com.mysql.jdbc.Driver"); //loading driver //this function returns an exception which is checked //so we require try catch
			        con=DriverManager.getConnection("jdbc:mysql://localhost:3308/BTS","root","");//1starg i hostname+dbname
	
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
			System.out.println("Welcome To Bug Tracking System");
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader br= new BufferedReader(input);
		
		
			System.out.println("-----LOGIN------");
		
		
	
			PreparedStatement pst=con.prepareStatement("Select Role,empName from Employee where empCode=? and empPassword=?");
			System.out.println("Enter username( Your Employee Code is your username)");
		
			int userId=Integer.parseInt(br.readLine());
		
			System.out.println("Enter password");
	    
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
				/Call Manager Module
				System.out.println("Logged in Succesfuly as a Manager");
				Manager_Panel ob1=new Manager_Panel(userId,name,role);
				ob1.menu();
				}
		
				else if(role.equals("Admin"))
				{
				//Call Admin Module
				System.out.println("SuccesfulAdmin");
				Admin ob2=new Admin(userId,name,role);
				}
				else if(role.equals("tester")||role.equals("developer"))
				{
				//Employee ob3=new Employee_Panel(userId,name,role);
				}
	    
		    }
			else
			{
				System.out.println("Invalid username or password");
			}
			System.out.println("Do you want to continue to the portal ? Press Y to continue and N to exit the systems");
			char ch=(char)br.read();
			if(ch=='N')
			{
				System.exit(0);
			}
		}
		
		catch(Exception e)
		{
			System.out.println("Please enter a valid input");
		}
	
	    }
	
	}
}*/


	
	