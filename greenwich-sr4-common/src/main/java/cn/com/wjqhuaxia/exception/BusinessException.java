package cn.com.wjqhuaxia.exception;
/**
 * 自定义业务异常
 * @author wjqhuaxia
 */
public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2867690197891330608L;

	private int code;
	private String msg;
	public BusinessException(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public BusinessException(String msg){
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
