package frc.robot.subsystems.index.io;

import static edu.wpi.first.units.Units.Amps;
import static frc.robot.subsystems.index.IndexConstants.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.RobotMap;
import frc.robot.utils.PhoenixUtils;
import team2679.atlantiskit.logfields.LogFieldsTable;

public class IndexIOTalon extends IndexIO{
    private TalonFX feederMotor = new TalonFX(RobotMap.CANBUS.FEEDER_ID);    
    private TalonFX hopperMotor = new TalonFX(RobotMap.CANBUS.HOPPER_ID);
    
    public IndexIOTalon(LogFieldsTable fieldsTable){
        super(fieldsTable);

        TalonFXConfiguration feederConfig = new TalonFXConfiguration();
        TalonFXConfiguration hopperConfig = new TalonFXConfiguration();

        feederConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        feederConfig.CurrentLimits.StatorCurrentLimit = FEEDER_CURRENT_LIMIT.in(Amps);

        hopperConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        hopperConfig.CurrentLimits.StatorCurrentLimit = HOPPER_CURRENT_LIMIT.in(Amps);

        PhoenixUtils.tryUntilOk(5, () -> feederMotor.getConfigurator().apply(feederConfig));
        PhoenixUtils.tryUntilOk(5, () -> hopperMotor.getConfigurator().apply(hopperConfig));
    }

    @Override
    protected Current getHopperCurrent() {
        return hopperMotor.getSupplyCurrent().getValue();
    }

    @Override
    protected Current getFeederCurrent() {
        return feederMotor.getSupplyCurrent().getValue();
    }

    @Override
    public void setHopperVolt(Voltage volt) {
        hopperMotor.setVoltage(volt.in(Units.Volts));
    }

    @Override
    public void setFeederVolt(Voltage volt) {
        feederMotor.setVoltage(volt.in(Units.Volts));
    }
}
