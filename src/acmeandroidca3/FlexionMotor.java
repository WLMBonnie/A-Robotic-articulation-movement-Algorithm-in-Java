/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroidca3;

/**
 *
 *
 * @author WailuiMa
 */
public class FlexionMotor extends Motor {

    private int maxFlexionDegree;
    private int currentFlexionDegree;
    private int targetFlexionDegree;
    private int initFlexionDegree;
    private boolean addDeg = false;

    public FlexionMotor(String name, int initDegree, int maxMovementDegree, int initFlexionDegree, int maxFlexionDegree, int voltageUsage) {
        super(name, initDegree, maxMovementDegree, voltageUsage);
        this.maxFlexionDegree = maxFlexionDegree;
        this.initFlexionDegree = initFlexionDegree;
        this.currentFlexionDegree = initFlexionDegree;
    }

    public boolean bend() {

        boolean bended = false;

        if (Battery.isDischargable(this.getVoltageUsage())) {
            System.out.println(name + " has enough battery voltage to bend.");
            System.out.println(
                    name + " is ready to bend from " + currentFlexionDegree + " deg to " + targetFlexionDegree
                    + " deg");

            if (addDeg) {
                int newCurrentDeg = currentFlexionDegree + maxDegreePerSecond;
                 if(newCurrentDeg > targetFlexionDegree)
                    newCurrentDeg = targetFlexionDegree;
                 
                System.out.println(
                        name + " bended from " + currentFlexionDegree + " deg to " + newCurrentDeg + " deg");
                currentFlexionDegree = newCurrentDeg;
            } else {
                int newCurrentDeg = currentFlexionDegree - maxDegreePerSecond;
                if(newCurrentDeg < targetFlexionDegree)
                    newCurrentDeg = targetFlexionDegree;
                    
                System.out.println(
                        name + " bended from " + currentFlexionDegree + " deg to " + newCurrentDeg + " deg");
                currentFlexionDegree = newCurrentDeg;
            }
            Battery.discharge(this.getVoltageUsage());
            bended = true;
        } else {
            System.out.println(name + " doesn't have enough battery voltage to bend.");
        }
        return bended;
    }

    public void initFlexion(int targetFlexionDegree) {

        this.targetFlexionDegree = targetFlexionDegree;
        if ((Math.abs(targetFlexionDegree - currentFlexionDegree)) > (maxFlexionDegree * 0.6)) {
            defaultVoltageUsage += 3;
        }

        if (currentFlexionDegree < targetFlexionDegree) {
            addDeg = true;
        } else {
            addDeg = false;
        }
    }

    public int getCurrentFlexionDegree() {
        return currentFlexionDegree;
    }

    public int getTargetFlexionDegree() {
        return targetFlexionDegree;
    }

}
