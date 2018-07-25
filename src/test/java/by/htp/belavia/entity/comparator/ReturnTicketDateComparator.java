package by.htp.belavia.entity.comparator;

import java.util.Comparator;

import by.htp.belavia.entity.ReturnTicket;

public class ReturnTicketDateComparator implements Comparator<ReturnTicket> {

	@Override
	public int compare(ReturnTicket rt1, ReturnTicket rt2) {
		int compareDate = rt1.getDate().compareTo(rt2.getDate());
		return compareDate != 0 ? compareDate : rt1.getReturnDate().compareTo(rt2.getReturnDate());
	}

}
