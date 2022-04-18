package com.electrogrid.payment.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.payment.model.Payment;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



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
	
	//
	@POST
	@Path("/pay")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String makePayment(String payJSON) throws JsonParseException, JsonMappingException, IOException
	 {
		//String json = "{ \"bill\":\"999\", \"user\":\"189\", \"pay_type\":\"master\",\"amount\":\"120.00\" }";
		//pay.insertItem(101, 01, "Visa", 10.00f);
		
		// ObjectMapper instantiation
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Deserialization into the `Payment` class
		Payment payment = objectMapper.readValue(payJSON, Payment.class);
		
		//Save payment object to DB
		payment.save();
		String response = objectMapper.writeValueAsString(payment);
		System.out.println(response);
		return response;
	 }
	
	@GET
	@Path("/pay")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLastTransaction() throws JsonProcessingException
	{
		Payment pay = new Payment();
		pay.getLastTransaction(); //Fetch data from DB
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(pay);
		
		System.out.println(response);
		return response;
	}
	
	@GET
	@Path("/recent")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecentTransactions() throws IOException
	{
		Payment pay = new Payment();
		List<Payment> payList = pay.fetchRecentTransactions(); //Fetch data
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(payList);
		
		System.out.println(response);
		return response;
	}
	
} 
