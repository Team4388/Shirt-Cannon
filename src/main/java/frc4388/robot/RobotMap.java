/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc4388.robot;

import static frc4388.robot.Constants.DriveConstants.*;
import static frc4388.robot.Constants.HornConstants.*;
import static frc4388.robot.Constants.ShooterConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Defines and holds all I/O objects on the roboRIO. This is useful for unit testing and
 * modularization.
 */
public class RobotMap {

  public RobotMap() {
    configureDriveMotorControllers();
    configureLiveWindow();
  }

  /* Drive Subsystem */
  public final WPI_TalonSRX driveLeftLeaderMotor = new WPI_TalonSRX(DRIVE_LEFT_LEADER_CAN_ID);
  public final WPI_TalonSRX driveRightLeaderMotor = new WPI_TalonSRX(DRIVE_RIGHT_LEADER_CAN_ID);
  public final WPI_TalonSRX driveLeftFollowerMotor = new WPI_TalonSRX(DRIVE_LEFT_FOLLOWER_CAN_ID);
  public final WPI_TalonSRX driveRightFollowerMotor = new WPI_TalonSRX(DRIVE_RIGHT_FOLLOWER_CAN_ID);

  public final DifferentialDrive driveBase = new DifferentialDrive(driveLeftLeaderMotor, driveRightLeaderMotor);

  private void configureDriveMotorControllers() {
    driveLeftLeaderMotor.configFactoryDefault();
    driveRightLeaderMotor.configFactoryDefault();
    driveLeftFollowerMotor.configFactoryDefault();
    driveRightFollowerMotor.configFactoryDefault();

    driveLeftLeaderMotor.setNeutralMode(NeutralMode.Brake);
    driveRightLeaderMotor.setNeutralMode(NeutralMode.Brake);
    driveLeftFollowerMotor.setNeutralMode(NeutralMode.Brake);
    driveRightFollowerMotor.setNeutralMode(NeutralMode.Brake);

    driveLeftFollowerMotor.follow(driveLeftLeaderMotor);
    driveRightFollowerMotor.follow(driveRightLeaderMotor);
  }

  /* Horn Subsystem */
  public final Solenoid hornSolenoid = new Solenoid(PneumaticsModuleType.REVPH, HORN_SOLENOID_ID);

  /* Shooter Subsystem */
  public final Solenoid shooterLowerLefterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_LEFTER_SOLENOID_ID);
  public final Solenoid shooterLowerLeftSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_LEFT_SOLENOID_ID);
  public final Solenoid shooterLowerRightSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_RIGHT_SOLENOID_ID);
  public final Solenoid shooterLowerRighterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_RIGHTER_SOLENOID_ID);
  public final Solenoid shooterUpperLefterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_LEFTER_SOLENOID_ID);
  public final Solenoid shooterUpperLeftSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_LEFT_SOLENOID_ID);
  public final Solenoid shooterUpperRightSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_RIGHT_SOLENOID_ID);
  public final Solenoid shooterUpperRighterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_RIGHTER_SOLENOID_ID);


  private void configureLiveWindow() {
    SendableRegistry.setName(driveLeftFollowerMotor, "Drive", "Left Follower Motor");
    SendableRegistry.setName(driveRightFollowerMotor, "Drive", "Right Follower Motor");
    SendableRegistry.setName(driveBase, "Drive", "Drive Base");

    SendableRegistry.setName(hornSolenoid, "Horn", "Solenoid");

    SendableRegistry.setName(shooterLowerLefterSolenoid, "Shooter", "Lower Lefter Solenoid");
    SendableRegistry.setName(shooterLowerLeftSolenoid, "Shooter", "Lower Left Solenoid");
    SendableRegistry.setName(shooterLowerRightSolenoid, "Shooter", "Lower Right Solenoid");
    SendableRegistry.setName(shooterLowerRighterSolenoid, "Shooter", "Lower Righter Solenoid");
    SendableRegistry.setName(shooterUpperLefterSolenoid, "Shooter", "Upper Lefter Solenoid");
    SendableRegistry.setName(shooterUpperLeftSolenoid, "Shooter", "Upper Left Solenoid");
    SendableRegistry.setName(shooterUpperRightSolenoid, "Shooter", "Upper Right Solenoid");
    SendableRegistry.setName(shooterUpperRighterSolenoid, "Shooter", "Upper Righter Solenoid");
  }
}
