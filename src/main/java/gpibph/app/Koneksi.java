package gpibph.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Koneksi {
	
private static Connection connection;
	
	public static PreparedStatement ps;
	public static ResultSet rs;

	public static Connection getConnection() {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/gpibph","root","");
			System.out.print("Koneksi Berhasil \n" + connection);
		} catch (Exception e) {
			 e.printStackTrace();
			 System.out.print("Gagal Koneksi Database");
		}
		return connection;
	}
	
	public static PreparedStatement setPreparedStatement() {
		return ps;
	}
	
	public static ResultSet setResultSet() {
		return rs;
	}
}
