package frc.robot.subsystems.motor;

import edu.wpi.first.units.measure.Voltage;

public interface MotorIO {
  public void setTarget(Voltage target);

  public void periodic();

  public void stop();
}
