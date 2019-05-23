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

public class BatteryRunner extends Thread {

  @Override
  public void run(){

    while(true){
        Battery.recharge();
      try {
        sleep(1000);
      }catch (InterruptedException ie){

      }
    }
  }

}
