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
public class MoveMovement extends MotorMovement{


    public MoveMovement(Motor motor, int movementDegree, String action) {
      super(motor, movementDegree, action);
    }


    public void initMovement() {
//        int targetDegree = this.motor.getCurrentDegree() + movementDegree;
        this.motor.initMovement(movementDegree);
        this.ready = true;
    }
    

    public boolean isMovementDone() {

        boolean done = false;
        if (this.motor.getCurrentDegree() == this.motor.getTargetDegree()) {
            done = true;
        } else {
            done = false;
        }

        return done;
    }

    public boolean isReady() {
        return ready;
    }
    
    public boolean isDone(){
       boolean done = true;
//             System.out.println(motor.getName() + " movement done? " + isMovementDone());
                done = done && isMovementDone();
                
            return done;
    }

}
