package acmeandroidca3;

public class MotorRunner extends Thread {

 /* private String name;
  private Motor motor;
  private Battery battery;


  public MotorRunner(String name, Motor motor, Battery battery){
    this.name = name;
    this.motor = motor;
    this.battery = battery;
  }

  @Override
  public void run(){
    System.out.println(name + " will move from " + this.motor.getCurrentDegree() + " deg to " + this.motor.getTargetDegree() + " deg");
    while(!isMovementDone()){
      if(this.battery.isDischargable(this.motor.getVoltageUsage())){
        move();
      }

      try {
        sleep(1000);
      }catch (InterruptedException ie){

      }
    }
  }

  public void move(){
    int currentDeg = this.motor.getCurrentDegree();
    int targetDeg = this.motor.getTargetDegree();

    if(currentDeg < targetDeg){
      int newCurrentDeg = currentDeg + maxDegreePerSecond;
      this.motor.setCurrentDegree(newCurrentDeg);
      System.out.println(name + " moved from " + currentDeg + " deg to " + newCurrentDeg + " deg");
      this.battery.discharge(this.motor.getVoltageUsage());
    }
  }

  public boolean isMovementDone(){
    int currentDeg = this.motor.getCurrentDegree();
    int targetDeg = this.motor.getTargetDegree();
    boolean done = false;
    if(currentDeg == targetDeg){
      done = true;
    }else{
      done = false;
    }

    return done;
  }*/
}
