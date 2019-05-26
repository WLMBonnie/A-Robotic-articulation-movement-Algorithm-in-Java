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
public abstract class MotorMovement {
    
    protected Motor motor;
    protected int movementDegree;
    protected boolean ready;
    protected String action;

    public MotorMovement(Motor motor, int movementDegree, String action) {
        this.motor = motor;
        this.movementDegree = movementDegree;
        this.action = action;
    }

    public Motor getMotor() {
        return this.motor;
    }
    
    public String getAction(){
        return this.action;
    }

    public int getMovementDegree() {
        return this.movementDegree;
    }

    public abstract void initMovement();
  

    public abstract boolean isMovementDone();

   
    public boolean isReady() {
        return ready;
    }
   
}
