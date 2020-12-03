/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="UG TeleOp", group="Linear Opmode")
//@Disabled
public class CuriosityTeleOp extends LinearOpMode {
    //telep code from last year: https://drive.google.com/drive/u/1/folders/1t2_lAwFfjKIcn_lX6ayanWx7Nz-9Ct1v
    //sorry it is really messy
    
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    //Drivetrain
    private DcMotor drivetrainFrontLeft = null;
    private Dcmotor drivetrainFrontRight = null;
    private DcMotor drivetrainBackLeft = null;
    private DcMotor drivetrainBackRight = null;
    
    //Shooter
    private DcMotor frontShooter = null;
    private DcMotor backShooter = null;
    private Servo shooterServo = null;
    
    //Intake
    private DcMotor intake = null;

    //arm and claw - wobbleLeft, wobbleRight, wobbleClaw
    private Servo wobbleLeft = null;
    private Servo wobbleRight = null;    
    private Servo wobbleClaw = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        //Drivetrain
        drivetrainFrontLeft = hardwareMap.get(DcMotor.class, "drivetrainFrontLeft");
        drivetrainFrontRight = hardwareMap.get(DcMotor.class, "drivetrainFrontRight");
        drivetrainBackLeft = hardwareMap.get(DcMotor.class, "drivetrainBackLeft");
        drivetrainBackRight = hardwareMap.get(DcMotor.class, "drivetrainBackRight");
        
        //setting drivetrain Motors        
        drivetrainFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        drivetrainFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drivetrainFrontRight.setDirection(DcMotor.Direction.FORWARD);
        drivetrainFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drivetrainBackLeft.setDirection(DcMotor.Direction.REVERSE);
        drivetrainBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drivetrainBackRight.setDirection(DcMotor.Direction.FORWARD);
        drivetrainBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        //Shooter
        frontShooter = hardwareMap.get(DcMotor.class, "frontShooter");
        backShooter = hardwareMap.get(DcMotor.class, "backShooter");
        shooterServo = hardwareMap.get(Servo.class, "shooterServo");

        //Intake
        

        //wobble
        wobbleLeft = hardwareMap.get(Servo.class, "")
        wobbleRight = hardwareMap.get(Servo.class, "wobbleRight");
        wobbleClaw = hardwareMap.get(Servo.class, "wobbleClaw")

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
        
            //mecanum wheels code stuff
            double drivetrainLeftFrontPower;
            double drivetrainLeftBackPower;
            double drivetrainRightFrontPower;
            double drivetrainRightBackPower;
            
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            
            if(gamepad1.right_bumper) {
                drivetrainLeftFrontPower = Range.clip(drive + turn + strafe, -.3, .3);
                drivetrainRightFrontPower = Range.clip(drive - turn - strafe, -.3, .3);
                drivetrainRightBackPower = Range.clip(drive - turn + strafe, -.3, .3);
                drivetrainLeftBackPower = Range.clip(drive + turn - strafe, -.3, .3);
            } else {
                drivetrainLeftFrontPower = Range.clip(drive + turn + strafe, -1, 1);
                drivetrainRightFrontPower = Range.clip(drive - turn - strafe, -1, 1);
                drivetrainRightBackPower = Range.clip(drive - turn + strafe, -1, 1);
                drivetrainLeftBackPower = Range.clip(drive + turn - strafe, -1, 1);
            }
            
            drivetrainFrontLeft.setPower(drivetrainLeftFrontPower);
            frontRight.setPower(rightFrontPower);
            backLeft.setPower(leftBackPower);
            backRight.setPower(rightBackPower);
            
            
            telemetry.addData("leftFrontPower: ", leftFrontPower);
            telemetry.addData("rightFrontPower: ", rightFrontPower);
            telemetry.addData("leftBackPower: ", leftBackPower);
            telemetry.addData("rightBackPower: ", rightBackPower);


            
            //Shooter 
            if (gamepad1.dpad_up)//turn shooter on, set both shooter motors to power .9
            {
              frontShooter.setPower(0.9);
              backShooter.setPower(0.9);
            } else {
              frontShooter.setPower(0);
              backShooter.setPower(0);
            }
            
             
             
             if (gamepad1.dpad_right)
             {
             servoShooter.setPosition(.5)
             }
             else 
             (
               servoShooter 
             )

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
