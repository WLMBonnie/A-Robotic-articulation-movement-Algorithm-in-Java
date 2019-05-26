/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroidca3;

/**
 *
 * Walking Gait
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
        MoveMovement lowerLeftHip = new MoveMovement(leftHip, 20, "lower left Hip");
//        lowerLeftHip.initMovement();
        MoveMovement lowerRightHip = new MoveMovement(rightHip, 20, "lower right Hip");
//        lowerRightHip.initMovement();
        MoveMovement straightenLeftKnee = new MoveMovement(leftKnee, 0, "straighten Left Knee");
//        straightenLeftKnee.initMovement();

        MoveMovement straightenRightKnee = new MoveMovement(rightKnee, 0, "straighten Right Knee");

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
        
        MoveMovement walkInitialContactHip = new MoveMovement(leftHip, 40, "initial contact left hip");
        MoveMovement walkInitialContactLeftKnee = new MoveMovement(leftKnee, 0, "loading response left knee");
        MoveMovement walkInitialContacteLeftAnkle = new MoveMovement(leftAnkle, 15, "loading response left ankle");
        MoveMovement walkPreSwingRightHip = new MoveMovement(rightHip, 10, "preswing right hip");
        MoveMovement walkPreSwingRightKnee = new MoveMovement(rightKnee, 40, "preswing right knee");
        MoveMovement walkPreSwingRightAnkle = new MoveMovement(rightAnkle, 30, "preswing right ankle");
        
        
        walkMotorsRunner.addMovement(walkInitialContactHip);
        walkMotorsRunner.addMovement(walkInitialContactLeftKnee);
        walkMotorsRunner.addMovement(walkInitialContacteLeftAnkle);
        walkMotorsRunner.addMovement(walkPreSwingRightHip);
        walkMotorsRunner.addMovement(walkPreSwingRightKnee);
        walkMotorsRunner.addMovement(walkPreSwingRightAnkle);
        
        
        MoveMovement walkLoadingResponseLeftHip = new MoveMovement(leftHip, 40, "loading response left hip");
        MoveMovement walkLoadingResponseLeftKnee = new MoveMovement(leftKnee, 20, "loading response left knee");
        MoveMovement walkLoadingResponseLeftAnkle = new MoveMovement(leftAnkle, 20, "loading response left ankle");
        MoveMovement walkInitialSwingRightHip = new MoveMovement(rightHip, 35, "initial swing right hip");
        MoveMovement walkInitialSwingRightKnee = new MoveMovement(rightKnee, 60, "initial swing right knee");
        MoveMovement walkInitialSwingRightAnkle = new MoveMovement(rightAnkle, 20, "initial swing right ankle");
        
        
        walkMotorsRunner.addMovement(walkLoadingResponseLeftHip);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftKnee);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftAnkle);
        walkMotorsRunner.addMovement(walkInitialSwingRightHip);
        walkMotorsRunner.addMovement(walkInitialSwingRightKnee);
        walkMotorsRunner.addMovement(walkInitialSwingRightAnkle);
        
       
        
        MoveMovement walkMidStanceLeftHip = new MoveMovement(leftHip, 20, "mid stance left hip");
        MoveMovement walkMidStanceLeftKnee = new MoveMovement(leftKnee, 5, "mid stance left knee");
        MoveMovement walkMidStanceLeftAnkle = new MoveMovement(leftAnkle, 10, "mid stance left ankle");
        MoveMovement walkMidSwingRightHip = new MoveMovement(rightHip, 45, "mid swing right hip");
        MoveMovement walkMidSwingRightKnee = new MoveMovement(rightKnee, 25, "mid swing right knee");
        MoveMovement walkMidSwingRightAnkle = new MoveMovement(rightAnkle, 15, "mid stance right ankle");
        
        walkMotorsRunner.addMovement(walkMidStanceLeftHip);
        walkMotorsRunner.addMovement(walkMidStanceLeftKnee);
        walkMotorsRunner.addMovement(walkMidStanceLeftAnkle);
        walkMotorsRunner.addMovement(walkMidSwingRightHip);
        walkMotorsRunner.addMovement(walkMidSwingRightKnee);
        walkMotorsRunner.addMovement(walkMidSwingRightAnkle);
        
        
        MoveMovement walkTerminalStanceLeftHip = new MoveMovement(leftHip, 0, "terminal stance left hip");
        MoveMovement walkTerminalStanceLeftKnee = new MoveMovement(leftKnee, 0, "terminal stance left knee");
        MoveMovement walkTerminalStanceLeftAnkle = new MoveMovement(leftAnkle, 5, "terminal stance left ankle");
        MoveMovement walkTerminalSwingRightHip = new MoveMovement(rightHip, 40, "Terminal swing right hip");
        MoveMovement walkTerminalSwingRightKnee = new MoveMovement(rightKnee, 0, "Terminal swing right knee");
        MoveMovement walkTerminalSwingRightAnkle = new MoveMovement(rightAnkle, 15, "Terminal stance right ankle");
        
        walkMotorsRunner.addMovement(walkTerminalStanceLeftHip);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftKnee);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftAnkle);
        walkMotorsRunner.addMovement(walkTerminalSwingRightHip);
        walkMotorsRunner.addMovement(walkTerminalSwingRightKnee);
        walkMotorsRunner.addMovement(walkTerminalSwingRightAnkle);
        
        
     
        MoveMovement walkPreSwingLeftHip = new MoveMovement(leftHip, 10, "preswing left hip");
        MoveMovement walkPreSwingLeftKnee = new MoveMovement(leftKnee, 40, "preswing left knee");
        MoveMovement walkPreSwingLeftAnkle = new MoveMovement(leftAnkle, 30, "preswing left ankle");
        MoveMovement walkInitialContactRightHip = new MoveMovement(rightHip, 40, "initial contact right hip");
        MoveMovement walkInitialContactRightKnee = new MoveMovement(rightKnee, 0, "loading response right knee");
        MoveMovement walkInitialContactRightAnkle = new MoveMovement(rightAnkle, 15, "loading response right ankle");
        
        walkMotorsRunner.addMovement(walkPreSwingLeftHip);
        walkMotorsRunner.addMovement(walkPreSwingLeftKnee);
        walkMotorsRunner.addMovement(walkPreSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkInitialContactRightHip);
        walkMotorsRunner.addMovement(walkInitialContactRightKnee);
        walkMotorsRunner.addMovement(walkInitialContactRightAnkle);
        
        
        MoveMovement walkInitialSwingLeftHip = new MoveMovement(leftHip, 35, "initial swing left hip");
        MoveMovement walkInitialSwingLeftKnee = new MoveMovement(leftKnee, 60, "initial swing left knee");
        MoveMovement walkInitialSwingLeftAnkle = new MoveMovement(leftAnkle, 20, "initial swing left ankle");
        MoveMovement walkLoadingResponseRighttHip = new MoveMovement(rightHip, 40, "loading response right hip");
        MoveMovement walkLoadingResponseRightKnee = new MoveMovement(rightKnee, 20, "loading response right knee");
        MoveMovement walkLoadingResponseRightAnkle = new MoveMovement(rightAnkle, 20, "loading response right ankle");
        
        walkMotorsRunner.addMovement(walkInitialSwingLeftHip);
        walkMotorsRunner.addMovement(walkInitialSwingLeftKnee);
        walkMotorsRunner.addMovement(walkInitialSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkLoadingResponseRighttHip);
        walkMotorsRunner.addMovement(walkLoadingResponseRightKnee);
        walkMotorsRunner.addMovement(walkLoadingResponseRightAnkle);
        
        
        MoveMovement walkMidSwingLeftHip = new MoveMovement(leftHip, 45, "mid swing left hip");
        MoveMovement walkMidSwingLeftKnee = new MoveMovement(leftKnee, 25, "mid swing left knee");
        MoveMovement walkMidSwingLeftAnkle = new MoveMovement(leftAnkle, 15, "mid stance left ankle");
        MoveMovement walkMidStanceRightHip = new MoveMovement(rightHip, 20, "mid stance right hip");
        MoveMovement walkMidStanceRightKnee = new MoveMovement(rightKnee, 5, "mid stance right knee");
        MoveMovement walkMidStanceRightAnkle = new MoveMovement(rightAnkle, 10, "mid stance right ankle");
        
        walkMotorsRunner.addMovement(walkMidSwingLeftHip);
        walkMotorsRunner.addMovement(walkMidSwingLeftKnee);
        walkMotorsRunner.addMovement(walkMidSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkMidStanceRightHip);
        walkMotorsRunner.addMovement(walkMidStanceRightKnee);
        walkMotorsRunner.addMovement(walkMidStanceRightAnkle);
        
        MoveMovement walkTerminalSwingLeftHip = new MoveMovement(leftHip, 40, "Terminal swing left hip");
        MoveMovement walkTerminalSwingLeftKnee = new MoveMovement(leftKnee, 0, "Terminal swing left knee");
        MoveMovement walkTerminalSwingLeftAnkle = new MoveMovement(leftAnkle, 15, "Terminal stance left ankle");
        MoveMovement walkTerminalStanceRightHip = new MoveMovement(rightHip, 0, "terminal stance right hip");
        MoveMovement walkTerminalStanceRightKnee = new MoveMovement(rightKnee, 0, "terminal stance right knee");
        MoveMovement walkTerminalStanceRightAnkle = new MoveMovement(rightAnkle, 5, "terminal stance right ankle");
        
        walkMotorsRunner.addMovement(walkTerminalSwingLeftHip);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftKnee);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkTerminalStanceRightHip);
        walkMotorsRunner.addMovement(walkTerminalStanceRightKnee);
        walkMotorsRunner.addMovement(walkTerminalStanceRightAnkle);
        
        MoveMovement walkStopLeftHip = new MoveMovement(leftHip, 20, "return to stand pose left hip");
        MoveMovement walkStopLeftKnee = new MoveMovement(leftKnee, 0, "return to stand pose left knee");
        MoveMovement walkStopSwingLeftAnkle = new MoveMovement(leftAnkle, 15, "return to stand pose left ankle");
        MoveMovement walkStopRightHip = new MoveMovement(rightHip, 20, "return to stand posee right hip");
        MoveMovement walkStopRightKnee = new MoveMovement(rightKnee, 0, "return to stand pose right knee");
        MoveMovement walkStopeRightAnkle = new MoveMovement(rightAnkle, 15, "return to stand pose right ankle");
    
        walkMotorsRunner.addMovement(walkStopLeftHip);
        walkMotorsRunner.addMovement(walkStopLeftKnee);
        walkMotorsRunner.addMovement(walkStopSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkStopRightHip);
        walkMotorsRunner.addMovement(walkStopRightKnee);
        walkMotorsRunner.addMovement(walkStopeRightAnkle);
        
        
        MotorRunner sitDownMotorsRunner = new MotorRunner("Sit down");
        FlexionMovement siLeanForwardBody = new FlexionMovement(waist, 30, "sit down lean Forward Body");
        MoveMovement sitLowerLeftHip = new MoveMovement(leftHip, 70, "sit down lower left Hip");
        MoveMovement sitLowerRightHip = new MoveMovement(rightHip, 70, "sit down lower right Hip");
        MoveMovement sitBendLeftKnee = new MoveMovement(leftKnee, 70, "sit down bend Left Knee");
        MoveMovement sitBendRightKnee = new MoveMovement(rightKnee,70, "sit down bend Right Knee");
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
