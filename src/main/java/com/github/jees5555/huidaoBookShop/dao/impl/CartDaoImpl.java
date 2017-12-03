package com.github.jees5555.huidaoBookShop.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.github.jees5555.huidaoBookShop.dao.CartDao;
import com.github.jees5555.huidaoBookShop.entity.Book;
import com.github.jees5555.huidaoBookShop.entity.Cart;
import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.util.DBCPUtil;
import com.github.jees5555.huidaoBookShop.util.TransactionUtil;
import com.github.jees5555.huidaoBookShop.vo.CartVo;

public class CartDaoImpl implements CartDao {

private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	

	
@Override
	public List<Cart> showUserCartList(User user) throws SQLException {	
	
	return qr.query(TransactionUtil.getConnection(),"select * from cart where uid=?", new BeanListHandler<Cart>(Cart.class),user.getUid());
		
	}


@Override
	public int update(Cart cart) throws Exception {
		String sql="update cart set count=? where bid=? and uid =?";
		return qr.update(TransactionUtil.getConnection(), sql,cart.getCount(),cart.getBid(),cart.getUid());
	}

@Override
	public int add(Cart cart) throws Exception {
		String sql="insert into cart (uid,bid,count) values (?,?,?)" ;
		return qr.update(TransactionUtil.getConnection(), sql,cart.getUid(),cart.getBid(),cart.getCount());
	}


@Override
public List<CartVo> findCartByUid(User user,String keywords) throws Exception {
	if(keywords==null ||"".equals(keywords)){
		String sql="select * from cart,book where cart.bid=book.bid and cart.uid=?";
		return qr.query(TransactionUtil.getConnection(),sql, new BeanListHandler<CartVo>(CartVo.class),user.getUid());
	}else{
		String sql="select * from cart,book where cart.bid=book.bid and cart.uid=? and book.bookname like ?";
		keywords="%"+keywords+"%";
		return qr.query(TransactionUtil.getConnection(),sql, new BeanListHandler<CartVo>(CartVo.class),user.getUid(),keywords);
	}
	
}
@Override
	public int del(Cart cart) throws Exception {
	String sql="delete from cart where uid=? and bid=?";
		return qr.update(TransactionUtil.getConnection(), sql,cart.getUid(),cart.getBid());
	}
}
