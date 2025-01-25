package frc.robot.subsystems.fingeys;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.VelocityUnit;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.PhoenixUtil;

public class FingeysIOTalonFX implements FingeysIO {
  public VoltageOut Request;
  public TalonFX Motor;

  //private final FingeysConstants m_Constants;

  public FingeysIOTalonFX(int id) {
    Motor = new TalonFX(id);
    Request = new VoltageOut(0.0);

    Motor.setControl(Request);
    configureTalons();
  }

  private void configureTalons() {
    TalonFXConfiguration cfg = new TalonFXConfiguration();
    cfg.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    cfg.CurrentLimits.SupplyCurrentLimit = 40;
    cfg.CurrentLimits.StatorCurrentLimit = 120;
    PhoenixUtil.tryUntilOk(5, () -> Motor.getConfigurator().apply(cfg));
  }

  @Override
  public void updateInputs(FingeysInputs inputs) {
    inputs.angularVelocity.mut_replace(Motor.getVelocity().getValue());
    inputs.voltageSetPoint.mut_replace(
        Voltage.ofRelativeUnits(
            ((VoltageOut) Motor.getAppliedControl()).Output, Volts));
    inputs.voltage.mut_replace(Motor.getMotorVoltage().getValue());
    inputs.supplyCurrent.mut_replace(Motor.getStatorCurrent().getValue());
  }

  @Override
  public void setTarget(Voltage target) {
    Request = Request.withOutput(target);
    Motor.setControl(Request);
  }

  @Override
  public void stop() {
    Motor.setControl(new StaticBrake());
  }

  @Override
  public void log() {
    SmartDashboard.putNumber("Fingeys/Voltage", Motor.getMotorVoltage().getValueAsDouble());
    SmartDashboard.putString("Fingeys/Request", Motor.getAppliedControl().toString());
    SmartDashboard.putNumber("Fingeys/Position", Motor.getPosition().getValueAsDouble());
  }
}
