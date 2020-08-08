
import java.util.*;
import java.sql.*;
import java.io.*;
public class Admin
{

	
	static Connection con=null;
	
	static
	{
	
		try
		{
			
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bts","root","Rated!12");
		if(con!=null)
		{
		System.out.println("				Connection establish");}
			else 
			{System.out.println("				Connection not establish");
		}
		}
		catch(Exception e)
		{
			System.out.println("				Cannot load Driver");
		}
	}
	
	int id;
	String name;
	String role;
	
	Admin(int id,String name,String role)
	{
		this.id=id;
		this.name=name;
		this.role=role;
		
	}
			
	  public  void addManagerAccount() throws Exception
	{
		
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
		System.out.print("							Enter Employee Code : ");	
        int ecode=	Integer.parseInt(br.readLine());
		PreparedStatement pst1=con.prepareStatement("Select * from  Employee where empCode=?");
		pst1.setInt(1,ecode);
		ResultSet rs=pst1.executeQuery();
		if(!rs.next())
		{
		
		PreparedStatement pst=con.prepareStatement("insert into Employee values(?,?,?,?,?,?,?,?)");
		pst.setInt(1,ecode);
        
		System.out.print("							Enter Employee Name : ");
		pst.setString(2,br.readLine());
		System.out.print("							Enter Employee Email : ");
		pst.setString(3,br.readLine());
		System.out.print("							Enter Employee Password : ");
		pst.setString(4,br.readLine());
		System.out.print("							Enter Employee Gender : ");
		pst.setString(5,br.readLine());
		System.out.print("							Enter Employee Date Of Birth : ");
		pst.setString(6,br.readLine());
		System.out.print("							Enter Employee Moblie number : ");
		pst.setLong(7,Long.parseLong(br.readLine()));
		pst.setString(8,"manager");
		int res=pst.executeUpdate();
		System.out.println(res);
		if(res>0)
			System.out.println("							Record inserted");
		else
			System.out.println("							Record not inserted");
		}
		else{
			System.out.println("							Record with this id already present");
		}
	}
	public  void viewManagerAccount() throws Exception
	{
		 System.out.println("***********************************************************************************************************************************************************************");
		String h1=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t| %14s | %14s \t| %14s |","Manager Code","Employee Name","Employee Email","Employee Passward","Gender","Date Of Birth","Mobile No","Role");
		System.out.println(h1);
		System.out.println("************************************************************************************************************************************************************************");	
	    PreparedStatement pst =con.prepareStatement("select * from Employee where Role='manager'");
		ResultSet res=pst.executeQuery();
		while(res.next())
		{
		 String h2=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t| %14s | %14s \t| %14s |",res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getLong(7),res.getString(8));
		 System.out.println(h2);
		//	System.out.println(res.getInt(1)+"\t"+res.getString(2)+"\t"+res.getString(3)+"\t"+res.getString(4)+"\t"+res.getString(5)+"\t"+res.getString(6)+"\t"+res.getLong(7)+"\t"+"\t"+res.getString(8));
		}
		System.out.println("************************************************************************************************************************************************************************");
		
	}
	public  void deleteManager() throws Exception
	{
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
		PreparedStatement pst=con.prepareStatement("delete from Employee where empCode=? and Role ='manager'");
		System.out.println("							ENTER MANAGER CODE WHO IS RECORD TO BE DELETE");
		pst.setInt(1,Integer.parseInt(br.readLine()));
		int res=pst.executeUpdate();
		if(res>0)
			System.out.println("							RECORD SUCCESSFULLY DELETED");
		else
			System.out.println("							RECORD IS NOT DELETED BECAUSE MANAGER  CODE NOT EXIST");
		
	}
	public  void updateManagerDetails() throws Exception
	{
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
		PreparedStatement pst=con.prepareStatement("select * from Employee where empCode=?");
		System.out.println("							ENTER EMPLOYEE CODE WHO IS RECORD TO BE UPDATE");
		int x=Integer.parseInt(br.readLine());
		pst.setInt(1,x);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
		    pst=con.prepareStatement("update Employee set empName=?,empEmail=?,empPassword=?,gender=?,DOB=?,mobileNo=?,Role=? where empCode=?");
		    System.out.print("							Enter Employee Name : ");
			pst.setString(1,br.readLine());
			System.out.print("							Enter Employee Email : ");
			pst.setString(2,br.readLine());
			System.out.print("							Enter Employee Passward : ");
		    pst.setString(3,br.readLine());
			System.out.print("							Enter Employee Gender : ");
			pst.setString(4,br.readLine());
			System.out.print("							Enter Employee Date Of Birth : ");
			pst.setString(5,br.readLine());
			System.out.print("							Enter Employee Moblie number : ");
			pst.setLong(6,Long.parseLong(br.readLine()));
			System.out.print("							Enter Employee Role : ");
			pst.setString(7,br.readLine());
			pst.setInt(8,x);
			int re =pst.executeUpdate();
			if(re>0)
				System.out.println("							RECORD IS UPDATED");
			else 
				System.out.println("							RECORD IS NOT UPDATED");
			
				
		}
		else
			System.out.println("							NO SUCH RECORD FOUND");
		
		
				
	}
	public  void addEmployeeAccount() throws Exception
	{
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
		System.out.print("							Enter Employee Code : ");	
        int ecode=	Integer.parseInt(br.readLine());
		PreparedStatement pst1=con.prepareStatement("Select * from  Employee where empCode=?");
		pst1.setInt(1,ecode);
		ResultSet rs=pst1.executeQuery();
		if(!rs.next())
		{
		
		PreparedStatement pst=con.prepareStatement("insert into Employee values(?,?,?,?,?,?,?,?)");
		pst.setInt(1,ecode);
        
		System.out.print("							Enter Employee Name : ");
		pst.setString(2,br.readLine());
		System.out.print("							Enter Employee Email : ");
		pst.setString(3,br.readLine());
		System.out.print("							Enter Employee Password : ");
		pst.setString(4,br.readLine());
		System.out.print("							Enter Employee Gender : ");
		pst.setString(5,br.readLine());
		System.out.print("							Enter Employee Date Of Birth : ");
		pst.setString(6,br.readLine());
		System.out.print("							Enter Employee Moblie number : ");
		pst.setLong(7,Long.parseLong(br.readLine()));
		System.out.print("							Enter Employee Role : ");
		pst.setString(8,br.readLine());
		int res=pst.executeUpdate();
		System.out.println(res);
		if(res>0)
			System.out.println("							Record inserted");
		else
			System.out.println("							Record not inserted");
		}
		else{
			System.out.println("							Record with this id already present");
		}
	}
	public  void viewEmployeesAccount() throws Exception
	{
		
		System.out.println("***********************************************************************************************************************************************************************");
		String h1=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t| %14s | %14s \t| %14s |","Employee Code","Employee Name","Employee Email","Employee Passward","Gender","Date Of Birth","Mobile No","Role");
		System.out.println(h1);
		System.out.println("************************************************************************************************************************************************************************");
		PreparedStatement pst =con.prepareStatement("select * from Employee where Role='developer' or Role= 'tester'");
		ResultSet res=pst.executeQuery();
		while(res.next())
		{
			String h2=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t| %14s | %14s \t| %14s |",res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getLong(7),res.getString(8));
		 System.out.println(h2);
		}
		System.out.println("************************************************************************************************************************************************************************");
	}
	public  void deleteEmployeeAccount() throws Exception
	{
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
		PreparedStatement pst=con.prepareStatement("delete from Employee where empCode=? and( Role='developer' or Role='Tester')");
		System.out.println("							ENTER EMPLOYEE CODE WHO'S RECORD TO BE DELETE");
		pst.setInt(1,Integer.parseInt(br.readLine()));
		int res=pst.executeUpdate();
		if(res>0)
			System.out.println("							RECORD SUCCESSFULLY DELETED");
		else
			System.out.println("							RECORD IS NOT DELETED BECAUSE MANAGER  CODE NOT EXIST");
		
		
	}
	public  void updateEmployeeDetails() throws Exception
	{
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
		PreparedStatement pst=con.prepareStatement("select * from Employee where empCode=?");
		System.out.println("							ENTER EMPLOYEE CODE WHO IS RECORD TO BE UPDATE");
		int x=Integer.parseInt(br.readLine());
		pst.setInt(1,x);
		ResultSet res=pst.executeQuery();
		if(res.next())
		{
		    pst=con.prepareStatement("update Employee set empName=?,empEmail=?,empPassword=?,gender=?,DOB=?,mobileNo=?,Role=? where empCode=?");
		    System.out.print("										Enter Employee Name : ");
			pst.setString(1,br.readLine());
			System.out.print("							Enter Employee Email : ");
			pst.setString(2,br.readLine());
			System.out.print("							Enter Employee Passward : ");
		    pst.setString(3,br.readLine());
			System.out.print("							Enter Employee Gender : ");
			pst.setString(4,br.readLine());
			System.out.print("							Enter Employee Date Of Birth : ");
			pst.setString(5,br.readLine());
			System.out.print("							Enter Employee Moblie number : ");
			pst.setLong(6,Long.parseLong(br.readLine()));
			System.out.print("							Enter Employee Role : ");
			pst.setString(7,br.readLine());
			pst.setInt(8,x);
			int re =pst.executeUpdate();
			if(re>0)
				System.out.println("								   RECORD IS UPDATED");
			else 
				System.out.println("								   RECORD IS NOT UPDATED");
			
				
		}
		else
			System.out.println("								   NO SUCH RECORD FOUND");
		
	}
	public  void viewAllProject() throws Exception
	{
		System.out.println("*****************************************************************************************************************");
		String h1=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t|","Project Id","Project Name","Start Date","End Date","Project Description");
		System.out.println(h1);
		System.out.println("*****************************************************************************************************************");
		PreparedStatement pst =con.prepareStatement("select * from Project ");
		ResultSet res=pst.executeQuery();
		while(res.next())
		{
			String h2=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t|",res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
			System.out.println(h2);
		}
		System.out.println("*****************************************************************************************************************");
		
	}
	public  void viewBugsReports() throws Exception
	{
		System.out.println("*********************************************************************************************************************************************************");
		String h1=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t| %14s | %14s \t|","Bug No","Bug Code","Project Id","Tester Code","Employee Code","Status","Mobile No","Bug Description");
		System.out.println(h1);
		System.out.println("*********************************************************************************************************************************************************");
		PreparedStatement pst =con.prepareStatement("select * from BugReport ");
		ResultSet res=pst.executeQuery();
		while(res.next())
		{
			String h2=String.format("|%14s \t| %14s \t| %14s |%14s \t| %14s \t| %14s | %14s \t|",res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7));
			System.out.println(h2);
		}
		System.out.println("*********************************************************************************************************************************************************");
	}
	
	
	public void menu()
	{
		Scanner sc =new Scanner(System.in);
		Blankscreen.clearScreen();
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(isr);
		System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
		while(true)
		{
			int n=0,n1=0,n2=0;
			try
			{
				
			    System.out.println("								   Enter 0 to Go to Main Menu");
			    int l12=sc.nextInt();
			    Blankscreen.clearScreen();
				System.out.println();
				
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("                    	 *************************************Logged in successfully as a manager***************************************");
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
				isr = new InputStreamReader(System.in);
				br =new BufferedReader(isr);
				System.out.println("						--------------------------------------------------------------");
				System.out.println("						|			1.MANAGER                            |");
				System.out.println("						--------------------------------------------------------------");
				System.out.println("		   				|			2.EMPLOYEE                           |");
				System.out.println("						--------------------------------------------------------------");
				System.out.println("						|			3.VIEW ALL PROJECT                   |");
				System.out.println("						--------------------------------------------------------------");	
				System.out.println("						|			4.VIEW BUGS REPORT                   |");
				System.out.println("						--------------------------------------------------------------");
				System.out.println("						|			5.LOGOUT                             |");
			    System.out.println("						--------------------------------------------------------------");
				System.out.println("									ENTER CHOICE");
				n=Integer.parseInt(br.readLine());
			}
			catch(Exception e)
			{
				System.out.println("ENTER CHOICE AS NUMBER");
			}
			switch(n)
			{
				
				case 1:
				  do
				    {
						
						System.out.println("								   Enter any digit to Go to  Manager Menu");
					    int l11=sc.nextInt();
					    Blankscreen.clearScreen();
						System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									System.out.println("						--------------------------------------------------------------");
						            System.out.println("						|			1.ADD MANAGER ACCOUNT                |");
									System.out.println("						--------------------------------------------------------------");
						            System.out.println("						|			2.VIEW MANAGER ACCOUNT               |");
									System.out.println("						--------------------------------------------------------------");
						            System.out.println("						|			3.DELETE MANAGER                     |");
									System.out.println("						--------------------------------------------------------------");
						            System.out.println("						|			4.UPDATE MANAGER DETAIL'S            |");
									System.out.println("						--------------------------------------------------------------");
						            System.out.println("						|			5.BACK                               |");
									System.out.println("						--------------------------------------------------------------");
						            System.out.println("								   ENTER CHOICE");
						try{
							isr = new InputStreamReader(System.in);
                            br =new BufferedReader(isr);
						     n1=Integer.parseInt(br.readLine());
						   }
						catch(Exception e)
						{
						System.out.println("			ENTER CHOICE AS NUMBER");
						}
						switch(n1)
						{
							
							case 1 :
							try{
								    Blankscreen.clearScreen();
									System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									addManagerAccount();
									
									
				               }
				            catch(Exception e){System.out.println("				INVALID EMPLOYEE CODE TRY AGAIN");}
							break;
							case 2 :
							try{
								    Blankscreen.clearScreen();
									System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									viewManagerAccount();
									
							    }
							catch(Exception e){}
							break;
							case 3 :
							try{
									Blankscreen.clearScreen();
									System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									deleteManager();
									
								}
							catch(Exception e){
								System.out.println("			INVALID INPUT TRY AGAIN");
							}
							break;
							case 4 :
							try{
									Blankscreen.clearScreen();
									System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									updateManagerDetails();
									
								}
							catch(Exception e){System.out.println("				INVALID INPUT TRY AGAIN");}
							break;
							case 5 :
							try{
									n1=5;
									
								}
							catch(Exception e){System.out.println("				INVALID INPUT TRY AGAIN");}
							break;
							default :
							Blankscreen.clearScreen();
					        System.out.println("			INVALID CHOICE"); 
							break;
						}
			         
				    }while(n1!=5);
					break;
                    case 2 :
					do
					{
                        
						System.out.println("								   Enter Any  Didits Go to Employee Menu");
					    int l13=sc.nextInt();
					    Blankscreen.clearScreen();
					              System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
						System.out.println("						--------------------------------------------------------------");
						System.out.println("						|			1.ADD EMPLOYEE ACCOUNT               |");
						System.out.println("						--------------------------------------------------------------");
						System.out.println("						|			2.VIEW EMPLOYEE'S ACCOUNT            |");
						System.out.println("						--------------------------------------------------------------");
						System.out.println("						|			3.DELETE EMPLOYEE ACCOUNT            |");
						System.out.println("						--------------------------------------------------------------");
						System.out.println("						|			4.UPDATE EMPLOYEE DETAIL'S           |");
						System.out.println("						--------------------------------------------------------------");
						System.out.println("						|			5.BACK                               |");
						System.out.println("						--------------------------------------------------------------");
						System.out.println("  										   ENTER CHOICE");
						try{
							isr = new InputStreamReader(System.in);
                            br =new BufferedReader(isr);
						    n2=Integer.parseInt(br.readLine());
						}
						catch(Exception e)
						{
						System.out.println("								   ENTER CHOICE AS NUMBER");
						}
						switch(n2)
						{
							case 1 :
							try{
								    Blankscreen.clearScreen();
								System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									addEmployeeAccount();
									
								}
							catch(Exception e){System.out.println("								   INVALID INPUT TRY AGAIN");}
							break;
							case 2 :
							try{
								    Blankscreen.clearScreen();
									System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									viewEmployeesAccount();
									
								}
				            catch(Exception e){System.out.println("								   INVALID INPUT TRY AGAIN");}
							break;
							case 3 :
							try{
									Blankscreen.clearScreen();
									System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									deleteEmployeeAccount();
									
								}
							catch(Exception e){System.out.println("								   INVALID EMPLOYEE CODE TRY AGAIN");}
							break;
							case 4 :
							try{
									System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
									updateEmployeeDetails();
									
								}
									catch(Exception e){System.out.println("								   INVALID EMPLOYEE CODE TRY AGAIN");}
							break;
							case 5 :
							try{
									n2=5;
								}
							catch(Exception e){System.out.println("								   INVALID CHOICE TRY AGAIN");}
							break;
							default :
									Blankscreen.clearScreen();
									System.out.println("								   INVALID CHOICE"); 
							break;
						}
					}while(n2!=5);	
					break;
					case 3:
					try{
						Blankscreen.clearScreen();
						System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
						viewAllProject();
						
						}
					catch(Exception e){System.out.println("								   INVALID CHOICE TRY AGAIN");}
					break;
					case 4:
					try{
						Blankscreen.clearScreen();
								System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
						viewBugsReports();
						
						}
					catch(Exception e){System.out.println("								   INVALID CHOICE TRY AGAIN") ;}
					break;
					case 5:
					Blankscreen.clearScreen();
					System.out.println();
				System.out.println("		             ****************************************Welcome To Bug Tracking System****************************************");
				System.out.println();
				System.out.println();
				System.out.println("				              --------------------------Admin "+name+"--------------------------");
				System.out.println();
					System.out.println("							   SUCCESSFULLY LOGOUT"); 
					return;
					
					default :
						Blankscreen.clearScreen();
						System.out.println("								   INVALID CHOICE");
						break;
			}
		}
	}
}
/*public class Blankscreen
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
}*/

	
		

	



	
	 
	
