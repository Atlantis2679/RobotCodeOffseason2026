package frc.robot.subsystems.index;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.index.io.IndexIO;
import frc.robot.subsystems.index.io.IndexIOSim;
import frc.robot.subsystems.index.io.IndexIOTalon;
import team2679.atlantiskit.logfields.LogFieldsTable;

public class Index extends SubsystemBase{
    private final LogFieldsTable fieldsTable = new LogFieldsTable(getName());
    private final IndexIO io = Robot.isReal()? new IndexIOTalon(fieldsTable) : new IndexIOSim(fieldsTable);

    public Index(){
    }

    public void periodic(){
        fieldsTable.recordOutput("Current command: ",
            getCurrentCommand() != null? getCurrentCommand().getName() : "None");
    }

    public void stop(){
        io.setFeederVolt(Units.Volts.zero());
        io.setHopperVolt(Units.Volts.zero());
    }

    public void setFeederVolt(Voltage volt){
        fieldsTable.recordOutput("FeederMotorDesiredVolt", volt);
        volt = Volts.of(MathUtil.clamp(volt.magnitude(), -IndexConstants.MAX_FEEDER_VOLT, IndexConstants.MAX_FEEDER_VOLT));
        io.setFeederVolt(volt);
    }

    public void setHopperVolt(Voltage volt){
        fieldsTable.recordOutput("HopperMotorDesiredVolt", volt);
        volt = Volts.of(MathUtil.clamp(volt.magnitude(), -IndexConstants.MAX_HOPPER_VOLT, IndexConstants.MAX_HOPPER_VOLT));
        io.setHopperVolt(volt);
    }

    public Current getFeederCurrent(){
        return io.feederCurrent.get();
    }
    public Current getHopperCurrent(){
        return io.hopperCurrent.get();
    }
}
