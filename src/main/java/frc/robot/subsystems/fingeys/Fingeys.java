package frc.robot.subsystems.fingeys;

import static edu.wpi.first.units.Units.*;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Fingeys extends SubsystemBase {
  private FingeysIO m_FingeysIO;

  private Voltage setpoint;

  FingeysInputsAutoLogged loggedfingeys = new FingeysInputsAutoLogged();

  public Fingeys(FingeysIO fingeysIO) {
    m_FingeysIO = fingeysIO;
    loggedfingeys.angularVelocity = DegreesPerSecond.mutable(0);
    loggedfingeys.supplyCurrent = Amps.mutable(0);
    loggedfingeys.timestamp = 0.0;
    loggedfingeys.torqueCurrent = Amps.mutable(0);
    loggedfingeys.voltageSetPoint = Volts.mutable(0);
    loggedfingeys.voltage = Volts.mutable(0);
  }
// negetive voltage = eject
// positive voltage = intake
  public void setTarget(Voltage target) {
    setpoint = target;
    m_FingeysIO.setTarget(target);
  }

  public Command getNewSetAngleCommand(double i) {
    return new InstantCommand(
        () -> {
          setTarget(Volts.of(i));
        },
        this);
  }

  @Override
  public void periodic() {
    m_FingeysIO.updateInputs(loggedfingeys);
    Logger.processInputs("RobotState/Fingeys", loggedfingeys);
    m_FingeysIO.log();
    
  }
}
