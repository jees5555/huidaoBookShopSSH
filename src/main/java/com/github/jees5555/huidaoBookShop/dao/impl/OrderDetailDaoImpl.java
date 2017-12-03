package com.github.jees5555.huidaoBookShop.dao.impl;

import org.apache.commons.dbutils.QueryRunner;

import com.github.jees5555.huidaoBookShop.dao.OrderDetailDao;
import com.github.jees5555.huidaoBookShop.entity.OrderDetail;
import com.github.jees5555.huidaoBookShop.util.DBCPUtil;
import com.github.jees5555.huidaoBookShop.util.TransactionUtil;

public class OrderDetailDaoImpl implements OrderDetailDao{
	
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());	
	
@Override
public int add(OrderDetail od) throws Exception {
	String sql="insert into `orderdetail` (oid,bid,count,historybookprice) values (?,?,?,?)";
	return qr.update(TransactionUtil.getConnection(),sql,od.getOid(),od.getBid(),od.getCount(),od.getHistoryBookPrice());
}
}
