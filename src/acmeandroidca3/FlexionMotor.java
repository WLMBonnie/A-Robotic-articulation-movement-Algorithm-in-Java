package acmeandroidca3;

public class FlexionMotor extends Motor {

  private int flexionDegree;
  private int currentFlexionDegree;
  private int targetFlexionDegree;

  public FlexionMotor(String name, int moveDegree, int flexionDegree, int voltageUsage){
    super(name, moveDegree, voltageUsage);
    this.flexionDegree = flexionDegree;
  }

  public boolean bend(){

    boolean bended = false;
      if(currentFlexionDegree < targetFlexionDegree){


        if(Battery.isDischargable(this.getVoltageUsage())) {
          System.out.println(name + "has enough battery voltage to bend.");
          System.out.println(
              name + " is ready to bend from " + currentDegree + " deg to " + targetDegree
                  + " deg");
          int newCurrentDeg = currentFlexionDegree + maxDegreePerSecond;
          System.out.println(
              name + " bended from " + currentFlexionDegree + " deg to " + newCurrentDeg + " deg");
          currentFlexionDegree = newCurrentDeg;
          Battery.discharge(this.getVoltageUsage());
          bended = true;
        }else{
          System.out.println(name + "doesn't have enough battery voltage to bend.");
        }
      }
     return bended;
  }

  public boolean isBendDone(){

    boolean done = false;
    if(currentFlexionDegree == targetFlexionDegree){
      done = true;
    }else{
      done = false;
    }

    return done;
  }

  public void initFlexion(int currentFlexionDegree, int targetFlexionDegree){
    this.currentFlexionDegree = currentFlexionDegree;
    this.targetFlexionDegree = targetFlexionDegree;
    if((targetFlexionDegree - currentFlexionDegree) > (flexionDegree * 0.6)){
      voltageUsage += 3;
    }
  }

  public int getFlexionDegree() {
    return flexionDegree;
  }


  public int getCurrentFlexionDegree() {
    return currentFlexionDegree;
  }


  public int getTargetFlexionDegree() {
    return targetFlexionDegree;
  }

}