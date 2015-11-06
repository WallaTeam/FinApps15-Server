package rest.finapps;

import static rest.finapps.PhoneType.*;

/**
 * A phone number 
 *
 */
public class PhoneNumber {
	
	private String number;
	private PhoneType type = HOME;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public PhoneType getType() {
		return type;
	}
	public void setType(PhoneType type) {
		this.type = type;
	}

}
