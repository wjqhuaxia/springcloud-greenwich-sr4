package cn.com.wjqhuaxia.mode;

/**
 * 封装API的错误码
 * @author wjqhuaxia
 *
 */
public interface IErrorCode {
	
    long getCode();

    String getMessage();
}
