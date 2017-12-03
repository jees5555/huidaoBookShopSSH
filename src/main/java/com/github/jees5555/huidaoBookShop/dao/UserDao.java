package com.github.jees5555.huidaoBookShop.dao;

import com.github.jees5555.huidaoBookShop.entity.User;

public interface UserDao extends BaseDao<User>{
		User login(User user) throws Exception;
		
		int findUserByName(String username) throws Exception;
}
