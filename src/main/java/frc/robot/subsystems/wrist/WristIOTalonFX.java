package frc.robot.subsystems.wrist;

import static edu.wpi.first.units.Units.*;

import java.util.Optional;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.LoggedTunableNumber;
import frc.robot.util.PhoenixUtil;

public class WristIOTalonFX implements WristIO {
  public PositionVoltage Request;
  public TalonFX Motor;

  public WristIOTalonFX(int MotorId) {
    Motor= new TalonFX(MotorId,"RIO");
    // Request = new MotionMagicVoltage(0);
    Request = new PositionVoltage(0);
    configureTalons();
    Motor.setPosition(0);
  }

  private void configureTalons() {
    TalonFXConfiguration cfg = new TalonFXConfiguration();
    cfg.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    cfg.Feedback.SensorToMechanismRatio = 9.0;

    cfg.Slot0.kP = 2.4;
    cfg.Voltage.withPeakForwardVoltage(8).withPeakReverseVoltage(-8);
    
    

    // MotionMagicConfigs mm_cfg = cfg.MotionMagic;
    // mm_cfg.MotionMagicAcceleration = 1.0;
    // mm_cfg.MotionMagicCruiseVelocity = 1.0;
    // mm_cfg.MotionMagicExpo_kA = 1.0;
    // mm_cfg.MotionMagicExpo_kV = 1.0;
    // mm_cfg.MotionMagicJerk = 1.0;

    PhoenixUtil.tryUntilOk(5, () -> Motor.getConfigurator().apply(cfg), Optional.of(Motor));
  }

  public void setTarget(Angle target) {
    Motor.setControl(Request.withPosition(target));
  }

  @Override
  public void updateInputs(WristInputs inputs) {
    inputs.wristAngle.mut_replace(Motor.getPosition().getValue());
    inputs.wristAngularVelocity.mut_replace(Motor.getVelocity().getValue());
    // inputs.wristSetPoint.mut_replace(
    //     Angle.ofRelativeUnits(
    //         ((MotionMagicVoltage) Motor.getAppliedControl()).Position, Rotations));
    inputs.supplyCurrent.mut_replace(Motor.getStatorCurrent().getValue());
  }

  @Override
  public void log() {
    SmartDashboard.putNumber("Wrist/Voltage", Motor.getMotorVoltage().getValueAsDouble());
    SmartDashboard.putString("Wrist/Request", Motor.getAppliedControl().toString());
    SmartDashboard.putNumber("Wrist/Position", Motor.getPosition().getValueAsDouble());
  }
}
