package com.electrogrid.billing.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.billing.model.Bill;

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
}
