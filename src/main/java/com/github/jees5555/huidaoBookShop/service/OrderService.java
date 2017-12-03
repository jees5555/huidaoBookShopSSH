package com.github.jees5555.huidaoBookShop.service;

import java.util.List;

import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.util.Page;

public interface OrderService extends BaseService{
	int createOrder(List<Integer> bids, User user);

	Page showOrderList(User user, String pagenum, String keywords, String history) throws Exception;

	int cancleOrder(String oid,User user);
}
