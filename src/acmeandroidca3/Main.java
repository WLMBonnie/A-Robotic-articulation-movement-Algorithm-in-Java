/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroidca3;

/**
 *
 * Walking Gait reference:
 * https://www.youtube.com/watch?v=QAnEhz6Eqn4
 * 
 * @author WailuiMa
 */
public class Main {

    public static void main(String[] args) {

        /* Android articulations: The motor's name, the initial position/degree, the maximum movement degrees, the voltage it consumes during a movement 
         */
        Battery.setCurrentVoltage(8);
        Motor leftHip = new Motor("left hip", 70, 90, 4);
        Motor rightHip = new Motor("right hip", 70, 90, 4);
        Motor leftKnee = new Motor("left knee", 90, 90, 3);
        Motor rightKnee = new Motor("right knee", 90, 90, 3);
        FlexionMotor waist = new FlexionMotor("waist", 0, 30, 0, 90, 4);
        
        Motor leftAnkle = new Motor("left ankle", 15, 30, 3);
        Motor rightAnkle = new Motor("right ankle", 15, 30, 3);

        BatteryRunner batteryRunner = new BatteryRunner();
//        batteryRunner.setPriority(1);
        batteryRunner.start();

        /* To set a series of movement, with the detail of the joints/parts (motors) name, the target degree/position, and the description the movement.
         */
        RotationMovement lowerLeftHip = new RotationMovement(leftHip, 20, "lower left Hip");
//        lowerLeftHip.initMovement();
        RotationMovement lowerRightHip = new RotationMovement(rightHip, 20, "lower right Hip");
//        lowerRightHip.initMovement();
        RotationMovement straightenLeftKnee = new RotationMovement(leftKnee, 0, "straighten Left Knee");
//        straightenLeftKnee.initMovement();

        RotationMovement straightenRightKnee = new RotationMovement(rightKnee, 0, "straighten Right Knee");

//        straightenRightKnee.initMovement();

        FlexionMovement leanForwardBody = new FlexionMovement(waist, 45, "lean Forward Body");
//        leanForwardBody.initMovement();
        FlexionMovement leanBackwardBody = new FlexionMovement(waist, 0, "lean Backward Body");

        /*  Start a MotorRunner thread to execute all the desired movements    
         */
        MotorRunner standUpMotorsRunner = new MotorRunner("Stand up");
        standUpMotorsRunner.addMovement(lowerLeftHip);
        standUpMotorsRunner.addMovement(lowerRightHip);
        standUpMotorsRunner.addMovement(straightenLeftKnee);
        standUpMotorsRunner.addMovement(straightenRightKnee);
        standUpMotorsRunner.addMovement(leanForwardBody);
        standUpMotorsRunner.addMovement(leanBackwardBody);
        standUpMotorsRunner.initMovement();
        standUpMotorsRunner.start();

        
         
       
        MotorRunner walkMotorsRunner = new MotorRunner("Walk");
        
        RotationMovement walkInitialContactHip = new RotationMovement(leftHip, 40, "initial contact left hip");
        RotationMovement walkInitialContactLeftKnee = new RotationMovement(leftKnee, 0, "loading response left knee");
        RotationMovement walkInitialContacteLeftAnkle = new RotationMovement(leftAnkle, 15, "loading response left ankle");
        RotationMovement walkPreSwingRightHip = new RotationMovement(rightHip, 10, "preswing right hip");
        RotationMovement walkPreSwingRightKnee = new RotationMovement(rightKnee, 40, "preswing right knee");
        RotationMovement walkPreSwingRightAnkle = new RotationMovement(rightAnkle, 30, "preswing right ankle");
        
        
        walkMotorsRunner.addMovement(walkInitialContactHip);
        walkMotorsRunner.addMovement(walkInitialContactLeftKnee);
        walkMotorsRunner.addMovement(walkInitialContacteLeftAnkle);
        walkMotorsRunner.addMovement(walkPreSwingRightHip);
        walkMotorsRunner.addMovement(walkPreSwingRightKnee);
        walkMotorsRunner.addMovement(walkPreSwingRightAnkle);
        
        
        RotationMovement walkLoadingResponseLeftHip = new RotationMovement(leftHip, 40, "loading response left hip");
        RotationMovement walkLoadingResponseLeftKnee = new RotationMovement(leftKnee, 20, "loading response left knee");
        RotationMovement walkLoadingResponseLeftAnkle = new RotationMovement(leftAnkle, 20, "loading response left ankle");
        RotationMovement walkInitialSwingRightHip = new RotationMovement(rightHip, 35, "initial swing right hip");
        RotationMovement walkInitialSwingRightKnee = new RotationMovement(rightKnee, 60, "initial swing right knee");
        RotationMovement walkInitialSwingRightAnkle = new RotationMovement(rightAnkle, 20, "initial swing right ankle");
        
        
        walkMotorsRunner.addMovement(walkLoadingResponseLeftHip);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftKnee);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftAnkle);
        walkMotorsRunner.addMovement(walkInitialSwingRightHip);
        walkMotorsRunner.addMovement(walkInitialSwingRightKnee);
        walkMotorsRunner.addMovement(walkInitialSwingRightAnkle);
        
       
        
        RotationMovement walkMidStanceLeftHip = new RotationMovement(leftHip, 20, "mid stance left hip");
        RotationMovement walkMidStanceLeftKnee = new RotationMovement(leftKnee, 5, "mid stance left knee");
        RotationMovement walkMidStanceLeftAnkle = new RotationMovement(leftAnkle, 10, "mid stance left ankle");
        RotationMovement walkMidSwingRightHip = new RotationMovement(rightHip, 45, "mid swing right hip");
        RotationMovement walkMidSwingRightKnee = new RotationMovement(rightKnee, 25, "mid swing right knee");
        RotationMovement walkMidSwingRightAnkle = new RotationMovement(rightAnkle, 15, "mid stance right ankle");
        
        walkMotorsRunner.addMovement(walkMidStanceLeftHip);
        walkMotorsRunner.addMovement(walkMidStanceLeftKnee);
        walkMotorsRunner.addMovement(walkMidStanceLeftAnkle);
        walkMotorsRunner.addMovement(walkMidSwingRightHip);
        walkMotorsRunner.addMovement(walkMidSwingRightKnee);
        walkMotorsRunner.addMovement(walkMidSwingRightAnkle);
        
        
        RotationMovement walkTerminalStanceLeftHip = new RotationMovement(leftHip, 0, "terminal stance left hip");
        RotationMovement walkTerminalStanceLeftKnee = new RotationMovement(leftKnee, 0, "terminal stance left knee");
        RotationMovement walkTerminalStanceLeftAnkle = new RotationMovement(leftAnkle, 5, "terminal stance left ankle");
        RotationMovement walkTerminalSwingRightHip = new RotationMovement(rightHip, 40, "Terminal swing right hip");
        RotationMovement walkTerminalSwingRightKnee = new RotationMovement(rightKnee, 0, "Terminal swing right knee");
        RotationMovement walkTerminalSwingRightAnkle = new RotationMovement(rightAnkle, 15, "Terminal stance right ankle");
        
        walkMotorsRunner.addMovement(walkTerminalStanceLeftHip);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftKnee);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftAnkle);
        walkMotorsRunner.addMovement(walkTerminalSwingRightHip);
        walkMotorsRunner.addMovement(walkTerminalSwingRightKnee);
        walkMotorsRunner.addMovement(walkTerminalSwingRightAnkle);
        
        
     
        RotationMovement walkPreSwingLeftHip = new RotationMovement(leftHip, 10, "preswing left hip");
        RotationMovement walkPreSwingLeftKnee = new RotationMovement(leftKnee, 40, "preswing left knee");
        RotationMovement walkPreSwingLeftAnkle = new RotationMovement(leftAnkle, 30, "preswing left ankle");
        RotationMovement walkInitialContactRightHip = new RotationMovement(rightHip, 40, "initial contact right hip");
        RotationMovement walkInitialContactRightKnee = new RotationMovement(rightKnee, 0, "loading response right knee");
        RotationMovement walkInitialContactRightAnkle = new RotationMovement(rightAnkle, 15, "loading response right ankle");
        
        walkMotorsRunner.addMovement(walkPreSwingLeftHip);
        walkMotorsRunner.addMovement(walkPreSwingLeftKnee);
        walkMotorsRunner.addMovement(walkPreSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkInitialContactRightHip);
        walkMotorsRunner.addMovement(walkInitialContactRightKnee);
        walkMotorsRunner.addMovement(walkInitialContactRightAnkle);
        
        
        RotationMovement walkInitialSwingLeftHip = new RotationMovement(leftHip, 35, "initial swing left hip");
        RotationMovement walkInitialSwingLeftKnee = new RotationMovement(leftKnee, 60, "initial swing left knee");
        RotationMovement walkInitialSwingLeftAnkle = new RotationMovement(leftAnkle, 20, "initial swing left ankle");
        RotationMovement walkLoadingResponseRighttHip = new RotationMovement(rightHip, 40, "loading response right hip");
        RotationMovement walkLoadingResponseRightKnee = new RotationMovement(rightKnee, 20, "loading response right knee");
        RotationMovement walkLoadingResponseRightAnkle = new RotationMovement(rightAnkle, 20, "loading response right ankle");
        
        walkMotorsRunner.addMovement(walkInitialSwingLeftHip);
        walkMotorsRunner.addMovement(walkInitialSwingLeftKnee);
        walkMotorsRunner.addMovement(walkInitialSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkLoadingResponseRighttHip);
        walkMotorsRunner.addMovement(walkLoadingResponseRightKnee);
        walkMotorsRunner.addMovement(walkLoadingResponseRightAnkle);
        
        
        RotationMovement walkMidSwingLeftHip = new RotationMovement(leftHip, 45, "mid swing left hip");
        RotationMovement walkMidSwingLeftKnee = new RotationMovement(leftKnee, 25, "mid swing left knee");
        RotationMovement walkMidSwingLeftAnkle = new RotationMovement(leftAnkle, 15, "mid stance left ankle");
        RotationMovement walkMidStanceRightHip = new RotationMovement(rightHip, 20, "mid stance right hip");
        RotationMovement walkMidStanceRightKnee = new RotationMovement(rightKnee, 5, "mid stance right knee");
        RotationMovement walkMidStanceRightAnkle = new RotationMovement(rightAnkle, 10, "mid stance right ankle");
        
        walkMotorsRunner.addMovement(walkMidSwingLeftHip);
        walkMotorsRunner.addMovement(walkMidSwingLeftKnee);
        walkMotorsRunner.addMovement(walkMidSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkMidStanceRightHip);
        walkMotorsRunner.addMovement(walkMidStanceRightKnee);
        walkMotorsRunner.addMovement(walkMidStanceRightAnkle);
        
        RotationMovement walkTerminalSwingLeftHip = new RotationMovement(leftHip, 40, "Terminal swing left hip");
        RotationMovement walkTerminalSwingLeftKnee = new RotationMovement(leftKnee, 0, "Terminal swing left knee");
        RotationMovement walkTerminalSwingLeftAnkle = new RotationMovement(leftAnkle, 15, "Terminal stance left ankle");
        RotationMovement walkTerminalStanceRightHip = new RotationMovement(rightHip, 0, "terminal stance right hip");
        RotationMovement walkTerminalStanceRightKnee = new RotationMovement(rightKnee, 0, "terminal stance right knee");
        RotationMovement walkTerminalStanceRightAnkle = new RotationMovement(rightAnkle, 5, "terminal stance right ankle");
        
        walkMotorsRunner.addMovement(walkTerminalSwingLeftHip);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftKnee);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkTerminalStanceRightHip);
        walkMotorsRunner.addMovement(walkTerminalStanceRightKnee);
        walkMotorsRunner.addMovement(walkTerminalStanceRightAnkle);
        
        RotationMovement walkStopLeftHip = new RotationMovement(leftHip, 20, "return to stand pose left hip");
        RotationMovement walkStopLeftKnee = new RotationMovement(leftKnee, 0, "return to stand pose left knee");
        RotationMovement walkStopSwingLeftAnkle = new RotationMovement(leftAnkle, 15, "return to stand pose left ankle");
        RotationMovement walkStopRightHip = new RotationMovement(rightHip, 20, "return to stand posee right hip");
        RotationMovement walkStopRightKnee = new RotationMovement(rightKnee, 0, "return to stand pose right knee");
        RotationMovement walkStopeRightAnkle = new RotationMovement(rightAnkle, 15, "return to stand pose right ankle");
    
        walkMotorsRunner.addMovement(walkStopLeftHip);
        walkMotorsRunner.addMovement(walkStopLeftKnee);
        walkMotorsRunner.addMovement(walkStopSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkStopRightHip);
        walkMotorsRunner.addMovement(walkStopRightKnee);
        walkMotorsRunner.addMovement(walkStopeRightAnkle);
        
        
        MotorRunner sitDownMotorsRunner = new MotorRunner("Sit down");
        FlexionMovement siLeanForwardBody = new FlexionMovement(waist, 30, "sit down lean Forward Body");
        RotationMovement sitLowerLeftHip = new RotationMovement(leftHip, 70, "sit down lower left Hip");
        RotationMovement sitLowerRightHip = new RotationMovement(rightHip, 70, "sit down lower right Hip");
        RotationMovement sitBendLeftKnee = new RotationMovement(leftKnee, 70, "sit down bend Left Knee");
        RotationMovement sitBendRightKnee = new RotationMovement(rightKnee,70, "sit down bend Right Knee");
        FlexionMovement sitLeanBackwardBody = new FlexionMovement(waist, 0, "sit down lean Backward Body");
        sitDownMotorsRunner.addMovement(siLeanForwardBody);
        sitDownMotorsRunner.addMovement(sitLowerLeftHip);
        sitDownMotorsRunner.addMovement(sitLowerRightHip);
        sitDownMotorsRunner.addMovement(sitBendLeftKnee);
        sitDownMotorsRunner.addMovement(sitBendRightKnee);
        sitDownMotorsRunner.addMovement(sitLeanBackwardBody);
//        sitDownMotorsRunner.initMovement();
//        sitDownMotorsRunner.start();

        AndroidRunner androidRunner = new AndroidRunner();
        androidRunner.getMotorRunners().add(standUpMotorsRunner);
        androidRunner.getMotorRunners().add(walkMotorsRunner);
        androidRunner.getMotorRunners().add(sitDownMotorsRunner);

        androidRunner.start();

    }

}
