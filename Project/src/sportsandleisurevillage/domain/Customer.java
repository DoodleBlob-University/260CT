package sportsandleisurevillage.domain;

public class Customer {

	private int id;
	private String name;
	private String email;

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	public String getEmail() {
		return this.email;
	}

	public Customer(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

}