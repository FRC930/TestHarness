package frc.robot.subsystems.motor;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.units.measure.Voltage;

public class MotorIOSpark implements MotorIO {
  public Voltage speed;
  public Spark motor;

  public MotorIOSpark(int id) {
    motor = new Spark(id);
    speed = Volts.of(0.0);
  }

  @Override
  public void periodic() {
    motor.setVoltage(speed);
  }

  @Override
  public void setTarget(Voltage target) {
    speed = target;
  }

  @Override
  public void stop() {
    speed = Volts.of(0.0);
  }
}
