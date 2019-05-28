/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroidca3;

/**
 * Walking Gait reference:
 * https://www.youtube.com/watch?v=QAnEhz6Eqn4
 * 
 *      Requirement:
 *      Ankle : 30 deg. Voltage required : 3 volts
 *      Knee : 90 deg. Voltage required : 3 volts
 *      Hip : 90 deg. Voltage required : 4 volts
 *      Waist : 30 deg. rotation / 90 deg. flexion. Voltage required : 4 volts
 *      Wrist : 180 deg. Voltage required : 2 volts
 *      Elbow : 140 deg. Voltage required : 3 volts
 *      Shoulder : 360 rotation / 180 flexion. Voltage required : 2 volts
 *      Neck : 30 deg rotation. Voltage required : 3 volts
 *      Head : 180 deg rotation / 180 deg. flexion. Voltage required : 3 volts
 * 
 * @author WailuiMa
 */
public class Main {

    public static void main(String[] args) {

        /* Android articulations: The motor's name, the initial position/degree, the maximum movement degrees, the voltage it consumes during a movement.     
        */
        Battery.setCurrentVoltage(8);
        
        Motor neck = new Motor("neck", 15, 30, 3);
        /* The elbows and wrists are in the connection of the joints/motors of shoulders, as its relative position to the shoulders in a swing while walking 
        remains the same, that the movement of these joints will not be printed out in a swing while walking.
        */
        Motor leftElbow= new Motor("left elbow",0, 140, 3);  
        Motor rightElbow= new Motor("right elbow",0, 140, 3);
        Motor leftWrist= new Motor("left wrist",0, 180, 2);
        Motor rightWrist= new Motor("right wrist",0, 180, 2);
        
        Motor leftHip = new Motor("left hip", 70, 90, 4);
        Motor rightHip = new Motor("right hip", 70, 90, 4);
        Motor leftKnee = new Motor("left knee", 90, 90, 3);
        Motor rightKnee = new Motor("right knee", 90, 90, 3);
        
        /* The flexion motor's name, the initial position/degree, the maximum movement degrees,
        the initial Flexion Degree, the maximum Flexion Degree, the voltage it consumes during a movement 
        */
        FlexionMotor waist = new FlexionMotor("waist", 0, 30, 0, 90, 4);      
        FlexionMotor head = new FlexionMotor("head", 90, 180, 90, 180, 3);
        FlexionMotor leftShoulder = new FlexionMotor("left shoulder",180, 360, 90, 180,  2);
        FlexionMotor rightShoulder = new FlexionMotor("right shoulder",180, 360, 90, 180, 2);
        
        Motor leftAnkle = new Motor("left ankle", 15, 30, 3);
        Motor rightAnkle = new Motor("right ankle", 15, 30, 3);

        BatteryRunner batteryRunner = new BatteryRunner();
//        batteryRunner.setPriority(1);
        batteryRunner.start();

        /* To set a series of movement, with the detail of the joints/parts (motors) name, the target degree/position, and the description the movement.
        
        Very Important (see the design documents):
        1. The initial degrees are based on the types of joints/parts (motors)
        
        2. Please be noted that the movement of joints/motors in a connection will not be printed out. For example, when the whole arm is swinging, the three points of 
        shoulder, elbow, and wrist will be in a straight line, then the print out will be showed as 'the shoulder moved from 0 degree to 10 degree'. However, the 
        absolute changed degree of elbow and wrist have been changed as well, just the relative changed degree to the should in a connection remains the same, that 
        the 'movement' of the joints/motors would not be displayed.
         */
        
        RotationMovement lowerLeftHip = new RotationMovement(leftHip, 20, "lower left Hip");
        RotationMovement lowerRightHip = new RotationMovement(rightHip, 20, "lower right Hip");
        RotationMovement straightenLeftKnee = new RotationMovement(leftKnee, 0, "straighten Left Knee");
        RotationMovement straightenRightKnee = new RotationMovement(rightKnee, 0, "straighten Right Knee");
        FlexionMovement leanForwardHead = new FlexionMovement(head, 95, "lean Forward head");
        RotationMovement leanForwardNeck = new RotationMovement(neck, 20, "lean forward neck");
        FlexionMovement leanForwardBody = new FlexionMovement(waist, 45, "lean Forward Body");
        FlexionMovement leanBackwardBody = new FlexionMovement(waist, 0, "lean Backward Body");

        /*  Start a MotorRunner thread to execute all the desired movements performed by the motors, in an action of standing up.   
         */
        MotorRunner standUpMotorsRunner = new MotorRunner("Standing up");
        standUpMotorsRunner.addMovement(lowerLeftHip);
        standUpMotorsRunner.addMovement(lowerRightHip);
        standUpMotorsRunner.addMovement(straightenLeftKnee);
        standUpMotorsRunner.addMovement(straightenRightKnee);
        standUpMotorsRunner.addMovement(leanForwardHead);
        standUpMotorsRunner.addMovement(leanForwardNeck);
        standUpMotorsRunner.addMovement(leanForwardBody);
        standUpMotorsRunner.addMovement(leanBackwardBody);
        standUpMotorsRunner.initMovement();
        standUpMotorsRunner.start();   
         
        /*  Start a MotorRunner thread to execute all the desired movements performed by the motors, in an action of walking.   
         */
        MotorRunner walkMotorsRunner = new MotorRunner("Walking");
       
//        RotationMovement swingForwardLeftShoulder = new RotationMovement(leftShoulder, 190, "swing forward left shoulder");
//        RotationMovement swingForwardRightShoulder = new RotationMovement(rightShoulder, 190, "swing forward right shoulder");
        
        RotationMovement walkInitialContactLeftHip = new RotationMovement(leftHip, 40, "initial contact left hip");
        RotationMovement walkInitialContactLeftKnee = new RotationMovement(leftKnee, 0, "loading response left knee");
        RotationMovement walkInitialContacteLeftAnkle = new RotationMovement(leftAnkle, 15, "loading response left ankle");
        RotationMovement walkInitialContactLeftShoulder = new RotationMovement(leftShoulder, 200, "initial contact left shoulder");
        RotationMovement walkInitialContactLeftElbow = new RotationMovement(leftElbow, 20, "initial contact left elbow");
        RotationMovement walkPreSwingRightHip = new RotationMovement(rightHip, 10, "preswing right hip");
        RotationMovement walkPreSwingRightKnee = new RotationMovement(rightKnee, 40, "preswing right knee");
        RotationMovement walkPreSwingRightAnkle = new RotationMovement(rightAnkle, 30, "preswing right ankle");
        RotationMovement walkPreSwingRightShoulder = new RotationMovement(rightShoulder, 175, "preswing right shoulder");
        RotationMovement walkPreSwingRightElbow = new RotationMovement(rightElbow, 20, "preswing right elbow");
        
        
        walkMotorsRunner.addMovement(walkInitialContactLeftHip);
        walkMotorsRunner.addMovement(walkInitialContactLeftKnee);
        walkMotorsRunner.addMovement(walkInitialContacteLeftAnkle);
        walkMotorsRunner.addMovement(walkInitialContactLeftShoulder);
        walkMotorsRunner.addMovement(walkInitialContactLeftElbow);
        walkMotorsRunner.addMovement(walkPreSwingRightHip);
        walkMotorsRunner.addMovement(walkPreSwingRightKnee);
        walkMotorsRunner.addMovement(walkPreSwingRightAnkle);
        walkMotorsRunner.addMovement(walkPreSwingRightShoulder);
        walkMotorsRunner.addMovement(walkPreSwingRightElbow);
           

        RotationMovement walkLoadingResponseLeftHip = new RotationMovement(leftHip, 40, "loading response left hip");
        RotationMovement walkLoadingResponseLeftKnee = new RotationMovement(leftKnee, 20, "loading response left knee");
        RotationMovement walkLoadingResponseLeftAnkle = new RotationMovement(leftAnkle, 20, "loading response left ankle");
        RotationMovement walkLoadingResponseLeftShoulder = new RotationMovement(leftShoulder, 190, "loading response left shoulder");
        RotationMovement walkLoadingResponseLeftElbow = new RotationMovement(leftElbow, 10, "loading response left elbow");
        RotationMovement walkInitialSwingRightHip = new RotationMovement(rightHip, 35, "initial swing right hip");
        RotationMovement walkInitialSwingRightKnee = new RotationMovement(rightKnee, 60, "initial swing right knee");
        RotationMovement walkInitialSwingRightAnkle = new RotationMovement(rightAnkle, 20, "initial swing right ankle");
        RotationMovement walkInitialSwingRightShoulder = new RotationMovement(rightShoulder, 190, "initial swing right shoulder");
        RotationMovement walkInitialSwingRightElbow = new RotationMovement(rightElbow, 15, "initial swing right elbow");
        
        
        walkMotorsRunner.addMovement(walkLoadingResponseLeftHip);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftKnee);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftAnkle);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftShoulder);
        walkMotorsRunner.addMovement(walkLoadingResponseLeftElbow);
        walkMotorsRunner.addMovement(walkInitialSwingRightHip);
        walkMotorsRunner.addMovement(walkInitialSwingRightKnee);
        walkMotorsRunner.addMovement(walkInitialSwingRightAnkle);
        walkMotorsRunner.addMovement(walkInitialSwingRightShoulder);
        walkMotorsRunner.addMovement(walkInitialSwingRightElbow);
       
        
        RotationMovement walkMidStanceLeftHip = new RotationMovement(leftHip, 20, "mid stance left hip");
        RotationMovement walkMidStanceLeftKnee = new RotationMovement(leftKnee, 5, "mid stance left knee");
        RotationMovement walkMidStanceLeftAnkle = new RotationMovement(leftAnkle, 10, "mid stance left ankle");
        RotationMovement walkMidStanceeLeftShoulder = new RotationMovement(leftShoulder, 185, "mid stance  left shoulder");
        RotationMovement walkMidStanceLeftElbow = new RotationMovement(leftElbow, 5, "mid stance  left elbow");
        RotationMovement walkMidSwingRightHip = new RotationMovement(rightHip, 45, "mid swing right hip");
        RotationMovement walkMidSwingRightKnee = new RotationMovement(rightKnee, 25, "mid swing right knee");
        RotationMovement walkMidSwingRightAnkle = new RotationMovement(rightAnkle, 15, "mid stance right ankle");
        RotationMovement walkMidSwingRightShoulder = new RotationMovement(rightShoulder, 200, "initial swing right shoulder");
        RotationMovement walkMidSwingRightElbow = new RotationMovement(rightElbow, 15, "initial swing right elbow");
        
        walkMotorsRunner.addMovement(walkMidStanceLeftHip);
        walkMotorsRunner.addMovement(walkMidStanceLeftKnee);
        walkMotorsRunner.addMovement(walkMidStanceLeftAnkle);
        walkMotorsRunner.addMovement(walkMidStanceeLeftShoulder);
        walkMotorsRunner.addMovement(walkMidStanceLeftElbow);
        walkMotorsRunner.addMovement(walkMidSwingRightHip);
        walkMotorsRunner.addMovement(walkMidSwingRightKnee);
        walkMotorsRunner.addMovement(walkMidSwingRightAnkle);
        walkMotorsRunner.addMovement(walkMidSwingRightShoulder);
        walkMotorsRunner.addMovement(walkMidSwingRightElbow);
        
        
        RotationMovement walkTerminalStanceLeftHip = new RotationMovement(leftHip, 0, "terminal stance left hip");
        RotationMovement walkTerminalStanceLeftKnee = new RotationMovement(leftKnee, 0, "terminal stance left knee");
        RotationMovement walkTerminalStanceLeftAnkle = new RotationMovement(leftAnkle, 5, "terminal stance left ankle");
        RotationMovement walkTerminalStanceLeftShoulder = new RotationMovement(leftShoulder, 165, "terminal stance  left shoulder");
        RotationMovement walkTerminalStanceLeftElbow = new RotationMovement(leftElbow, 30, "terminal stance  left elbow");
        RotationMovement walkTerminalSwingRightHip = new RotationMovement(rightHip, 40, "Terminal swing right hip");
        RotationMovement walkTerminalSwingRightKnee = new RotationMovement(rightKnee, 0, "Terminal swing right knee");
        RotationMovement walkTerminalSwingRightAnkle = new RotationMovement(rightAnkle, 15, "Terminal stance right ankle");
        RotationMovement walkTerminalSwingRightShoulder = new RotationMovement(rightShoulder, 210, "Terminal swing right shoulder");
        RotationMovement walkTerminalSwingRightElbow = new RotationMovement(rightElbow, 20, "Terminal swing right elbow");
        
        walkMotorsRunner.addMovement(walkTerminalStanceLeftHip);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftKnee);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftAnkle);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftShoulder);
        walkMotorsRunner.addMovement(walkTerminalStanceLeftElbow);
        walkMotorsRunner.addMovement(walkTerminalSwingRightHip);
        walkMotorsRunner.addMovement(walkTerminalSwingRightKnee);
        walkMotorsRunner.addMovement(walkTerminalSwingRightAnkle);
        walkMotorsRunner.addMovement(walkTerminalSwingRightShoulder);
        walkMotorsRunner.addMovement(walkTerminalSwingRightElbow);
        
     
        RotationMovement walkPreSwingLeftHip = new RotationMovement(leftHip, 10, "preswing left hip");
        RotationMovement walkPreSwingLeftKnee = new RotationMovement(leftKnee, 40, "preswing left knee");
        RotationMovement walkPreSwingLeftAnkle = new RotationMovement(leftAnkle, 30, "preswing left ankle");
        RotationMovement walkPreSwingLeftShoulder = new RotationMovement(leftShoulder, 175, "preswing left shoulder");
        RotationMovement walkPreSwingLeftElbow = new RotationMovement(leftElbow, 20, "preswing left elbow");
        RotationMovement walkInitialContactRightHip = new RotationMovement(rightHip, 40, "initial contact right hip");
        RotationMovement walkInitialContactRightKnee = new RotationMovement(rightKnee, 0, "loading response right knee");
        RotationMovement walkInitialContactRightAnkle = new RotationMovement(rightAnkle, 15, "loading response right ankle");
        RotationMovement walkInitialContactRightShoulder = new RotationMovement(rightShoulder, 200, "initial contact right shoulder");
        RotationMovement walkInitialContactRightElbow = new RotationMovement(rightElbow, 20, "initial contact right elbow");
        
        
        walkMotorsRunner.addMovement(walkPreSwingLeftHip);
        walkMotorsRunner.addMovement(walkPreSwingLeftKnee);
        walkMotorsRunner.addMovement(walkPreSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkPreSwingLeftShoulder);
        walkMotorsRunner.addMovement(walkPreSwingLeftElbow);
        walkMotorsRunner.addMovement(walkInitialContactRightHip);
        walkMotorsRunner.addMovement(walkInitialContactRightKnee);
        walkMotorsRunner.addMovement(walkInitialContactRightAnkle);
        walkMotorsRunner.addMovement(walkInitialContactRightShoulder);
        walkMotorsRunner.addMovement(walkInitialContactRightElbow);
        
        
        RotationMovement walkInitialSwingLeftHip = new RotationMovement(leftHip, 35, "initial swing left hip");
        RotationMovement walkInitialSwingLeftKnee = new RotationMovement(leftKnee, 60, "initial swing left knee");
        RotationMovement walkInitialSwingLeftAnkle = new RotationMovement(leftAnkle, 20, "initial swing left ankle");
        RotationMovement walkInitialSwingLeftShoulder = new RotationMovement(leftShoulder, 190, "initial swing left shoulder");
        RotationMovement walkInitialSwingLeftElbow = new RotationMovement(leftElbow, 15, "initial swing left elbow");
        RotationMovement walkLoadingResponseRighttHip = new RotationMovement(rightHip, 40, "loading response right hip");
        RotationMovement walkLoadingResponseRightKnee = new RotationMovement(rightKnee, 20, "loading response right knee");
        RotationMovement walkLoadingResponseRightAnkle = new RotationMovement(rightAnkle, 20, "loading response right ankle");
        RotationMovement walkLoadingResponseRightShoulder = new RotationMovement(rightShoulder, 190, "loading response right shoulder");
        RotationMovement walkLoadingResponsRightElbow = new RotationMovement(rightElbow, 10, "loading response right elbow");
        
        walkMotorsRunner.addMovement(walkInitialSwingLeftHip);
        walkMotorsRunner.addMovement(walkInitialSwingLeftKnee);
        walkMotorsRunner.addMovement(walkInitialSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkInitialSwingLeftShoulder);
        walkMotorsRunner.addMovement(walkInitialSwingLeftElbow);
        walkMotorsRunner.addMovement(walkLoadingResponseRighttHip);
        walkMotorsRunner.addMovement(walkLoadingResponseRightKnee);
        walkMotorsRunner.addMovement(walkLoadingResponseRightAnkle);
        walkMotorsRunner.addMovement(walkLoadingResponseRightShoulder);
        walkMotorsRunner.addMovement(walkLoadingResponsRightElbow);
        
        
        RotationMovement walkMidSwingLeftHip = new RotationMovement(leftHip, 45, "mid swing left hip");
        RotationMovement walkMidSwingLeftKnee = new RotationMovement(leftKnee, 25, "mid swing left knee");
        RotationMovement walkMidSwingLeftAnkle = new RotationMovement(leftAnkle, 15, "mid stance left ankle");
        RotationMovement walkMidSwingLeftShoulder = new RotationMovement(leftShoulder, 200, "initial swing left shoulder");
        RotationMovement walkMidSwingLeftElbow = new RotationMovement(leftElbow, 15, "initial swing left elbow");
        RotationMovement walkMidStanceRightHip = new RotationMovement(rightHip, 20, "mid stance right hip");
        RotationMovement walkMidStanceRightKnee = new RotationMovement(rightKnee, 5, "mid stance right knee");
        RotationMovement walkMidStanceRightAnkle = new RotationMovement(rightAnkle, 10, "mid stance right ankle");
        RotationMovement walkMidStanceRightShoulder = new RotationMovement(rightShoulder, 185, "mid stance  right shoulder");
        RotationMovement walkMidStanceRightElbow = new RotationMovement(rightElbow, 5, "mid stance  right elbow");
        
        walkMotorsRunner.addMovement(walkMidSwingLeftHip);
        walkMotorsRunner.addMovement(walkMidSwingLeftKnee);
        walkMotorsRunner.addMovement(walkMidSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkMidSwingLeftShoulder);
        walkMotorsRunner.addMovement(walkMidSwingLeftElbow);
        walkMotorsRunner.addMovement(walkMidStanceRightHip);
        walkMotorsRunner.addMovement(walkMidStanceRightKnee);
        walkMotorsRunner.addMovement(walkMidStanceRightAnkle);
        walkMotorsRunner.addMovement(walkMidStanceRightShoulder);
        walkMotorsRunner.addMovement(walkMidStanceRightElbow);
        
        RotationMovement walkTerminalSwingLeftHip = new RotationMovement(leftHip, 40, "Terminal swing left hip");
        RotationMovement walkTerminalSwingLeftKnee = new RotationMovement(leftKnee, 0, "Terminal swing left knee");
        RotationMovement walkTerminalSwingLeftAnkle = new RotationMovement(leftAnkle, 15, "Terminal swing left ankle");
        RotationMovement walkTerminalSwingLeftShoulder = new RotationMovement(leftShoulder, 210, "Terminal swing left shoulder");
        RotationMovement walkTerminalSwingLeftElbow = new RotationMovement(leftElbow, 20, "Terminal swing left elbow");
        RotationMovement walkTerminalStanceRightHip = new RotationMovement(rightHip, 0, "terminal stance right hip");
        RotationMovement walkTerminalStanceRightKnee = new RotationMovement(rightKnee, 0, "terminal stance right knee");
        RotationMovement walkTerminalStanceRightAnkle = new RotationMovement(rightAnkle, 5, "terminal stance right ankle");
        RotationMovement walkTerminalStanceRightShoulder = new RotationMovement(rightShoulder, 165, "terminal stance  left shoulder");
        RotationMovement walkTerminalStanceRightElbow = new RotationMovement(rightElbow, 30, "terminal stance  left elbow");
        
        walkMotorsRunner.addMovement(walkTerminalSwingLeftHip);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftKnee);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftAnkle);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftShoulder);
        walkMotorsRunner.addMovement(walkTerminalSwingLeftElbow);
        walkMotorsRunner.addMovement(walkTerminalStanceRightHip);
        walkMotorsRunner.addMovement(walkTerminalStanceRightKnee);
        walkMotorsRunner.addMovement(walkTerminalStanceRightAnkle);
        walkMotorsRunner.addMovement(walkTerminalStanceRightShoulder);
        walkMotorsRunner.addMovement(walkTerminalStanceRightElbow);
        
        RotationMovement walkStopLeftHip = new RotationMovement(leftHip, 20, "return to stand pose left hip");
        RotationMovement walkStopLeftKnee = new RotationMovement(leftKnee, 0, "return to stand pose left knee");
        RotationMovement walkStopLeftAnkle = new RotationMovement(leftAnkle, 15, "return to stand pose left ankle");
        RotationMovement walkStopLeftShoulder = new RotationMovement(leftShoulder, 180, "return to stand pose left shoulder");
        RotationMovement walkStopLeftElbow = new RotationMovement(leftElbow, 0, "return to stand pose left elbow");
        RotationMovement walkStopRightHip = new RotationMovement(rightHip, 20, "return to stand posee right hip");
        RotationMovement walkStopRightKnee = new RotationMovement(rightKnee, 0, "return to stand pose right knee");
        RotationMovement walkStopeRightAnkle = new RotationMovement(rightAnkle, 15, "return to stand pose right ankle");
        RotationMovement walkStopRightShoulder = new RotationMovement(rightShoulder, 180, "return to stand pose right shoulder");
        RotationMovement walkStopRightElbow = new RotationMovement(rightElbow, 0, "return to stand pose right elbow");
        
        walkMotorsRunner.addMovement(walkStopLeftHip);
        walkMotorsRunner.addMovement(walkStopLeftKnee);
        walkMotorsRunner.addMovement(walkStopLeftAnkle);
        walkMotorsRunner.addMovement(walkStopLeftShoulder);
        walkMotorsRunner.addMovement(walkStopLeftElbow);
        walkMotorsRunner.addMovement(walkStopRightHip);
        walkMotorsRunner.addMovement(walkStopRightKnee);
        walkMotorsRunner.addMovement(walkStopeRightAnkle);
        walkMotorsRunner.addMovement(walkStopRightHip);
        walkMotorsRunner.addMovement(walkStopRightShoulder);
        walkMotorsRunner.addMovement(walkStopRightElbow);
        
        
        /*  Start a MotorRunner thread to execute all the desired movements performed by the motors, in an action of sitting down.   
         */
        MotorRunner sitDownMotorsRunner = new MotorRunner("Sitting down");
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
//        androidRunner.getMotorRunners().add(walkMotorsRunner);
//        androidRunner.getMotorRunners().add(sitDownMotorsRunner);

        androidRunner.start();

    }

}
