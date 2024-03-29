/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acmeandroidca3;

/**
 * A class to define the rules applied to batteries
 * 
 * @author WailuiMa
 */

public class Battery {

  private static final double capacity = 8;
  private static final double recoverRate = 2.66;
  private static int currentVoltage = 0;

  public static double getCapacity() {
    return capacity;
  }

  public static double getRecoverRate() {
    return recoverRate;
  }

  public static synchronized int discharge(int voltage){
    int newVoltage = currentVoltage - voltage;
    System.out.println("*** Battery discharged from " + currentVoltage + "v to " + newVoltage + "v ***");
    currentVoltage = newVoltage;
    return currentVoltage;
  }

  public static synchronized boolean isDischargable(int voltage){
    boolean dischargable = false;
    if(currentVoltage >= voltage) {
      dischargable = true;

    }else {
      dischargable = false;
      //System.out.println("Battery doesn't have engugh voltage to move.");
    }

    return dischargable;
  }

  /* the synchronized method is to make sure this method will only be executed by one thread at the same time/concurrently
  */
  public static synchronized void recharge(){
    if(currentVoltage < capacity){
      double newVoltage = currentVoltage + recoverRate;

      if(newVoltage > capacity){
        newVoltage = capacity;
      }
      /* In the requirement: 
        The power plant is a cheap model of lithium battery from AndroidsRus and can only produce 8 volts,
        its recovery time after discharging is 3 seconds.
        Here the voltage has been round up as 2.66 volts per second, because 8/3 = 2.66
      */
      System.out.println("*** Battery recharged from " + currentVoltage + "v to " + newVoltage + "v ***");
      currentVoltage = (int)Math.round(newVoltage);
    }else{
        System.out.println("*** Battery is fully charged at " + currentVoltage + "v");
    }
  }

  public static int getCurrentVoltage() {
    return currentVoltage;
  }

  public static void setCurrentVoltage(int voltage){
    currentVoltage = voltage;
  }

}
