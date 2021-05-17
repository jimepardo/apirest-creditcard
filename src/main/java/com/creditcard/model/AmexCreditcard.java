package com.creditcard.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AmexCreditcard extends Creditcard {

	@Override
	public Double calculateRate() {
		Date actualDate= new Date();
		SimpleDateFormat getMonthFormat = new SimpleDateFormat("mm");
        String currentMonth = getMonthFormat.format(actualDate);
        Double month = Double.parseDouble(currentMonth);
        
		return (month * 0.1);
	}

}
