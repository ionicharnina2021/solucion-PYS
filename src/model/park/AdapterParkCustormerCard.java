package model.park;

import model.customer.CustomerCard;

public class AdapterParkCustormerCard {

	//PROF: esto es la pregunta numero 2
	public void insertFairgorundRate(Park park,CustomerCard customer) {
		park.changeRideRate(customer.getFairGround(), customer.getRate());
	}
}
