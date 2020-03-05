package gpibph.model;

public class UserLogin {
	
	private Integer id_user;
	private String username;
	private String password;
	private String email;
	private String nomor_telepon;
	
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomor_telepon() {
		return nomor_telepon;
	}
	public void setNomor_telepon(String nomor_telepon) {
		this.nomor_telepon = nomor_telepon;
	}
}
