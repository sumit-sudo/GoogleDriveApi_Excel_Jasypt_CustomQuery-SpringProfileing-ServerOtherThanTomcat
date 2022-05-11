package com.excel.publicapi.services;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.publicapi.dao.ItemDaoRepo;
import com.excel.publicapi.entities.Item;
import com.excel.publicapi.entities.NewItems;





@Service
public class ItemService {

	@Autowired
	ItemDaoRepo repo;
	int cellnum;
	int rownum=1;
	Row row;
	Map<Integer, Item> map=new HashMap<>();

	public Map<Integer, Item> getItems() {
		List<Item> items= (List<Item>) repo.findAll();
		
		NewItems newItems=new NewItems();
		newItems.setName(null);
		newItems.setIsRequired(null);
		System.out.println(newItems.getName());
//		List<String> col_name=repo.getColumnName();
//		col_name.forEach(x->System.out.println(x));
		
		for(Item item:items)
		{
			item.setValue(item.getQuantity()*item.getPrice());
			//System.out.println(item.getValue());
		}
		
		items.forEach(item->map.put(item.getId(),item));
		
		return map;
	}

	
	public Item getItem(int id) {
		
		
		return repo.findById(id).orElse(null);
	}


	public Item saveItem(Item item){
		
			
		
		DateTimeFormatter format=null;
		String parsedate=null;
		System.out.println("HIi"+item.getDate().length());
		if(item.getDate().length()==11)
		{
			System.out.println("HIIIIIIIIIIII");
			parsedate=item.getDate();
		}
		else
		{
			LocalDate date=LocalDate.parse(item.getDate());
			 format=DateTimeFormatter.ofPattern("yyyy-MMM-dd");
			  parsedate = date.format(format);
		}
		
		
		item.setDate(parsedate);
		return repo.save(item);
		

		
	}
	public void createExcelFromDatabaseTable()
	{
		// Read a table from Database and insert into excel
		//Blank workbook
		XSSFWorkbook workbook=new XSSFWorkbook();
		// Creating blank excel sheet
		XSSFSheet sheet=workbook.createSheet("item");
		
		// Fetching all required data to display in the excel sheet
		List<String> col_name=repo.getColumnName();
		List<Object [] > list=repo.FindAllItem();
		int n=repo.getTotalNumRow();
		
		cellnum=0;
		row=sheet.createRow(rownum++);
		// Setting Heading
		for(String head:col_name)
			{
				Cell cell=row.createCell(cellnum);
				cell.setCellValue(head);
				cellnum++;
			}
		for( int i=0;i<n;i++)
			{
			cellnum=0;
			row=sheet.createRow(rownum++);
			Object arr[]=list.get(i);
			//System.out.println("arr "+arr);
				for(Object val:arr)
				{
					//System.out.println("string instance or not  "+val.getClass().getSimpleName() );
					try {
					Cell cell=row.createCell(cellnum);
					if (val instanceof String)
	                    cell.setCellValue((String)val);
	 
	                else if (val instanceof Integer)
	                    cell.setCellValue((Integer)val);
	                else if (val instanceof Timestamp)
	                    cell.setCellValue((Timestamp)val);
					cellnum++;
					}catch(Exception e) {
					
					}
					
				}
			
			
			}
		

		try
		{
			FileOutputStream out=new FileOutputStream(new File("C:\\Users\\Sumit\\Desktop\\Google_API\\Test.xlsx"));
			workbook.write(out);
			out.close();
			
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		col_name.forEach(name->
		{
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(name);
		
		});	
	}
	public void ReadExcel()
	{
		try
		{
			FileInputStream fis=new FileInputStream(new File("C:\\Users\\Sumit\\Desktop\\Google_API\\Employee.xlsx"));
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
			// WE can access sheet with index value as well by getSheetAt(0);, for multiple sheet
			XSSFSheet sheet=workbook.getSheet("data");
			Iterator<Row> rows=sheet.iterator();
			
			
			//Row headerRow= rows.next();
			HashMap<String,Integer> map=new HashMap<>();
			while(rows.hasNext())
			{
				Row row=rows.next();
				 // For each row, iterate through all the
                // columns
				Iterator<Cell> cells=row.cellIterator();
				StringBuilder data=new StringBuilder();
				data.append("(");
				while(cells.hasNext()) {
					Cell cell=cells.next();
					switch(cell.getCellTypeEnum())
					{
					case NUMERIC:
						double numericCellValue = cell.getNumericCellValue();
						//System.out.println(numericCellValue);
						data.append(numericCellValue+",");
						break;
			
					case STRING:
						String stringCellValue = cell.getStringCellValue();
						//System.out.println(stringCellValue);
						data.append(stringCellValue+",");
						break;
						
					}

				}
				data.deleteCharAt(data.length()-1);
				data.append(")");
				System.out.println(data);
				//repo.insertEmployee( data);
			}
			
			fis.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
	public void uploadFiles()
	{
		
	}
	

}
