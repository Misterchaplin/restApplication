package net.models;

public class ActifUser {
	@Override
	public String toString() {
		return "connectedUser [connected=" + connected + ", token=" + token + "]";
	}
	private Boolean connected;
	private String token;
	
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
