package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String beerName;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private String beerType;
	@ManyToOne
	@JoinColumn
	private User beerOwner;
	
	public Beer() {
		super();
	}

	public Beer(Beer beer) {
		super();
		this.id = beer.getId();
		this.beerName = beer.getBeerName();
		this.price = beer.getPrice();
		this.beerType = beer.getType();
		this.beerOwner = beer.getBeerOwner();
	}

	public Beer(int i, String string, String string2, double d, int j) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	} 

	public String getType() {
		return beerType;
	}

	public void setType(String type) {
		this.beerType = type;
	}

	public User getBeerOwner() {
		return beerOwner;
	}

	public void setBeerOwner(User beerOwner) {
		this.beerOwner = beerOwner;
	}

	@Override
	public String toString() {
		return "Beer [id=" + id + ", beerName=" + beerName + ", price=" + price + ", type=" + beerType + ", beerOwner="
				+ beerOwner + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(beerName, beerOwner, id, price, beerType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		return Objects.equals(beerName, other.beerName) && Objects.equals(beerOwner, other.beerOwner) && id == other.id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(beerType, other.beerType);
	}


	
	

	
	
	

}
