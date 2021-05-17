package com.creditcard.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NaraCreditcard extends Creditcard {

	@Override
	public Double calculateRate() {
		Date actualDate= new Date();
		
		SimpleDateFormat getDayFormat = new SimpleDateFormat("dd");
        String currentDay = getDayFormat.format(actualDate);
        Double day = Double.parseDouble(currentDay);
		return (day * 0.5 );
	}

}
