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

        /* Android articulations: The motor's name, the initial position/degree, the maximum movement degrees, the voltage it consumes during a movement 
         */
        Battery.setCurrentVoltage(8);
        Motor lefHip = new Motor("left hip", 90, 90, 4);
        Motor rightHip = new Motor("right hip", 90, 90, 4);
        Motor leftKnee = new Motor("left knee", 90, 90, 3);
        Motor rightKnee = new Motor("right knee", 90, 90, 3);
        FlexionMotor waist = new FlexionMotor("waist", 0, 30, 0, 90, 4);

        BatteryRunner batteryRunner = new BatteryRunner();
//        batteryRunner.setPriority(1);
        batteryRunner.start();

        /* To set a series of movement, with the detail of the joints/parts (motors) name, the target degree/position, and the description the movement.
         */
        MoveMovement lowerLeftHip = new MoveMovement(lefHip, 0, "lower left Hip");
        lowerLeftHip.initMovement();
        MoveMovement lowerRightHip = new MoveMovement(rightHip, 0, "lower right Hip");
        lowerRightHip.initMovement();
        MoveMovement straightenLeftKnee = new MoveMovement(leftKnee, 0, "straighten Left Knee");
        straightenLeftKnee.initMovement();

        MoveMovement straightenRightKnee = new MoveMovement(rightKnee, 0, "straighten Right Knee");

        straightenRightKnee.initMovement();

        FlexionMovement leanForwardBody = new FlexionMovement(waist, 45, "lean Forward Body");
        leanForwardBody.initMovement();
        FlexionMovement leanBackwardBody = new FlexionMovement(waist, 0, "lean Backward Body");

        /* 
         Start a MotorRunner thread to execute all the desired mvoements
       
         */
        MotorRunner standUpMotorsRunner = new MotorRunner("Stand up");
        standUpMotorsRunner.addMovement(lowerLeftHip);
        standUpMotorsRunner.addMovement(lowerRightHip);
        standUpMotorsRunner.addMovement(straightenLeftKnee);
        standUpMotorsRunner.addMovement(straightenRightKnee);
        standUpMotorsRunner.addMovement(leanForwardBody);
        standUpMotorsRunner.addMovement(leanBackwardBody);
        standUpMotorsRunner.start();

        MotorRunner walkMotorsRunner = new MotorRunner("Walk");
        MoveMovement walkRaiseLeftHip = new MoveMovement(lefHip, 30, "raise left Hip");

        MoveMovement walkLowerLeftHip = new MoveMovement(lefHip, 0, "lower left Hip");


        MoveMovement walkRaiseRightHip = new MoveMovement(rightHip, 30, "raise right hip");

        MoveMovement walklowerRightHip = new MoveMovement(rightHip, 0, "lower right Hip");


        walkMotorsRunner.addMovement(walkRaiseLeftHip);
        walkMotorsRunner.addMovement(walkLowerLeftHip);
        walkMotorsRunner.addMovement(walkRaiseRightHip);
        walkMotorsRunner.addMovement(walklowerRightHip);

        AndroidRunner androidRunner = new AndroidRunner();
        androidRunner.getMotorRunners().add(standUpMotorsRunner);
        androidRunner.getMotorRunners().add(walkMotorsRunner);

        androidRunner.start();

    }

}
