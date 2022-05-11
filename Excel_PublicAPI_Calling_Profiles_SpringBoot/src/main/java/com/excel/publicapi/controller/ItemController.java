package com.excel.publicapi.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excel.publicapi.entities.Item;
import com.excel.publicapi.services.ItemService;



@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	ItemService service;
	
	@Value("${name}")
	String name;
	Logger log=LoggerFactory.getLogger(ItemController.class);
	
	@GetMapping(path="/get_items"
			//consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			//produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
			)
	public Map<Integer, Item>  getItemsFromDatabase() throws IOException, ParseException 
	{
		
		log.error("HII");
		System.out.println(name);
		Map<Integer, Item> items = service.getItems();
		service.createExcelFromDatabaseTable();
		service.ReadExcel();
		return items;
		
	}
	
	
	@GetMapping("/weatherData")
	public JSONArray hello_world() throws Exception
	{
		// Calling public outside API and converting it into JSON
		JSONArray dataobject;
		URL url=new URL("https://www.metaweather.com/api/location/search/?query=London");
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		
		int responseCode=con.getResponseCode();
		System.out.println("responseCode "+responseCode);
		if(responseCode!=200)
		{
			throw new RuntimeException("responseCode "+ responseCode);
		}
		else
		{
			StringBuilder data=new StringBuilder();
			Scanner sc=new Scanner(url.openStream());
			while(sc.hasNext())
			{
				data.append(sc.nextLine());
			}
			
			sc.close();
			
			JSONParser parse=new JSONParser();
			dataobject= (JSONArray) parse.parse(String.valueOf(data));
			
			JSONObject firstrow = (JSONObject) dataobject.get(0);
			System.out.println(firstrow);// get(0) will return first object of json array
			
			System.out.println(firstrow.get("location_type"));
			
		}
		
		
		return dataobject;
	}
	@GetMapping("/{itemid}")
	public Item getOneItem(@PathVariable("itemid") int  itemid) 
	//public Item getOneItem() 
	{
		System.out.println(itemid);
		System.out.println("HIIIIIII");
		Item item = service.getItem(itemid);
		
		return item;
	}
	
	@PostMapping("/save")
	public Item saveItem(@RequestBody Item item) 
	{
		Item saveItem = service.saveItem(item);
		return saveItem;
	}
	
	@PutMapping("/updateItemById/{id}")
	public Item updateItemByObject(@PathVariable("id") int id,
				@RequestBody Item item) 
	{
		
	/*		This is the best approach to perform Put Mapping
			two input parameter , one is It and another one is Item object
			and then follow below steps
			 Employee employee = employeeRepository.findById(employeeId)
				        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
			employee.setEmailId(employeeDetails.getEmailId());
			employee.setLastName(employeeDetails.getLastName());
			employee.setFirstName(employeeDetails.getFirstName());
		    Employee updatedEmployee = employeeRepository.save(employee);*/
		
		Item saveItem = service.saveItem(item);

		return saveItem;
	
	}
	
	@PutMapping("/{id}")
	public Item updateItemById( int id,@RequestParam String name,@RequestParam(defaultValue = "1") int price) 
	{
		Item item=service.getItem(id);

		item.setName(name);
		item.setPrice(price);
		Item saveItem = service.saveItem(item);

		return saveItem;
		
	}
	
	
	
	
	
	
}
