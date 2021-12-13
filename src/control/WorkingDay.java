package control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import model.customer.BiasCustomer;
import model.customer.CustomerCard;
import model.park.AdapterParkCustormerCard;
import model.park.Fraction;
import model.park.Park;
import modelAtractions.FairFactory;
import modelAtractions.FairGround;

public class WorkingDay implements Runnable {

	private Park park;
	private Long totalRides=0l, totalVisitors=0l,happyVisitors=0l;
	private final int maxConcurrentVisitors=100;
	private final Long maxRides=1000l;
	private float correntRate = 5;
	private ArrayList<BiasCustomer> concurrentVisitors=new ArrayList<>();
	private Fraction currentEnjoyment=new Fraction();

	public WorkingDay() throws Exception {
		super();
		park = new Park();
		createPark();
	}

	@Override
	public void run() {
		ExecutorService executorServiceCustomer = Executors.newCachedThreadPool();
		while (!endOfDay()) {
			for (int i = 0; i < calculateNewVisitors(); i++) {
				BiasCustomer biasCustomer = new BiasCustomer(20, this);
				executorServiceCustomer.execute(biasCustomer);
				concurrentVisitors.add(biasCustomer);
				totalVisitors++;
			}
			Comment.talk("visitantes concurrentes "+concurrentVisitors.size()+" y totales "+totalVisitors);
		}
		
		System.out.println("happy users "+currentEnjoyment.getCurrentValue());
		System.out.println("fairgrounds valoration "+park.getCurrentAverageRate());
		executorServiceCustomer.shutdown();
	}
	
	private void createPark() throws Exception {
		park.addFairGround(FairFactory.Performance);
		park.addFairGround(FairFactory.Performance);
		park.addFairGround(FairFactory.RollerCoaster);
		park.addFairGround(FairFactory.RollerCoaster);
		park.addFairGround(FairFactory.Show);
		park.addFairGround(FairFactory.Show);
	}

	private int calculateNewVisitors() {
		return maxConcurrentVisitors-concurrentVisitors.size();
	}

	private boolean endOfDay() {
		return totalRides>=maxRides;
	}

	public CustomerCard anotateCard(CustomerCard takeRide) {
		totalRides++;
		new AdapterParkCustormerCard().insertFairgorundRate(park,takeRide);
		return takeRide;
	}

	public void endVisitation(BiasCustomer biasCustomer) {
		addHappyUser(biasCustomer);
		concurrentVisitors.remove(biasCustomer);
	}

	private void addHappyUser(BiasCustomer biasCustomer) {
		if(biasCustomer.getCurrentValue()>5) {
			happyVisitors++;
		}
		
	}

	public FairGround getFairGround(int i) {
		return park.getFairGround(i);
	}

	public int getFairGroundsSize() {
		return park.getFairGroundsSize();
	}

	public void finalizeVisitor(BiasCustomer biasCustomer) {
			concurrentVisitors.remove(biasCustomer);
			currentEnjoyment.incrementOneValoration(biasCustomer.getCurrentValue());
			endVisitation(biasCustomer);
	}
}
