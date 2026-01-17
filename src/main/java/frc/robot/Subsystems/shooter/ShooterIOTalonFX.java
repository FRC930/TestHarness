package frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Voltage;

public class ShooterIOTalonFX implements ShooterIO {
    private VoltageOut request;
    private TalonFX roller;
    private Voltage m_setPoint = Volts.of(0);

     /* Keep a neutral out so we can disable the motor */
    private final NeutralOut m_brake = new NeutralOut();

    public ShooterIOTalonFX(int rollerMotorCAN, CANBus canBus) {
        roller = new TalonFX(rollerMotorCAN, canBus);
        request = new VoltageOut(0.0);
    }

    public void updateInputs(ShooterInputs inputs) {
        inputs.angularVelocity.mut_replace(roller.getVelocity().getValue());
        inputs.voltageSetPoint.mut_replace(m_setPoint);
        inputs.voltage.mut_replace(roller.getMotorVoltage().getValue());
        inputs.supplyCurrent.mut_replace(roller.getSupplyCurrent().getValue());
        inputs.torqueCurrent.mut_replace(roller.getTorqueCurrent(false).getValue());
    }


    @Override
    public void shoot(Voltage target) {
        request = request.withOutput(target);
        roller.setControl(request);
        m_setPoint = target;
    }
  
    @Override
    public void stop() {
        roller.setControl(m_brake);
    }
}