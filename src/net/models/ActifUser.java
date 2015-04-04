package net.models;

public class ActifUser {
	
	private Boolean connected;
	private String token;
	private Integer who;
	
	@Override
	public String toString() {
		return "ActifUser [connected=" + connected + ", token=" + token
				+ ", who=" + who + "]";
	}
	
	public Integer getWho() {
		return who;
	}
	
	public void setWho(Integer who) {
		this.who = who;
	}
	public Boolean getconnected() {
		return connected;
	}
	public void setconnected(Boolean connected) {
		this.connected = connected;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
