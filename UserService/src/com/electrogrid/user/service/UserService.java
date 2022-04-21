package com.electrogrid.user.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.user.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/user")
public class UserService {
	
	@POST
	@Path("/registerUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerNewUser(String userJSON) {
		
		User user = new User();
		//Convert the input string to a JSON object
		JsonObject jsonObject = new JsonParser().parse(userJSON).getAsJsonObject();
		
		user.setUsername(jsonObject.get("username").getAsString());
		user.setEmail(jsonObject.get("email").getAsString());
		user.setPassword(jsonObject.get("password").getAsString());
		user.setPhone_no(jsonObject.get("phone").getAsInt());
		user.setAccNo(jsonObject.get("accNo").getAsInt());
		user.setNic(jsonObject.get("nic").getAsString());
			
		return user.registerUser();
	}
	
	@PUT
	@Path("/editUser/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editUserbyId(@PathParam("id") String id, String userJSON) {
		User user = new User();

		JsonObject jsonObject = new JsonParser().parse(userJSON).getAsJsonObject();
		//Read the values from the JSON object
		int userId = Integer.parseInt(id);
		String username = jsonObject.get("username").getAsString();
		String email = jsonObject.get("email").getAsString();
		String password = jsonObject.get("password").getAsString();
		int accNo = jsonObject.get("accNo").getAsInt();
		int phone = jsonObject.get("phone").getAsInt();
		String nic = jsonObject.get("nic").getAsString();
		
		String response = user.editUser(userId,username,email,password,phone,accNo,nic);
		return response;
	}
	
	@DELETE
	@Path("/deleteUser/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteById(@PathParam("id") String id) {
		Integer accNoInt = Integer.parseInt(id); //get numeric id from uri parameter
		User user = new User();
		String response = user.deleteUser(accNoInt); //execute command
		System.out.println(response);
		return response;
		
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String userLogin(String userJSON) {
		
		User user = new User();
		JsonObject jsonObject = new JsonParser().parse(userJSON).getAsJsonObject();
		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();
		
		return user.userlogin(username, password);
	}

}
