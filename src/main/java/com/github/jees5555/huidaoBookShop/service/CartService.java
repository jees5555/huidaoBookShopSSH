package com.github.jees5555.huidaoBookShop.service;

import java.util.List;

import com.github.jees5555.huidaoBookShop.entity.Cart;
import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.vo.CartVo;

public interface CartService extends BaseService<Cart>{


	int addOrUpdateCart(List<Integer> bids, User user);

	List<CartVo> findCartByUid(User user,String keywords);

	int deleteCart(List<Integer> cartlist, User user);

	int updateCartBookCount(Integer bid, Integer bookcount, User user);
}
