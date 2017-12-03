package com.github.jees5555.huidaoBookShop.service;

import com.github.jees5555.huidaoBookShop.entity.User;

public interface UserService extends BaseService<User>{
		User login(String username,String password)throws Exception;
		
		int checkUsername(String username) throws Exception;
		
		User register(User user) throws Exception;
}
