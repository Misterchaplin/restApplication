package net.models;

public class ReponseUtilisateur {
	private Integer reponse_id;
	private Integer realisation_id;
	
	public Integer getReponse_id() {
		return reponse_id;
	}
	public void setReponse_id(Integer reponse_id) {
		this.reponse_id = reponse_id;
	}
	public Integer getRealisation_id() {
		return realisation_id;
	}
	public void setRealisation_id(Integer realisation_id) {
		this.realisation_id = realisation_id;
	}
	
	@Override
	public String toString() {
		return "ReponseUtilisateur [reponse_id=" + reponse_id
				+ ", realisation_id=" + realisation_id + "]";
	}
	
	
}
