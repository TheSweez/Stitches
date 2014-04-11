
public class Currency {

	private String name = null;
	private int amount = 0;
	private int totalAmount = 0;
	
	public Currency(String name) {
		this.name = name;
		
	}
	
	public Currency() {
			
	}

	public int getAmount() {
		return amount;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	
	public void add(int number) {
		amount = number + amount;
		totalAmount = number + amount;
	}
	public void subtract(int number) {
		amount =  amount - number;
	}


}
