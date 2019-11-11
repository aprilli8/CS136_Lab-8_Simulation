//I am the sole author of the work in this repository.

import structure5.*;

/**
 * This class creates customer objects that can be compared to each other based
 on their arrival times.
 */
public class Customer implements Comparable<Customer> {
	private int arrivalTime;
	private int serviceTimeAmt;

	/**
	 * Creates a Customer that arrives at time step @eventTime and
	 * requires @serviceTime time steps to be satisfied after
	 * arriving at a service point.
	 * @param eventTime time step that this Customer appears in the
	 *        simulation
	 * @param serviceTime number of time steps required to service this
	 *        Customer.
	 * pre: eventTime and serviceTime are valid integers
	 * post: a Customer object is created
	 */
	public Customer(int eventTime, int serviceTime){
		arrivalTime = eventTime;
		serviceTimeAmt = serviceTime;
	}

	/**
	 * Compares Customers by arrival time
	 * @param other Customer to compare against this.
	 * pre: other is a valid Customer
	 * post: returns an integer where 1 represents a situation in which the arrival
	 time of the current customer is greater, 0 means that they are equal, and -1
	 means that the current customer's arrival time is smaller than the other
	 */
	public int compareTo(Customer other) {
		if(arrivalTime > other.getArrivalTime()){
			return 1;
		} else if(arrivalTime == other.getArrivalTime()){
			return 0;
		} else{
			return -1;
		}
	}

	//pre: none
	//post: returns the int value in the arrivalTime variable
	public int getArrivalTime(){
		return arrivalTime;
	}

	//pre: none
	//post: returns the int value in the serviceTimeAmt variable
	public int getServiceTimeAmt(){
		return serviceTimeAmt;
	}

	//pre: none
	//post: decrements the serviceTimeAmt by 1
	public void decServiceTimeAmt(){
		serviceTimeAmt--;
	}

	//pre: none
	//post: returns a String representation of the customer object
	public String toString() {
		return "Customer - arrival time: " + arrivalTime + ", service time: " + serviceTimeAmt + ".";
	}
}
