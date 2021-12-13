package model.customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import modelAtractions.FairGround;

public class CustomerVisit {

	Set<CustomerCard> myRidesList;

	public CustomerVisit() {
		super();
		myRidesList=new HashSet();
	}
	
	public boolean insertAtraccionCard(FairGround fairGround,float rate) {
		return myRidesList.add(new CustomerCard(fairGround, rate));
	}
	
	public List<CustomerCard> getCustomerCards(){
		return myRidesList.stream().collect(Collectors.toList());
	}
}
