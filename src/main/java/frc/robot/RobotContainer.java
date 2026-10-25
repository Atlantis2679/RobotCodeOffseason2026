package frc.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.FlippingUtil;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.swerve.Swerve;
import frc.robot.subsystems.swerve.SwerveCommands;
import frc.robot.subsystems.swerve.SwerveConstants.PathPlanner;
// import frc.robot.subsystems.vision.Vision;
import frc.robot.utils.NaturalXboxController;
import team2679.atlantiskit.tunables.Tunable;
import team2679.atlantiskit.tunables.TunableBuilder;
import team2679.atlantiskit.tunables.TunablesManager;
import team2679.atlantiskit.tunables.extensions.TunableCommand;
import team2679.atlantiskit.valueholders.DoubleHolder;
import frc.robot.subsystems.poseestimation.PoseEstimator;

public class RobotContainer {
    private final Swerve swerve = new Swerve();

    private final SwerveCommands swerveCommands = new SwerveCommands(swerve);
    
    private final PowerDistribution pdh = new PowerDistribution();
    private final NaturalXboxController driverController = new NaturalXboxController( RobotMap.Controllers.DRIVER_PORT); 
    private final NaturalXboxController operatorController = new NaturalXboxController(
            RobotMap.Controllers.OPERATOR_PORT);

    private BooleanSupplier isShootingHub;

    public static final LoggedDashboardChooser<Boolean> isRedAlliance = new LoggedDashboardChooser<>("alliance");

    static {
        isRedAlliance.addDefaultOption("red", true);
        isRedAlliance.addOption("blue", false);
        SmartDashboard.putBoolean("isRedAlliance", RobotContainer.isRedAlliance());
    }

    private SendableChooser<Command> autoChooser = null;

    public RobotContainer() {
        pdh.setSwitchableChannel(true);
        isRedAlliance.onChange((isRedAlliance) -> {
            swerve.resetGyroYawZero();
            PoseEstimator.getInstance().resetYawZero();
        });
        TunablesManager.add("Reset Yaw", new Tunable() {
            @Override
            public void initTunable(TunableBuilder builder) {
                DoubleHolder angleToReset = new DoubleHolder(0);
                builder.addDoubleProperty("angleToResetDegrees", angleToReset::get, angleToReset::set);
                builder.addChild("Reset!", new InstantCommand(() -> {
                    swerve.resetGyroYaw(angleToReset.get());
                    PoseEstimator.getInstance().resetYaw(Rotation2d.fromDegrees(angleToReset.get()));
                }));
            }
        });
        new Trigger(DriverStation::isDisabled).whileTrue(swerveCommands.stop());
        // SignalLogger.setPath("/media/sda1/"); // Cofigure pheonixLib logging path
        configureDrive();
        configureOperator();
        configureAuto();
    }

    private void configureDrive() {
        TunableCommand driveCommand = swerveCommands.driverController(
                driverController::getLeftY,
                driverController::getLeftX,
                driverController::getRightX,
                () -> 0,
                driverController.b(),
                driverController.leftBumper().negate()::getAsBoolean,
                driverController.rightBumper()::getAsBoolean);

        swerve.setDefaultCommand(driveCommand);
        TunablesManager.add("Swerve/drive command", driveCommand.fullTunable());

        driverController.x().onTrue(swerveCommands.xWheelLock());

        TunablesManager.add("Swerve/modules control mode",
                swerveCommands.controlModules(
                        driverController::getLeftX,
                        driverController::getLeftY,
                        driverController::getRightY).fullTunable());

        driverController.a().onTrue(new InstantCommand(() -> {
            swerve.resetGyroYawZero();
            PoseEstimator.getInstance().resetYawZero();
        }));
    }

    public void configureOperator() {

    }

    public void configureAuto() {
        
    }

    public void enterSwerveIntoTest() {
        swerve.costAll();
    }

    public void periodicUpdate() {
        // vision.update();
        // hubShootingCalculator.update(PoseEstimator.getInstance().getEstimatedPose(), isRedAlliance());
    }

    public static boolean isRedAlliance() {
        return isRedAlliance.get() != null && isRedAlliance.get();
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}
