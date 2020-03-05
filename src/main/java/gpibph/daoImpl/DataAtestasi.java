package gpibph.daoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.parser.JSONParser;

import gpibph.app.Koneksi;
import gpibph.model.Atestasi;
import gpibph.util.Util;
import org.json.simple.JSONObject;

@Path("/datanonaktif/")
public class DataAtestasi {
	
	@GET
	@Path("allatestasi/{id_atestasi}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Atestasi> getDataAtestasi(@PathParam("id_atestasi") String id_atestasi){
		
		List<Atestasi> listAllAtestasi = new ArrayList<Atestasi>();
		String selectAtestasi;
		if(id_atestasi.equalsIgnoreCase("all")) {
			selectAtestasi	 = "SELECT * FROM Atestasi";
		} else {
			selectAtestasi = "SELECT * FROM Atestasi where id_atestasi = ?";
		}
		try {	
			Koneksi.ps = Koneksi.getConnection().prepareStatement(selectAtestasi);
			if(!id_atestasi.equalsIgnoreCase("all")) {
				Koneksi.ps.setString(1, id_atestasi);
			}
			Koneksi.rs = Koneksi.ps.executeQuery();
			while(Koneksi.rs.next()) {
				Atestasi atestasi = new Atestasi();
				atestasi.setId_atestasi(Koneksi.rs.getInt("id_atestasi"));
				atestasi.setNo_kk(Koneksi.rs.getString("no_kk"));
				atestasi.setNama(Koneksi.rs.getString("nama"));
				atestasi.setSektor(Koneksi.rs.getString("sektor"));
				try {
					atestasi.setTanggal_atestasi(Util.stringToDate(Koneksi.rs.getString("tanggal_atestasi"))); //set tanggal Lahir
				} catch (ParseException e) {
					e.printStackTrace();
				}
				atestasi.setGereja_tujuan(Koneksi.rs.getString("gereja_tujuan"));
				atestasi.setAlamat_gereja(Koneksi.rs.getString("alamat_gereja"));				
				
				listAllAtestasi.add(atestasi);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return listAllAtestasi;
	}
	
	@POST
	@Path("inputatestasi/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postDataAtestasi(@Context HttpServletRequest request, @Context HttpServletResponse response) 
			throws org.json.simple.parser.ParseException {
		
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if(inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}else {
				stringBuilder.append("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}	
		
		JSONParser jsonParser = new JSONParser();
		
		body = stringBuilder.toString();
		System.out.println("Body : " +body);
		
		//format ketika request dengan text_plain
//		String [] data = body.split("\\#",-1);
		
		//request dengan format APPLICATION_JSON
		Object obj = jsonParser.parse(body);
		JSONObject jsonObject = (JSONObject) obj;
		
		String sqlSimpan = "INSERT INTO atestasi values(null ,?,?,?,?,?,?)";
		
		try {
			Koneksi.ps = Koneksi.getConnection().prepareStatement(sqlSimpan);
			Koneksi.ps.setString(1, jsonObject.get("no_kk").toString());
			Koneksi.ps.setString(2, jsonObject.get("nama").toString());
			Koneksi.ps.setString(3, jsonObject.get("sektor").toString());
			Koneksi.ps.setString(4, jsonObject.get("tanggal_atestasi").toString());
			Koneksi.ps.setString(5, jsonObject.get("gereja_tujuan").toString());
			Koneksi.ps.setString(6, jsonObject.get("alamat_gereja").toString());
			Koneksi.ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return Response.status(200).entity(body).build();
	}
}
		
		
		
		
		
