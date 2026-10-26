package frc.robot.subsystems.Index.io;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.RobotMap;
import team2679.atlantiskit.logfields.LogFieldsTable;


public class IndexIOTalon extends IndexIO{
    private TalonFX feederMotor = new TalonFX(RobotMap.CANBUS.FEEDER_MOTOR);    
    private TalonFX hopperMotor = new TalonFX(RobotMap.CANBUT.HOPPER_MOTOR);
    
    public IndexIOTalon(LogFieldsTable fieldsTable){
        super(fieldsTable);
    }

    @Override
    protected double getHopperCurrent() {
        return hopperMotor.getSupplyCurrent().getValueAsDouble();
    }

    @Override
    protected double getFeederCurrent() {
        return feederMotor.getSupplyCurrent().getValueAsDouble();
    }

    @Override
    protected void setHopperVolt(double volt) {
        hopperMotor.setVoltage(volt);
    }

    @Override
    protected void setFeederVolt(double volt) {
        feederMotor.setVoltage(volt);
    }
}
