package model.park;

import java.util.Observable;

import modelAtractions.FairGround;
import utils.Comment;

public class FairGroundObservable extends Observable {
	private FairGround fairGround;
	private Fraction fraction;
	private double changeValueHigh = 7;
	private boolean changeHigh = false;
	private double changeValueLow = 4;
	private boolean changeLow = true;

	public FairGroundObservable(FairGround fairGround, Statistics statistics) {
		super();
		this.fairGround = fairGround;
		this.fraction = new Fraction();
		this.fraction.incrementOneValoration(5);
		addObserver(statistics);
	}

	// PROF posible implementacion de la pregunta 5 
	public void incrementOneValoration(float rate) {
		fraction.incrementOneValoration(rate);
		float currentValue = fraction.getCurrentValue();
		Comment.talk("current rate " + currentValue);
		boolean high = !changeHigh && currentValue >= changeValueHigh;
		boolean low = !changeLow && currentValue <= changeValueLow;
		if (high||low) {
			setChanged();
			notifyObservers(new ConverterFairGorund(fairGround, high));
			changeHigh=!changeHigh;
			changeLow=!changeLow;
		}
	}

	private void notification() {

	}

	public boolean isChangeHigh() {
		return changeHigh;
	}

	public void setFairGround(FairGround fairGround) {
		this.fairGround = fairGround;
	}

	public float getCurrentValoration() {
		return this.fraction.getCurrentValue();
	}
}
