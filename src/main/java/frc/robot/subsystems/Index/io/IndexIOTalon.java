package frc.robot.subsystems.Index.io;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.RobotMap;
import team2679.atlantiskit.logfields.LogFieldsTable;


public class IndexIOTalon extends IndexIO{
    private TalonFX feederMotor = new TalonFX(RobotMap.CANBUS.FEEDER_MOTOR);    
    private TalonFX hopperMotor = new TalonFX(RobotMap.CANBUT.HOPPER_MOTOR);

    TalonFXConfiguration feederMotorConfig = new TalonFXConfiguration();
    
    feederMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    feederMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
    feederMotorConfig.CurrentLimits.StatorCurrentLimit = IndexConstants.FEEDER_STATOR_CURRENT_LIMIT;
    feederMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    feederMotorConfig.CurrentLimits.SupplyCurrentLimit = IndexConstants.FEEDER_SUPPLY_CURRENT_LIMIT;
    

    TalonFXConfiguration hopperMotorConfig = new TalonFXConfiguration();

    hopperMotor.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    hopperMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
    hopperMotorConfig.CurrentLimits.StatorCurrentLimit = IndexConstants.HOPPER_STATOR_CURRENT_LIMIT;
    hopperMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    hopperMotorConfig.CurrentLimits.SupplyCurrentLimit = IndexConstants.HOPPER_SUPPLY_CURRENT_LIMIT;
    
    
    public IndexIOTalon(LogFieldsTable fieldsTable){
        super(fieldsTable);
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
