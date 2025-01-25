package frc.robot.subsystems.wrist;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Optional;
import org.littletonrobotics.junction.Logger;

public class Wrist extends SubsystemBase {
  private WristIO m_WristIO;
  private Angle setpoint;

  WristInputsAutoLogged loggedwrist = new WristInputsAutoLogged();

  public Wrist(WristIO wristIO) {
    m_WristIO = wristIO;
    loggedwrist.wristAngle = Degrees.mutable(0);
    loggedwrist.wristAngularVelocity = DegreesPerSecond.mutable(0);
    loggedwrist.wristSetPoint = Degrees.mutable(0);
    loggedwrist.supplyCurrent = Amps.mutable(0);
    loggedwrist.timestamp = 0.0;
    loggedwrist.torqueCurrent = Amps.mutable(0);
    loggedwrist.voltageSetPoint = Volts.mutable(0);
  }

  public void setAngle(Angle angle) {
    setpoint = angle;
    m_WristIO.setTarget(angle);
  }

  public Command getNewWristTurnCommand(double i) {
    return new InstantCommand(
        () -> {
          setAngle(Degrees.of(i));
        },
        this);
  }

  @Override
  public void periodic() {
    m_WristIO.updateInputs(loggedwrist);
    Logger.processInputs("RobotState/Wrist", loggedwrist);
    m_WristIO.log();
  }
}
