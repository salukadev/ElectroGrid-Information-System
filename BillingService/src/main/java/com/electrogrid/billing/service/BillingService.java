package com.electrogrid.billing.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.billing.model.Bill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/billing")
public class BillingService {
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	 {
		return "Service is active!";
	 }
	
	@GET
	@Path("/new")
	@Produces(MediaType.TEXT_PLAIN)
	public String newBill()
	 {
		Bill bill = new Bill();
		String output = bill.insertRecord(199, 320, 4000.35f);
		return output;
		
	 }
	
	@GET
	@Path("/bill/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String newBill(@PathParam("id") String id) throws JsonProcessingException
	 {
		Bill bill = new Bill();
		Bill obj = bill.getBillById(Integer.parseInt(id));
		
		if(obj==null) {
			return "Invalid id!";
		}
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(obj);
				
		return response;
		
	 }
	
	@POST
	@Path("/history")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getHistory(String data) throws JsonProcessingException
	 {
		//parse json into JsonObject
		JsonObject dataObj = new JsonParser().parse(data).getAsJsonObject();
		int id = dataObj.get("accNo").getAsInt();
		int length = dataObj.get("length").getAsInt();
				
			
		Bill bill = new Bill();
		List<Bill> objs = bill.getHistory(id, length);
		
		if(objs==null) {
			return "Invalid account number!!";
		}
		
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(objs);
				
		return response;
		
	 }
	
	@PUT
	@Path("/bill")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePayInfo(String data) throws JsonProcessingException
	 {
		//parse json into JsonObject
		JsonObject dataObj = new JsonParser().parse(data).getAsJsonObject();
		int id = dataObj.get("billId").getAsInt();
		float amount = dataObj.get("amount").getAsFloat();
				
			
		Bill bill = new Bill();
		bill.updateBillPayment(id, amount);  //update payment balances
		
		
	 }
	
}
