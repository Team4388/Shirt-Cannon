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
  public final WPI_TalonSRX driveMotorLeftLeader = new WPI_TalonSRX(DRIVE_LEFT_LEADER_ID);
  public final WPI_TalonSRX driveMotorRightLeader = new WPI_TalonSRX(DRIVE_RIGHT_LEADER_ID);
  public final WPI_TalonSRX driveMotorLeftFollower = new WPI_TalonSRX(DRIVE_LEFT_FOLLOWER_ID);
  public final WPI_TalonSRX driveMotorRightFollower = new WPI_TalonSRX(DRIVE_RIGHT_FOLLOWER_ID);

  public final DifferentialDrive driveBase = new DifferentialDrive(driveMotorLeftLeader, driveMotorRightLeader);

  private void configureDriveMotorControllers() {
    driveMotorLeftLeader.configFactoryDefault();
    driveMotorRightLeader.configFactoryDefault();
    driveMotorLeftFollower.configFactoryDefault();
    driveMotorRightFollower.configFactoryDefault();

    driveMotorLeftLeader.setNeutralMode(NeutralMode.Brake);
    driveMotorRightLeader.setNeutralMode(NeutralMode.Brake);
    driveMotorLeftFollower.setNeutralMode(NeutralMode.Brake);
    driveMotorRightFollower.setNeutralMode(NeutralMode.Brake);

    // driveMotorLeftFollower.follow(driveMotorLeftLeader);
    // driveMotorRightFollower.follow(driveMotorRightLeader);
  }

  /* Horn Subsystem */
  //public final Solenoid hornSolenoid = new Solenoid(PneumaticsModuleType.REVPH, HORN_SOLENOID_ID);

  /* Shooter Subsystem */
  public final Solenoid shooterSolenoidBottomLeft = new Solenoid(PneumaticsModuleType.CTREPCM, SHOOTER_SOLENOID_BOTTOM_LEFT_ID);
  public final Solenoid shooterSolenoidBottomMiddle = new Solenoid(PneumaticsModuleType.CTREPCM, SHOOTER_SOLENOID_BOTTOM_MIDDLE_ID);
  public final Solenoid shooterSolenoidBottomRight = new Solenoid(PneumaticsModuleType.CTREPCM, SHOOTER_SOLENOID_BOTTOM_RIGHT_ID);
  public final Solenoid shooterSolenoidTopLeft = new Solenoid(PneumaticsModuleType.CTREPCM, SHOOTER_SOLENOID_TOP_LEFT_ID);
  public final Solenoid shooterSolenoidTopMiddle = new Solenoid(PneumaticsModuleType.CTREPCM, SHOOTER_SOLENOID_TOP_MIDDLE_ID);
  public final Solenoid shooterSolenoidTopRight = new Solenoid(PneumaticsModuleType.CTREPCM, SHOOTER_SOLENOID_TOP_RIGHT_ID);
  
  // public final Solenoid shooterSolenoidBottomLeftOuter = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_BOTTOM_LEFT_OUTER_ID);
  // public final Solenoid shooterSolenoidBottomLeftInner = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_BOTTOM_LEFT_INNER_ID);
  // public final Solenoid shooterSolenoidBottomRightInner = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_BOTTOM_RIGHT_INNER_ID);
  // public final Solenoid shooterSolenoidBottomRightOuter = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_BOTTOM_RIGHT_OUTER_ID);
  // public final Solenoid shooterSolenoidTopLeftOuter = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_TOP_LEFT_OUTER_ID);
  // public final Solenoid shooterSolenoidTopLeftInner = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_TOP_LEFT_INNER_ID);
  // public final Solenoid shooterSolenoidTopRightInner = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_TOP_RIGHT_INNER_ID);
  // public final Solenoid shooterSolenoidTopRightOuter = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_SOLENOID_TOP_RIGHT_OUTER_ID);


  private void configureLiveWindow() {
    // SendableRegistry.setName(driveMotorLeftFollower, "Drive", "Motor Left Follower");
    // SendableRegistry.setName(driveMotorRightFollower, "Drive", "Motor Right Follower");
    // SendableRegistry.setName(driveBase, "Drive", "Base");

    // SendableRegistry.setName(hornSolenoid, "Horn", "Solenoid");

    // SendableRegistry.setName(shooterSolenoidBottomLeftOuter, "Shooter", "Solenoid Bottom Left Outer");
    // SendableRegistry.setName(shooterSolenoidBottomLeftInner, "Shooter", "Solenoid Bottom Left Inner");
    // SendableRegistry.setName(shooterSolenoidBottomRightInner, "Shooter", "Solenoid Bottom Right Inner");
    // SendableRegistry.setName(shooterSolenoidBottomRightOuter, "Shooter", "Solenoid Bottom Right Outer");
    // SendableRegistry.setName(shooterSolenoidTopLeftOuter, "Shooter", "Solenoid Top Left Outer");
    // SendableRegistry.setName(shooterSolenoidTopLeftInner, "Shooter", "Solenoid Top Left Inner");
    // SendableRegistry.setName(shooterSolenoidTopRightInner, "Shooter", "Solenoid Top Right Inner");
    // SendableRegistry.setName(shooterSolenoidTopRightOuter, "Shooter", "Solenoid Top Right Outer");
  }
}
