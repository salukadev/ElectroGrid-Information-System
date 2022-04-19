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
	@Produces(MediaType.TEXT_PLAIN)
	public String consumptionHistoryAll(){
		Consumption consumption = new Consumption();
		String output = consumption.consumptionHistoryAll();
		return output;
	}
	

}
