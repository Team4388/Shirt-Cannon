package frc4388.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;

public class Shooter extends SubsystemBase {
  Solenoid m_solenoid;

  public Shooter(Solenoid solenoid) {
    m_solenoid = solenoid;
  }

  public boolean set(boolean on) {
    if (m_solenoid.get() == on) return false;
    m_solenoid.set(on);
    return true;
  }
}
