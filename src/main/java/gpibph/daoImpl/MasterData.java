package gpibph.daoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


import gpibph.app.Koneksi;
import gpibph.model.Atestasi;
import gpibph.model.Jemaat;
import gpibph.model.StatusKeluarga;
import gpibph.util.Util;

@Path("/dataaktif/")
public class MasterData {
	
	@GET
	@Path("datapelkat/{no_kk}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Jemaat> getDataPelkat(@PathParam("no_kk") String no_kk){
		List<Jemaat> listDataPelkat = new ArrayList<Jemaat>();
		String selectPelkat = "";
		if(no_kk.equalsIgnoreCase("all")) {
			selectPelkat = "SELECT no_kk, nama, pelkat from jemaat";
		}else {
			selectPelkat = "SELECT no_kk, nama, pelkat from jemaat where no_kk = ?";
		}
		try {
			Koneksi.ps = Koneksi.getConnection().prepareStatement(selectPelkat);
			if(!no_kk.equalsIgnoreCase("all")) {
				Koneksi.ps.setString(1, no_kk);
			}
			Koneksi.rs = Koneksi.ps.executeQuery();
			while(Koneksi.rs.next()) {
				Jemaat jemaat = new Jemaat();
				jemaat.setNo_kk(Koneksi.rs.getString("no_kk"));
				jemaat.setNama(Koneksi.rs.getString("nama"));
				jemaat.setPelkat(Koneksi.rs.getString("pelkat")); 
				
				listDataPelkat.add(jemaat);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listDataPelkat;		
	}
	
	@GET
	@Path("laporankeluarga/{id_atestasi}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Atestasi> getLaporanKeluarga(@PathParam("id_atestasi") String id_atestasi){
		List<Atestasi> listLaporanKeluarga = new ArrayList<Atestasi>();
		String selectLaporan = "";
		if(id_atestasi.equalsIgnoreCase("all")) {
			selectLaporan = "SELECT * FROM atestasi";
		}else {
			selectLaporan = "SELECT * FROM atestasi where id_atestasi = ?";
		}
		try {
			Koneksi.ps = Koneksi.getConnection().prepareStatement(selectLaporan);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				atestasi.setGereja_tujuan(Koneksi.rs.getString("gereja_tujuan"));
				atestasi.setAlamat_gereja(Koneksi.rs.getString("alamat_gereja"));
				listLaporanKeluarga.add(atestasi);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listLaporanKeluarga;
	}

	@GET
	@Path("datakeluarga/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Jemaat> getDataKeluarga(@PathParam("id") String id){
		List<Jemaat> listDataKeluarga = new ArrayList<Jemaat>();
		String selectKeluarga = "";
		if(id.equalsIgnoreCase("all")) {
			selectKeluarga = "SELECT * FROM JEMAAT";
		} else {
			selectKeluarga = "SELECT * FROM JEMAAT where id = ?";
		}
		try {
			Koneksi.ps = Koneksi.getConnection().prepareStatement(selectKeluarga);
			if(!id.equalsIgnoreCase("all")) {
				Koneksi.ps.setString(1, id);
			}
			Koneksi.rs = Koneksi.ps.executeQuery();
			while(Koneksi.rs.next()) {
				Jemaat jemaat = new Jemaat();
				jemaat.setId(Koneksi.rs.getInt("id"));
				jemaat.setNo_kk(Koneksi.rs.getString("no_kk"));
				jemaat.setNama(Koneksi.rs.getString("nama"));
				try {
					jemaat.setTanggal_lahir(Util.stringToDate(Koneksi.rs.getString("tanggal_lahir"))); //set tanggal Lahir
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jemaat.setTempat_tinggal(Koneksi.rs.getString("tempat_tinggal"));
				jemaat.setAlamat(Koneksi.rs.getString("alamat"));
				jemaat.setPelkat(Koneksi.rs.getString("pelkat"));
				jemaat.setSektor(Koneksi.rs.getString("sektor"));
				if(Koneksi.rs.getInt("status_keluarga") == StatusKeluarga.KEPALA_KELUARGA.ordinal()) {
					jemaat.setStatusKeluarga(StatusKeluarga.KEPALA_KELUARGA);
				}
				else if(Koneksi.rs.getInt("status_keluarga") == StatusKeluarga.ANGGOTA_KELUARGA.ordinal()) {
					jemaat.setStatusKeluarga(StatusKeluarga.ANGGOTA_KELUARGA);
				}
				listDataKeluarga.add(jemaat);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listDataKeluarga;
	}
	
	@POST
	@Path("jemaat/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postDataFilm(@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
					}
				} else {
					stringBuilder.append("");
				}
			} catch (IOException ex) {
			ex.printStackTrace();
			} finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException ex){
						ex.printStackTrace();
					}
				}
			}
		
		body = stringBuilder.toString();
		System.out.println("Body : "+body);
		String [] data = body.split("\\#",-1);		
		String sqlsave = "INSERT INTO jemaat values(null ,?,?,?,?,?,?,?,?,now(),now())";
        PreparedStatement ps;
		try {
			ps = Koneksi.getConnection().prepareStatement(sqlsave);
			ps.setString(1, data[0]);
			ps.setString(2, data[1]);
			ps.setString(3, data[2]);
			ps.setString(4, data[3]);
			ps.setString(5, data[4]);
			ps.setString(6, data[5]);
			ps.setString(7, data[6]);
			ps.setString(8, data[7]);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(body).build();
	}
	
	@POST
	@Path("kepalakeluarga/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postDataKepalaKeluarga(@Context HttpServletRequest request, @Context HttpServletResponse response) {
				
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
		System.out.println("Body :"+body);
		String[] data = body.split("\\#",-1);
		String sqlKepalaKeluarga = "INSERT INTO kepalaKeluarga values(null, ?,?)";
		
		try {
			Koneksi.ps = Koneksi.getConnection().prepareStatement(sqlKepalaKeluarga);
			Koneksi.ps.setString(1, data[0]);
			Koneksi.ps.setString(2, data[1]);
			Koneksi.ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.status(200).entity(body).build();
	}
}