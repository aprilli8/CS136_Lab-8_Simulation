//I am the sole author of the work in this repository.

import java.util.Random;
import structure5.*;

/**
 * This abstract class is intended to represent a scenario in which a queue of
 multiple customers are to be served by a number of service tellers.
 */
public abstract class BusinessSimulation {

	/* sequence of customers, prioritized by randomly-generated event time */
	protected PriorityQueue<Customer> eventQueue;

	/* series of service points where customers queue and are served */
	protected Vector<Queue<Customer>> servicePoints;

	/* current time step in the simulation */
	protected int time;

	/* seed for Random() so that simulation is repeatable */
	protected int seed;

	/* sum of the wait times for all customers */
	protected int totalWaitTime;

	/* keeps track of the total number of customers */
	protected int totalCustomers;

	/* Used to bound the range of service times that Customers require */
	public static final int MIN_SERVICE_TIME = 5;
	public static final int MAX_SERVICE_TIME = 30;

	/**
	 * Creates a BusinessSimulation.
	 * @post A businessSimulation object is created
	 *
	 * @param numCustomers number of random customers that appear in the simulation
	 * @param numServicePoints number of tellers in this simulation
	 * @param maxEventStart latest timeStep that a Customer may appear in the simulation
	 * @param seed used to seed a Random() so that simulation is repeatable.
	 */
	public BusinessSimulation(int numCustomers, int numServicePoints, int maxEventStart, int seed){
		servicePoints = new Vector<Queue<Customer>>(numServicePoints);
		eventQueue = generateCustomerSequence(numCustomers, maxEventStart, seed);
		time = 0;
		totalWaitTime = 0;
		totalCustomers = numCustomers;
	}

	/**
	 * Generates a sequence of Customer objects, stored in a PriorityQueue.
	 * Customer priority is determined by arrival time
	 * @param numCustomers number of customers to simulate
	 * @param maxEventStart maximum timeStep that a customer arrives
	 *      in @eventQueue
	 * @param seed use Random(seed) to make customer sequence repeatable
	 * @pre numCustomers, maxEventStart, and seed are valid integers
	 * @post returns a PriorityQueue of Customers that were generated with
	 * 				random arrival times and service time amounts
	 * @return A PriorityQueue that represents Customer arrivals,
	 *         ordered by Customer arrival time
	 */
	public static PriorityQueue<Customer> generateCustomerSequence(int numCustomers, int maxEventStart, int seed){
		VectorHeap<Customer> tempQueue = new VectorHeap<Customer>();
		Random rand = new Random(seed);
		for(int i = 0; i < numCustomers; i++){
			tempQueue.add(new Customer(rand.nextInt(maxEventStart), rand.nextInt(MAX_SERVICE_TIME-MIN_SERVICE_TIME)+MIN_SERVICE_TIME));
		}
		return tempQueue;
	}

	/**
	 * Advances 1 time step through the simulation.
	 *
	 * @post the simulation has advanced 1 time step
	 * @return true if the simulation is over, false otherwise
	 */
	abstract public boolean step();

	/**
	 * @return a string representation of the simulation
	 */
	public String toString() {
		// TODO: modify if needed.
		String str = "Time: " + time + "\n";
		str = str + "Event Queue: ";
		if (eventQueue != null) {
			str = str + eventQueue.toString();
		}
		str = str + "\n";

		if (servicePoints != null)  {
			for (Queue<Customer> sp : servicePoints) {
				str = str + "Service Point: " + sp.toString() + "\n";
			}
		}
		str = str + "Average Wait Time: " + (double)totalWaitTime/totalCustomers + "\n";

		return str;
	}
}
