
class Store {
	
	final int MAX_NUM_OF_PRODUCT_TYPES = 5;
	int balance;			
	ProductType[] productTypeArray;
	Store() {
		balance = 0;
		productTypeArray = new ProductType[MAX_NUM_OF_PRODUCT_TYPES];
	}
	boolean addProductType(ProductType productType){
		if (productType != null){
			for (int i=0; i< MAX_NUM_OF_PRODUCT_TYPES; i++){
				if (productTypeArray[i] == null){
					productTypeArray[i] = productType;
					return true;
				}
			}
		}	
		return false;
	}	
	int makePurchase(Customer customer, String productTypeName, int quantity){
		if (customer == null || productTypeName == null)
			return 0;
		int purchaseQuantity = 0;
		if (sellsProductsOfType(productTypeName) == true){
			int index = 0;
			while (productTypeArray[index].name!= productTypeName)     // locates the suitable product by name
				index ++;
			if (customer.maximumAffordableQuantity(productTypeArray[index])> quantity) // takes the mini. between
				purchaseQuantity = quantity;                                           // requested quantity and  
			else                                                                       // affordable quantity 
				purchaseQuantity = customer.maximumAffordableQuantity(productTypeArray[index]);
			balance +=  purchaseQuantity * productTypeArray[index].profitPerUnit();        // changes store balance
			customer.makePurchase(purchaseQuantity, productTypeArray[index]);              // activates customer purchase
		}
		return purchaseQuantity;
	}
	boolean removeProductTypeFromStore(String productTypeName){
		if (productTypeName != null)
		{	
			for (int i=0; i< MAX_NUM_OF_PRODUCT_TYPES; i++){
				if (productTypeArray[i] != null){
					if (productTypeArray[i].name == productTypeName){
						productTypeArray[i] = null;
						return true;
					}
				}
			}
		}
		return false;
	}
	boolean sellsProductsOfType(String productTypeName){
		if (productTypeName != null)
		{	
			for (int i=0; i< MAX_NUM_OF_PRODUCT_TYPES; i++){
				if(productTypeArray[i] != null){
					if (productTypeArray[i].name == productTypeName)
						return true;
				}
			}
		}
		return false;
	}
	String 	stringRepresentation(){
		String storeLog; 
		storeLog = "Store has a balance of " + balance + ", and the following products:";
		for (int i=0; i< MAX_NUM_OF_PRODUCT_TYPES; i++){
			if (productTypeArray[i]!= null){
					storeLog += "\n" + productTypeArray[i].stringRepresentation();
					}
		}
		return storeLog;		
		
	}
}
