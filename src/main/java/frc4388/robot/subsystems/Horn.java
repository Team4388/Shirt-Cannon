package frc4388.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Horn extends SubsystemBase {
  private final Solenoid m_solenoid;

  public Horn(Solenoid solenoid) {
    m_solenoid = solenoid;
  }

  public void set(boolean on) {
    m_solenoid.set(on);
  }
}
