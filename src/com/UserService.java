package com;

import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService{
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems(){
		return "Hello";
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("name") String name){
		
		User user = new User();
		String output = user.insertUser(name);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData){
		User user = new User();
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		String id = itemObject.get("id").getAsString();
		String name = itemObject.get("name").getAsString();
		String output = user.updateUser(id, name);
		return output;
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String readAllItems(){
		User user = new User();
		String output = user.readAllUsers();
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String userData){
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	 User user = new User();
	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = user.deleteUser(id);
	return output;
	}
	
}



