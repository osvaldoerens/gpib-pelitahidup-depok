package gpibph.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import gpibph.daoImpl.JsonDateSerializer;

public class Atestasi {

	private Integer id_atestasi;
	private String no_kk;
	private String nama;
	private String sektor;
	private Date tanggal_atestasi;
	private String gereja_tujuan;
	private String alamat_gereja;
	private KepalaKeluarga kepalaKeluarga;
	
	
	public KepalaKeluarga getKepalaKeluarga() {
		return kepalaKeluarga;
	}
	public void setKepalaKeluarga(KepalaKeluarga kepalaKeluarga) {
		this.kepalaKeluarga = kepalaKeluarga;
	}
	public Integer getId_atestasi() {
		return id_atestasi;
	}
	public void setId_atestasi(Integer id_atestasi) {
		this.id_atestasi = id_atestasi;
	}
	public String getNo_kk() {
		return no_kk;
	}
	public void setNo_kk(String no_kk) {
		this.no_kk = no_kk;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getSektor() {
		return sektor;
	}
	public void setSektor(String sektor) {
		this.sektor = sektor;
	}
	@JsonSerialize(using=JsonDateSerializer.class) // Format tanggal Json
	public Date getTanggal_atestasi() {
		return tanggal_atestasi;
	}
	public void setTanggal_atestasi(Date tanggal_atestasi) {
		this.tanggal_atestasi = tanggal_atestasi;
	}
	public String getGereja_tujuan() {
		return gereja_tujuan;
	}
	public void setGereja_tujuan(String gereja_tujuan) {
		this.gereja_tujuan = gereja_tujuan;
	}
	public String getAlamat_gereja() {
		return alamat_gereja;
	}
	public void setAlamat_gereja(String alamat_gereja) {
		this.alamat_gereja = alamat_gereja;
	}
	
	
}
