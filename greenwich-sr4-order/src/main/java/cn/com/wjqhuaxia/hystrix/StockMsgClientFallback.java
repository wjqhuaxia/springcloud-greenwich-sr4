package cn.com.wjqhuaxia.hystrix;

import org.springframework.stereotype.Component;

import cn.com.wjqhuaxia.client.StockMsgClient;
/**
 * 
 * @author wjqhuaxia
 *
 */
@Component
public class StockMsgClientFallback implements StockMsgClient{

	@Override
	public String getStockMsg() {
		return "getStockMsg 服务异常，请稍后再试！";
	}

}
