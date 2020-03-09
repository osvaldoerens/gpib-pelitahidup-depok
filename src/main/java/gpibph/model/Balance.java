package gpibph.model;

public class Balance {
	
	private String partnerid;
	private Integer deposit;
	private Integer limit;
	private String status;
	private String last_update;
	private String last_depositby;
	
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public Integer getDeposit() {
		return deposit;
	}
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLast_update() {
		return last_update;
	}
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}
	public String getLast_depositby() {
		return last_depositby;
	}
	public void setLast_depositby(String last_depositby) {
		this.last_depositby = last_depositby;
	}	
	
	
	
}
