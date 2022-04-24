package com.electrogrid.billing.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.billing.model.Bill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	
}
