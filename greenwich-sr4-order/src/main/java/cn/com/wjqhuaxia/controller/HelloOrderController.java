package cn.com.wjqhuaxia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.wjqhuaxia.client.IntegralMsgClient;
import cn.com.wjqhuaxia.client.StockMsgClient;

@RestController
public class HelloOrderController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StockMsgClient stockMsgClient;
	@Autowired
	private IntegralMsgClient integralMsgClient;
	
	@GetMapping("/testStockRequest")
	@ResponseBody
	public String testStockRequest(){
		logger.info("testStockRequest start...");
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
