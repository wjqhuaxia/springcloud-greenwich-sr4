package cn.com.wjqhuaxia.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cn.com.wjqhuaxia.hystrix.IntegralMsgClientFallback;

/**
 * 
 * @author wjqhuaxia
 *
 */
@FeignClient(name = "integral-service", fallback = IntegralMsgClientFallback.class)
public interface IntegralMsgClient {

	@GetMapping("/integral/getIntegralMsg")
	public String getIntegralMsg();
}
