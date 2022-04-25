package com.electrogrid.payment.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.electrogrid.payment.model.RecurringPay;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/rec")
public class RecurringPayService {
	
	@POST
	@Path("/record")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createRecPayment(String payJSON) throws JsonParseException, JsonMappingException, IOException
	 {
		
		// ObjectMapper instantiation
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Deserialization into the the object
		RecurringPay record = objectMapper.readValue(payJSON, RecurringPay.class);
		
		//Save payment object to DB
		record.save();
		
		//echo the inserted data
		String resp = objectMapper.writeValueAsString(record);
		return resp;
	 }
	
	
	
	@PUT
	@Path("/record")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRecPayment(String data) throws JsonParseException, JsonMappingException, IOException, ParseException
	 {
			
		//parse json into JsonObject
		JsonObject dataObj = new JsonParser().parse(data).getAsJsonObject();
		int id = dataObj.get("id").getAsInt();
		float amount = dataObj.get("amount").getAsFloat();
		String dateStr = dataObj.get("until").getAsString();
		
		//DD-MM-YYYY
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse(dateStr);
		java.sql.Date sqlUntilDate = new java.sql.Date(date.getTime());  
		
		RecurringPay recPay = new RecurringPay();
		RecurringPay rtnPay = recPay.updateRecPay(id,amount,sqlUntilDate); //execute update
		
		if(rtnPay!=null) {
			System.out.println("Updated successfully");
			
			//Convert to JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writeValueAsString(rtnPay);
			
			//return with http status code
			return Response.status(Response.Status.OK).entity(response).build();
		}else {
			System.out.println("Error updating!");
			return Response.status(Response.Status.BAD_REQUEST).entity("Operation Failed!").build();
		}
		
	 }
	
	@GET
	@Path("/record/{accNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLastTransaction(@PathParam("accNo") String accno) throws JsonProcessingException
	{
		Integer accNo = Integer.parseInt(accno); //get numeric id from uri parameter
		
		RecurringPay recPay = new RecurringPay().getRecPayByAcc(accNo); //read from db
		
		//Convert to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(recPay);
		
		if(recPay!= null) {
			System.out.println(response);
			return response;
		}else {
			return "Invalid request!";
		}
		
	}
	
	@DELETE
	@Path("/record/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteById(@PathParam("id") String id) 
	{
		Integer idInt = Integer.parseInt(id); //get numeric id from uri parameter
		RecurringPay pay = new RecurringPay();
		String response = pay.cancelById(idInt); //execute command
		System.out.println(response);
		return response;
	}
	
}
