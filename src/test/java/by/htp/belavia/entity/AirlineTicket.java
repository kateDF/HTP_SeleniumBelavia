package by.htp.belavia.entity;

import java.time.LocalDate;

public class AirlineTicket {

	private String departureCountry;
	private String arrivalCountry;
	private LocalDate departureDate;
	private boolean isReturnTicket;
	private LocalDate returnDate;

	public AirlineTicket() {

	}

	public AirlineTicket(String departureCountry, String arrivalCountry, LocalDate departureDate,
			boolean isReturnTicket) {
		this.departureCountry = departureCountry;
		this.arrivalCountry = arrivalCountry;
		this.departureDate = departureDate;
		this.isReturnTicket = isReturnTicket;
	}

	public AirlineTicket(String departureCountry, String arrivalCountry, LocalDate departureDate,
			boolean isReturnTicket, LocalDate returnDate) {
		this.departureCountry = departureCountry;
		this.arrivalCountry = arrivalCountry;
		this.departureDate = departureDate;
		this.isReturnTicket = isReturnTicket;
		this.returnDate = returnDate;
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

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public boolean isReturnTicket() {
		return isReturnTicket;
	}

	public void setReturnTicket(boolean isReturnTicket) {
		this.isReturnTicket = isReturnTicket;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalCountry == null) ? 0 : arrivalCountry.hashCode());
		result = prime * result + ((departureCountry == null) ? 0 : departureCountry.hashCode());
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + (isReturnTicket ? 1231 : 1237);
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
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
		AirlineTicket other = (AirlineTicket) obj;
		if (arrivalCountry == null) {
			if (other.arrivalCountry != null)
				return false;
		} else if (!arrivalCountry.equals(other.arrivalCountry))
			return false;
		if (departureCountry == null) {
			if (other.departureCountry != null)
				return false;
		} else if (!departureCountry.equals(other.departureCountry))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (isReturnTicket != other.isReturnTicket)
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Airline Ticket: departureCountry: " + departureCountry + ", arrivalCountry: " + arrivalCountry
				+ ". DepartureDate: " + departureDate + ", isReturnTicket: " + isReturnTicket + "."
				+ (returnDate != null ? " ReturnDate:" + returnDate : "");
	}

}
