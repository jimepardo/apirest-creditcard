package com.creditcard.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VisaCreditcard extends Creditcard {

	@Override
	public Double calculateRate() {
		Date actualDate= new Date();
		
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yy");
        String currentYear = getYearFormat.format(actualDate);
        
        SimpleDateFormat getMonthFormat = new SimpleDateFormat("mm");
        String currentMonth = getMonthFormat.format(actualDate);
        Double year = Double.parseDouble(currentYear);
        Double month = Double.parseDouble(currentMonth);
		return ( year/ month );
	}

}
