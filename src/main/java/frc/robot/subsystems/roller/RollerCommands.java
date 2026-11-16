package frc.robot.subsystems.roller;

import static edu.wpi.first.units.Units.Volts;
import static frc.robot.subsystems.roller.RollerConstants.*;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;

public class RollerCommands {
    private Roller roller;
    public RollerCommands(Roller roller){
        this.roller = roller;
    }

    public Command stop() {
        return roller.run(roller::stop);
    }

    public Command setVoltage(Voltage volt) {
        return roller.run(() -> roller.setVoltage(volt));
    }

    public Command setVoltage(Supplier<Voltage> volt) {
        return roller.run(() -> roller.setVoltage(volt.get()));
    }

    public Command manualController(DoubleSupplier supplier) {
        return roller.run(() -> roller.setVoltage(
            Volts.of(MAX_VOLTAGE*supplier.getAsDouble())));
    }
}
