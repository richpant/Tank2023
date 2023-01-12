// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  private final MotorController leftDrive1 = new CANSparkMax(6,MotorType.kBrushless);
  private final MotorController leftDrive2 = new CANSparkMax(4,MotorType.kBrushless);
  private final MotorController rightDrive1 = new CANSparkMax(1,MotorType.kBrushless);
  private final MotorController rightDrive2 = new CANSparkMax(2,MotorType.kBrushless);
  private final MotorController lift = new CANSparkMax(3, MotorType.kBrushless);
  private final MotorController clawTilt = new CANSparkMax(5, MotorType.kBrushless);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightDrive1, rightDrive2);
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftDrive1, leftDrive2);
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightDrive1.setInverted(true);
    rightDrive2.setInverted(true);


    
    m_myRobot = new DifferentialDrive(rightMotors, leftMotors);
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(-m_leftStick.getY(), -m_rightStick.getY());
    if(m_leftStick.getTrigger()){
      lift.set(.5);

    }else{
      lift.set(0.0);
    }
   
      
    }
  }

