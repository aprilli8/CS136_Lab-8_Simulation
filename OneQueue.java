//I am the sole author of the work in this repository.

import structure5.*;

/**
 * This class creates a OneQueue object that also initializes a BusinessSimulation
 object. Customers in a queue are only added to a service queue when one is free.
 */
public class OneQueue extends BusinessSimulation{
  //pre: numCustomers, numServicePoints, maxEventStart, and seed are valid integers
  //post: creates a OneQueue object and populates servicePoints with empty queues
  public OneQueue(int numCustomers, int numServicePoints, int maxEventStart, int seed){
    super(numCustomers, numServicePoints, maxEventStart, seed);
    for(int i = 0; i < servicePoints.capacity(); i++){
      servicePoints.add(new QueueVector<Customer>());
    }
  }

  //pre: none
  //post: performs all necessary functions in a time step and returns true if
  // the simulation is over, false if not
  public boolean step(){
    //boolean variable to represent whether or not eventQueue is empty
    boolean emptyLine = false;
    //if statement that runs only if there are customers in eventQueue
    if(eventQueue.size() != 0){
      while(!eventQueue.isEmpty() && eventQueue.getFirst().getArrivalTime() <= time){
        //finds the first empty queue in servicePoints vector
        int i = 0;
        while(i < servicePoints.size() && servicePoints.get(i).size() != 0){
          i++;
        }
        //adds the highest priority customer in eventQueue to empty queue in
        //servicePoints and adds the customer's wait time to totalWaitTime
        if(i < servicePoints.size()){
          servicePoints.get(i).add(eventQueue.remove());
          totalWaitTime = totalWaitTime + (time - servicePoints.get(i).getFirst().getArrivalTime());
        } else {
          break;
        }
      }
    }
    else{
      emptyLine = true;
    }

    //counter for number of empty service queues
    int emptyService = 0;
    //decreases the serviceTimeAmt for each customer in a service queue and if
    //the customer has a serviceTimeAmt of 0, removes it from the queue
    for(int i = 0; i < servicePoints.size(); i++){
      Queue<Customer> line = servicePoints.get(i);
      if(line.size() != 0){
        line.getFirst().decServiceTimeAmt();
        if(line.getFirst().getServiceTimeAmt() == 0){
          line.remove();
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
