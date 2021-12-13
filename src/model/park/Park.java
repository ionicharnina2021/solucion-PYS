package model.park;

import java.util.LinkedList;
import java.util.List;

import modelAtractions.FairFactory;
import modelAtractions.FairGround;

public class Park {
	private List<FairGround> fairGrounds;
	private Statistics statistics;
	
	public Park() {
		super();
		fairGrounds = new LinkedList<FairGround>();
		statistics=new Statistics(fairGrounds);
	}

	public void addFairGround(FairFactory type) throws Exception {
		fairGrounds.add(type.createFairGround());
		statistics.updateMap();
	}
	
	public void changeRideRate(FairGround fairGround,float rate) {
		statistics.changeRate(fairGround,rate);
	}

	public List<FairGround> getFairGrounds() {
		return fairGrounds;
	}

	public float getCurrentAverageRate() {
		return statistics.getCurrentAverageRate();
	}

	public FairGround getFairGround(int i) {
		return fairGrounds.get(i);
	}

	public int getFairGroundsSize() {
		return fairGrounds.size();
	}
}
