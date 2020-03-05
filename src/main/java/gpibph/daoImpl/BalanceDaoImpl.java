package gpibph.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gpibph.app.Koneksi;
import gpibph.model.Balance;

@Path("/balance/")
public class BalanceDaoImpl {
	
	@GET
	@Path("addBalance/{partnerid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Balance> getDataBalance(@PathParam("partnerid") String partnerid){
		List<Balance> listDataBalance = new ArrayList<Balance>();
		String selectBalance= "";
		if(partnerid.equalsIgnoreCase("all")) {
			selectBalance = "Select partnerid, deposit, limit from balance";
		} else {
			selectBalance = "SELECT partnerid, deposit, limit from balance where partnerid = ?";
		}
		try {
			Koneksi.ps = Koneksi.getConnection().prepareStatement(selectBalance);
			if(!partnerid.equalsIgnoreCase("all")) {
				Koneksi.ps.setString(1, partnerid);
			}
			Koneksi.rs = Koneksi.ps.executeQuery();
			while (Koneksi.rs.next()) {
				Balance balance = new Balance();
				balance.setPartnerid(Koneksi.rs.getString("partnerid"));
				balance.setDeposit(Koneksi.rs.getInt("deposit"));
				balance.setLimit(Koneksi.rs.getInt("limit"));
				
				listDataBalance.add(balance);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listDataBalance;		
	}
}