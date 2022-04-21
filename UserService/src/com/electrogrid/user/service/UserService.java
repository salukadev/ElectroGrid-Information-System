package com.electrogrid.user.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

}
