package ticket_booking;

import java.sql.*;
import java.util.*;


public class Reservation {
	

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root","root","12345");
		//PreparedStatement pst=con.prepareStatement();
		//System.out.println("Connected successfully!");
		//Scanner sc=new Scanner(System.in);
		System.out.println("Select mode of operation:");
		System.out.println("1.User mode"
				          +"\n"
				          +"2.Admin mode");
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1:
		
		System.out.println("*****welcome to Railway Reservation System******");
		System.out.println("Select your choice to continue");
		System.out.println("press 1 for login"
		                   +"\n"
			               +"\n"
		                   +"Press 2 for New User Registration");
		//Scanner sc=new Scanner(System.in);
		int user_choice=sc.nextInt();
		switch(user_choice) {
		case 1://Login module for existing user
			Scanner s=new Scanner(System.in);
			System.out.println("Enter your username:");
			String username=s.nextLine();
			
			System.out.println("Enter your password:");
			String password=s.nextLine();
			
			String sql="select* from reservation.user_details where username=? and password=?";
			PreparedStatement pst=con.prepareStatement(sql);
		    pst.setString(1,username);
		    pst.setString(2, password);
		    ResultSet R=pst.executeQuery();
		    
		    if(R.next()) {
		    	//To validate credentials entered by user with values existing in database
		    	String user=R.getString("username");
		    	String pwd=R.getString("password");
		    	System.out.println("Login successful");
		    }
		    else if(R.next()==false) {
		    	System.out.println("Invalid details");
		    	System.exit(0);
		    	break;
		    }
		    break;
		case 2://Registration module for new user
			Scanner sa=new Scanner(System.in);
			System.out.println("Enter your username:");
			String userid=sa.nextLine();
			
			System.out.println("Enter your password:");
			String pw=sa.nextLine();
			
			System.out.println("Enter your mobile number:");
			Double mob=sa.nextDouble();
			
			String query="insert into reservation.user_details"+"(username,password,mobile)values "+"(?,?,?);";//Used to insert user details into database
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,userid);
			ps.setString(2, pw);
			//pst.setString(4, email);
			ps.setDouble(3, mob);
			
			ps.executeUpdate();
		
		
		System.out.println("Registration Completed!");
		break;
		
		default:
			
			System.out.println("Enter valid details");	
		}
		System.out.println("\n");
		System.out.println("*******************Services Available**************************");
		System.out.println("press 1 for ticket booking"
		                   +"\n"
				           +"\n"
		                   +"press 2 for ticket cancellation"
				           +"\n"
		                   +"\n"
				           +"Press 3 to updating passenger details"
		                   +"\n"
				           +"\n"
		                   +"press 4 to view ticket chart"
		                  );
		//System.out.println("\n");
		System.out.println("Enter your choice to proceed!");
		Scanner a=new Scanner(System.in);
		int cust_choice= a.nextInt();
		
		if(cust_choice<1) {
			System.out.println("Please choose only from available options");
		}
		switch(cust_choice){
		case 1://Booking new ticket by inserting details entered by the passenger into database
			
			System.out.println("Welcome to ticket booking!");
			
			String q="Select * from reservation.train_details";
			PreparedStatement pst=con.prepareStatement(q);
			ResultSet rs=pst.executeQuery(q);
			
			if(rs.next()){ 
				//To print the list of available train routes
				System.out.println("********************************************LIST OF TRAINS AVAILABLE**************************************************************");
				
					System.out.println("Train_number" + "   "+"Source_city" + "   " + "Destination_city" + "   " + "Departure_time" +"    "+ "Tickets_avaialable");
					do {
				System.out.println(rs.getInt(1) + "        " + rs.getString(2) +"         " + rs.getString(3) + "            " + rs.getString(4) + "          " + rs.getInt(5));
				}while(rs.next());
			}
			System.out.println("****************************************************************************************************************************************");
			
			System.out.println("Please select your train from above list of trains");
			
			System.out.println(" ");

			
			Scanner sn=new Scanner(System.in);
			System.out.println("Enter number of tickets to be booked:");
			int tickets=sn.nextInt();
			for(int i=1;i<=tickets;i++) {
				Scanner sa=new Scanner(System.in);
				
			System.out.println("Enter name of passenger " +i+":");
			String name=sa.nextLine();
			
			System.out.println("Enter age of passenger " +i+":");
			int age=sa.nextInt();
			if(age<=0||age>120) {
				System.out.println("Enter valid age");
				System.exit(0);
			}
			Scanner st=new Scanner(System.in);
			
			System.out.println("Enter gender of passenger "+i+":");
			String gender=st.nextLine();
			
			System.out.println("Enter mobile number of passenger "+i+":");
			double mobile_number=st.nextDouble();
			Scanner sr=new Scanner(System.in);
			
			System.out.println("Enter boarding station of passenger "+i+":");
			String boarding=sr.nextLine();
			
			//Scanner sr=new Scanner(System.in);
			
			
			System.out.println("Enter destination of passenger "+i+":");
			String destination=sr.nextLine();
			//To validate train route entered by user with existing train routes
			String sql3="select* from reservation.train_details where Source_city=? and Destination_city=?";
			PreparedStatement pa=con.prepareStatement(sql3);
		    pa.setString(1,boarding);
		    pa.setString(2, destination);
		    ResultSet R=pa.executeQuery();
		    
		    if(R.next()) {
		    	String from=R.getString("Source_city");
		    	String to=R.getString("Destination_city");
		    	System.out.println("You have chosen train from "+boarding+" "+"to"+" "+destination+" "+"and train number is"+" "+R.getInt(1));
		    }
		    else if(R.next()==false) {
		    	System.out.println("Invalid train details");
		    	System.exit(0);
		    	break;
		    }
			
			System.out.println("Enter date of journey of passenger "+i+":");
			String date_of_journey=sr.nextLine();
			
			System.out.println("Enter choice of berth for passenger "+i+":");
			
			int berth_ch=sr.nextInt();
			switch(berth_ch) {
			case 1:
				System.out.println("You have chosen upper berth");
				break;
			case 2:
				System.out.println("You have chosen lower berth");
				break;
			case 3:
				System.out.println("You have chosen middle berth");
				break;
				default:
					System.out.println("Enter valid choice of berth");
			}
			
			
			 /*Date date=null;
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Enter your date of journey:");
			String cinput=st.nextLine();
			if(null!=cinput&&cinput.trim().length()>0) {
				date=format.parse(cinput);			
				}*/
			//System.out.println();
			//To store passenger details into database
			String query="insert into reservation.passenger_details"+"(name,age,gender,mobile_number,boarding,destination,date_of_journey)values "+"(?,?,?,?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			ps.setDouble(4, mobile_number);
			ps.setString(5,boarding );
			ps.setString(6, destination);
			ps.setString(7, date_of_journey);
			
			ps.executeUpdate();
			ps.close();
			
			String qu="insert into reservation.passengerdetails_admin"+"(name,age,gender,mobile_number,boarding,destination,date_of_journey)values"+"(?,?,?,?,?,?,?);";
			PreparedStatement pr=con.prepareStatement(qu);
			
			pr.setString(1,name);
			pr.setInt(2, age);
			pr.setString(3, gender);
			pr.setDouble(4, mobile_number);
			pr.setString(5,boarding );
			pr.setString(6, destination);
			pr.setString(7, date_of_journey);
			
			pr.executeUpdate();
			pr.close();
			
			
		}
			System.out.println("Your ticket has been booked successfully!");
			break;
			
			
		  case 2://To cancel tickets booked by passenger
			System.out.println("Enter your booking details to proceed with cancellation!");
			System.out.println("Enter number of tickets to be cancelled");
			Scanner ss=new Scanner(System.in);
			int ticket=ss.nextInt();
			for(int j=1;j<=ticket;j++) {
				System.out.println("Enter name of passenger "+j+":");
				Scanner se=new Scanner(System.in); 
				String name=se.nextLine();
				
				System.out.println("Enter date of journey of passenger "+j+":");
				String date=se.nextLine();
				
				String query1="DELETE FROM reservation.passenger_details WHERE name=? and date_of_journey=?";
				PreparedStatement pt=con.prepareStatement(query1);
				
				pt.setString(1, name);
				pt.setString(2, date);
				
				pt.executeUpdate();
				
				
				
				
				
				
			}
			System.out.println("Tickets cancelled successfully!");
			break;
		case 3:// To update passenger details like name and mobile number
		System.out.println("Enter the details you want to update");
		System.out.println(" ");
		String query3="UPDATE reservation.passenger_details SET name=?,mobile_number=? WHERE NAME=?";
		PreparedStatement pe=con.prepareStatement(query3);
		
		Scanner se=new Scanner(System.in);
		System.out.println("Enter passenger name:");
		String Passenger_name=se.nextLine();
		
		String query4="select* from reservation.passenger_details where name=?";
		PreparedStatement pn=con.prepareStatement(query4);
	    pn.setString(1,Passenger_name);
	    //pn.setString(2, password);
	    ResultSet R=pn.executeQuery();
	    
	    
	    if(R.next()) {//To validate if user has entered correct passenger name for updating
	    	String passenger_name=R.getString("name");
	    	//String pwd=R.getString("password");
	    	System.out.println("Details can be updated!");
	    	
	    }
	    else {
	    	System.out.println("Invalid passenger name,Details cannot be updated!");
	    	System.exit(0);
	    }
		
		//System.out.println(" ");
		//String name=se.nextLine();
		System.out.println("Enter updated passenger name:");
		//System.out.println(" ");
		String name_updated=se.nextLine();
		System.out.println("Enter updated mobile number:");
		Double mobile_updated=se.nextDouble();
		
		
		
		

		pe.setString(1, name_updated);
		pe.setDouble(2, mobile_updated);
		pe.setString(3, Passenger_name);
		pe.executeUpdate();
		
		
		System.out.println(" ");
		System.out.println("Details updated Successfully!");
		break;
		case 4://To view passenger ticket chart
			
			
			String sq="Select * from reservation.passengerdetails_admin";//To get ticket details from database used to store passenger details
			
			PreparedStatement pd=con.prepareStatement(sq);
			ResultSet rt=pd.executeQuery(sq);
			//pd.setString(1,passenger_name);
			
			
			System.out.println("*****************************************************************************TICKET CHART************************************************************************************************");
			if(rt.next()) {
				System.out.println("Passenger name" + "        " + "Age" + "         " + "Gender" + "        " + "Mobile number" + "            " 
			                      + "From" + "             " + "to" + "              " + "date_of_journey" + "             " + "PNR Number" 
						          + "         " + "Booking Status");
			do {
			
			System.out.println(rt.getString(1) + "         " + rt.getInt(2) + "            " + rt.getString(3)+"            " 
			                  +rt.getBigDecimal(4)+"             " + rt.getString(5) + "          " + rt.getString(6) + "            "  +rt.getString(7)
			                  +"               " + rt.getBigDecimal(8) + "          " +rt.getString(9));
			}while(rt.next());
			}
			System.out.println("********************************************************************************************************************************************************************************************");
			
			
			
			
			
		break;
		
		default:
			System.exit(0);

		
		
		}
		break;
		//To enter Admin mode of operation
		case 2:
			
			
			Scanner sr=new Scanner(System.in);
			System.out.println("Enter username for admin:");
			String user_admin=sr.nextLine();
			
			System.out.println("Enter password for admin:");
			String password_admin=sr.nextLine();
			
			if(user_admin.equals("admin")&&password_admin.equals("admin")) //To validate admin login credentials
			{
			
			System.out.println("Admin mode");
			}
			else 
			{
				System.out.println("Access denied!");
				System.exit(0);
			}
			System.out.println("ADMIN OPERATIONS");
			System.out.println("  ");
			System.out.println("SELECT OPERATION TO BE PERFORMED");
			System.out.println("  ");
			System.out.println("1.Add new train routes "
					           +"\n"
					           +"2.Update Existing Train Details"
					           +"\n"
					           +"3.Update Passenger Ticket Details"
					           +"\n"
					           +"4.Remove users from database"
					           +"\n"
					           +"5.Delete Passengers from database"
					           );
			Scanner ss=new Scanner(System.in);
			int admin_choice=ss.nextInt();
			switch(admin_choice) {
			case 1://To add new train routes
				Scanner as=new Scanner(System.in);
				System.out.println("Enter details for new train route");
				System.out.println(" ");
				System.out.println("Enter train number");
				int train=as.nextInt();
				Scanner sd=new Scanner(System.in);
				
				System.out.println("Enter Source city:");
				String source=sd.nextLine();
				
				System.out.println("Enter destination:");
				String dest=sd.nextLine();
				
				System.out.println("Enter departure time:");
				String dep_time=sd.nextLine();
				
				System.out.println("Enter number of tickets available:");
				int avlbl_tkts=sd.nextInt();
				
				System.out.println("Enter ticket fare:");
				int fare=sd.nextInt();
				
				//To 
				String query="insert into reservation.train_details"+"(Train_number,Source_city,Destination_city,Departure_time,Tickets_available,ticket_fare)values "+"(?,?,?,?,?,?);";
				PreparedStatement pst=con.prepareStatement(query);
				pst.setInt(1,train);
				pst.setString(2, source);
				pst.setString(3, dest);
				pst.setString(4, dep_time);
				pst.setInt(5,avlbl_tkts);
				pst.setInt(6, fare);
				
				pst.executeUpdate();
				System.out.println("New Train Route has been added successfully!");
				break;
			case 2:
				String sql5="UPDATE reservation.train_details SET ticket_fare=? WHERE Train_number=?";
				PreparedStatement pr=con.prepareStatement(sql5);
				System.out.println("Enter train number:");
				Scanner er= new Scanner(System.in);
				int train_num=er.nextInt();
				
				pr.setInt(1,550);
				pr.setInt(2, train_num);
				
				pr.executeUpdate();
				break;
			case 3:
				Scanner an=new Scanner(System.in);
				System.out.println("Enter PNR number for passenger");
				Double pnr= an.nextDouble();
				String sql6="UPDATE reservation.passengerdetails_admin SET pnr_number=? WHERE name=?";
				PreparedStatement pm=con.prepareStatement(sql6);
				
				pm.setDouble(1,pnr);
				pm.setString(2, "satvika");
				
				pm.executeUpdate();
				
				
				String sql7="UPDATE reservation.passengerdetails_admin SET Ticket_status=? WHERE name=?";
				PreparedStatement ps=con.prepareStatement(sql7);
				
				ps.setString(1, "Confirmed");
				ps.setString(2, "satvika");
				ps.executeUpdate();
				
				System.out.println("Ticket details updated successfully!");
				break;
			case 4:
				Scanner sa=new Scanner(System.in);
				System.out.println("Enter name of user to be deleted");
				String user=sa.nextLine();
				
				String sql8="DELETE FROM reservation.user_details WHERE username=?";
				PreparedStatement pt=con.prepareStatement(sql8);
				pt.setString(1,user);
				pt.executeUpdate();
				
				System.out.println("User deleted from database successfully!");
				break;
			case 5:
				System.out.println("Enter name of passenger you want to remove from database");
				Scanner nn=new Scanner(System.in);
				String name=nn.nextLine();
				String query1="DELETE FROM reservation.passengerdetails_admin WHERE name=?";
				PreparedStatement pe=con.prepareStatement(query1);
				
				
				
				pe.setString(1, name);
				pe.executeUpdate();
				System.out.println("Passenger Removed from database successfully");
				default:
					System.exit(0);
				
				
				
				
				
				
				
				
			}
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
}
}


//To-do List
/*If user selects login, then ask for credentials and check if the values exist in database
 * If user selects new user, then ask for credentials and insert into table
 * After completing this process, create another switch case for booking choice
 * 1. Booking-->Ask for doj,src,dest,mobile number and email id
 * Ask for berth choice
 * 2. Cancellation-->Ask for pnr num and delete row from database
 * update can be done by both passenger and admin, passenger will be able to update his details and admin will be able to update booking status and add pnr number by generating a random integer to tickets
 * For print ticket, ask user name and doj and display that particular row with ticket details
 * Admin will have access to tickets table, after user books ticket, admin updates table by adding pnr number and booking status
 * Create another table with train details which includes train number, source destination and train timings*/
 