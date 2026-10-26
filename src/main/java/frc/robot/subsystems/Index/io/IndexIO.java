package frc.robot.subsystems.Index.io;

import java.util.function.DoubleSupplier;

import team2679.atlantiskit.logfields.IOBase;
import team2679.atlantiskit.logfields.LogFieldsTable;

public abstract class IndexIO extends IOBase{

    public DoubleSupplier hopperCurrent = fields.addDouble("hopperCurrent", this::getHopperCurrent);
    public DoubleSupplier feederCurrent = fields.addDouble("feederCurrent", this::getFeederCurrent);

    public IndexIO(LogFieldsTable fieldsTable){
        super (fieldsTable);
    }
    

    protected abstract double getHopperCurrent();
    protected abstract double getFeederCurrent();

    protected abstract void setHopperVolt(double volt);
    protected abstract void setFeederVolt(double volt);

}
