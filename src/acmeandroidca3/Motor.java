/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroidca3;

/**
 * A class to define the motors
 *
 * @author WailuiMa
 */
public class Motor {

    /* A motor can move at most 15 degrees per second
    */ 
    protected final int maxDegreePerSecond = 15;
    String name;
    int maxMovementDegree;
    int defaultVoltageUsage;
    int voltageUsage;
    int initDegree;
    int currentDegree;
    int targetDegree;
    private boolean addDeg;

    /* The motor's name, the initial position/degree, the maximum movement degrees, the voltage it consumes during a movement 
    */
    public Motor(String name, int initDegree, int maxMovementDegree, int defaultVoltageUsage) {
        this.name = name;
        this.initDegree = initDegree;
        this.currentDegree = initDegree;
        this.maxMovementDegree = maxMovementDegree;
        this.defaultVoltageUsage = defaultVoltageUsage;
        this.voltageUsage = defaultVoltageUsage;
    }

    public int getInitDegree() {
        return this.initDegree;
    }

    public int getCurrentDegree() {
        return this.currentDegree;
    }

    public int getTargetDegree() {
        return this.targetDegree;
    }

    public int getMaxMovementDegree() {
        return maxMovementDegree;
    }

    public void setMaxMovementDegree(int maxMovementDegree) {
        this.maxMovementDegree = maxMovementDegree;
    }

     public int getDefaultVoltageUsage() {
        return defaultVoltageUsage;
    }
      
    public int getVoltageUsage() {
        return voltageUsage;
    }


    /* each motor uses a max of 4 volts per second to operate and can move 15 degrees per second (see the main method)- 
    if movement is more than 60% of available motion for a motor an additional 3 volts are required.
    */
    public void initMovement(int targetDegree) {

        this.targetDegree = targetDegree;
        if (Math.abs(targetDegree - currentDegree) > (maxMovementDegree * 0.6)) {
           voltageUsage = defaultVoltageUsage + 3;
        }else{
            voltageUsage = defaultVoltageUsage;
        }
        
        /* When the current degree is 0° and the target degree is 90°, then it is adding the degree (addDeg = true). Then the current degree is 15°/s.
        Reversely, is not adding degree (add Deg = false)
        */

        if (currentDegree < targetDegree) {
            addDeg = true;
        } else {
            addDeg = false;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean move() {
        boolean moved = false;
        
    /* To check if the battery has enough voltage to support a move. 
       It's going to add or reduce the current degree according to the instruction
    */

        if (Battery.isDischargable(this.getVoltageUsage())) {

            System.out.println(name + " has enough battery voltage to move.");
              System.out.println(
                        name + " is ready to move from " + currentDegree + " deg to " + targetDegree
                        + " deg");

            if (addDeg) {

             
                int newCurrentDeg = currentDegree + maxDegreePerSecond;
                if(newCurrentDeg > targetDegree)
                    newCurrentDeg = targetDegree;

                System.out
                        .println(name + " moved from " + currentDegree + " deg to " + newCurrentDeg + " deg");

                currentDegree = newCurrentDeg;
            } else {
                int newCurrentDeg = currentDegree - maxDegreePerSecond;
                if(newCurrentDeg < targetDegree)
                    newCurrentDeg = targetDegree;

                System.out
                        .println(name + " moved from " + currentDegree + " deg to " + newCurrentDeg + " deg");

                currentDegree = newCurrentDeg;
            }
            
            /* to discharge the voltage as the motor comsumes voltage          
            */
            Battery.discharge(this.getVoltageUsage());
            moved = true;

        } else {
            System.out.println(name + " doesn't have enough battery voltage to move. it needs " + this.getVoltageUsage() + "v and current battery is " + Battery.getCurrentVoltage() + "v");
        }

        return moved;

    }

}
