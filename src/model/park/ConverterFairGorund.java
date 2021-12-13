package model.park;

import modelAtractions.FairGround;
import modelAtractions.SuperFairGround;

public class ConverterFairGorund {
	private FairGround fairGround;
	private boolean high;

	public ConverterFairGorund(FairGround fairGround, boolean high) {
		super();
		this.fairGround = fairGround;
		this.high = high;
	}

	public boolean isHigh() {
		return high;
	}

	public FairGround getFairGround() {
		return fairGround;
	}

	public FairGround changeFairGround() {
		if(high) {
			return new SuperFairGround(fairGround);
		}
		return fairGround.dismantle();
	}
}
