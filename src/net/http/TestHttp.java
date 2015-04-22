package net.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import net.models.Domaine;
import net.models.Groupe;
import net.models.Utilisateur;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class TestHttp {
	private Gson gson;
	public Gson getGson() {
		return gson;
	}

	private HttpClient httpClient;
	
	public TestHttp(){
		httpClient=HttpClients.createDefault();
		gson=new GsonBuilder().create();
	}
	
	public String post(String url, Object object) throws ClientProtocolException, IOException{
		String result;
		HttpPost postRequest=new HttpPost(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String jsonStr=gson.toJson(object);
		StringEntity entity=new StringEntity(jsonStr, "utf-8");
		postRequest.setEntity(entity);
		result = httpClient.execute(postRequest, responseHandler);
		return result;
	}
	
	public String put(String url, Object object) throws ClientProtocolException, IOException{
		String result;
		HttpPut putRequest=new HttpPut(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String jsonStr=gson.toJson(object);
		StringEntity entity=new StringEntity(jsonStr, "utf-8");
		putRequest.setEntity(entity);
		result = httpClient.execute(putRequest, responseHandler);
		return result;
	}
	
	public String get(String url) throws ClientProtocolException, IOException{
		String result="";
		HttpGet getRequest = new HttpGet(url);
		ResponseHandler<String> responseHandler=new BasicResponseHandler();
		result=httpClient.execute(getRequest,responseHandler);
		return result;
	}

	
	public String delete(String id) throws ClientProtocolException, IOException{
		String result="";
		HttpDelete deleteRequest = new HttpDelete(id);
		ResponseHandler<String> responseHandler=new BasicResponseHandler();
		result=httpClient.execute(deleteRequest,responseHandler);
		return result;
	}
	
	public String postClassic(String urlToRead, Object o)
	        throws ClientProtocolException, IOException {
	    String result = "";
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    try {
	        HttpPost postRequest = new HttpPost(urlToRead);
	        postRequest.setHeader("content-type","application/x-www-form-urlencoded");
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	         
	        JsonElement elm = gson.toJsonTree(o);
	        JsonObject jsonObj = elm.getAsJsonObject();
	        for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
	            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry
	                    .getValue().getAsString()));
	        }
	        postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        result = httpClient.execute(postRequest, responseHandler);
	    } finally {
	        httpClient.close();
	        ;
	    }
	    return result;
	}
	
	public static void main(String args[]){
		TestHttp test=new TestHttp();
		String baseUrl="http://localhost/rest-qcm/";
		
		String reponse;
		try {
			/*Utilisateur user = new Utilisateur();
			user.setLogin("admin@admin.fr");
			user.setPassword("0000");
			System.out.println(test.postClassic(baseUrl+"utilisateurs/connect", user));*/
			/*reponse=test.get(baseUrl+"domaines");
			System.out.println(reponse);*/
			
			/*Domaine domaine=new Domaine();
			domaine.setLibelle("Informatique");
			System.out.println(test.post(baseUrl+"domaines", domaine));
			
			ArrayList<Domaine> domaines = test.gson.fromJson(reponse, new TypeToken<List<Domaine>>(){}.getType());
			System.out.println(domaines);*/
			
			Groupe dom=test.gson.fromJson(test.get(baseUrl+"groupes/44"), Groupe.class);
			System.out.println("Dom "+dom.getId()+" "+dom.getLibelle()+" "+dom.getCode());
			dom.setLibelle(dom.getLibelle()+"modifie");
			dom.setCode(dom.getCode()+"modifie");
			System.out.println("Dom "+dom.getId()+" "+dom.getLibelle()+" "+dom.getCode());
			System.out.println(test.put(baseUrl+"groupes/"+dom.getId(), dom));
			
			/*domaines = test.gson.fromJson(reponse, new TypeToken<List<Domaine>>(){}.getType());
			System.out.println(domaines);
			
			test.delete(baseUrl+"domaines/3");*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
