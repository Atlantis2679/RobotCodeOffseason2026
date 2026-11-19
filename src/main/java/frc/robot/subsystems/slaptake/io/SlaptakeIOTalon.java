package frc.robot.subsystems.slaptake.io;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.RobotMap;
import frc.robot.utils.AlertsFactory;
import team2679.atlantiskit.logfields.LogFieldsTable;
import team2679.atlantiskit.periodicalerts.PeriodicAlertsGroup;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.Volts;
import static frc.robot.subsystems.slaptake.SlaptakeConstants.*;

public class SlaptakeIOTalon extends SlaptakeIO {
    private TalonFX motor = new TalonFX(RobotMap.CANBUS.FOURBAR_ID);
    private DutyCycleEncoder encoder = new DutyCycleEncoder(RobotMap.DIO.SLAPTAKE_ENCODER_ID);
    private VoltageOut voltageControl = new VoltageOut(Volts.zero());
    private StatusCode statusCode;
    
    public SlaptakeIOTalon(LogFieldsTable logFieldsTable){
        super(logFieldsTable);
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.SupplyCurrentLimit = CURRENT_LIMIT.in(Amps);
        config.CurrentLimits.SupplyCurrentLimitEnable = true;
        statusCode = motor.getConfigurator().apply(config);
        AlertsFactory.phoenixMotor(
            new PeriodicAlertsGroup("Slaptake"), 
            () -> statusCode,
            "slaptake error");
    }

    public void setVoltage(Voltage voltage){
        motor.setVoltage(voltage.in(Volts));
    }

    protected Current getCurrent() {
        return motor.getTorqueCurrent().getValue();
    }

    protected Angle getAngle() {
        return Rotations.of(encoder.get());
    }

}
