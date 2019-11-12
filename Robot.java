/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.OI;
import edu.wpi.first.wpilibj.Encoder;






/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveSubsystem subsystem = new DriveSubsystem();
  public static OI stickclass = new OI();
  //public static final double kDistance

  public static final double kDistancePerRevolution = 18.84;
  public static final double kPulsesPerRevolution = 1024;
  public static final double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution;

  private Encoder leftEncoder = new Encoder(1, 2, false, EncodingType.k4X);
  private Encoder rightEncoder = new Encoder(3, 4, true, EncodingType.k4X);

  public static OI oi;
  

  //public WPI_VictorSPX TLV = new WPI_VictorSPX(RobotMap.TLVic);
  //public WPI_VictorSPX BLV = new WPI_VictorSPX(RobotMap.BLVic);
  //public WPI_VictorSPX TRV = new WPI_VictorSPX(RobotMap.TRVic);
  //public WPI_VictorSPX BRV = new WPI_VictorSPX(RobotMap.BRVic);

  //public WPI_VictorSPX ATLV = new WPI_VictorSPX(RobotMap.ATLVic);
  //public WPI_VictorSPX ABLV = new WPI_VictorSPX(RobotMap.ABLVic);
  //public WPI_VictorSPX ATRV = new WPI_VictorSPX(RobotMap.ATRVic);
  //public WPI_VictorSPX ABRV = new WPI_VictorSPX(RobotMap.ABRVic);
  //Gyro gyro = new ADXRS450_Gyro(SPI.Port.kMXP);

  //public DifferentialDrive Drive = new DifferentialDrive(TRV, TLV);
  //public DifferentialDrive Drive2 = new DifferentialDrive(ATLV, ATRV);

  //public Robot(){

    //BLV.follow(TLV);
    //BRV.follow(TRV);

    //ABLV.follow(ATLV);
    //ABRV.follow(ATRV);

  //}

  //public Joystick stick = new Joystick(RobotMap.JoyStickPort);
  //public Joystick stick1 = new Joystick(RobotMap.JoyStickPort1);

  Command autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    leftEncoder.setDistancePerPulse(kDistancePerPulse);
    //rightEncoder.setDistancePerPulse(kDistancePerPulse);
    leftEncoder.reset();
    rightEncoder.reset();
    leftEncoder.setDistancePerPulse(1./256.);
    

    //resetEncoders();

  }

  private double getAverageEncoderPosition(){
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;

  }

  private void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();
    //if(leftEncoder.getDistance  { 
      //Drive.tankDrive(.5, .5);
  //} else {
    //  Drive.tankDrive(0, 0);
  //}
   //Find the heading error; setpoint is 90
   //double error = 90 - gyro.getAngle();

  // Turns the robot to face the desired direction
  // Drive.tankDrive(kP * error, kP * error);

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
                            //Movement motor
    Robot.subsystem.Drive.arcadeDrive(Robot.oi.stick.getRawAxis(2), Robot.oi.stick.getRawAxis(1));
    Robot.subsystem.Drive2.arcadeDrive(Robot.oi.stick.getRawAxis(0), Robot.oi.stick.getRawAxis(1));

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    //LiveWindow.run();
  }
}
