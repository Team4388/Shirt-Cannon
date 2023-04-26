package frc4388.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int DRIVE_LEFT_LEADER_ID = 2;
    public static final int DRIVE_RIGHT_LEADER_ID = 3;
    public static final int DRIVE_LEFT_FOLLOWER_ID = 4;
    public static final int DRIVE_RIGHT_FOLLOWER_ID = 5;
  }

  public static final class ShooterConstants {
    public static final int SHOOTER_SOLENOID_BOTTOM_LEFT_ID = 0;
    public static final int SHOOTER_SOLENOID_BOTTOM_MIDDLE_ID = 1;
    public static final int SHOOTER_SOLENOID_BOTTOM_RIGHT_ID = 2;
    public static final int SHOOTER_SOLENOID_TOP_LEFT_ID = 3;
    public static final int SHOOTER_SOLENOID_TOP_MIDDLE_ID = 4;
    public static final int SHOOTER_SOLENOID_TOP_RIGHT_ID = 5;
    // Will most likely need rebinding for the 6 barrel setup

    // public static final int SHOOTER_SOLENOID_BOTTOM_LEFT_OUTER_ID = 1;
    // public static final int SHOOTER_SOLENOID_BOTTOM_LEFT_INNER_ID = 2;
    // public static final int SHOOTER_SOLENOID_BOTTOM_RIGHT_INNER_ID = 3;
    // public static final int SHOOTER_SOLENOID_BOTTOM_RIGHT_OUTER_ID = 4;
    // public static final int SHOOTER_SOLENOID_TOP_LEFT_OUTER_ID = 5;
    // public static final int SHOOTER_SOLENOID_TOP_LEFT_INNER_ID = 6;
    // public static final int SHOOTER_SOLENOID_TOP_RIGHT_INNER_ID = 7;
    // public static final int SHOOTER_SOLENOID_TOP_RIGHT_OUTER_ID = 8;
  }

  public static final class HornConstants {
    public static final int HORN_SOLENOID_ID = 0;
  }

  public static final class OIConstants {
    public static final int CONTROLLER_ID = 0;
    // Odd, This robot supports single person operation and driving.
  }
}
