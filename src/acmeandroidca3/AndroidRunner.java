package acmeandroidca3;

import java.util.ArrayList;
import java.util.List;

public class AndroidRunner extends Thread {

  private List<MotorsRunner> motorsRunners = new ArrayList<MotorsRunner>();

  @Override
  public void run() {
    boolean motorsAreRunning = true;
    boolean working = true;
    while (working) {


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

  public List<MotorsRunner> getMotorRunners() {
    return motorsRunners;
  }
}
