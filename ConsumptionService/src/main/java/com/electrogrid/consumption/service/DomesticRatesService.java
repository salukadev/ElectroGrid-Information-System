package com.electrogrid.consumption.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.consumption.model.DomesticRates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
