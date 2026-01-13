
package frc.robot.subsystems.motor;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
  private MotorIO m_sparkIO;

  public Motor(MotorIO sparkIO) {
    m_sparkIO = sparkIO;
  }

  public void setTarget(Voltage target) {
    m_sparkIO.setTarget(target);
  }

  public Command getNewSetVoltsCommand(double i) {
    return new InstantCommand(
        () -> {
          setTarget(Volts.of(i));
        },
        this);
  }

  @Override
  public void periodic() {
    m_sparkIO.periodic();
  }
}
