package com.electrogrid.billing.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.electrogrid.billing.model.Bill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//For Inter-Microservice communications


//updates billing details after a payment(Called by payment microservice)
@Path("/intercom")
public class InterComService {
	@POST
	@Path("/billpay")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePayInfo(String data) throws JsonProcessingException
	 {
		//parse json into JsonObject
		JsonObject dataObj = new JsonParser().parse(data).getAsJsonObject();
		int id = dataObj.get("billId").getAsInt();
		float amount = dataObj.get("amount").getAsFloat();
				
			
		Bill bill = new Bill();
		bill.updateBillPayment(id, amount);  //update payment balances
		return "Updated Bill!";
	 }
	

}
