// I am the sole author of the work in this repository.

import structure5.*;
import java.util.Random;
import java.util.Scanner;

/**
 * This class runs simulations with OneQueue and MultiQueue class objects. There
 is also an option for manual creation of simulations.
 */
public class Experiments {
  public static void main(String[] args){
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);
    System.out.println("To run created simulations, enter 1. To create a simulation, enter 2.");
    int userChoice = scan.nextInt();
    if(userChoice == 1){
      //Simulation 1: 14 customers, 3 service points, 10 max arrival time, seed of 2
      OneQueue one1 = new OneQueue(14, 3, 10, 2);
      System.out.println(one1);
      while(!one1.step()){
        //System.out.println(one1);
      }
      System.out.println(one1);

      MultiQueue multi1 = new MultiQueue(14, 3, 10, 2);
      System.out.println(multi1);
      while(!multi1.step()){
        //System.out.println(multi1);
      }
      System.out.println(multi1);


      //Simulation 2: 100 customers, 15 service points, 25 max arrival time, seed of 11
      OneQueue one2 = new OneQueue(100, 15, 25, 11);
      System.out.println(one2);
      while(!one2.step()){
        //System.out.println(one2);
      }
      System.out.println(one2);

      MultiQueue multi2 = new MultiQueue(100, 15, 25, 11);
      System.out.println(multi2);
      while(!multi2.step()){
        //System.out.println(multi2);
      }
      System.out.println(multi2);


      //Simulation 3: 14 customers, 3 service points, 10 max arrival time, seed of 2
      OneQueue one3 = new OneQueue(150, 8, 20, 18);
      System.out.println(one3);
      while(!one3.step()){
        //System.out.println(one3);
      }
      System.out.println(one3);

      MultiQueue multi3 = new MultiQueue(150, 8, 20, 18);
      System.out.println(multi3);
      while(!multi3.step()){
        //System.out.println(multi3);
      }
      System.out.println(multi3);
    }
    else if(userChoice == 2){
      //user's manual input for a custom simulation
      System.out.print("Enter number of customers: ");
      int userNumCust = scan.nextInt();
      System.out.print("Enter number of service points: ");
      int userNumServ = scan.nextInt();
      System.out.print("Enter maximum event start time: ");
      int userMaxStart = scan.nextInt();
      System.out.print("Enter seed number: ");
      int userSeed = scan.nextInt();
      System.out.print("For a One-Queue Simulation, enter 1. For a Multi-Queue Simulation, enter 2.");
      int userNum = scan.nextInt();
      //choice between a one-queue simulation and a multi-queue simulation
      if(userNum == 1){
        OneQueue userOne = new OneQueue(userNumCust, userNumServ, userMaxStart, userSeed);
        System.out.println(userOne);
        while(!userOne.step()){
          System.out.println(userOne);
        }
      }
      else if(userNum == 2){
        MultiQueue userMulti = new MultiQueue(userNumCust, userNumServ, userMaxStart, userSeed);
        System.out.println(userMulti);
        while(!userMulti.step()){
          System.out.println(userMulti);
        }
      }
      else{
        System.out.println("Not a valid input.");
      }
    }
    else{
      System.out.println("Not a valid input.");
    }
  }
}
