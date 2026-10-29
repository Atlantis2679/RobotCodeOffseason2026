package frc.robot.subsystems.Index;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import team2679.atlantiskit.tunables.TunablesManager;

public class IndexCommands {
    private Index index;

    public IndexCommands(Index index){
        this.index = index;
        //tuneables maneger
    }

    public Command spinBoth(Supplier<Voltage> feederVolt, Supplier<Voltage> hopperVolt){
        return index.run(() -> {
            index.setFeederVolt(feederVolt.get());
            index.setHopperVolt(feederVolt.get());

            
        });
    }
}
