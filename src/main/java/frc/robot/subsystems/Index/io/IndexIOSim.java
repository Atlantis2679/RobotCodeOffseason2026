package frc.robot.subsystems.Index.io;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.LogFieldsTable;

public class IndexIOSim extends IndexIO{

    public IndexIOSim(LogFieldsTable fieldsTable){
        super(fieldsTable);
    }

    @Override
    protected Current getHopperCurrent() {
        return Units.Amps.zero();
    }

    @Override
    protected Current getFeederCurrent() {
        return Units.Amps.zero();
    }

    @Override
    public void setHopperVolt(Voltage volt) {}

    @Override
    public void setFeederVolt(Voltage volt) {}
    
}
