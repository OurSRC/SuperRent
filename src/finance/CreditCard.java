package finance;

public class CreditCard {
	public enum TYPE {
		VISA, MASTER, AMERICAN_EXPRESS
	}
	public int creditCardId;
	public int customerId;
	public String cardNumber;
	public String holderName;
	public String expiry;
	public TYPE type;
	
	public boolean create(){
		return false;
	}
	public boolean delete(){
		return false;
	}
	public CreditCard getByCreditCardId(){
		return null;
	}
}
