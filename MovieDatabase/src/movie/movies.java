package movie;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

public class movies {
	 private Connection connect() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:/C:\\sqlite\\movies.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 public void insert(String name, String actor,String actress,String director ,int year) {
	        String sql = "INSERT INTO movie(name,actor,actress,director,year) VALUES(?,?,?,?,?)";
	        try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, name);
	            pstmt.setString(2, actor);
	            pstmt.setString(3, actress);
	            pstmt.setString(4, director);
	            pstmt.setLong(5, year);
	            pstmt.executeUpdate();
	            System.out.println("Inserted Successfully!!");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:sqlite:/C:\\sqlite\\movies.db";
		int op,release;
		Scanner sc=new Scanner(System.in);
		System.out.println("Select your opiton\n1.Display\n2.Insert\n3.Search\n");
		op=sc.nextInt();	
		if(op==1) {
			try 
			{
				Connection con=DriverManager.getConnection(url);
				String query="SELECT * FROM movie";
				java.sql.Statement stm=con.createStatement();
				ResultSet res=((java.sql.Statement) stm).executeQuery(query);
				while(res.next()) {
					String name=res.getString("name");
					String actor=res.getString("actor");
					String actress=res.getString("actress");
					String director=res.getString("director");
					String year=res.getString("year");
					
					System.out.println(name+" | "+actor+" | "+actress+" | "+director+" | "+year);
				}
				

			}catch (SQLException e) {
				System.out.println("fault in displaying");
				e.printStackTrace();
			}
		}
		else if(op==2) {
			try 
			{
				Scanner input=new Scanner(System.in);
				Connection con=DriverManager.getConnection(url);
				System.out.println("Enter movie name:");
				String val1=input.nextLine();
				System.out.println("Enter actor name:");
				String val2=input.nextLine();
				System.out.println("Enter actress name:");
				String val3=input.nextLine();
				System.out.println("Enter director name:");
				String val4=input.nextLine();
				System.out.println("Enter year of release:");
				release=input.nextInt();
				movies app = new movies();
		        // insert three new rows
		        app.insert(val1,val2,val3,val4,release);
				
				

			}catch (SQLException e) {
				System.out.println("fault in inserting");
				e.printStackTrace();
			}
		}
		else if (op==3) {
			Scanner input=new Scanner(System.in);
			System.out.println("Enter Actor name:");
			String acname=input.nextLine();
			try 
			{
				Connection con=DriverManager.getConnection(url);
				String query="SELECT * FROM movie WHERE actor='"+acname+"'";
				java.sql.Statement stm=con.createStatement();
				ResultSet res=((java.sql.Statement) stm).executeQuery(query);
				while(res.next()) {
					String name=res.getString("name");
					String actor=res.getString("actor");
					String actress=res.getString("actress");
					String director=res.getString("director");
					String year=res.getString("year");
					System.out.println("results found are as follows");
					System.out.println(name+" | "+actor+" | "+actress+" | "+director+" | "+year);
				}
				

			}catch (SQLException e) {
				System.out.println("fault in searching");
				e.printStackTrace();
			
			

		}
		
		

	}

	}
}
