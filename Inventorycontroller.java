package com.wcs.app.main.controller;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.wcs.app.main.model.Block;
import com.wcs.app.main.model.Category;
import com.wcs.app.main.model.District;
import com.wcs.app.main.model.GstTaxType;
import com.wcs.app.main.model.ProductStockMaintain;
import com.wcs.app.main.model.Products;
import com.wcs.app.main.model.State;
import com.wcs.app.main.model.Supplier;
import com.wcs.app.main.model.SystemUsers;
import com.wcs.app.main.model.TempBlock;
import com.wcs.app.main.model.TempCategory;
import com.wcs.app.main.model.TempGstTaxType;
import com.wcs.app.main.model.TempProduct;
import com.wcs.app.main.model.Tempsupplier;
import com.wcs.app.main.model.VehicleModel;
import com.wcs.app.main.model.VehicleType;
import com.wcs.app.main.serviceImp.Serviceimpl;
import com.wcs.app.main.utility.ExcelGenerator;
//import com.wcs.app.main.utility.GeneratePdfReport;
import com.wcs.app.main.utility.GeneratePdfReport;


@RestController
@CrossOrigin("*")
@RequestMapping("/inventorycontroller")
public class Inventorycontroller {

	@Autowired
	private Serviceimpl service;
	
	@GetMapping(value="/getStates", produces = "application/json")
	@ResponseBody
	public String getStates() throws JsonProcessingException
	{
		System.out.println("In getStates");
		List<State>  statelist=service.getStates();;
		ObjectMapper objmap=new ObjectMapper();
		String json=objmap.writeValueAsString(statelist);
		return json;
	}
	
	@GetMapping(value="/getCities", produces = "application/json")
	@ResponseBody
	public String getDistrict(@RequestParam Integer id) throws JsonProcessingException
	{
		System.out.println(id);
		List<District>  dlist=service.getDistricts(id);
		ObjectMapper objmap= new ObjectMapper();
		String json=objmap.writeValueAsString(dlist);
		return json;
	}

	@RequestMapping(value="/supplierdetails", consumes= "application/json",method=RequestMethod.POST)
	public String addSupplier(@RequestBody Tempsupplier tsupplier)
	{
		System.out.println("In addsupplier");
		String name=tsupplier.getSname();
		String compname=tsupplier.getCompname();
		String address=tsupplier.getAddress();
		String email=tsupplier.getEmail();
		String mobile=tsupplier.getMobile();
		
		int sid=tsupplier.getStateid();
		State state=service.getStatebyid(sid);
		
		int did=tsupplier.getDistrictid();
		District district=service.getDistrictbyid(did);
	    
		Supplier s=new Supplier();
		s.setName(name);
		s.setCompany_name(compname);
		s.setAddress(address);
		s.setEmail(email);
		s.setMobile(mobile);
		s.setState(state);
		s.setDistrict(district);
		String msg=service.addSupplier(s);
		System.out.println(msg);
		return msg;
	}
	

 	@RequestMapping(value="/getsupplier", produces="application/json")
 	@ResponseBody
 	public String viewSupplier() throws JsonProcessingException {
 		List <Supplier> list =service.viewSuppliergetall();
 		for(Supplier s:list)
 		{
 			System.out.println(s.getId());
 			System.out.println(s.getCompany_name());
 			
 		}
 		
 		
 		ObjectMapper objmap= new ObjectMapper();
 		String json=objmap.writeValueAsString(list);
 		return json;
 		
 		
 	}
 	
	@RequestMapping("/getSupplierList")
	public String getSupplierList() throws JsonProcessingException
	{
		List<Supplier> slist=service.getSupplierList();
		
		  for (Supplier s : slist)
		  {
			  System.out.println(s.getName());
		  }
		 
		ObjectMapper obj=new ObjectMapper();
		String json=obj.writeValueAsString(slist);
		return json;
	}

    @RequestMapping(value="/UpdateSupplierid")
	public Supplier updateById(@RequestParam int sid)
	{
		System.out.println("update"+sid);
		List<Supplier> lsup=service.viewSuppliergetall();
		Supplier s=new Supplier();
		for(Supplier so:lsup) {
			if(so.getId()==sid) {
				s=so;
			}
		}
		return s;
	}
    
	@RequestMapping(value="/Updatesupplierdetails", consumes= "application/json",method=RequestMethod.POST)
		public String updateSupplier(@RequestBody Tempsupplier tsupplier)
		{
			List<Supplier> suplist=service.viewSuppliergetall();
			Supplier upd=new Supplier();
			for(Supplier so:suplist) {
				if(tsupplier.getId()==so.getId()) {
					upd=so;
				}
			}
			System.out.println("In addsupplier");
     		String name=tsupplier.getSname();
			String compname=tsupplier.getCompname();
			String address=tsupplier.getAddress();
			String email=tsupplier.getEmail();
			String mobile=tsupplier.getMobile();
			int sid=tsupplier.getStateid();
			int did=tsupplier.getDistrictid();
			State state=service.getStatebyid(sid);
			District district=service.getDistrictbyid(did);
		    
		    
			//s.setId(id);
			upd.setName(name);
			upd.setCompany_name(compname);
			upd.setAddress(address);
			upd.setEmail(email);
			upd.setMobile(mobile);
			upd.setState(state);
			upd.setDistrict(district);
			String msg=service.addSupplier(upd);
			//System.out.println(msg);
			return "supplier update";	
		}

    
	@RequestMapping(value="/deleteSupplierbyid")
 	public String deletebyid(@RequestParam int sid)
 	{
 	  	System.out.println("delete"+sid);
 		service.deleteSupplierById(sid);
 		
 		return  "delete id";
 		
 	} 
	
     @RequestMapping("/getProductCategoryList")
	public String getProductCategoryList() throws JsonProcessingException
	{
		List<Category> pclist=service.getProductCategoryList();
		
		 for (Category c : pclist) 
		 {
			 System.out.println(c.getCategory()); 
	     }
		 
		ObjectMapper obj=new ObjectMapper();
		String json=obj.writeValueAsString(pclist);
		return json;
	}
     
     @RequestMapping(value="/categorydetails", consumes= "application/json",method=RequestMethod.POST)
 	public String addCategory(@RequestBody TempCategory tpc)
    	{
    		System.out.println("In add Category");
 		
 	        String category=tpc.getCatname();
 	    
 	        Category c = new Category();
 	        c.setCategory(category);
    		String msg = service.addCategoryDetails(c);
    		System.out.println(msg);
    		return msg;
    	}
     

   	@RequestMapping(value="/getcategory",produces="application/json")
      public String viewCategory() throws JsonProcessingException {
   		List<Category> list = service.getProductCategoryList();
   		for(Category c : list)
   		{
   			System.out.println(c.getId());
   			System.out.println(c.getCategory());
   		}
  		ObjectMapper objmap= new ObjectMapper(); 
  		String  json=objmap.writeValueAsString(list);
  		 
   		return json;
   	}

     @RequestMapping("/getGstTaxTypeList")
     public String getGstTaxTypeList() throws JsonProcessingException
     {
    	 List<GstTaxType> gstlist=service.getGstTaxTypeList();
		
		 for (GstTaxType gst : gstlist) 
		 {
			 System.out.println(gst.getGst_percent()); 
		 }
		 
    	 ObjectMapper obj=new ObjectMapper();
    	 String json=obj.writeValueAsString(gstlist);
    	 return json;
     }
     
     @RequestMapping(value="/gstdetails", consumes= "application/json",method=RequestMethod.POST)
 	public String addGst(@RequestBody TempGstTaxType tpgst)
    	{
    		
    		System.out.println("In add GST");
 		
 	    String gstname=tpgst.getTgst_name();
 	    String gstpercent=tpgst.getTgst_percent();
 		
    		GstTaxType gst=new GstTaxType();
    		gst.setGst_name(gstname);
    		gst.setGst_percent(gstpercent);
    		
    		 String msg = service.addGstTax(gst);
    		 System.out.println(msg);
    		 return msg;
    	}
     

    	@RequestMapping(value="/getGstTax",produces="application/json")
       public String viewGstTax() throws JsonProcessingException {
    		List<GstTaxType> list = service.getGstTaxTypeList();
    		for(GstTaxType gt : list)
    		{
    			System.out.println(gt.getGst_name());
    			System.out.println(gt.getGst_percent());
    		}
   		ObjectMapper objmap= new ObjectMapper(); 
   		String  json=objmap.writeValueAsString(list);
   		 
    		return json;
    	}
     
     @RequestMapping("/getBlockList")
     public String getBlockList() throws JsonProcessingException
     {
    	 List<Block> blist=service.getBlockList();
    	 
		
		  for (Block b : blist) 
		  {
			  System.out.println(b.getBlock_name());
		  }
		 
    	 ObjectMapper obj=new ObjectMapper();
    	 String json=obj.writeValueAsString(blist);
    	 return json;
     }
     
  	@RequestMapping(value="/blockdetails", consumes= "application/json",method=RequestMethod.POST)
  	public String addBlock(@RequestBody TempBlock tblock)
  	{
  		System.out.println("In add Block");
  		int bnum = tblock.getTblock_num();
  		String bname = tblock.getTblock_name();
  		String btitle = tblock.getTtitle();
  		 
  		Block b = new Block();
  		b.setBlock_num(bnum);
  		b.setBlock_name(bname);
         b.setTitle(btitle);
         
   		String msg=service.addBlock(b);
  		System.out.println(msg);
  		return msg;
  	}
  	
  	@RequestMapping(value="/getblock",produces="application/json")
     public String viewBlock() throws JsonProcessingException {
  		List<Block> list =service.getBlockList();
  		for(Block b : list)
  		{
  			System.out.println(b.getBlock_name());
  			System.out.println(b.getBlock_num());	
  		}
 		ObjectMapper objmap= new ObjectMapper(); 
 		String  json=objmap.writeValueAsString(list);
 		 
  		return json;
  	}

     
     @RequestMapping("/getVehicleModelList")
     public String getVehicleModelList() throws JsonProcessingException
     {
    	 List<VehicleModel> vmodellist=service.getVehicleModelList();
    	 ObjectMapper obj=new ObjectMapper();
    	 String json=obj.writeValueAsString(vmodellist);
    	 return json;
     }
     
     @RequestMapping("/getVehicleTypeList")
     public String getVehicleTypeList() throws JsonProcessingException
     {
    	 List<VehicleType> vtypelist=service.getVehicleTypeList();
    	 ObjectMapper obj=new ObjectMapper();
    	 String json=obj.writeValueAsString(vtypelist);
    	 return json;
     }
     @RequestMapping("/getSystemUserList")
     public String getSystemUserList() throws JsonProcessingException
     {
    	 List<SystemUsers> sysulist=service.getsystemUserlist();
    	 ObjectMapper obj=new ObjectMapper();
    	 String json=obj.writeValueAsString(sysulist);
    	 return json;
     }
     

  	@RequestMapping("/getAllProductsList")
  	public String getAllProductsList() throws JsonProcessingException
  	{
  		List<Products> pmlist=service.getAllProductsList();
  		ObjectMapper obj=new ObjectMapper();
  		String json=obj.writeValueAsString(pmlist);
  		return json;
  	}
     
     @RequestMapping(value="/addProductDetails",consumes= "application/json",method=RequestMethod.POST)
     public String addProductDetails(@RequestBody TempProduct tp)
     {
    	 System.out.println("In addProductDetails");
		
		  String procode=tp.getProduct_code();
		  System.out.println("Pro code- "+procode);
		 
    	 String proname=tp.getProduct_name();
    	 
    	 int supplierId=tp.getSupplierId();
    	 Supplier supplier=service.getSupplierById(supplierId);
    	 
    	 int categoryid=tp.getCategoryId();
    	 Category category=service.getProductCategoryById(categoryid);
    	  
    	 int gstid=tp.getGsttaxtypeId();
    	 GstTaxType gsttype=service.getGstTypeById(gstid);
    	 
    	 int blockid=tp.getBlockId();
    	 Block block=service.getBlockById(blockid);
    	 
    	 int vehiclemodelid=tp.getVehicleModelId();
    	 VehicleModel vehiclemodel=service.getVehicleModelById(vehiclemodelid);
    	 
    	 int vehicletypeid=tp.getVehicleTypeId();
    	 VehicleType vehicletype=service.getVehicleTypeById(vehicletypeid);

    	 int systemuid = tp.getSysuserid();
    	 SystemUsers sysuser=service.getSystemUserById(systemuid);
    	 
    	 int proquantity=tp.getProduct_quantity();
    	 String hsn=tp.getHsn_num();
    	 String propricewithgst=tp.getProduct_buying_price_with_gst();
    	 String prosellingprice=tp.getProduct_selling_price();
    	 String receivedate=tp.getReceiving_date();
    	 String updatedate=tp.getUpdate_date();
     	
    	  
    	 Products pro=new Products();
    	 pro.setProduct_code(procode);
    	 pro.setProduct_name(proname);
    	 pro.setSupplier(supplier);
    	 pro.setProduct_quantity(proquantity);
    	 pro.setCategory(category);
    	 pro.setHsn_num(hsn);
    	 pro.setGsttaxtype(gsttype);
    	 pro.setProduct_buying_price_with_gst(propricewithgst);
    	 pro.setProduct_selling_price(prosellingprice);
    	 pro.setReceiving_date(receivedate);
    	 pro.setBlock(block);
    	 pro.setVehicleModel(vehiclemodel);
    	 pro.setVehicleType(vehicletype);
    	 pro.setSystemUsers(sysuser);
    	 
    	 String msg=service.addProductDetails(pro);
    	 
    	 //Add ProductStockMaintain data
    	 
    	 ProductStockMaintain promaint=new ProductStockMaintain();
    	 
    	 System.out.println("Get GST percent "+gsttype.getGst_percent());
    	 String gstp=gsttype.getGst_percent();
    	 //String gstpp=""+gstp;
    	 Double gstPEr=Double.parseDouble(gstp);
    	 Double prosellprice=Double.parseDouble(prosellingprice);
         double prosellingwithgst=finallSellingPriceWithGST(prosellprice, gstPEr);
    	 String fp=""+prosellingwithgst;
    	 System.out.println(prosellingwithgst);
    	 promaint.setProduct_code(procode);
    	 promaint.setProduct_name(proname);
    	 promaint.setSupplier(supplier);
    	 promaint.setProduct_quantity(proquantity);
    	 promaint.setCategory(category);
    	 promaint.setHsn_num(hsn);
    	 promaint.setProduct_buying_price_with_gst(propricewithgst);
    	 promaint.setProduct_selling_price(prosellingprice);
    	 promaint.setGsttaxtype(gsttype);
    	 promaint.setPro_sell_price_with_gst(fp);
    	 promaint.setUpdated_date(updatedate);
    	 promaint.setBlock(block);
    	 promaint.setVehicleModel(vehiclemodel);
    	 promaint.setVehicleType(vehicletype);
    	 promaint.setSystemUsers(sysuser);
    	 
    	 List<ProductStockMaintain> promlist=service.ProductStockMaintaingetAll();
    	 if(promlist.isEmpty())
    	 {
    	 
    	 String msg2=service.addProductMaintain(promaint);
    	 return msg2;
    	 }
    	 
    	 else {
    		 for (ProductStockMaintain pm : promlist) {
				 String pmcode=pm.getProduct_code();
				 Category cat=pm.getCategory();
				 int pmcatid=cat.getId();
				 if(pmcode.equals(procode) && pmcatid==categoryid)
				 {
					 pm.setProduct_code(procode);
			    	 pm.setProduct_name(proname);
			    	 pm.setSupplier(supplier);
			    	 pm.setProduct_quantity(proquantity);
			    	 pm.setCategory(category);
			    	 pm.setHsn_num(hsn);
			    	 pm.setProduct_buying_price_with_gst(propricewithgst);
			    	 pm.setProduct_selling_price(prosellingprice);
			    	 pm.setGsttaxtype(gsttype);
			    	 pm.setPro_sell_price_with_gst(fp);
			    	 pm.setUpdated_date(updatedate);
			    	 pm.setBlock(block);
			    	 pm.setVehicleModel(vehiclemodel);
			    	 pm.setVehicleType(vehicletype);
			    	 pm.setSystemUsers(sysuser);
			    	 String msg1=service.addProductMaintain(pm);
			    	 System.out.println(msg1);
			    	 return msg1;
				 }
				 
			}
    		 
    		 ProductStockMaintain pm1=new ProductStockMaintain();
    		 pm1.setProduct_code(procode);
	    	 pm1.setProduct_name(proname);
	    	 pm1.setSupplier(supplier);
	    	 pm1.setProduct_quantity(proquantity);
	    	 pm1.setCategory(category);
	    	 pm1.setHsn_num(hsn);
	    	 pm1.setProduct_buying_price_with_gst(propricewithgst);
	    	 pm1.setProduct_selling_price(prosellingprice);
	    	 pm1.setGsttaxtype(gsttype);
	    	 pm1.setPro_sell_price_with_gst(fp);
	    	 pm1.setUpdated_date(updatedate);
	    	 pm1.setBlock(block);
	    	 pm1.setVehicleModel(vehiclemodel);
	    	 pm1.setVehicleType(vehicletype);
	    	 pm1.setSystemUsers(sysuser);
	    	 String msg1=service.addProductMaintain(pm1);
	    	 System.out.println(msg1);
	    	 return msg1;
    		 
    	 }
     }
     
     public Double finallSellingPriceWithGST(Double amt,Double gstPer) {
    	 Double per=amt*(gstPer/100);
    	 Double finallPrice=amt+per;
    	return finallPrice;		 
     }

     
     //getProductStockMaintain
 	 	@RequestMapping(value="/getProductStockMaintain", produces="application/json")
 	 	@ResponseBody
 	 	public String viewProductStockMaintain() throws JsonProcessingException {
 	 		List <ProductStockMaintain> list =service.ProductStockMaintaingetAll();
 	 		for(ProductStockMaintain p:list)
 	 		{
 	 			System.out.println(p.getProduct_code());
 	 			System.out.println(p.getProduct_name());
 	 		}
 	 		ObjectMapper objmap= new ObjectMapper();
 	 		String json=objmap.writeValueAsString(list);
 	 		return json;
 	 		
 	 	}

 	    @RequestMapping(value="/UpdateProductid")
 		public ProductStockMaintain updateProductid(@RequestParam int sid)
 		{
 			System.out.println("update"+sid);
 			List<ProductStockMaintain> lp=service.ProductStockMaintaingetAll();
 			ProductStockMaintain ps = new ProductStockMaintain();
 			for(ProductStockMaintain p:lp) 
 			{
 				if(p.getId()==sid) {
 					ps=p;
 				}
 			}
 			return ps;
 		}
 	    

 		@RequestMapping(value="/UpdateproductStock", consumes= "application/json",method=RequestMethod.POST)
 			public String updateProductStock(@RequestBody TempProduct tp)
 			{
 			      System.out.println("Update Product........................");
 				List<ProductStockMaintain> prolist=service.ProductStockMaintaingetAll();
 				ProductStockMaintain updpro=new ProductStockMaintain();
 				for(ProductStockMaintain ps:prolist) {
 					if(tp.getId()==ps.getId()) {
 						updpro=ps;
 					}
 				}
 				System.out.println("In Add Product(Updated)");

 				  String procode=tp.getProduct_code();
 				  System.out.println("Pro code- "+procode);
 				 
 		    	 String proname=tp.getProduct_name();
 		    	 
 		    	 int supplierId=tp.getSupplierId();
 		    	 Supplier supplier=service.getSupplierById(supplierId);
 		    	 
 		    	 int categoryid=tp.getCategoryId();
 		    	 Category category=service.getProductCategoryById(categoryid);
 		    	  
 		    	 int gstid=tp.getGsttaxtypeId();
 		    	 GstTaxType gsttype=service.getGstTypeById(gstid);
 		    	 
 		    	 int blockid=tp.getBlockId();
 		    	 Block block=service.getBlockById(blockid);
 		    	 
 		    	 int vehiclemodelid=tp.getVehicleModelId();
 		    	 VehicleModel vehiclemodel=service.getVehicleModelById(vehiclemodelid);
 		    	 
 		    	 int vehicletypeid=tp.getVehicleTypeId();
 		    	 VehicleType vehicletype=service.getVehicleTypeById(vehicletypeid);

 		    	 int systemuid = tp.getSysuserid();
 		    	 SystemUsers sysuser=service.getSystemUserById(systemuid);
 		    	 
 		    	 int proquantity=tp.getProduct_quantity();
 		    	 String hsn=tp.getHsn_num();
 		    	 String propricewithgst=tp.getProduct_buying_price_with_gst();
 		    	 String prosellingprice=tp.getProduct_selling_price();
 		    	 String receivedate=tp.getReceiving_date();
 		    	 String updatedate=tp.getUpdate_date();
 		     	
 		    	 System.out.println("Get GST percent "+gsttype.getGst_percent());
 		    	 String gstp=gsttype.getGst_percent();
 		    	 //String gstpp=""+gstp;
 		    	 Double gstPEr=Double.parseDouble(gstp);
 		    	 Double prosellprice=Double.parseDouble(prosellingprice);
 		         double prosellingwithgst=finallSellingPriceWithGST(prosellprice, gstPEr);
 		    	 String fp=""+prosellingwithgst;
 		    	 System.out.println(prosellingwithgst);
 		    	 updpro.setProduct_code(procode);
 		    	updpro.setProduct_name(proname);
 		    	updpro.setSupplier(supplier);
 		    	updpro.setProduct_quantity(proquantity);
 		    	updpro.setCategory(category);
 		    	updpro.setHsn_num(hsn);
 		    	updpro.setProduct_buying_price_with_gst(propricewithgst);
 		    	updpro.setProduct_selling_price(prosellingprice);
 		    	updpro.setGsttaxtype(gsttype);
 		    	updpro.setPro_sell_price_with_gst(fp);
 		    	updpro.setUpdated_date(updatedate);
 		    	updpro.setBlock(block);
 		    	updpro.setVehicleModel(vehiclemodel);
 		    	updpro.setVehicleType(vehicletype);
 		    	updpro.setSystemUsers(sysuser);
 		    	 
 		     if(prolist.isEmpty())
 		        {
 		    	 String msg2=service.addProductMaintain(updpro);
 		    	 return msg2;
 		    	 }
 		    	 
 		    	 else {
 		    		 for (ProductStockMaintain pm : prolist) {
 						 String pmcode=pm.getProduct_code();
 						 Category cat=pm.getCategory();
 						 int pmcatid=cat.getId();
 						 if(pmcode.equals(procode) && pmcatid==categoryid)
 						 {
 							 pm.setProduct_code(procode);
 					    	 pm.setProduct_name(proname);
 					    	 pm.setSupplier(supplier);
 					    	 pm.setProduct_quantity(proquantity);
 					    	 pm.setCategory(category);
 					    	 pm.setHsn_num(hsn);
 					    	 pm.setProduct_buying_price_with_gst(propricewithgst);
 					    	 pm.setProduct_selling_price(prosellingprice);
 					    	 pm.setGsttaxtype(gsttype);
 					    	 pm.setPro_sell_price_with_gst(fp);
 					    	 pm.setUpdated_date(updatedate);
 					    	 pm.setBlock(block);
 					    	 pm.setVehicleModel(vehiclemodel);
 					    	 pm.setVehicleType(vehicletype);
 					    	 pm.setSystemUsers(sysuser);
 					    	 String msg1=service.addProductMaintain(pm);
 					    	 System.out.println(msg1);
 					    	 return msg1;
 						 }
 						 
 					}
 		    		 
 		    		 ProductStockMaintain pm1=new ProductStockMaintain();
 		    		 pm1.setProduct_code(procode);
 			    	 pm1.setProduct_name(proname);
 			    	 pm1.setSupplier(supplier);
 			    	 pm1.setProduct_quantity(proquantity);
 			    	 pm1.setCategory(category);
 			    	 pm1.setHsn_num(hsn);
 			    	 pm1.setProduct_buying_price_with_gst(propricewithgst);
 			    	 pm1.setProduct_selling_price(prosellingprice);
 			    	 pm1.setGsttaxtype(gsttype);
 			    	 pm1.setPro_sell_price_with_gst(fp);
 			    	 pm1.setUpdated_date(updatedate);
 			    	 pm1.setBlock(block);
 			    	 pm1.setVehicleModel(vehiclemodel);
 			    	 pm1.setVehicleType(vehicletype);
 			    	 pm1.setSystemUsers(sysuser);
 			    	 String msg1=service.addProductMaintain(pm1);
 			    	 System.out.println(msg1);
 			    	 return msg1;
 		    		 
 		    	 }
	
 			
 			}



 		@RequestMapping(value="/deleteProductbyid")
 	 	public String deleteProductbyid(@RequestParam int pid)
 	 	{
 	 	  	System.out.println("delete"+pid);
 	 		service.deleteProductsbyid(pid);
 	 		
 	 		return  "delete id";
 	 		
 	 	} 
 	 	
 	   //gblist 
 	 	@RequestMapping("/gblist")
 	 	public String getgblist() throws JsonProcessingException {
 	 		List <Block> list =service.getBlockList();
 	 		for(Block p:list)
 	 		{
 	 			System.out.println("..........."+p.getId());
 	 			System.out.println("..........."+p.getBlock_name());
 	 			
 	 		}
 	 		
 	 		
 	 		ObjectMapper objmap= new ObjectMapper();
 	 		String json=objmap.writeValueAsString(list);
 	 		return json;
 	 		
 	 	}
 	 	

 		 @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
 		            produces = MediaType.APPLICATION_PDF_VALUE)
 		    public ResponseEntity<InputStreamResource> productsReport() throws IOException {

 		        List<ProductStockMaintain> products = (List<ProductStockMaintain>) service.ProductStockMaintaingetAll();

 		        ByteArrayInputStream bis = GeneratePdfReport.productsReport(products);

 		        HttpHeaders headers = new HttpHeaders();
 		        headers.add("Content-Disposition", "inline; filename=ProductStockReport.pdf");

 		        return ResponseEntity
 		                .ok()
 		                .headers(headers)
 		                .contentType(MediaType.APPLICATION_PDF)
 		                .body(new InputStreamResource(bis));
 		    }

 		 
    @GetMapping(value = "/download/products.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
        List<ProductStockMaintain> products = (List<ProductStockMaintain>)service.ProductStockMaintaingetAll();
		
		ByteArrayInputStream in = ExcelGenerator.productsToExcel(products);
		// return IOUtils.toByteArray(in);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ProductStock.xlsx");
		
		 return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(new InputStreamResource(in));
    }

}
