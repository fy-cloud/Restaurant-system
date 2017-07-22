package com.yc.test;

import java.io.IOException;
import java.sql.SQLException;

import com.yc.dao.SeatDao;

public class AddTest {
	
	public void addTest(int quantity) throws IOException, SQLException{
		SeatDao seatDao = new SeatDao();
		int update = seatDao.addByQuantity(quantity);
		System.out.println(update);
	}
}
