package frc.robot.subsystems.roller.io;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.RobotMap;
import frc.robot.utils.AlertsFactory;
import team2679.atlantiskit.logfields.LogFieldsTable;
import team2679.atlantiskit.periodicalerts.PeriodicAlertsGroup;

import static frc.robot.subsystems.roller.RollerConstants.*;

public class RollerIOTalon extends RollerIO{
    private TalonFX motor = new TalonFX(RobotMap.CANBUS.ROLLER_ID);
    private StatusCode statusCode;

    public RollerIOTalon(LogFieldsTable logFieldsTable){
        super(logFieldsTable);
        TalonFXConfiguration talonFXConfiguration = new TalonFXConfiguration();
        talonFXConfiguration.CurrentLimits.StatorCurrentLimitEnable = true;
        talonFXConfiguration.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT;
        statusCode = motor.getConfigurator().apply(talonFXConfiguration);
        AlertsFactory.phoenixError(
            new PeriodicAlertsGroup("Roller"),
            () -> statusCode,
            "Roller error");
    }

    public void setVoltage(Voltage volt){
        fields.recordOutput("voltage", volt.baseUnitMagnitude());
        motor.setVoltage(volt.magnitude());
    }

    @Override
    protected Current getCurrent() {
        return motor.getTorqueCurrent().getValue();
    }

}
