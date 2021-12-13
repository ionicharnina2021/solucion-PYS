package modelAtractions;

import java.util.function.Supplier;

import modelAtractions.FairGround;
import modelAtractions.Performance;
import modelAtractions.RollerCoaster;
import modelAtractions.Show;

//PROF solucion a la pregunta 2
public enum FairFactory {
	Performance(()->{return new Performance();}),
	RollerCoaster(()->{return new RollerCoaster();}),
	Show(()->{return new Show();});
	
	private Supplier<FairGround> supplier;
	
	
	private FairFactory(Supplier<FairGround> supplier) {
		this.supplier = supplier;
	}


	public FairGround createFairGround() {
		return this.supplier.get();
	}
}
