package by.htp.belavia.entity;

public enum TicketClassEnum {

	ECONOMY_PROMOTION("Economy Promotion"), ECONOMY_RESTRICTED("Economy Restricted"), ECONOMY_SEMI_FLEXIBLE(
			"Economy Semi-Flexible"), ECONOMY_FLEXIBLE("Economy Flexible"), BUSINESS("Bussiness");

	private String value;

	TicketClassEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
