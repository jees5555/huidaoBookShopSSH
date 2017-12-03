package com.github.jees5555.huidaoBookShop.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.github.jees5555.huidaoBookShop.dao.OrderDao;
import com.github.jees5555.huidaoBookShop.entity.Book;
import com.github.jees5555.huidaoBookShop.entity.Order;
import com.github.jees5555.huidaoBookShop.entity.User;
import com.github.jees5555.huidaoBookShop.util.DBCPUtil;
import com.github.jees5555.huidaoBookShop.util.TransactionUtil;
import com.github.jees5555.huidaoBookShop.vo.OrderVo;

public class OrderDaoImpl implements OrderDao{

	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());	
	
@Override
public int add(Order order) throws Exception {
	String sql="insert into `order` (uid,receiver,allprice,createtime,status) values (?,?,?,?,?)" ;
	return qr.update(TransactionUtil.getConnection(), sql,order.getUid(),order.getReceiver(),order.getAllprice(),order.getCreatetime(),order.getStatus());
}
@Override
public int updateAllPrice(Order order) throws Exception {
	String sql="update `order` set allprice = ? where oid=?" ;
	return qr.update(TransactionUtil.getConnection(), sql,order.getAllprice(),order.getOid());
}
@Override
public Long getId() throws Exception {
	BigInteger bi =qr.query(TransactionUtil.getConnection(), "SELECT LAST_INSERT_ID()", new ScalarHandler<BigInteger>(1));
	return  Long.valueOf(bi.toString());
}
@Override
public int findAllRecords(User user,String keywords, String history) throws Exception {
	String sql;
	Long rec =null;
	if(keywords==null ||"".equals(keywords)){
		if(history==null || "".equals(history)){
		sql="select count(*) from `order` o left join orderdetail od on o.oid=od.oid where uid=?";
		rec = qr.query(sql, new ScalarHandler<Long>(),user.getUid());
		}else{
			if(history.equals("1M")){
				sql="select count(*) from `order` o  left join orderdetail od on o.oid=od.oid where o.createtime >? and o.createtime<=? and uid=?";
				rec = qr.query(sql, new ScalarHandler<Long>(),LocalDate.now().minusYears(1),LocalDate.now().minusMonths(1),user.getUid());
			}else if(history.equals("1Y")){
				sql="select count(*) from `order` o left join orderdetail od on o.oid=od.oid where o.createtime <=? and uid=?";
				rec = qr.query(sql, new ScalarHandler<Long>(),LocalDate.now().minusYears(1),user.getUid());
			}else{
				return findAllRecords(user,keywords,null);
			}
		}
	}else{
		if(history==null || "".equals(history)){
			keywords="%"+keywords+"%";
			sql="select count(*) from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid where b.bookname like ? and uid=?";
			rec = qr.query(sql, new ScalarHandler<Long>(),keywords,user.getUid());
		}else{
			keywords="%"+keywords+"%";
			if(history.equals("1M")){
				sql="select count(*) from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid"
						+" where o.createtime >? and o.createtime<=? and b.bookname like ? and uid=?";
				rec = qr.query(sql, new ScalarHandler<Long>(),LocalDate.now().minusYears(1),LocalDate.now().minusMonths(1),keywords,user.getUid());
			}else if(history.equals("1Y")){
				sql="select count(*) from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid"
						+" where o.createtime <=? and b.bookname like ? and uid=?";
				rec = qr.query(sql, new ScalarHandler<Long>(),LocalDate.now().minusYears(1),keywords,user.getUid());
			}else{
				return findAllRecords(user,keywords,null);
			}
		}
	}		
		return rec==null?0:rec.intValue();
	
}
@Override
public List<OrderVo> showOrderList(User user,int startIndex, int pagesize, String keywords, String history) throws SQLException {
	String sql;
	if(keywords==null ||"".equals(keywords)){
		if(history==null || "".equals(history)){
			sql="select * from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid where uid=? Order by createtime DESC limit ?,?";
			return qr.query(sql,  new BeanListHandler<OrderVo>(OrderVo.class),user.getUid(),startIndex,pagesize);
		}else{
			if(history.equals("1M")){
				sql="select * from `order` o  left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid"
						+" where o.createtime >? and o.createtime<=? and uid=? Order by createtime DESC limit ?,?";
				return  qr.query(sql,  new BeanListHandler<OrderVo>(OrderVo.class),LocalDate.now().minusYears(1),LocalDate.now().minusMonths(1),user.getUid(),startIndex,pagesize);
			}else if(history.equals("1Y")){
				sql="select * from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid"
						+" where o.createtime <=? and uid=? Order by createtime DESC limit ?,?";
				return qr.query(sql,  new BeanListHandler<OrderVo>(OrderVo.class),LocalDate.now().minusYears(1),user.getUid(),startIndex,pagesize);
			}else{
				return showOrderList(user,startIndex, pagesize, keywords, null);
			}
		}
	}else{
		if(history==null || "".equals(history)){
			keywords="%"+keywords+"%";
			sql="select * from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid"
					+" where b.bookname like ? and uid=? Order by createtime DESC limit ?,?";
			return qr.query(sql,  new BeanListHandler<OrderVo>(OrderVo.class),keywords,user.getUid(),startIndex,pagesize);
		}else{
			keywords="%"+keywords+"%";
			if(history.equals("1M")){
				sql="select * from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid"
						+" where o.createtime >? and o.createtime<=? and b.bookname like ? and uid=? Order by createtime DESC limit ?,?";
				return qr.query(sql,  new BeanListHandler<OrderVo>(OrderVo.class),LocalDate.now().minusYears(1),LocalDate.now().minusMonths(1),keywords,user.getUid(),startIndex,pagesize);
			}else if(history.equals("1Y")){
				sql="select * from `order` o left join orderdetail od on o.oid=od.oid LEFT JOIN book b on od.bid=b.bid"
						+" where o.createtime <=? and b.bookname like ? and uid=? Order by createtime DESC limit ?,?";
				return qr.query(sql, new BeanListHandler<OrderVo>(OrderVo.class),LocalDate.now().minusYears(1),keywords,user.getUid(),startIndex,pagesize);
			}else{
				return showOrderList(user,startIndex, pagesize, keywords, null);
			}
		}
	}


}
@Override
public int cancleOrder(String oid, User user) throws Exception {
	System.out.println(oid+"  "+user.getUid());
	String sql="update `order` set status='已取消' where oid=? and uid=?";
	return qr.update(TransactionUtil.getConnection(),sql,oid,user.getUid());
}
}
