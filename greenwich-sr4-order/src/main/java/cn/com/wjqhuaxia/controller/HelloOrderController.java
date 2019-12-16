package cn.com.wjqhuaxia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.wjqhuaxia.client.IntegralMsgClient;
import cn.com.wjqhuaxia.client.StockMsgClient;

@RestController
public class HelloOrderController {
	
	@Autowired
	private StockMsgClient stockMsgClient;
	@Autowired
	private IntegralMsgClient integralMsgClient;
	
	@GetMapping("/testStockRequest")
	@ResponseBody
	public String testStockRequest(){
		return "order service: " + stockMsgClient.getStockMsg();
	}
	
	@GetMapping("/testIntegralRequest")
	@ResponseBody
	public String testIntegralRequest(){
		return "order service: " + integralMsgClient.getIntegralMsg();
	}
	
	@RequestMapping(value = "/hello")
	@ResponseBody
	public String hello(){
		return "Hello Order Controller!";
	}
}
