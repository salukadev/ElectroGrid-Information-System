package com.electrogrid.supplyconnection.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.supplyconnection.model.SupplyConnection;
import com.google.gson.Gson;
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
	
	
	@GET
	@Path("/allconnections")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllConnections() throws IOException
	{
		SupplyConnection sup = new SupplyConnection();
		List<SupplyConnection> connectionList = sup.viewAllConnections(); //Fetch data
		
		//Convert to JSON
		String response = new Gson().toJson(connectionList);
		
		System.out.println(response);
		return response;
	}
	
	@DELETE
	@Path("/account/{accNo}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteByAccNo(@PathParam("accNo") String accNo) 
	{
		Integer accNoInt = Integer.parseInt(accNo); //get numeric id from uri parameter
		SupplyConnection sup = new SupplyConnection();
		String response = sup.deleteConnection(accNoInt); //execute command
		System.out.println(response);
		return response;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConnection(String supplyJSON){
		SupplyConnection sup = new SupplyConnection();
		//Convert the input string to a JSON object
		JsonObject jsonObject = new JsonParser().parse(supplyJSON).getAsJsonObject();
		//Read the values from the JSON object
		int accNo = jsonObject.get("accNo").getAsInt();
		String name = jsonObject.get("name").getAsString();
		String address = jsonObject.get("address").getAsString();
		String area = jsonObject.get("area").getAsString();
		String type = jsonObject.get("type").getAsString();
		String status = jsonObject.get("status").getAsString();
		String output = sup.updateConnection(accNo, name, address, area, type, status);
		return output;
	}
}
