package frc.robot.subsystems.roller.io;

import static edu.wpi.first.units.Units.Amps;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.LogFieldsTable;

public class RollerIOSim extends RollerIO{
    public RollerIOSim(LogFieldsTable fields){
        super(fields);
    }

    public void setVoltage(Voltage voltage){}

    @Override
    protected Current getCurrent(){
        return Amps.of(0);
    }
}
