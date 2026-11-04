package frc.robot.subsystems.Index;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.Index.io.IndexIO;
import frc.robot.subsystems.Index.io.IndexIOSim;
import frc.robot.subsystems.Index.io.IndexIOTalon;
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
        io.setFeederVolt(volt);
    }

    public void setHopperVolt(Voltage volt){
        fieldsTable.recordOutput("HopperMotorDesiredVolt", volt);
        io.setHopperVolt(volt);
    }

    public Current getFeederCurrent(){
        return io.feederCurrent.get();
    }
    public Current getHopperCurrent(){
        return io.hopperCurrent.get();
    }
}
