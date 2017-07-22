package com.yc.test;

import java.util.Calendar;

public class TestDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.YEAR, 2000);
//		calendar.set(Calendar.MONTH, 11-1);
		System.out.println(calendar.getTime());
		System.out.println(calendar.getActualMaximum(Calendar.DATE));
	}

}
