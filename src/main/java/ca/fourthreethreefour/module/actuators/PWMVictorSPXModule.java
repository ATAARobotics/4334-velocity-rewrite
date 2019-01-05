package ca.fourthreethreefour.module.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.first.module.Module;
import edu.first.module.actuators.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class PWMVictorSPXModule extends Module.StandardModule implements SpeedController {

	private final PWMVictorSPX victor;
	
	protected PWMVictorSPXModule(PWMVictorSPX victor) {
		if (victor == null) {
            throw new NullPointerException("Null talon given");
		}
		this.victor = victor;
	}
	
	public PWMVictorSPXModule(int channel) {
		this(new PWMVictorSPX(channel));
	}
	
	@Override
	public void init() {
	}

	@Override
	protected void enableModule() {
	}
	
	@Override
	protected void disableModule() {
		victor.set(0);
		//set(ControlMode.Disabled, 0);
	}

	@Override
	public double get() {
		return victor.get();
	}

	@Override
	public double getRate() {
		return victor.get();
	}
	
	@Override
	public double getSpeed() {
		return victor.get();
	}

	@Override
	public int getRawSpeed() {
		return (int) victor.get();
	}

	@Override
	public void set(double value) {
		victor.set(value);
	}

	@Override
	public void setRate(double value) {
		victor.set(value);
	}

	@Override
	public void setRawSpeed(int value) {
		victor.set(value);
	}

	@Override
	public void setSpeed(double value) {
		victor.set(value);
	}
	
	public double getAnalogIn() {
		return 0;
	}

	@Override
	public void update() {

	}

}
