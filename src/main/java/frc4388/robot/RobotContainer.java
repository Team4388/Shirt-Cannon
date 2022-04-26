package frc4388.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
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
  private final Drive m_robotDrive = new Drive(m_robotMap.driveLeftMotor, m_robotMap.driveRightMotor, m_robotMap.driveBase);
  private final Shooter[][] m_robotShooterRows = {
    {
      new Shooter(m_robotMap.shooterLowerLefterSolenoid),
      new Shooter(m_robotMap.shooterLowerLeftSolenoid),
      new Shooter(m_robotMap.shooterLowerRightSolenoid),
      new Shooter(m_robotMap.shooterLowerRighterSolenoid),
    },
    {
      new Shooter(m_robotMap.shooterUpperLefterSolenoid),
      new Shooter(m_robotMap.shooterUpperLeftSolenoid),
      new Shooter(m_robotMap.shooterUpperRightSolenoid),
      new Shooter(m_robotMap.shooterUpperRighterSolenoid)
    }
  };
  private final Horn m_robotHorn = new Horn(m_robotMap.hornSolenoid);

  /* Controllers */
  private final XboxController m_controller = new XboxController(OIConstants.CONTROLLER_ID);
  private final NetworkTableInteger m_shooterRowIndex = new NetworkTableInteger("Shooter/Row", 0, 0, 1);
  private final NetworkTableInteger m_shooterColumnIndex = new NetworkTableInteger("Shooter/Column", 0, 0, 3);

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
    // B Button: Fire Selected Shooter
    new JoystickButton(getController(), XboxController.Button.kB.value).whenPressed(this::fireShooterWithFeedback);
    // A Button: Sound Horn
    new JoystickButton(getController(), XboxController.Button.kA.value).whenPressed(() -> m_robotHorn.set(true)).whenReleased(() -> m_robotHorn.set(false));
    // Right Bumper: Shift Column Selection Right
    new JoystickButton(getController(), XboxController.Button.kRightBumper.value).whenPressed(() -> m_shooterColumnIndex.set(m_shooterColumnIndex.get() + 1));
    // Left Bumper: Shift Column Selection Left
    new JoystickButton(getController(), XboxController.Button.kLeftBumper.value).whenPressed(() -> m_shooterColumnIndex.set(m_shooterColumnIndex.get() - 1));
    // D-Pad Down: Select Lower Shooter Row
    new POVButton(getController(), 180).whenPressed(() -> m_shooterRowIndex.set(0));
    // D-Pad Up: Select Upper Shooter Row
    new POVButton(getController(), 0).whenPressed(() -> m_shooterRowIndex.set(1));
    // D-Pad Left: Select Righter Shooter Column
    new POVButton(getController(), 90).whenPressed(() -> m_shooterColumnIndex.set(3));
    // D-Pad Right: Select Lefter Shooter Column
    new POVButton(getController(), 270).whenPressed(() -> m_shooterColumnIndex.set(0));
  }

  private void fireShooterWithFeedback() {
    boolean fired = m_robotShooterRows[m_shooterRowIndex.get()][m_shooterColumnIndex.get()].set(true);
    RumbleType rumbleType = m_shooterColumnIndex.get() < 2 ? RumbleType.kLeftRumble : RumbleType.kRightRumble;
    double value = fired ? 0.3 : 0.6;
    double duration = fired ? 0.3 : 0.4;
    CommandGroupBase.sequence(new InstantCommand(() -> getController().setRumble(rumbleType, value)), new WaitCommand(duration), new InstantCommand(() -> getController().setRumble(rumbleType, 0.0))).schedule();
    System.out.println(m_shooterRowIndex.get() + ":" + m_shooterColumnIndex.get());
  }

  private static class NetworkTableInteger {
    private final NetworkTableEntry m_entry;
    private final int m_defaultValue;

    public NetworkTableInteger(String name, int defaultValue, int minValue, int maxValue) {
      m_defaultValue = defaultValue;
      m_entry = NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry(name);
      m_entry.setDouble(m_defaultValue);
      m_entry.addListener(event -> {
        if (event.value.isDouble()) {
          double entryValue = event.value.getDouble();
          double safeValue = Math.max(minValue, Math.min((int) entryValue, maxValue));
          if (entryValue != safeValue) event.getEntry().setDouble(safeValue);
        } else event.getEntry().setDouble(m_defaultValue);
      }, EntryListenerFlags.kImmediate | EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }

    public int get() {
      return (int) m_entry.getDouble(m_defaultValue);
    }

    public void set(int value) {
      m_entry.setDouble(value);
    }
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
