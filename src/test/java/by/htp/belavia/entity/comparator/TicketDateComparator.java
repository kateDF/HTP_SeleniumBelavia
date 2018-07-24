package by.htp.belavia.entity.comparator;

import java.util.Comparator;

import by.htp.belavia.entity.Ticket;

public class TicketDateComparator implements Comparator<Ticket> {

	@Override
	public int compare(Ticket t1, Ticket t2) {
		return t1.getDate().compareTo(t2.getDate());
	}

}
