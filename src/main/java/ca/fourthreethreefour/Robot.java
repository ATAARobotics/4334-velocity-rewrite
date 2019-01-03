package ca.fourthreethreefour;

import edu.first.module.Module;
import edu.first.module.subsystems.Subsystem;
import edu.first.robot.IterativeRobotAdapter;

public class Robot extends IterativeRobotAdapter implements Constants {

	/*
	 * Creates Subsystems AUTO and TELEOP to separate modules required to be enabled
	 * in autonomous and modules required to be enabled in teleoperated mode, 
	 * then puts the two subsystems into ALL_MODULES subsystem. I just copied and
     * pasted this comment from 2018 lmao.
	 */
    private final Subsystem
        AUTO_MODULES = new Subsystem(new Module[] { }),
        TELEOP_MODULES = new Subsystem(new Module[] { controllers }),
        ALL_MODULES = new Subsystem(new Module[] { AUTO_MODULES, TELEOP_MODULES });
    
    public Robot() {
        super("Velocity");
    }
    
	// Runs when the robot is first turned on
    @Override
    public void init() {
        // Initializes all modules
        ALL_MODULES.init();
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