/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroidca3;

/** 
 * This class is to define the movement of flexion;
 * 
 * Inheritance: FlexionMovement is a child class of MotoMovement
 * 
 * @author WailuiMa
 */
public class FlexionMovement extends MotorMovement{
    
   public FlexionMovement(FlexionMotor motor, int movementDegree, String action) {
      super(motor, movementDegree, action);
    }
    
    @Override
    public void initMovement() {
          if(motor instanceof FlexionMotor){
            ((FlexionMotor)this.motor).initFlexion(movementDegree);
            this.ready = true;
        }
    }

    @Override
    public boolean isMovementDone() {
        
        boolean done = false;

        if (this.motor instanceof FlexionMotor) {
            FlexionMotor fMotor = (FlexionMotor) this.motor;
            if (fMotor.getCurrentFlexionDegree() == fMotor.getTargetFlexionDegree()) {
                done = true;
            } else {
                done = false;
            }
        } else {

            done = true;
        }

        return done;
    }
    
}
