package frc.robot.subsystems.slaptake;

import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj2.command.Command;

public class SlaptakeCommands {
    private Slaptake slaptake;

    public SlaptakeCommands(Slaptake slaptake){
        this.slaptake = slaptake;
    }

    public Command stop(){
        return slaptake.run(() -> slaptake.setVoltage(Units.Volts.of(0)));
    }
}
