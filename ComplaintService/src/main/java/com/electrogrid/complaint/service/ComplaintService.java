package com.electrogrid.complaint.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.electrogrid.complaint.model.Complaint;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/complaint")
public class ComplaintService
{
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	 {
		return "Service is active!";
	 }

	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String makeComplaint(String compJSON) throws JsonParseException, JsonMappingException, IOException
	 {
		// ObjectMapper instantiation
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Deserialization into the `Complaint` class
		Complaint complaint = objectMapper.readValue(compJSON, Complaint.class);
		
		//Save payment object to DB
		complaint.save();
		String response = objectMapper.writeValueAsString(complaint);
		System.out.println(response);
		return response;
	 }
	
	@GET
	@Path("/recent/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecentComplaints() throws IOException
	{
		Complaint comp = new Complaint();
		List<Complaint> compList = comp.fetchRecentComplaints(); //Fetch data
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(compList);
		
		System.out.println(response);
		return response;
	}
	
	@GET
	@Path("/view/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(@PathParam("id") String id) throws JsonProcessingException 
	{
		Integer idInt = Integer.parseInt(id); //get numeric id from uri parameter
		Complaint comp = new Complaint();
		String output = comp.fetchById(idInt); //execute command
		System.out.println(output);
		
		//if entry is not availabe in db
		if(output==null) {
			return "Incorrect id!";
		}
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(comp);
				
		System.out.println(response);
		return response;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteById(@PathParam("id") String id) 
	{
		Integer idInt = Integer.parseInt(id); //get numeric id from uri parameter
		Complaint comp = new Complaint();
		String response = comp.cancelById(idInt); //execute command
		System.out.println(response);
		return response;
	}
	
	@PUT
	@Path("/compUpdate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCompInfo(String data) throws JsonProcessingException
	 {
		//parse json into JsonObject
		JsonObject dataObj = new JsonParser().parse(data).getAsJsonObject();
		int id = dataObj.get("compId").getAsInt();
		String desc = dataObj.get("description").getAsString();
				
			
		Complaint comp = new Complaint();
		Complaint output = comp.updateItem(id, desc);  //update complaint
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(output);
				
		return response ;
	 }
	
}