//I am the sole author of the work in this repository.

import structure5.*;

/**
 * This class creates a MultiQueue object that also initializes a BusinessSimulation
 object. Customers in a queue are immediately added to a service queue when upon its arrival.
 */
public class MultiQueue extends BusinessSimulation{
  //pre: numCustomers, numServicePoints, maxEventStart, and seed are valid integers
  //post: creates a OneQueue object and populates servicePoints with empty queues
  public MultiQueue(int numCustomers, int numServicePoints, int maxEventStart, int seed){
    super(numCustomers, numServicePoints, maxEventStart, seed);
    for(int i = 0; i < servicePoints.capacity(); i++){
      servicePoints.add(new QueueVector<Customer>());
    }
  }

  //pre: none
  //post: performs all necessary functions in a time step and returns true if
  // the simulation is over, false if not
	//$(-0.5) Could we break this up into more functions that are simpler?
  public boolean step(){
    //boolean variable to represent whether or not eventQueue is empty
    boolean emptyLine = false;
    //if statement that runs only if there are customers in eventQueue
    if(eventQueue.size() != 0){
      while(!eventQueue.isEmpty() && eventQueue.getFirst().getArrivalTime() <= time){
        //finds the shortest service queue and adds the highestPriority customer
        //in eventQueue to it
        int shortest = Integer.MAX_VALUE;
        int shortIndex = 0;
        for(int i = 0; i < servicePoints.size(); i++){
          if(servicePoints.get(i).size() < shortest){
            shortest = servicePoints.get(i).size();
            shortIndex = i;
          }
        }
        servicePoints.get(shortIndex).add(eventQueue.remove());
      }
    }
    else{
      emptyLine = true;
    }

    //counter for number of empty service queues
    int emptyService = 0;
    //decreases the serviceTimeAmt for each customer in a service queue and if
    //the customer has a serviceTimeAmt of 0, removes it from the queue;
    //also updates totalWaitTime
    for(int i = 0; i < servicePoints.size(); i++){
      Queue<Customer> teller = servicePoints.get(i);
      if(teller.size() > 0){
        teller.getFirst().decServiceTimeAmt();
        if(teller.getFirst().getServiceTimeAmt() == 0){
          teller.remove();
          if(teller.size() > 0){
            totalWaitTime = totalWaitTime + (time - teller.getFirst().getArrivalTime());
          }
        }
      }
      else{
        emptyService++;
      }
    }

    time++;
    //if eventQueue is empty and all queues in servicePoints are empty, return true
    if(emptyService == servicePoints.size() && emptyLine){
      return true;
    }
    return false;
  }
}
