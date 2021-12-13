package model.park;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import modelAtractions.FairGround;
import modelAtractions.Performance;
import modelAtractions.SuperFairGround;

public class Statistics implements Observer {

	private List<FairGround> fairgrounds;
	private Map<FairGround, FairGroundObservable> rateAtractions;
	private float total = 0;

	public Statistics(List<FairGround> fairgrounds) {
		super();
		rateAtractions = new HashMap<FairGround, FairGroundObservable>();
		this.fairgrounds =fairgrounds;
		//PROF respuesta a la pregunta 3
		updateMap();

	}

	public void changeRate(FairGround fairGround, float rate) {
		FairGroundObservable fairGroundObservable = rateAtractions.get(fairGround);
		fairGroundObservable.incrementOneValoration(rate);
	}

	public void updateMap() {
		fairgrounds.stream().forEach((fairg) -> {
			rateAtractions.put(fairg, new FairGroundObservable(fairg, this));
		});
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		ConverterFairGorund converter=(ConverterFairGorund) arg1;
		FairGround changeFairGround = converter.changeFairGround();
		FairGroundObservable fgo=(FairGroundObservable) arg0;
		fgo.setFairGround(changeFairGround);
		rateAtractions.put(changeFairGround, fgo);
		changeFairGroundMap(converter.getFairGround(), changeFairGround);
	}

	private void changeFairGroundMap(FairGround fg, FairGround otherFairGround) {
		int indexOf = fairgrounds.indexOf(fg);
		fairgrounds.set(indexOf, otherFairGround);
		rateAtractions.remove(fg);
		
	}

	public float getCurrentAverageRate() {
		total = 0;
		//PROF respuesta a la pregunta 3
		fairgrounds.forEach((a) -> {
			total+=rateAtractions.get(a).getCurrentValoration();
		});
		return total / fairgrounds.size();
	}

}
