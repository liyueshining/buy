package com.buy.bean.coin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.buy.bean.database.DBConnect;

public class OrderSelectBean {

	String s_year ="1700";
	String s_month ="1";
	String s_day = "1";
	String e_year = "2099";
	String e_month = "1";
	String e_day = "1";
	String status = "0";
	String num = null;
	public String getS_year() {
		return s_year;
	}
	public void setS_year(String s_year) {
		this.s_year = s_year;
	}
	public String getS_month() {
		return s_month;
	}
	public void setS_month(String s_month) {
		this.s_month = s_month;
	}
	public String getS_day() {
		return s_day;
	}
	public void setS_day(String s_day) {
		this.s_day = s_day;
	}
	public String getE_year() {
		return e_year;
	}
	public void setE_year(String e_year) {
		this.e_year = e_year;
	}
	public String getE_month() {
		return e_month;
	}
	public void setE_month(String e_month) {
		this.e_month = e_month;
	}
	public String getE_day() {
		return e_day;
	}
	public void setE_day(String e_day) {
		this.e_day = e_day;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public StringBuffer selectOrder(String userID){
		String type1 = null;
		int j = Integer.parseInt(status);
		switch (j) {
		case 0:
			type1 = "any";
			break;
		case 1:
			type1 = "正在处理";
			break;
		case 2:
			type1 = "支付成功";
			break;
		case 3:
			type1 = "支付失败";
			break;
		default:
			break;
		}
		
		String s_date = s_year+"-"+s_month+"-"+s_day;
		String e_date = e_year+"-"+e_month+"-"+e_day;
		String condition;
		if(num != null){
			condition = "select orderid,type,price,datetime,productname from buy.order where userid='"+userID+"' and orderid= "+num +"";
			
		}else {
			condition = "select orderid,type,price,datetime,productname from buy.order where userid='"+userID+"' and datetime <= '"+e_date +"' and datetime >='"+s_date+"'";
		
			if(j != 0){
				condition += "and type='" +type1+"'";
			}
		}
		
		ResultSet rs = null;
		Statement sql = null;
		String num,status,coin,date,name;
		StringBuffer buffer = new StringBuffer();
		
		try {
			Connection conn = DBConnect.getConnection();
			sql = conn.createStatement();
			rs = sql.executeQuery(condition);
			int i = 1;
			
			buffer.append("<table width="+"100%"+" border="+"0"+" cellpadding="+"0"+" cellspacing="+"1"+">");
			buffer.append("<tr align="+"center"+">");
			buffer.append("<td width="+"150"+"height="+"25"+"class="+"cal_td12"+">订单号</td>");
			buffer.append("<td width="+"180"+"height="+"25"+"class="+"cal_td12"+">订单状态</td>");
			buffer.append("<td width="+"150"+"height="+"25"+"class="+"cal_td12"+">金额</td>");
			buffer.append("<td width="+"150"+"height="+"25"+"class="+"cal_td12"+">订单创建时间</td>");
			buffer.append("<td width="+"150"+"height="+"25"+"class="+"cal_td12"+">订购商品 名称</td>");
			buffer.append("</tr>");
			
			while(rs.next()){
				buffer.append("<tr align="+"center"+">");
				if(i%2 == 1){
					num = rs.getString(1);
					buffer.append("<td width="+180+" height="+"20"+" class="+"cal_td06"+">"+num+"</td>");
					status = rs.getString(2);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td06"+">"+status+"</td>");
					coin = rs.getString(3);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td06"+">"+coin+"</td>");
					date = rs.getString(4);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td06"+">"+date+"</td>");
					name = rs.getString(5);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td06"+">"+name+"</td>");
				}else {
					status = rs.getString(2);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td08"+">"+status+"</td>");
					coin = rs.getString(3);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td08"+">"+coin+"</td>");
					date = rs.getString(4);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td08"+">"+date+"</td>");
					name = rs.getString(5);
					buffer.append("<td width="+150+" height="+"20"+" class="+"cal_td08"+">"+name+"</td>");
				}
				buffer.append("</tr>");
				i++;
			}
			buffer.append("</table>");
			rs.close();
			sql.close();
			conn.close();
		} catch (SQLException e) {
			return new StringBuffer("查询错误");
		}
		return buffer;
	}
}
