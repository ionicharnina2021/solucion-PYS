package control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		ExecutorService executorService= Executors.newCachedThreadPool();
		WorkingDay workingDay = null;
		try {
			workingDay = new WorkingDay();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.execute(workingDay);
		executorService.shutdown();
	}
}
