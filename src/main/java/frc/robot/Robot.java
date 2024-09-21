// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftMotorID = 0;
  private static final int kFrontRightMotorID = 1;
  private static final int kBackLeftMotorID = 2;
  private static final int kBackRightMotorID = 3;

  private static final int kJoystickID = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick;

  @Override
  public void robotInit() {
    CANSparkMax frontLeft = new CANSparkMax(kFrontLeftMotorID, MotorType.kBrushless);
    CANSparkMax frontRight = new CANSparkMax(kFrontRightMotorID, MotorType.kBrushless);
    CANSparkMax backLeft = new CANSparkMax(kBackLeftMotorID, MotorType.kBrushless);
    CANSparkMax backRight = new CANSparkMax(kBackRightMotorID, MotorType.kBrushless);

    SendableRegistry.addChild(m_robotDrive, frontLeft);
    SendableRegistry.addChild(m_robotDrive, frontRight);
    SendableRegistry.addChild(m_robotDrive, backLeft);
    SendableRegistry.addChild(m_robotDrive, backRight);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    backRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft::set, backLeft::set, frontRight::set, backRight::set);

    m_stick = new Joystick(kJoystickID);
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick Y axis for forward movement, X axis for lateral
    // movement, and Z axis for rotation.
    m_robotDrive.driveCartesian(-m_stick.getY(), -m_stick.getX(), -m_stick.getZ());
  }
}
