package ca.fourthreethreefour;

import edu.first.module.Module;
import edu.first.module.joysticks.XboxController;
import edu.first.module.subsystems.Subsystem;
import edu.first.robot.IterativeRobotAdapter;
import edu.first.module.joysticks.BindingJoystick.DualAxisBind;

public class Robot extends IterativeRobotAdapter implements Constants {

	/*
	 * Creates Subsystems AUTO and TELEOP to separate modules required to be enabled
	 * in autonomous and modules required to be enabled in teleoperated mode, 
	 * then puts the two subsystems into ALL_MODULES subsystem. I just copied and
     * pasted this comment from 2018 lmao.
	 */
    private final Subsystem
        AUTO_MODULES = new Subsystem(new Module[] { drive }),
        TELEOP_MODULES = new Subsystem(new Module[] { drive, controllers }),
        ALL_MODULES = new Subsystem(new Module[] { AUTO_MODULES, TELEOP_MODULES });
    
    public Robot() {
        super("Velocity");
    }
    
	// Runs when the robot is first turned on
    @Override
    public void init() {
        // Initializes all modules
        ALL_MODULES.init();

        controller.addDeadband(XboxController.LEFT_FROM_MIDDLE, 0.12);
        controller.changeAxis(XboxController.LEFT_FROM_MIDDLE, speedFunction);
        controller.addDeadband(XboxController.RIGHT_X, 0.12);
        controller.invertAxis(XboxController.RIGHT_X);
        controller.changeAxis(XboxController.RIGHT_X, turnFunction);

        controller.addAxisBind(new DualAxisBind(controller.getLeftDistanceFromMiddle(), controller.getRightX()) {
			@Override
			public void doBind(double speed, double turn) {
                drivetrain.arcadeDrive(speed, turn);
            }
        });
    }

    @Override
    public void initDisabled() {
        ALL_MODULES.disable();
    }

	// Runs at the beginning of teleop
    @Override
    public void initTeleoperated() {
        TELEOP_MODULES.enable();
    }
    
	// Runs every (approx.) 20ms in teleop
    @Override
    public void periodicTeleoperated() {
        controller.doBinds();
    }

	// Runs at the end of teleop
    @Override
    public void endTeleoperated() {
        TELEOP_MODULES.disable();
    }
}