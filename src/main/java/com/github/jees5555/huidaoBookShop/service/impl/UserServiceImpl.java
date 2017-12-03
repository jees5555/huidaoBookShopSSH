package com.github.jees5555.huidaoBookShop.service.impl;

import java.util.List;

import com.github.jees5555.huidaoBookShop.dao.UserDao;
import com.github.jees5555.huidaoBookShop.dao.impl.UserDaoImpl;
import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.UserService;

public class UserServiceImpl implements UserService{
	UserDao ud =new UserDaoImpl();

	@Override
	public User login(String username, String password) throws Exception {
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		return ud.login(user);
	}

	@Override
	public int checkUsername(String username) throws Exception {
		return ud.findUserByName(username);
	}

	@Override
	public User register(User user) throws Exception {
		ud.add(user);
		return ud.findByName(user.getUsername());
	}

}
