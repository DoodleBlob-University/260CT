package sportsandleisurevillage.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {

	private int id;
	private String type;
	private Date date;
	private int cost;

	public int getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public Date getDate() {
		return this.date;
	}

	public int getCost() {
		return this.cost;
	}


	public Booking(int id, String type, String date, int cost) {
		this.id = id;
		this.type = type;
		try{
			this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		}catch(Exception e){e.printStackTrace();}
		this.cost = cost;
	}

}