package frc4388.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private final WPI_TalonSRX m_leftMotor;
  private final WPI_TalonSRX m_rightMotor;
  private final DifferentialDrive m_driveBase;

  public Drive(WPI_TalonSRX leftMotor, WPI_TalonSRX rightMotor, DifferentialDrive driveBase) {
    m_leftMotor = leftMotor;
    m_rightMotor = rightMotor;
    m_driveBase = driveBase;
  }

  public void arcadeDrive(double xSpeed, double zRotation) {
    m_driveBase.arcadeDrive(xSpeed, zRotation, true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_leftMotor.set(leftSpeed);
    m_rightMotor.set(rightSpeed);
  }
}
