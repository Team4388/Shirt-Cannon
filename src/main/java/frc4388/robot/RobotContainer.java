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
  private final Drive m_robotDrive = new Drive(m_robotMap.driveMotorLeftLeader, m_robotMap.driveMotorRightLeader, m_robotMap.driveMotorLeftFollower, m_robotMap.driveMotorRightFollower, m_robotMap.driveBase);

  private final Shooter m_robotShooterBottomLeft = new Shooter(m_robotMap.shooterSolenoidBottomLeft);
  private final Shooter m_robotShooterBottomMiddle = new Shooter(m_robotMap.shooterSolenoidBottomMiddle);
  private final Shooter m_robotShooterBottomRight = new Shooter(m_robotMap.shooterSolenoidBottomRight);
  
  private final Shooter m_robotShooterTopLeft = new Shooter(m_robotMap.shooterSolenoidTopLeft);
  private final Shooter m_robotShooterTopMiddle = new Shooter(m_robotMap.shooterSolenoidTopMiddle);
  private final Shooter m_robotShooterTopRight = new Shooter(m_robotMap.shooterSolenoidTopRight);

  // private final Shooter m_robotShooterBottomLeftOuter = new Shooter(m_robotMap.shooterSolenoidBottomLeftOuter);
  // private final Shooter m_robotShooterBottomLeftInner = new Shooter(m_robotMap.shooterSolenoidBottomLeftInner);
  // private final Shooter m_robotShooterBottomRightInner = new Shooter(m_robotMap.shooterSolenoidBottomRightInner);
  // private final Shooter m_robotShooterBottomRightOuter = new Shooter(m_robotMap.shooterSolenoidBottomRightOuter);

  // private final Shooter m_robotShooterTopLeftOuter = new Shooter(m_robotMap.shooterSolenoidTopLeftOuter);
  // private final Shooter m_robotShooterTopLeftInner = new Shooter(m_robotMap.shooterSolenoidTopLeftInner);
  // private final Shooter m_robotShooterTopRightInner = new Shooter(m_robotMap.shooterSolenoidTopRightInner);
  // private final Shooter m_robotShooterTopRightOuter = new Shooter(m_robotMap.shooterSolenoidTopRightOuter);

  // private final Horn m_robotHorn = new Horn(m_robotMap.hornSolenoid);

  /* Controllers */
  private final XboxController m_controller = new XboxController(OIConstants.CONTROLLER_ID);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();

    /* Default Commands */
    // drives the robot with a two-axis input from the driver controller
    m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.hotwireDrive(getController().getLeftY(), getController().getRightX()), m_robotDrive));
    // m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.tankDrive(getController().getLeftY(), getController().getLeftY()), m_robotDrive));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by instantiating
   * a {@link GenericHID} or one of its subclasses ({@link edu.wpi.first.wpilibj.Joystick} or
   * {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // X Button: Fire Top Left Shooter
    new JoystickButton(getController(), XboxController.Button.kX.value).whenPressed(() -> fireShooterWithFeedback(m_robotShooterTopLeft));
    // Y Button: Fire Top Middle Shooter
    new JoystickButton(getController(), XboxController.Button.kY.value).whenPressed(() -> fireShooterWithFeedback(m_robotShooterTopMiddle));
    // B Button: Fire Top Right Shooter
    new JoystickButton(getController(), XboxController.Button.kB.value).whenPressed(() -> fireShooterWithFeedback(m_robotShooterTopRight));
    
    // D-Pad Left: Fire Bottom Righter Shooter
    new POVButton(getController(), 270).whenPressed(() -> fireShooterWithFeedback(m_robotShooterBottomLeft));
    // D-Pad Up: Fire Bottom Middle Shooter
    new POVButton(getController(), 0).whenPressed(() -> fireShooterWithFeedback(m_robotShooterBottomMiddle));
    // D-Pad Right: Fire Bottom Right Shooter
    new POVButton(getController(), 90).whenPressed(() -> fireShooterWithFeedback(m_robotShooterBottomRight));
    
    // // Y Button: Fire Top Lefter Shooter
    // new JoystickButton(getController(), XboxController.Button.kY.value).whenPressed(putToDashboard("Y", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopLeftOuter)).withName("Fire Top Lefter")));
    // // B Button: Fire Top Right Shooter
    // new JoystickButton(getController(), XboxController.Button.kB.value).whenPressed(putToDashboard("B", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopRightInner)).withName("Fire Top Right")));
    // // A Button: Fire Top Righter Shooter
    // new JoystickButton(getController(), XboxController.Button.kA.value).whenPressed(putToDashboard("A", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopRightOuter)).withName("Fire Top Righter")));
    // // X Button: Fire Top Left Shooter
    // new JoystickButton(getController(), XboxController.Button.kX.value).whenPressed(putToDashboard("X", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterTopLeftInner)).withName("Fire Top Left")));

    // // D-Pad Up: Fire Bottom Lefter Shooter
    // new POVButton(getController(), 0).whenPressed(putToDashboard("Up", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomLeftOuter)).withName("Fire Bottom Lefter")));
    // // D-Pad Right: Fire Bottom Right Shooter
    // new POVButton(getController(), 90).whenPressed(putToDashboard("Right", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomRightInner)).withName("Fire Bottom Right")));
    // // D-Pad Left: Fire Bottom Righter Shooter
    // new POVButton(getController(), 180).whenPressed(putToDashboard("Down", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomRightOuter)).withName("Fire Bottom Righter")));
    // // D-Pad Up: Fire Bottom Left Shooter
    // new POVButton(getController(), 270).whenPressed(putToDashboard("Left", new InstantCommand(() -> fireShooterWithFeedback(m_robotShooterBottomLeftInner)).withName("Fire Bottom Left")));

    // Right menu button, aka Start: Reset Barrels
    new JoystickButton(getController(), XboxController.Button.kStart.value).whenPressed(() -> ResetShooters());
    
    // Left menu button, aka Back: Fire all barrels
    new JoystickButton(getController(), XboxController.Button.kBack.value).whenPressed(() -> FireAll());
    

    // Do we even have this on the robot?
    // // Right Bumper: Sound Horn
    // new JoystickButton(getController(), XboxController.Button.kRightBumper.value).whenPressed(() -> m_robotHorn.set(true)).whenReleased(() -> m_robotHorn.set(false));
    // // Left Bumper: Sound Horn
    // new JoystickButton(getController(), XboxController.Button.kLeftBumper.value).whenPressed(() -> m_robotHorn.set(true)).whenReleased(() -> m_robotHorn.set(false));
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

  public void ResetShooters() {
    m_robotShooterBottomLeft.ready();
    m_robotShooterBottomMiddle.ready();
    m_robotShooterBottomRight.ready();
    
    m_robotShooterTopLeft.ready();
    m_robotShooterTopMiddle.ready();
    m_robotShooterTopRight.ready();
    
    // m_robotShooterBottomLeftOuter.ready();
    // m_robotShooterBottomLeftInner.ready();
    // m_robotShooterBottomRightInner.ready();
    // m_robotShooterBottomRightOuter.ready();

    // m_robotShooterTopLeftOuter.ready();
    // m_robotShooterTopLeftInner.ready();
    // m_robotShooterTopRightInner.ready();
    // m_robotShooterTopRightOuter.ready();
  }

  public void FireAll() {
    m_robotShooterBottomLeft.fire();
    m_robotShooterBottomMiddle.fire();
    m_robotShooterBottomRight.fire();
    
    m_robotShooterTopLeft.fire();
    m_robotShooterTopMiddle.fire();
    m_robotShooterTopRight.fire();
    
    // m_robotShooterBottomLeftOuter.fire();
    // m_robotShooterBottomLeftInner.fire();
    // m_robotShooterBottomRightInner.fire();
    // m_robotShooterBottomRightOuter.fire();

    // m_robotShooterTopLeftOuter.fire();
    // m_robotShooterTopLeftInner.fire();
    // m_robotShooterTopRightInner.fire();
    // m_robotShooterTopRightOuter.fire();
  }
  public void putReadyState() {
    SmartDashboard.putBoolean("Bottom Left", m_robotShooterBottomLeft.isReady());
    SmartDashboard.putBoolean("Bottom Middle", m_robotShooterBottomMiddle.isReady());
    SmartDashboard.putBoolean("Bottom Right", m_robotShooterBottomRight.isReady());
    
    SmartDashboard.putBoolean("Top Left", m_robotShooterTopLeft.isReady());
    SmartDashboard.putBoolean("Top Middle", m_robotShooterTopMiddle.isReady());
    SmartDashboard.putBoolean("Top Right", m_robotShooterTopRight.isReady());
    
    // SmartDashboard.putBoolean("", m_robotShooterBottomLeftOuter.isReady());
    // SmartDashboard.putBoolean("", m_robotShooterBottomLeftInner.isReady());
    // SmartDashboard.putBoolean("", m_robotShooterBottomRightInner.isReady());
    // SmartDashboard.putBoolean("", m_robotShooterBottomRightOuter.isReady());

    // SmartDashboard.putBoolean("", m_robotShooterTopLeftOuter.isReady());
    // SmartDashboard.putBoolean("", m_robotShooterTopLeftInner.isReady());
    // SmartDashboard.putBoolean("", m_robotShooterTopRightInner.isReady());
    // SmartDashboard.putBoolean("", m_robotShooterTopRightOuter.isReady());
  }
  
}
