package cn.com.wjqhuaxia.hystrix;

import org.springframework.stereotype.Component;

import cn.com.wjqhuaxia.client.IntegralMsgClient;

@Component
public class IntegralMsgClientFallback implements IntegralMsgClient{

	@Override
	public String getIntegralMsg() {
		return "getIntegralMsg 服务异常，请稍后再试！";
	}

}
