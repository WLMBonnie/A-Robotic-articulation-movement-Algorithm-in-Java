/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroidca3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Each motor moves how many degrees per second
 * 
 * This is a collection of movement and it's a thread to simulate the movements of all motors in this connection
 *
 * @author WailuiMa
 */
public class MotorRunner extends Thread {

    private List<MotorMovement> movementList = new ArrayList<MotorMovement>();
    private String action;
    
    public MotorRunner(String action){
        this.action = action;
    }

    @Override
    public void run() {

        boolean finishedMove = false;
        MotorMovement movement = null;
        Motor motor = null;
        boolean isAllDone;
        
        System.out.println("------ Start " +  action + "  ------");
        
        
        while (!finishedMove) {

            isAllDone = true;
            for (int i = 0; i < movementList.size(); i++) {
                
                movement = movementList.get(i);
                motor = movement.getMotor();

                if (movement.isReady()) {
                    
                    System.out.println("------ " + movement.getAction() + " ------");
                    
                    
                    if (!movement.isMovementDone()) {
                        
                        
                        if (motor instanceof FlexionMotor) {
                             if (((FlexionMotor) motor).bend()) {
                                keepMotorsInPlace();
                            }
                             
                        }else{
                            if (motor.move()) {
                                keepMotorsInPlace();
                            }
                        }
                        

                    }
                    
                    
                    System.out.println("Action: " + movement.getAction() +" Motor: " + motor.getName() + " all done? " + movement.isMovementDone());
                }
                
                isAllDone = isAllDone && movement.isMovementDone();
                
                if(movement.isMovementDone()){
                    initNextMotorMovement(movement);
                    movementList.remove(i);
                    i--;
                }

                
                /* To exit the thread when the steps have been done (the lines are for debugging)

                 */
           
            }
            
            if(isAllDone)
                finishedMove = true;

            try {
                sleep(1000);
            } catch (InterruptedException ie) {

            }
        }

          
        
        System.out.println("------ Finished " + action + "------");
    }

    /* once a motor is moved the Android requires 1 volt to hold all motors in place(must always be 1 volt
in battery).
    */
    public void keepMotorsInPlace() {
        System.out.println("Motors are running, consumes 1 voltage to keep them in place");
        if (Battery.isDischargable(1)) {
            Battery.discharge(1);
        }
    }

    public List<MotorMovement> addMovement(MotorMovement movement) {
        movementList.add(movement);
        return movementList;
    }

    public void initNextMotorMovement(MotorMovement movement) {

        MotorMovement nextMovement = null;
        Motor motor = movement.getMotor();
        Motor nextMotor = null;

        for (int i = 0; i < movementList.size(); i++) {
            nextMovement = movementList.get(i);
            
            if (movement.isMovementDone()
                    && !movement.getAction().equals(nextMovement.getAction())) {
                
                nextMotor = nextMovement.getMotor();
                
                if (motor.getName().equals(nextMotor.getName())
                        && !nextMovement.isReady()){
                    
                    nextMovement.initMovement();
                    break;
                }

            }

        }
    }
    
    public void initMovement(){
         HashSet<String> motorName = new HashSet<String>();
         MotorMovement movement = null;
         Motor motor = null;
         
         for (int i = 0; i < movementList.size(); i++) {
             movement = movementList.get(i);
             motor = movement.motor;
             
             if(!motorName.contains(motor.name)){
                 movement.initMovement();
                 motorName.add(motor.name);
             }
            

        }
    }
    
    
}
