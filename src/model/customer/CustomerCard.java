package model.customer;

import modelAtractions.FairGround;

public class CustomerCard {

	private FairGround fairGround;
	private float rate;
	
	public CustomerCard(FairGround fairGround, float rate) {
		super();
		this.fairGround = fairGround;
		this.rate = rate;
	}

	public FairGround getFairGround() {
		return fairGround;
	}

	public float getRate() {
		return rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fairGround == null) ? 0 : fairGround.hashCode());
		return result;
	}

	//PROF: Para contestar a la pregunta Uno
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerCard other = (CustomerCard) obj;
		if (fairGround == null) {
			if (other.fairGround != null)
				return false;
		} else if (!fairGround.equals(other.fairGround))
			return false;
		return true;
	}

	
	
	
}
