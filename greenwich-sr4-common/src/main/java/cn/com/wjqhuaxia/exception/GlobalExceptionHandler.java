package cn.com.wjqhuaxia.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.com.wjqhuaxia.mode.ResultBean;
/**
 * 统一的异常处理
 * @author wjqhuaxia
 */
@RestControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 默认统一异常处理方法
     * @ExceptionHandler 注解用来配置需要拦截的异常类型, 也可以是自定义异常
     */
    // 此处可以指定返回的状态码 和 返回 结果说明
    // @ResponseStatus(reason = "exception",value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
    public Object runtimeExceptionHandler(Exception e) {
        log.error("请求出现异常,异常信息为: {}", e.getMessage());
        return ResultBean.validateFailed(e.getMessage());
    }
 
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        // 封装错误信息格式为：defaultMessage(field)
        String msg = String.format("%s(%s)", fieldError.getDefaultMessage(), fieldError.getField());
        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return ResultBean.validateFailed(msg);
    }
 
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(BindException.class)
    public ResultBean handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        // 封装错误信息格式为：defaultMessage(field)
        String msg = String.format("%s(%s)", fieldError.getDefaultMessage(), fieldError.getField());
        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return ResultBean.validateFailed(msg);
    }
}
