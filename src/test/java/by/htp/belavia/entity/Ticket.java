package by.htp.belavia.entity;

import java.time.LocalDate;

public class Ticket {

	private String departureCountry;
	private String arrivalCountry;
	private LocalDate date;
	private double cost;

	public Ticket() {

	}

	public Ticket(String departureCountry, String arrivalCountry, LocalDate date, double cost) {
		super();
		this.departureCountry = departureCountry;
		this.arrivalCountry = arrivalCountry;
		this.date = date;
		this.cost = cost;
	}

	public String getDepartureCountry() {
		return departureCountry;
	}

	public void setDepartureCountry(String departureCountry) {
		this.departureCountry = departureCountry;
	}

	public String getArrivalCountry() {
		return arrivalCountry;
	}

	public void setArrivalCountry(String arrivalCountry) {
		this.arrivalCountry = arrivalCountry;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalCountry == null) ? 0 : arrivalCountry.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((departureCountry == null) ? 0 : departureCountry.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (arrivalCountry == null) {
			if (other.arrivalCountry != null)
				return false;
		} else if (!arrivalCountry.equals(other.arrivalCountry))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (departureCountry == null) {
			if (other.departureCountry != null)
				return false;
		} else if (!departureCountry.equals(other.departureCountry))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket: " + departureCountry + " - " + arrivalCountry + ", date: " + date + ", Cost: " + cost;
	}

}
