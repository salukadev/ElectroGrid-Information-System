package com.electrogrid.supplyconnection.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.supplyconnection.model.SupplyConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/supplyconnection")
public class SupplyConnectionService {

	@POST
	@Path("/newconnection")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createSupplyConnecton(String supplyJSON) {
		
		SupplyConnection supplycon = new SupplyConnection();
		//Convert the input string to a JSON object
		JsonObject jsonObject = new JsonParser().parse(supplyJSON).getAsJsonObject();
		
		supplycon.setAccNo(jsonObject.get("accNo").getAsInt());
		supplycon.setName(jsonObject.get("name").getAsString());
		supplycon.setAddress(jsonObject.get("address").getAsString());
		supplycon.setArea(jsonObject.get("area").getAsString());
		supplycon.setType(jsonObject.get("type").getAsString());
		supplycon.setConnection_status(jsonObject.get("status").getAsString());
		
		
		return supplycon.createConnection();
	}
}
