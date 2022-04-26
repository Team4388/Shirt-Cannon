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

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Defines and holds all I/O objects on the Roborio. This is useful for unit testing and
 * modularization.
 */
public class RobotMap {

  public RobotMap() {
    configureDriveMotorControllers();
  }

  /* Drive Subsystem */
  public final WPI_TalonSRX driveLeftMotor = new WPI_TalonSRX(DRIVE_LEFT_CAN_ID);
  public final WPI_TalonSRX driveRightMotor = new WPI_TalonSRX(DRIVE_RIGHT_CAN_ID);
  public final DifferentialDrive driveBase = new DifferentialDrive(driveLeftMotor, driveRightMotor);

  void configureDriveMotorControllers() {
    /* factory default values */
    driveLeftMotor.configFactoryDefault();
    driveRightMotor.configFactoryDefault();

    /* set neutral mode */
    driveLeftMotor.setNeutralMode(NeutralMode.Brake);
    driveRightMotor.setNeutralMode(NeutralMode.Brake);
  }

  /* Horn subsystem */
  public final Solenoid hornSolenoid = new Solenoid(PneumaticsModuleType.REVPH, HORN_SOLENOID_ID);

  /* Shooter subsystem */
  public final Solenoid shooterLowerLefterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_LEFTER_SOLENOID_ID);
  public final Solenoid shooterLowerLeftSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_LEFT_SOLENOID_ID);
  public final Solenoid shooterLowerRightSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_RIGHT_SOLENOID_ID);
  public final Solenoid shooterLowerRighterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_LOWER_RIGHTER_SOLENOID_ID);
  public final Solenoid shooterUpperLefterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_LEFTER_SOLENOID_ID);
  public final Solenoid shooterUpperLeftSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_LEFT_SOLENOID_ID);
  public final Solenoid shooterUpperRightSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_RIGHT_SOLENOID_ID);
  public final Solenoid shooterUpperRighterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, SHOOTER_UPPER_RIGHTER_SOLENOID_ID);

}
