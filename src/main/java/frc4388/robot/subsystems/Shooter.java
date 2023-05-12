package frc4388.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;

public class Shooter extends SubsystemBase {
  private final Solenoid m_solenoid;

  public Shooter(Solenoid solenoid) {
    m_solenoid = solenoid;
  }

  /**
   * Check if the shooter is ready to fire.
   * 
   * @return True if the shooter is ready or false if the shooter is not ready
   */
  public boolean isReady() {
    return !m_solenoid.get();
  }

  /**
   * Ready the shooter to fire.
   */
  public void ready() {
    m_solenoid.set(false);
  }

  /**
   * Fire the shooter if it is ready.
   * 
   * @return True if the shooter was ready or false if the shooter was not ready
   */
  public boolean fire() {
    m_solenoid.set(true);
    return true;
  }
}
