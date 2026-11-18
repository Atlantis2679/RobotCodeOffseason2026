package frc.robot.subsystems.roller.io;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.RobotMap;
import frc.robot.utils.PhoenixUtils;
import team2679.atlantiskit.logfields.LogFieldsTable;

import static edu.wpi.first.units.Units.Amps;
import static frc.robot.subsystems.roller.RollerConstants.*;

public class RollerIOTalon extends RollerIO{
    private TalonFX motor = new TalonFX(RobotMap.CANBUS.ROLLER_ID);

    public RollerIOTalon(LogFieldsTable logFieldsTable){
        super(logFieldsTable);
        TalonFXConfiguration motorConfig = new TalonFXConfiguration();
        motorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        motorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT.in(Amps);
        PhoenixUtils.tryUntilOk(5, () -> motor.getConfigurator().apply(motorConfig));
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
