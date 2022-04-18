package com.electrogrid.payment.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.payment.model.Payment;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



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
	public String test() throws JsonParseException, JsonMappingException, IOException
	 {
		Payment pay = new Payment();
		String json = "{ \"bill\":\"999\", \"user\":\"189\", \"pay_type\":\"master\",\"amount\":\"120.00\" }";
		// ObjectMapper instantiation
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Deserialization into the `Payment` class
		Payment payment = objectMapper.readValue(json, Payment.class);
		
		//pay.insertItem(101, 01, "Visa", 10.00f);
		System.out.println(payment.getAmount());
		return "hello";
	 }
} 
