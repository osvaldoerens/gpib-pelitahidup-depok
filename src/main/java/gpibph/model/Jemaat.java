package gpibph.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import gpibph.daoImpl.JsonDateSerializer;

public class Jemaat {

	private Integer id;
	private String no_kk;
	private String nama;
	private Date tanggal_lahir;
	private String tempat_tinggal;
	private String alamat;
	private String pelkat;
	private String sektor;
	private StatusKeluarga statusKeluarga;
	private Date created_date;
	private Date updated_date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@JsonSerialize(using=JsonDateSerializer.class) // Format tanggal Json
	public Date getTanggal_lahir() {
		return tanggal_lahir;
	}
	public void setTanggal_lahir(Date tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}
	public String getTempat_tinggal() {
		return tempat_tinggal;
	}
	public void setTempat_tinggal(String tempat_tinggal) {
		this.tempat_tinggal = tempat_tinggal;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getPelkat() {
		return pelkat;
	}
	public void setPelkat(String pelkat) {
		this.pelkat = pelkat;
	}
	public String getSektor() {
		return sektor;
	}
	public void setSektor(String sektor) {
		this.sektor = sektor;
	}
	public StatusKeluarga getStatusKeluarga() {
		return statusKeluarga;
	}
	public void setStatusKeluarga(StatusKeluarga statusKeluarga) {
		this.statusKeluarga = statusKeluarga;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
}
