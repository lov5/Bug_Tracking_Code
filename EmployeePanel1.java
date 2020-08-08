
import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class EmployeePanel1
{
	
	
	static Connection con=null;
	static
	{
		//Class.forname("driver name");
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bts","root","Rated!12");
		
			if(con!=null)
			{
				System.out.print("							  Connection Established : ");
			}
			else
			{
				System.out.print("							  Connection Not Established : ");
				
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
	
		}

	}
	
	
	
	int id;
	String name;
	String role;
	EmployeePanel1(int id,String name,String role)
	{
	   this.id=id;
       this.name=name;
       this.role=role;	   
	}
	InputStreamReader Input=new InputStreamReader(System.in);
	BufferedReader br=new BufferedReader(Input);
	public void menu()
	{
	if(role.equals("tester"))
	{
		System.out.println("Logged in Succesfuly as a Tester");
		System.out.println("------Welcome "+name+"-------");
		
		while(true)
		{
			
		System.out.println("What will you want to do?");
		System.out.println("1----> Update Profile ");
		System.out.println("2----> Add Bug Report ");
		System.out.println("3----> Update Bug Status");
		System.out.println("4----> View Bugs");
		System.out.println("5----> Bug Details");
		System.out.println("6----> Exit");
		Scanner sc=new Scanner(System.in);
		int i=sc.nextInt();
		switch(i)
		{
			case 1: try
			        { 
			        updateProfile();
					}
					catch(Exception e){System.out.println(e);}
			        break;
			case 2: try
		 	        {
			        addBugReport();
					}
					catch(Exception e){System.out.println(e);}
					break;
			case 3: try
			        {
						updateBugStatus();
					}
					catch(Exception e){System.out.println(e);}
					break;
			case 4: try
			        {
						viewBug();
					}
                    catch(Exception e){System.out.println(e);}
					break;
			case 5:try
			       {
                       bugDetails();
				   }
                    catch(Exception e){System.out.println(e);}
					break;
            case 6: {System.out.println("Logged out successfully.....Redirecting to home page");}
			        return;					
			        
			default : System.out.print("							Invalid Input : ");
		}
		
		
		}
		
	}
        		
	
	if(role.equals("developer"))
	{
		System.out.println("Logged in Succesfuly as a DEVELOPER");
		System.out.println("------Welcome"+name+"-------");
		
		while(true)
		{
			
		System.out.println("What will you want to do?");
		System.out.println("1----> Update Profile ");
		System.out.println("2----> Update Bug Status");
		System.out.println("3----> View Bugs");
		System.out.println("4----> Bug Details");
		System.out.println("5----> Logout");
		Scanner sc=new Scanner(System.in);
		int i=sc.nextInt();
		switch(i)
		{
			case 1: try
			        { 
			        updateProfile();
					}
					catch(Exception e){
						System.out.println(e);
					}
			        break;
			case 2: try
		 	        {
			        updateBugStatus();
					}
					catch(Exception e){System.out.println(e);}
					break;
			case 3: try
			        {
						viewBug();
					}
					catch(Exception e){System.out.println(e);}
					break;
			case 4: try
			        {
						bugDetails();
					}
                    catch(Exception e){System.out.println(e);}					
			        break;
			case 5: return;
			default : System.out.print("							Invalid Input : ");
		}
		
		
		}
	}
	
	}
	public void updateProfile()throws Exception
	{
		PreparedStatement pst=con.prepareStatement("select * from Employee where empCode=?");
		pst.setInt(1,id);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
			pst=con.prepareStatement("update Employee set empName=?,empEmail=?,gender=?,DOB=?,mobileNo=? where empCode=?");
			System.out.print("							  Enter Employee Name : ");
			String newName=br.readLine();
			pst.setString(1,newName);
			System.out.print("							  Enter Employee Email : ");
			String newEmail=br.readLine();
			pst.setString(2,newEmail);
			
			System.out.print("							  Enter New Password : ");
			String newPassword=new String(System.console().readPassword());
			System.out.print("							  Confirm Password : ");
			String newPassword1=new String(System.console().readPassword());
			
			
			System.out.print("							  Enter Current Password : ");
			String oldPassword=new String(System.console().readPassword());
			PreparedStatement lov =con.prepareStatement("Select * from employee where empPassword=? and empCode=?");
		    lov.setString(1,oldPassword);
			lov.setInt(2,id);
			ResultSet pass= lov.executeQuery();
				
				
			if(pass.next())
				{
			      if(newPassword.equals(newPassword1))
			      {
				   pst.setString(3,newPassword);
				  }
			    }
			
			else{
				System.out.print("							    Enter Password Again : ");
				return;
			   }
				
			
			System.out.print("							        Gender To Be Updated: ");
			String newGender=br.readLine();
			pst.setString(3,newGender);
			System.out.print("							        Enter Date Of Birth To Be Upated: ");
			String newDob=br.readLine();
			pst.setString(4,newDob);
			System.out.print("							        Enter Mobile Number To Be Updated  : ");
			long newNumber=Long.parseLong(br.readLine());
			pst.setLong(5,newNumber);
			pst.setInt(6,id);
			
			int j=pst.executeUpdate();
			
			
			
			if(j>0)
			{
				System.out.print("							   Updated Sucessfully : ");
			}
			else
			{
				System.out.print("							   Error: ");
			}
		}
		else
		{
			System.out.println("//");
		}
			
		
			
	}
	public void addBugReport()throws Exception
	{
		PreparedStatement pst=con.prepareStatement("insert into BugReport  values(?,?,?,?,?,?,?)");
		System.out.print("							Enter BugNumber : ");
		int bugn=Integer.parseInt(br.readLine());
		pst.setInt(1,bugn);
		
		System.out.print("							Bug Code : ");
		int bugc=Integer.parseInt(br.readLine());
		pst.setInt(2,bugc);
		
		System.out.print("							Enter Project : ");
		int project=Integer.parseInt(br.readLine());
		pst.setInt(3,project);
		
		
		pst.setInt(4,id);
		
		System.out.print("							Enter Ecode : ");
		PreparedStatement pst1=con.prepareStatement("select * from Employee where empCode=? and role='developer'");
		int ecode=Integer.parseInt(br.readLine());
		pst1.setInt(1,ecode);
		ResultSet rs=pst1.executeQuery();
		if(rs.next())
		{
			pst.setInt(5,ecode);
		}
		else
		{
			System.out.print("						U Have Entered Invalid Code : ");
			return;
		}
		
		
		System.out.print("							Enter Status : ");
		String status=(br.readLine());
		pst.setString(6,status);
		System.out.print("							Enter Bug Description : ");
		String desc=(br.readLine());
		pst.setString(7,desc);	
		int j=pst.executeUpdate();
		if(j>0)
		{
			System.out.print("						Updated Sucessfully : ");
		}
		else{
			System.out.print("						Not Updated : ");
		}
		
		
	}
	public void updateBugStatus()throws Exception
	{
		System.out.print("							Enter Bug Number : ");
		int temp=Integer.parseInt(br.readLine());
		
		PreparedStatement pst=con.prepareStatement("select * from BugReport where bugNo=?");
		pst.setInt(1,temp);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
			pst=con.prepareStatement("update BugReport set status=?");
			System.out.print("							Enter New Status of Bug : ");
			String Newbug=br.readLine();
			pst.setString(1,Newbug);
			pst.executeUpdate();
			System.out.println("							Updated SucessFully: ");
		}
		else
		{
			System.out.println("							No Record Found : ");
		}
		
	}
	public void viewBug() throws Exception
	{
	
		
		
		
		
		
			if(role.equals("tester"))
			{
               PreparedStatement pst=con.prepareStatement("Select ProjectID,bugNo,bugCode,status from bugReport where Tcode=?");
			   pst.setInt(1,id);
			   ResultSet rs=pst.executeQuery();
			   System.out.println("***********************************************************************************");
			String h4=String.format("|%14s \t| %14s \t| %14s | %14s |","Project ID","Bug Number","Bug Code","Bug Status");
		  System.out.println(h4);
			   while(rs.next())
			   {
				   System.out.println("***********************************************************************************");
				   String h1=String.format("|%14d \t| %14d \t| %14d |%14s \t|",rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4));
				   System.out.println(h1);
				   
			   }
				   System.out.println("***********************************************************************************");
			}
			
			else if(role.equals("developer"))
			{
			   PreparedStatement pst=con.prepareStatement("Select ProjectID,bugNo,bugCode from bugReport where Ecode=? and status='pending'");
			   pst.setInt(1,id);
			 System.out.println("							Unresolved Bugs Are : ");
			   ResultSet rs=pst.executeQuery();
			   System.out.println("******************************************************************");
		  String h3=String.format("|%14s \t| %14s \t| %14s |","Project ID","Bug Number","Bug Code");
			System.out.println(h3);
			   while(rs.next())
			   {
				    System.out.println("******************************************************************");
				   String h2=String.format("|%14d \t| %14d \t| %14d |",rs.getInt(1),rs.getInt(2),rs.getInt(3));
				   System.out.println(h2);
				    
			   }
			   System.out.println("******************************************************************");
				   
			}
			
		
	}

	public void bugDetails() throws Exception
    {
		PreparedStatement pst=con.prepareStatement("select * from BugType");
		ResultSet res=pst.executeQuery();
		
		System.out.println("*****************************************************************************************");
		  String h1=String.format("|%14s \t| %40s \t| %14s |","Bug Code","Bug Type","Bug Severity");
			System.out.println(h1);
		while(res.next())
		{ System.out.println("*****************************************************************************************");
			String h2=String.format("|%14d \t| %40s \t| %14s |",res.getInt(1),res.getString(2),res.getString(3));
			System.out.println(h2);
		 
		}
		System.out.println("*****************************************************************************************");
	}
	public void exit()
	{
		System.exit(0);
	}
}
