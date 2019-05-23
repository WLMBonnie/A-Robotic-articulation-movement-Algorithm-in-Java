/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acmeandroidca3;

import java.util.ArrayList;
import java.util.List;

/**
 * Each motor moves how many degrees per second
 * 
 * @author WailuiMa
 */

public class MotorRunner extends Thread {


  private List<Motor> motorList = new ArrayList<Motor>();

  @Override
  public void run(){

    boolean finishedMove = false;
    Motor motor = null;
    boolean isAllDone = true;
    while(!finishedMove){

      isAllDone = true;
      for(int i = 0; i < motorList.size(); i++){

        motor = motorList.get(i);
        if(!motor.isMovementDone()){
          if(motor.move()){
            keepMotorsInPlace();
          }


        }else if(motor instanceof FlexionMotor){
          if(!((FlexionMotor) motor).isBendDone()){

            if(((FlexionMotor) motor).bend())
              keepMotorsInPlace();


          }
        }


        System.out.println(motor.getName() + " movement done? " + motor.isMovementDone());
        isAllDone = isAllDone && motor.isMovementDone();

        if(motor instanceof FlexionMotor){
          System.out.println(motor.getName() + " flexion done? " + ((FlexionMotor) motor).isBendDone());
          isAllDone = isAllDone && ((FlexionMotor) motor).isBendDone();
        }

        System.out.println(motor.getName() + " all done? " + isAllDone);
      }
      
      if(isAllDone)
        finishedMove = true;

      try {
        sleep(1000);
      }catch (InterruptedException ie){

      }
    }

    System.out.println("All motors are finfished.");
  }

  public void keepMotorsInPlace(){
    System.out.println("Motors are running, consumes 1 voltage to keep them in place");
    if(Battery.isDischargable(1))
      Battery.discharge(1);
  }

  public List<Motor> getMotorList() {
    return motorList;
  }

  public void setMotorList(List<Motor> motorList) {
    this.motorList = motorList;
  }
}
