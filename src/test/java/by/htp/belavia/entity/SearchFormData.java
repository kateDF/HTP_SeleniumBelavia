package by.htp.belavia.entity;

import java.time.LocalDate;

public class SearchFormData {

	private String departureCountry;
	private String arrivalCountry;
	private LocalDate departureDateStart;
	private LocalDate departureDateEnd;
	private boolean isReturnTicket;
	private LocalDate returnDateStart;
	private LocalDate returnDateEnd;

	public SearchFormData() {

	}

	public SearchFormData(String departureCountry, String arrivalCountry, LocalDate departureDateStart,
			boolean isReturnTicket) {
		this.departureCountry = departureCountry;
		this.arrivalCountry = arrivalCountry;
		this.departureDateStart = departureDateStart;
		this.isReturnTicket = isReturnTicket;
	}

	public SearchFormData(String departureCountry, String arrivalCountry, LocalDate departureDateStart,
			LocalDate departureDateEnd, boolean isReturnTicket) {
		this.departureCountry = departureCountry;
		this.arrivalCountry = arrivalCountry;
		this.departureDateStart = departureDateStart;
		this.departureDateEnd = departureDateEnd;
		this.isReturnTicket = isReturnTicket;
	}

	public SearchFormData(String departureCountry, String arrivalCountry, LocalDate departureDateStart,
			LocalDate departureDateEnd, boolean isReturnTicket, LocalDate returnDateStart, LocalDate returnDateEnd) {
		this.departureCountry = departureCountry;
		this.arrivalCountry = arrivalCountry;
		this.departureDateStart = departureDateStart;
		this.departureDateEnd = departureDateEnd;
		this.isReturnTicket = isReturnTicket;
		this.returnDateStart = returnDateStart;
		this.returnDateEnd = returnDateEnd;
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

	public LocalDate getDepartureDateStart() {
		return departureDateStart;
	}

	public void setDepartureDateStart(LocalDate departureDateStart) {
		this.departureDateStart = departureDateStart;
	}

	public LocalDate getDepartureDateEnd() {
		return departureDateEnd;
	}

	public void setDepartureDateEnd(LocalDate departureDateEnd) {
		this.departureDateEnd = departureDateEnd;
	}

	public boolean isReturnTicket() {
		return isReturnTicket;
	}

	public void setReturnTicket(boolean isReturnTicket) {
		this.isReturnTicket = isReturnTicket;
	}

	public LocalDate getReturnDateStart() {
		return returnDateStart;
	}

	public void setReturnDateStart(LocalDate returnDateStart) {
		this.returnDateStart = returnDateStart;
	}

	public LocalDate getReturnDateEnd() {
		return returnDateEnd;
	}

	public void setReturnDateEnd(LocalDate returnDateEnd) {
		this.returnDateEnd = returnDateEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalCountry == null) ? 0 : arrivalCountry.hashCode());
		result = prime * result + ((departureCountry == null) ? 0 : departureCountry.hashCode());
		result = prime * result + ((departureDateEnd == null) ? 0 : departureDateEnd.hashCode());
		result = prime * result + ((departureDateStart == null) ? 0 : departureDateStart.hashCode());
		result = prime * result + (isReturnTicket ? 1231 : 1237);
		result = prime * result + ((returnDateEnd == null) ? 0 : returnDateEnd.hashCode());
		result = prime * result + ((returnDateStart == null) ? 0 : returnDateStart.hashCode());
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
		SearchFormData other = (SearchFormData) obj;
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
		if (departureDateEnd == null) {
			if (other.departureDateEnd != null)
				return false;
		} else if (!departureDateEnd.equals(other.departureDateEnd))
			return false;
		if (departureDateStart == null) {
			if (other.departureDateStart != null)
				return false;
		} else if (!departureDateStart.equals(other.departureDateStart))
			return false;
		if (isReturnTicket != other.isReturnTicket)
			return false;
		if (returnDateEnd == null) {
			if (other.returnDateEnd != null)
				return false;
		} else if (!returnDateEnd.equals(other.returnDateEnd))
			return false;
		if (returnDateStart == null) {
			if (other.returnDateStart != null)
				return false;
		} else if (!returnDateStart.equals(other.returnDateStart))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Airline Ticket: departureCountry: " + departureCountry + ", arrivalCountry: " + arrivalCountry
				+ ". DepartureDatePeriod: " + departureDateStart + " to " + departureDateEnd + ", isReturnTicket: "
				+ isReturnTicket + "."
				+ (returnDateStart != null ? " ReturnDatePeriod:" + returnDateStart + " to " + returnDateEnd : "");
	}

}
