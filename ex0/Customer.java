
class Customer {
	
	String name;
	String address;
	int balance;
	String log;

	Customer(String customerName, String customerAddress, int customerBalance){
		this.name = customerName;
		this.address = customerAddress; 
		this.balance = customerBalance;
		log = "Shopping log for customer: " + customerName;
	}
	int maximumAffordableQuantity(ProductType productType){
		if (productType != null)
			return balance / productType.customerPrice;
		return 0;
	}
 	boolean canAfford(int quantity, ProductType productType){
 		if (productType != null){	
 			if (maximumAffordableQuantity(productType) < quantity)
 				return false;
 		}	
 		return true;
  	}
	void makePurchase(int quantity, ProductType productType){
		if (productType != null){	
			if ( canAfford (quantity, productType) == true && (quantity > 0) ){ 
				balance -= quantity * productType.customerPrice;
				log += "\n" + quantity + " " + productType.name;
 			}
		}
 	}
 	String getPurchaseLog(){ 
 		return log;
 	}
  	String stringRepresentation(){
 		return ("[" + name + "," + address + "," + balance + "]");
 	}
 }