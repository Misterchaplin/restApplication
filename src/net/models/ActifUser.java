package net.models;

public class ActifUser {
	@Override
	public String toString() {
		return "connectedUser [connected=" + connected + ", token=" + token + "]";
	}
	private String connected;
	private String token;
	
	public String getconnected() {
		return connected;
	}
	public void setconnected(String connected) {
		this.connected = connected;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
