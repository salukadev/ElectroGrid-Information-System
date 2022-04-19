package com.electrogrid.consumption.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.electrogrid.consumption.model.Consumption;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/consumption")
public class ConsumptionService {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	 {
		
		return "Hello world!";
	 }
	
	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public String consumptionHistoryAll()throws IOException{
	{
		Consumption consumptions = new Consumption();
		List<Consumption> consumptionList = consumptions.consumptionHistoryAll(); //Fetch data
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(consumptionList);
		
		System.out.println(response);
		return response;
	}
	}

}
	
