package frc4388.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private RobotContainer m_robotContainer;

  /** Robot-wide initialization code should go here. */
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  /** Periodic code for all robot modes should go here. */
  @Override
  public void robotPeriodic() {
    // Runs a single iteration of the scheduler.
    CommandScheduler.getInstance().run();
  }

  /** Initialization code for disabled mode should go here. */
  @Override
  public void disabledInit() {
    m_robotContainer.ResetShooters();
    // automaticly assume that when we dissable we will recharge the shooters
  }

  /** Periodic code for disabled mode should go here. */
  @Override
  public void disabledPeriodic() {
  }

  /** Initialization code for autonomous mode should go here. */
  @Override
  public void autonomousInit() {
  }

  /** Periodic code for autonomous mode should go here. */
  @Override
  public void autonomousPeriodic() {
  }

  /** Initialization code for teleop mode should go here. */
  @Override
  public void teleopInit() {
  }

  /** Periodic code for teleop mode should go here. */
  @Override
  public void teleopPeriodic() {
    m_robotContainer.putReadyState();
  }

  /** Initialization code for test mode should go here. */
  @Override
  public void testInit() {
  }

  /** Periodic code for test mode should go here. */
  @Override
  public void testPeriodic() {
  }
}
