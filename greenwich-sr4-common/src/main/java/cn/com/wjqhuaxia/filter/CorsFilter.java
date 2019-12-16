package cn.com.wjqhuaxia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/**
 * 澶勭悊寰湇鍔¤法鍩熼棶棰�
 * @author wjqhuaxia
 *
 */
@Component
public class CorsFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		/*
		 * String curOrigin = request.getHeader("Origin");
		 * System.out.println("###璺ㄥ煙杩囨护鍣�->褰撳墠璁块棶鏉ユ簮->"+curOrigin+"###");
		 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		 
	}

	@Override
	public void destroy() {
	}
}
