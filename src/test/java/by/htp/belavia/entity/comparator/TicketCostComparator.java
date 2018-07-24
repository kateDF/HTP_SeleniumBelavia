package by.htp.belavia.entity.comparator;

import java.util.Comparator;

import by.htp.belavia.entity.Ticket;

public class TicketCostComparator implements Comparator<Ticket> {

	@Override
	public int compare(Ticket t1, Ticket t2) {
		return Double.compare(t1.getCost(), t2.getCost());
	}

}
