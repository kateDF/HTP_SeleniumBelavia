package by.htp.belavia.entity;

import java.time.LocalDate;

public class ReturnTicket extends Ticket {

	private LocalDate ReturnDate;

	public ReturnTicket() {

	}

	public ReturnTicket(String departureCountry, String arrivalCountry, LocalDate date, double cost, String currency,
			LocalDate returnDate) {
		super(departureCountry, arrivalCountry, date, cost, currency);
		ReturnDate = returnDate;
	}

	public LocalDate getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		ReturnDate = returnDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ReturnDate == null) ? 0 : ReturnDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReturnTicket other = (ReturnTicket) obj;
		if (ReturnDate == null) {
			if (other.ReturnDate != null)
				return false;
		} else if (!ReturnDate.equals(other.ReturnDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Return ticket: " + super.getDepartureCountry() + "-" + super.getArrivalCountry() + "-"
				+ super.getDepartureCountry() + ". Date: " + super.getDate() + ", return date: " + ReturnDate
				+ ". Cost: " + super.getCost() + " " + super.getCurrency();
	}

}
