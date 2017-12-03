package com.github.jees5555.huidaoBookShop.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.github.jees5555.huidaoBookShop.dao.BookDao;
import com.github.jees5555.huidaoBookShop.dao.CartDao;
import com.github.jees5555.huidaoBookShop.dao.OrderDao;
import com.github.jees5555.huidaoBookShop.dao.OrderDetailDao;
import com.github.jees5555.huidaoBookShop.dao.impl.BookDaoImpl;
import com.github.jees5555.huidaoBookShop.dao.impl.CartDaoImpl;
import com.github.jees5555.huidaoBookShop.dao.impl.OrderDaoImpl;
import com.github.jees5555.huidaoBookShop.dao.impl.OrderDetailDaoImpl;
import com.github.jees5555.huidaoBookShop.entity.Book;
import com.github.jees5555.huidaoBookShop.entity.Cart;
import com.github.jees5555.huidaoBookShop.entity.Order;
import com.github.jees5555.huidaoBookShop.entity.OrderDetail;
import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.service.OrderService;
import com.github.jees5555.huidaoBookShop.util.Page;
import com.github.jees5555.huidaoBookShop.util.TransactionUtil;
import com.github.jees5555.huidaoBookShop.vo.CartVo;
import com.github.jees5555.huidaoBookShop.vo.OrderVo;

public class OrderServiceImpl implements OrderService{
	private CartDao cd=new CartDaoImpl();
	private BookDao bd =new BookDaoImpl();
	private OrderDao od =new OrderDaoImpl();
	private OrderDetailDao odd =new OrderDetailDaoImpl();
	
	public int createOrder(List<Integer> bids, User user){
		try{
		TransactionUtil.startTransaction();
		//获取CartList
		List<CartVo> carts =cd.findCartByUid(user, null);
		//新建Order并获取oid
		Order o =new Order();
		o.setUid(user.getUid());
		o.setReceiver(user.getUsername());
		o.setAllprice(0.0);
		o.setCreatetime(LocalDateTime.now());
		o.setStatus("已支付");
		od.add(o);
		Long oid=od.getId();
		//添加到OrderDetail
		Double totalPrice=0.0;
		for (Integer bid: bids){
			for(CartVo cart:carts){
				if(cart.getBid()==bid){
					//存在就
					OrderDetail od=new OrderDetail();
					od.setOid(oid.intValue());
					od.setBid(cart.getBid());
					od.setHistoryBookPrice(cart.getPrice());
					od.setCount(cart.getCount());
					odd.add(od);
					//减库存
					Book book =new Book();
					book.setBid(cart.getBid());
					book.setStock(cart.getStock()-cart.getCount());
					bd.updateBookStock(book);
					//计算总额
					totalPrice+=(cart.getCount()*cart.getPrice());
				}
			}
		}
		//更新Order
		o.setOid(oid);
		o.setAllprice(totalPrice);
		od.updateAllPrice(o);
		
		//从Cart删除
		for (Integer bid: bids){
		Cart c =new Cart();
		c.setBid(bid);
		c.setUid(user.getUid());
		cd.del(c);
		}
		TransactionUtil.commit();
		return bids.size();
		}catch(Exception e){
			e.printStackTrace();
			TransactionUtil.rollback();
		return 0;
		}finally{
			TransactionUtil.close();
		}
		
	}

	@Override
	public Page<OrderVo> showOrderList(User user,String pagenum, String keywords, String history) throws Exception {
		int num=1;
		if(pagenum!=null){
			num=Integer.parseInt(pagenum);
		}
	
		int totalRecords=od.findAllRecords(user,keywords,history);
		
		Page<OrderVo> page=new Page<>(num,totalRecords);
		
		List<OrderVo> orders=od.showOrderList(user,page.getStartIndex(), page.getPagesize(),keywords,history);
		
		page.setRecords(orders);
		
		return page;
	}

	@Override
	public int cancleOrder(String oid, User user) {
		try{
		TransactionUtil.startTransaction();
		int res=od.cancleOrder(oid,user);
		TransactionUtil.commit();
		return res;
		}catch(Exception e){
			e.printStackTrace();
			TransactionUtil.rollback();
			return 0;
		}finally{
			TransactionUtil.close();
		}
	}

}
