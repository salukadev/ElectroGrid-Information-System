package com.electrogrid.consumption.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.consumption.model.Consumption;
import com.electrogrid.consumption.model.DomesticRates;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/domestic")
public class DomesticRatesService {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	 {
		
		return "Domestic Rates page!";
	 }

	@GET
	@Path("/current")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCurrentRate() throws JsonProcessingException  
	{
		DomesticRates domRates = new DomesticRates();
		String output = domRates.currentRate();
		System.out.println(output);
		
		if(output==null) {
			return "No rates available";
		}
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(domRates);
				
		System.out.println(response);
		return response;
	}
	
	@POST
	@Path("/newrate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String makePayment(String ratejsn) throws JsonParseException, JsonMappingException, IOException
	 {
				
		// ObjectMapper instantiation
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		DomesticRates domesticrates = objectMapper.readValue(ratejsn, DomesticRates.class);
		domesticrates.insertRate();
		String response = objectMapper.writeValueAsString(domesticrates);
		System.out.println(response);
		return response;
	 }
	
	
	@POST
	@Path("/deleterate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String Delete(@PathParam("id") String id)
	 {
				
		
		Integer rateID = Integer.parseInt(id);
		DomesticRates rate = new DomesticRates();
		
		String output =rate.Delete(rateID);

		return output;
	 }
}
