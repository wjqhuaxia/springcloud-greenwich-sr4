package cn.com.wjqhuaxia.util;

import cn.com.wjqhuaxia.mode.dto.User;

/**
 * ThreadUserUtil<br>
 * 用于存储当前线程的用户登陆态信息
 * 
 * @author wjqhuaxia
 *
 */
public class ThreadSessionUtil {

	private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

	public static <T extends User> void set(T t) {
		threadLocal.set(t);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get() {
		return (T) threadLocal.get();
	}

	public static void remove() {
		threadLocal.remove();
	}
}
