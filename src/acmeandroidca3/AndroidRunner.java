/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acmeandroidca3;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *  
 * @author WailuiMa
 */

public class AndroidRunner extends Thread {

  private List<MotorRunner> motorRunners = new ArrayList<MotorRunner>();

  @Override
  public void run() {
    boolean motorsAreFinished = true;
    boolean working = true;
    while (working) {

        /*
        To stop application's execution once all the motors have run
        */

      for (int i = 0; i < motorRunners.size(); i++) {
        if(motorRunners.get(i).isAlive()){
            break;
        }else{
          if(i + 1 < motorRunners.size()){
              MotorRunner nextRunner = motorRunners.get(i + 1);
              nextRunner.initMovement();
              nextRunner.start();
              motorRunners.remove(i);
              break;
          }else if(motorRunners.size() == 1){
            motorRunners.remove(i);
          }
        }
            
      }
      
     
    

      if(motorRunners.isEmpty()){
        working = false;
        System.exit(0);
      }

      try {
        sleep(1000);
      } catch (InterruptedException ie) {

      }
    }


  }

  public List<MotorRunner> getMotorRunners() {
    return motorRunners;
  }
}
