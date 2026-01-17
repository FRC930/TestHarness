package frc.robot.Subsystems.shooter;

import static edu.wpi.first.units.Units.Volts;

import org.opencv.core.Point;

import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Voltage;

public class ShooterIOTalonFX implements ShooterIO {
    private VoltageOut request;
    private TalonFX roller;
    private Voltage m_setPoint = Volts.of(0);

    public ShooterIOTalonFX() {
        roller = new TalonFX(9);
        request = new VoltageOut(0.0);
    }

    public void updateInputs(ShooterInputs inputs) {
        inputs.voltage.mut_replace(roller.getMotorVoltage().getValue());
    }


    @Override
    public void shoot(Voltage target) {
        request = request.withOutput(target);
        roller.setControl(request);
        m_setPoint = target;
    }
  
    @Override
    public void stop() {
        roller.setControl(new StaticBrake());
    }
}