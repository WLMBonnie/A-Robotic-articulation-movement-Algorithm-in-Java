package acmeandroidca3;

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
