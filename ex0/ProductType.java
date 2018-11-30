
class ProductType {
	int customerPrice, storePrice; 
	String name;
	
	ProductType(String productName, int productStorePrice, int productCustomerPrice){
		this.name = productName; 
		this.storePrice = productStorePrice;
		this.customerPrice = productCustomerPrice;		
	}
	int profitPerUnit(){
		return customerPrice - storePrice;
	}
	String stringRepresentation(){
		return ("[" + name + "," + storePrice + "," + customerPrice + "]");
	}
	
}
