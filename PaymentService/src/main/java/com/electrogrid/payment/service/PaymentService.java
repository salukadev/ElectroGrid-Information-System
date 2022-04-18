package com.electrogrid.payment.service;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.payment.model.Payment;


@Path("/payment")
public class PaymentService
{
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	 {
		
		return "Hello world!";
	 }
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test()
	 {
		Payment pay = new Payment();
		pay.insertItem(101, 01, "Visa", 10.00f);
		return "Successfully entered";
	 }
} 
