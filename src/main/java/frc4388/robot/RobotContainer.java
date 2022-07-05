package frc4388.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc4388.robot.Constants.OIConstants;
import frc4388.robot.subsystems.Drive;
import frc4388.robot.subsystems.Horn;
import frc4388.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  /* RobotMap */
  private final RobotMap m_robotMap = new RobotMap();

  /* Subsystems */
  private final Drive m_robotDrive = new Drive(m_robotMap.driveMotorLeftLeader, m_robotMap.driveMotorRightLeader, m_robotMap.driveBase);

  private final Shooter m_robotShooterBottomLeftOuter = new Shooter(m_robotMap.shooterSolenoidBottomLeftOuter);
  private final Shooter m_robotShooterBottomLeftInner = new Shooter(m_robotMap.shooterSolenoidBottomLeftInner);
  private final Shooter m_robotShooterBottomRightInner = new Shooter(m_robotMap.shooterSolenoidBottomRightInner);
  private final Shooter m_robotShooterBottomRightOuter = new Shooter(m_robotMap.shooterSolenoidBottomRightOuter);

  private final Shooter m_robotShooterTopLeftOuter = new Shooter(m_robotMap.shooterSolenoidTopLeftOuter);
  private final Shooter m_robotShooterTopLeftInner = new Shooter(m_robotMap.shooterSolenoidTopLeftInner);
  private final Shooter m_robotShooterTopRightInner = new Shooter(m_robotMap.shooterSolenoidTopRightInner);
  private final Shooter m_robotShooterTopRightOuter = new Shooter(m_robotMap.shooterSolenoidTopRightOuter);

  private final Horn m_robotHorn = new Horn(m_robotMap.hornSolenoid);

  /* Controllers */
  private final XboxController m_controller = new XboxController(OIConstants.CONTROLLER_ID);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();

    /* Default Commands */
    // drives the robot with a two-axis input from the driver controller
    m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.arcadeDrive(getController().getLeftY(), getController().getRightX()), m_robotDrive));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by instantiating
   * a {@link GenericHID} or one of its subclasses ({@link edu.wpi.first.wpilibj.Joystick} or
   * {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Y Button: Fire Top Lefter Shooter
    new JoystickButton(getController(), XboxController.Button.kY.value).whenPressed(putToDashboard("Y", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopLeftOuter)).withName("Fire Top Lefter")));
    // B Button: Fire Top Right Shooter
    new JoystickButton(getController(), XboxController.Button.kB.value).whenPressed(putToDashboard("B", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopRightInner)).withName("Fire Top Right")));
    // A Button: Fire Top Righter Shooter
    new JoystickButton(getController(), XboxController.Button.kA.value).whenPressed(putToDashboard("A", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopRightOuter)).withName("Fire Top Righter")));
    // X Button: Fire Top Left Shooter
    new JoystickButton(getController(), XboxController.Button.kX.value).whenPressed(putToDashboard("X", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopLeftInner)).withName("Fire Top Left")));

    // D-Pad Up: Fire Bottom Lefter Shooter
    new POVButton(getController(), 0).whenPressed(putToDashboard("Up", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomLeftOuter)).withName("Fire Bottom Lefter")));
    // D-Pad Right: Fire Bottom Right Shooter
    new POVButton(getController(), 90).whenPressed(putToDashboard("Right", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomRightInner)).withName("Fire Bottom Right")));
    // D-Pad Left: Fire Bottom Righter Shooter
    new POVButton(getController(), 180).whenPressed(putToDashboard("Down", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomRightOuter)).withName("Fire Bottom Righter")));
    // D-Pad Up: Fire Bottom Left Shooter
    new POVButton(getController(), 270).whenPressed(putToDashboard("Left", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomLeftInner)).withName("Fire Bottom Left")));

    // Right Bumper: Sound Horn
    new JoystickButton(getController(), XboxController.Button.kRightBumper.value).whenPressed(() -> m_robotHorn.set(true)).whenReleased(() -> m_robotHorn.set(false));
    // Left Bumper: Sound Horn
    new JoystickButton(getController(), XboxController.Button.kLeftBumper.value).whenPressed(() -> m_robotHorn.set(true)).whenReleased(() -> m_robotHorn.set(false));
  }

  private static <T extends Sendable> T putToDashboard(String key, T data) {
    SmartDashboard.putData(key, data);
    return data;
  }

  private void fireShooterWithFeedback(Shooter shooter) {
    boolean fired = shooter.fire();
    RumbleType rumbleType = shooter.getName().indexOf('L', 13) >= 0 ? RumbleType.kLeftRumble : RumbleType.kRightRumble;
    double value = fired ? 0.3 : 0.6;
    double duration = fired ? 0.3 : 0.4;
    CommandGroupBase.sequence(new InstantCommand(() -> getController().setRumble(rumbleType, value)), new WaitCommand(duration), new InstantCommand(() -> getController().setRumble(rumbleType, 0.0))).schedule();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new PrintCommand("No Autonomous");
  }

  public XboxController getController() {
    return m_controller;
  }
}
