package cn.com.wjqhuaxia.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cn.com.wjqhuaxia.hystrix.StockMsgClientFallback;

/**
 * stockMsg调用类
 * @author wjqhuaxia
 *
 */
@FeignClient(name = "stock-service", fallback = StockMsgClientFallback.class)
public interface StockMsgClient {

	@GetMapping("/stock/getStockMsg")
	public String getStockMsg();
}
