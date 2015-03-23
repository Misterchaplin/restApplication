package net.gson;

import net.models.ActifUser;
import net.models.Utilisateur;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGson {
	private Gson gson;
	
	public TestGson(){
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
		gson= builder.create();
	}
	
	public String objectToJson(Object object){
		return gson.toJson(object);
	}
	
	public Utilisateur jsonToUser(String jsonString){
		return gson.fromJson(jsonString, Utilisateur.class);
	}
	
	public ActifUser jsonToActif(String jsonString){
		return gson.fromJson(jsonString, ActifUser.class);
	}
	
	public static void main(String args[]){
		TestGson test=new TestGson();
		Utilisateur user=new Utilisateur();
		user.setLogin("mymail@mail.fr");
		user.setPassword("John");
		String str=test.objectToJson(user);
		System.out.println(str);
		
		String json="{id:5;name:'Jim',dateCreation:'17/03/2015'}";
		Utilisateur user2=test.jsonToUser(json);
		System.out.println(user2);
	}
}
