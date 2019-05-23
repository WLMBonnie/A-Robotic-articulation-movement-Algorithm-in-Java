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

public class Main {

    public static void main(String[] args) {

/* Android articulations: Android joints/parts with degree and required voltages:
*/
        Battery.setCurrentVoltage(8);
        Motor hip = new Motor("hip", 90, 4);
        Motor knee = new Motor("knee", 90, 3);
        FlexionMotor waist = new FlexionMotor("waist", 30, 90, 4);

        BatteryRunner batteryRunner = new BatteryRunner();
        batteryRunner.setPriority(1);
        batteryRunner.start();

        hip.initMovement(0, 90);

        knee.initMovement(0, 90);

        waist.initMovement(0,0);
        waist.initFlexion(0, 45);

        MotorRunner motorsRunner = new MotorRunner();
        motorsRunner.getMotorList().add(waist);
        motorsRunner.getMotorList().add(hip);
        motorsRunner.getMotorList().add(knee);


        motorsRunner.start();

//        MotorRunner hipRunner = new MotorRunner("Hip", hip);
//        hipRunner.start();
//
//        knee.setCurrentDegree(0);
//        knee.setTargetDegree(90);
//        MotorRunner kneeRunner = new MotorRunner("Knee", knee, battery);
//        kneeRunner.start();

        AndroidRunner androidRunner = new AndroidRunner();
        androidRunner.getMotorRunners().add(motorsRunner);

        androidRunner.start();


    }


}
