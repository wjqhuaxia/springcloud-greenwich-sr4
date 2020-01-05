package cn.com.wjqhuaxia.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cn.com.wjqhuaxia.constants.UserSexEnum;
import cn.com.wjqhuaxia.validator.EnumValueValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 用户对象
 * @author wjqhuaxia
 */
@ApiModel(value="UserEntity", description="用户对象")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户id",name="id",example="1")
	private Long id;
	
	@ApiModelProperty(value="用户名",name="userName",example="mao2080")
	@NotBlank(message = "用户不能为空！")
    @Size(min = 3,max = 8,message = "name的长度范围为3-8")
	private String userName;
	
	@ApiModelProperty(value="密码",name="passWord",example="123456")
	@NotBlank(message = "密码不能为空！")
    @Size(min = 3,max = 8,message = "passWord的长度范围为3-8")
	private String passWord;
	
	@ApiModelProperty(value="性别",name="userSex",example="MAN")
	@EnumValueValidator(enumClass=UserSexEnum.class, enumMethod="isValidName")
	private String userSex;
	
	@ApiModelProperty(value="昵称",name="nickName",example="天际星痕")
	@NotBlank(message = "昵称不能为空！")
	private String nickName;

	public UserEntity() {
		super();
	}

	public UserEntity(String userName, String passWord, String userSex) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}