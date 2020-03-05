package gpibph.daoImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import gpibph.app.Koneksi;

@Path("/registrasi/")
public class UserLoginDaoImpl {
	
	@POST
	@Path("registrasiLogin/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUserLogin(@Context HttpServletRequest request, @Context HttpServletResponse response) {
				
		String body = null ;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if(inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char [128];
				int bytesRead = -1;
				while((bytesRead = bufferedReader.read(charBuffer))> 0) {
					stringBuilder.append(charBuffer, 0,bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		body = stringBuilder.toString();
		System.out.println("Body :" + body);
		String[] data = body.split("\\#",-1);
		String sqlUserLogin = "INSERT INTO login values(null, ?,?,?,?)";
		try {
			Koneksi.ps = Koneksi.getConnection().prepareStatement(sqlUserLogin);
			Koneksi.ps.setString(1, data[1]);
			Koneksi.ps.setString(2, data[2]);
			Koneksi.ps.setString(3, data[3]);
			Koneksi.ps.setString(4, data[4]);
			Koneksi.ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.status(200).entity(body).build();
	}
}