/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acmeandroidca3;


/**
 *
 * @author WailuiMa
 */

public class Motor {

  protected final int maxDegreePerSecond = 15;
  String name;
  int maxMovementDegree;
  int voltageUsage;
  int currentDegree;
  int targetDegree;

  public Motor(String name, int moveDegree, int voltageUsage){
    this.name = name;
    this.maxMovementDegree = moveDegree;
    this.voltageUsage = voltageUsage;
  }

  public int getMaxMovementDegree() {
    return maxMovementDegree;
  }

  public void setMaxMovementDegree(int maxMovementDegree) {
    this.maxMovementDegree = maxMovementDegree;
  }

  public int getVoltageUsage() {
    return voltageUsage;
  }

  public void setVoltageUsage(int voltageUsage) {
    this.voltageUsage = voltageUsage;
  }

  public void initMovement(int currentDegree, int targetDegree){
    this.currentDegree = currentDegree;
    this.targetDegree = targetDegree;
    if((targetDegree - currentDegree) > (maxMovementDegree * 0.6)){
      voltageUsage += 3;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean move(){
    boolean moved = false;

      if(currentDegree < targetDegree ){

        if(Battery.isDischargable(this.getVoltageUsage())) {

          System.out.println(name + " has enough battery voltage to move.");

          System.out.println(
              name + " is ready to move from " + currentDegree + " deg to " + targetDegree
                  + " deg");
          int newCurrentDeg = currentDegree + maxDegreePerSecond;

          System.out
              .println(name + " moved from " + currentDegree + " deg to " + newCurrentDeg + " deg");

          currentDegree = newCurrentDeg;
          Battery.discharge(this.getVoltageUsage());
          moved = true;

        }else{
          System.out.println(name + " doesn't have enough battery voltage to move.");
        }
      }
    return moved;

  }

  public boolean isMovementDone(){

    boolean done = false;
    if(currentDegree == targetDegree){
      done = true;
    }else{
      done = false;
    }

    return done;
  }

}
