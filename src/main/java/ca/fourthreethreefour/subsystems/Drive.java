package ca.fourthreethreefour.subsystems;

import edu.first.identifiers.Function;
import edu.first.identifiers.InversedSpeedController;
import edu.first.module.Module;
import edu.first.module.actuators.Drivetrain;
import ca.fourthreethreefour.module.actuators.MotorModule;
import ca.fourthreethreefour.module.actuators.PWMVictorSPXModule;
import edu.first.module.actuators.SpeedController;
import edu.first.module.actuators.SpeedControllerGroup;
import ca.fourthreethreefour.module.actuators.MotorModule.Type;
import edu.first.module.subsystems.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public interface Drive {

    /*MotorModule
        left1 = new MotorModule(Type.VICTOR_SPX, 0),
        left2 = new MotorModule(Type.VICTOR_SPX, 1),
        right1 = new MotorModule(Type.VICTOR_SPX, 2),
        right2 = new MotorModule(Type.VICTOR_SPX, 3);*/

    PWMVictorSPXModule
        left1 = new PWMVictorSPXModule(0),
        left2 = new PWMVictorSPXModule(1),
        right1 = new PWMVictorSPXModule(2),
        right2 = new PWMVictorSPXModule(3);

    SpeedControllerGroup
        left = new SpeedControllerGroup(new SpeedController[] { left1, left2 }),
        right = new SpeedControllerGroup(new SpeedController[] { right1, right2 });

    Drivetrain
        drivetrain = new Drivetrain(left, new InversedSpeedController(right));

        
	/**
	 * Function used in driving controls that squares the input of the joysticks on the controller. 
	 * This is done to make controls more intuitive.
	 */
	Function speedFunction = new Function() {
        @Override
        public double F(double in) {
            // if in is greater than 0, multiply it by itself, otherwise multiply by itself and make it negative
            return in > 0 ? in * in : -(in * in);
        }
    };

    Function turnFunction = new Function() {
		@Override
		public double F(double in) {
			//if in is greater than 0, bring it to the power of TURN_CURVE
			//otherwise bring its absolute value to the power of TURN_CURVE and make it negative
			return in > 0 ? Math.pow(in, 1.5) : -Math.pow(Math.abs(in), 1.5);
		}
    };
    
    public Subsystem drive = new Subsystem(new Module[] { drivetrain });
}