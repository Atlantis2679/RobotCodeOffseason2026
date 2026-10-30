package frc.robot.subsystems.roller;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.roller.io.RollerIO;
import frc.robot.subsystems.roller.io.RollerIOSim;
import frc.robot.subsystems.roller.io.RollerIOTalon;
import team2679.atlantiskit.logfields.LogFieldsTable;
import static frc.robot.subsystems.roller.RollerConstants.*;

public class Roller extends SubsystemBase {
    private LogFieldsTable logFieldsTable = new LogFieldsTable("Rolelr");
    private RollerIO io = Robot.isReal() ? new RollerIOTalon(logFieldsTable) 
                                         : new RollerIOSim(logFieldsTable);
    
    
    public Roller(){}

    @Override
    public void periodic() {
        logFieldsTable.update();
        logFieldsTable.recordOutput("current command", 
            this.getCurrentCommand() != null ? 
            this.getCurrentCommand().getName() :
            "none");
    }

    public void stop() {
        io.setVoltage(Volts.of(0));
    }

    public void setVoltage(Voltage voltage) {
        voltage = Volts.of(
            MathUtil.clamp(voltage.baseUnitMagnitude(), MIN_VOLTAGE, MAX_VOLTAGE));
        io.setVoltage(voltage);
    }

    public Current getCurrent() {
        return io.current.get();
    }
}
