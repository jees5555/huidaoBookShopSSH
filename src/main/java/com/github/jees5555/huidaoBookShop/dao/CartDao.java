package com.github.jees5555.huidaoBookShop.dao;

import java.sql.SQLException;
import java.util.List;

import com.github.jees5555.huidaoBookShop.entity.Cart;
import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.vo.CartVo;

public interface CartDao extends BaseDao<Cart>{

	List<Cart> showUserCartList(User user) throws SQLException;

	List<CartVo> findCartByUid(User user,String keywords) throws Exception;


}
