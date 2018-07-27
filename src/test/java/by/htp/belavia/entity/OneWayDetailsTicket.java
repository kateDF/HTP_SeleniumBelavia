package by.htp.belavia.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class OneWayDetailsTicket extends Ticket {

	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private double costWithTaxes;
	private TicketClassEnum ticketClass;

	public OneWayDetailsTicket() {

	}

	public OneWayDetailsTicket(String departureCountry, String arrivalCountry, LocalDate date, double cost,
			String currency, LocalTime departureTime, LocalTime arrivalTime, double costWithTaxes,
			TicketClassEnum ticketClass) {
		super(departureCountry, arrivalCountry, date, cost, currency);
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.costWithTaxes = costWithTaxes;
		this.ticketClass = ticketClass;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getCostWithTaxes() {
		return costWithTaxes;
	}

	public void setCostWithTaxes(double costWithTaxes) {
		this.costWithTaxes = costWithTaxes;
	}

	public TicketClassEnum getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(TicketClassEnum ticketClass) {
		this.ticketClass = ticketClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(costWithTaxes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((ticketClass == null) ? 0 : ticketClass.hashCode());
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
		OneWayDetailsTicket other = (OneWayDetailsTicket) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (Double.doubleToLongBits(costWithTaxes) != Double.doubleToLongBits(other.costWithTaxes))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (ticketClass == null) {
			if (other.ticketClass != null)
				return false;
		} else if (!ticketClass.equals(other.ticketClass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket: " + super.getDepartureCountry() + " - " + super.getArrivalCountry() + ", date: "
				+ super.getDate() + ". Time:" + departureTime + " - " + arrivalTime + ". Cost: " + super.getCost() + " "
				+ super.getCurrency() + ", cost With Taxes: " + costWithTaxes + " " + super.getCurrency()
				+ ". TicketClass: " + ticketClass.getValue();
	}

}
