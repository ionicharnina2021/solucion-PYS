package model.customer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import control.Comment;
import control.WorkingDay;
import model.park.Fraction;
import model.park.Statistics;
import modelAtractions.FairGround;

public class BiasCustomer implements Runnable {
	// las ganas que tiene de montarse en otra atraccion depende de lo bien
	// que se lo este pasando, aunque todo tiene un limite de X veces
	private int maxRides = 20;
	private int actualRides = 0;
	private float maxRate = 10;
	public static final float minimumEnjoyment = 5f;
	private Fraction currentEnjoyment = new Fraction();
	private WorkingDay workingDay;
	private long breakTime=10;

	public BiasCustomer(int maxRides,WorkingDay workingDay) {
		super();
		this.maxRides = maxRides;
		currentEnjoyment.incrementOneValoration(10);
		this.workingDay = workingDay;
	}

	@Override
	public void run() {
		while (actualRides++ < maxRides && isStillExcited()) {
			CustomerCard takeRide = takeRide();
			currentEnjoyment.incrementOneValoration(takeRide.getRate());
			workingDay.anotateCard(takeRide);
			try {
				Thread.sleep(breakTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		workingDay.finalizeVisitor(this);
	}

	public CustomerCard takeRide() {
		int randomFairGround = new Random().nextInt(workingDay.getFairGroundsSize());
		FairGround fairGround = workingDay.getFairGround(randomFairGround);
		float rate = new Random().nextFloat() * maxRate;
		CustomerCard customerCard = new CustomerCard(fairGround, rate);
		this.actualRides++;
		return customerCard;
	}

	public float getCurrentValue() {
		return currentEnjoyment.getCurrentValue();
	}

	public boolean isStillExcited() {
		return this.currentEnjoyment.getCurrentValue() >= minimumEnjoyment;
	}

}
