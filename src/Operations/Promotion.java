package Operations;

import VehicleManagement.Vehicle;

public class Promotion {
	public enum TYPE {
		COUPON, DISCOUNT, OTHER
	}
	
	public TYPE type;
	public long startTime;
	public long stopTime;
	public float value;
	public int branchId;
	public int customerId;
	public Vehicle.TYPE vehicleType;
}
