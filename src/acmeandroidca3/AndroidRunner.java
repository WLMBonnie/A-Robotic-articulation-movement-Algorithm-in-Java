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

  private List<MotorRunner> motorsRunners = new ArrayList<MotorRunner>();

  @Override
  public void run() {
    boolean motorsAreRunning = true;
    boolean working = true;
    while (working) {

        /*
        To stop application's execution once all the motors have run
        */

      for (int i = 0; i < motorsRunners.size(); i++) {
        motorsAreRunning = motorsAreRunning && motorsRunners.get(i).isAlive();
      }

      if(!motorsAreRunning){
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
    return motorsRunners;
  }
}
