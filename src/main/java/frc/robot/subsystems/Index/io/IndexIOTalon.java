package frc.robot.subsystems.Index.io;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
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
