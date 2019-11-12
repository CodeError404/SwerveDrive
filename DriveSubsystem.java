/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
 
  public WPI_VictorSPX TLV = new WPI_VictorSPX(RobotMap.TLVic);
  public WPI_VictorSPX BLV = new WPI_VictorSPX(RobotMap.BLVic);
  public WPI_VictorSPX TRV = new WPI_VictorSPX(RobotMap.TRVic);
  public WPI_VictorSPX BRV = new WPI_VictorSPX(RobotMap.BRVic);

  public WPI_VictorSPX ATLV = new WPI_VictorSPX(RobotMap.ATLVic);
  public WPI_VictorSPX ABLV = new WPI_VictorSPX(RobotMap.ABLVic);
  public WPI_VictorSPX ATRV = new WPI_VictorSPX(RobotMap.ATRVic);
  public WPI_VictorSPX ABRV = new WPI_VictorSPX(RobotMap.ABRVic);

  public DifferentialDrive Drive = new DifferentialDrive(TRV, TLV);
  public DifferentialDrive Drive2 = new DifferentialDrive(ATLV, ATRV);

  public DriveSubsystem(){

    BLV.follow(TLV);
    BRV.follow(TRV);

    ABLV.follow(ATLV);
    ABRV.follow(ATRV);

  }

  public Joystick stick = new Joystick(RobotMap.JoyStickPort);
  public Joystick stick1 = new Joystick(RobotMap.JoyStickPort1);


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
