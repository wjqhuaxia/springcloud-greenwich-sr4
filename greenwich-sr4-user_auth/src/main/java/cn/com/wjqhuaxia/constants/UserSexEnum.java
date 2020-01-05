package cn.com.wjqhuaxia.constants;


/**
 * 用户性别枚举类
 * @author wjqhuaxia
 *
 */
public enum UserSexEnum {
	/**
	 * 男
	 */
	MAN("man", "男"), 
	/**
	 * 女
	 */
	WOMAN("woman", "女");
	private UserSexEnum(String name, String nameCh){
		this.name = name;
		this.nameCh = nameCh;
	}
	/**
	 * name英文
	 */
	private String name;
	/**
	 * name中文
	 */
	private String nameCh;
	
	public String getName() {
		return name;
	}
	public String getNameCh() {
		return nameCh;
	}
	/**
	 * 判断参数合法性
	 */
	public static boolean isValidName(String name) {
		for (UserSexEnum userSexEnum : UserSexEnum.values()) {
			if (userSexEnum.name().equals(name)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 通过name获取对应UserSexEnum枚举值
	 * @param name
	 * @return
	 */
	public static UserSexEnum getEnum(String name) {
		for (UserSexEnum ele : UserSexEnum.values()) {
			if (name.equals(ele.getName())) {
				return ele;
			}
		}
		return null;
	}
}
