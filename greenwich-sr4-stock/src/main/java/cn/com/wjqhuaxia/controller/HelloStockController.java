package cn.com.wjqhuaxia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * helloStockController
 * @author wjqhuaxia
 *
 */
@RestController
public class HelloStockController {

	@GetMapping("/getStockMsg")
	@ResponseBody
	public String getStockMsg(){
		return "Hello Stock Controller!";
	};
}
