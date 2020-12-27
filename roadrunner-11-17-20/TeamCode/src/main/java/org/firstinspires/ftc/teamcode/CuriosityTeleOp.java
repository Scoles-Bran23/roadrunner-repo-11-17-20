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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
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
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

/*
    //Shooter
    private DcMotor shooterFront = null;
    private DcMotor shooterBack = null;
    private Servo shooterServo = null;

*/
/*
    //Intake
    private DcMotor intake = null;

    //arm and claw - wobbleLeft, wobbleRight, wobbleClaw
    private Servo wobbleLeft = null;
    private Servo wobbleRight = null;    
    private Servo wobbleClaw = null;
*/
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        //Drivetrain
        frontLeft = hardwareMap.get(DcMotor.class, "drivetrainFrontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "drivetrainFrontRight");
        backLeft = hardwareMap.get(DcMotor.class, "drivetrainBackLeft");
        backRight = hardwareMap.get(DcMotor.class, "drivetrainBackRight");
        
        //setting drivetrain Motors        
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
 /*
        //Shooter
        shooterFront = hardwareMap.get(DcMotor.class, "shooterFront");
        shooterBack = hardwareMap.get(DcMotor.class, "shooterBack");
        shooterServo = hardwareMap.get(Servo.class, "shooterServo");

        //Intake
        intake = hardwareMap.get
        (DcMotor.class, "intake");

        //wobble
        wobbleLeft = hardwareMap.get(Servo.class, "wobbleLeft");
        wobbleRight = hardwareMap.get(Servo.class, "wobbleRight");
        wobbleClaw = hardwareMap.get(Servo.class, "wobbleClaw");
*/
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
        
            //mecanum wheels code stuff
            double frontLeftPower;
            double backLeftPower;
            double frontRightPower;
            double backRightPower;
            
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            
            if(gamepad1.right_bumper) {
                frontLeftPower = Range.clip(drive + turn + strafe, -.3, .3);
                frontRightPower = Range.clip(drive - turn - strafe, -.3, .3);
                backRightPower = Range.clip(drive - turn + strafe, -.3, .3);
                backLeftPower = Range.clip(drive + turn - strafe, -.3, .3);
            } else {
                frontLeftPower = Range.clip(drive + turn + strafe, -1, 1);
                frontRightPower = Range.clip(drive - turn - strafe, -1, 1);
                backRightPower = Range.clip(drive - turn + strafe, -1, 1);
                backLeftPower = Range.clip(drive + turn - strafe, -1, 1);
            }
            
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backLeft.setPower(backRightPower);
            
            
            telemetry.addData("FrontLeftPower: ", frontLeftPower);
            telemetry.addData("FrontRightPower: ", frontRightPower);
            telemetry.addData("BackLeftPower: ", backLeftPower);
            telemetry.addData("BackRightPower: ", backRightPower);

            /*
            //Shooter 
            if (gamepad1.dpad_up)//turn shooter on, set both shooter motors to power .9
            {
              shooterFront.setPower(0.9);
              shooterBack.setPower(0.9);
            } 
            else 
            {
              shooterFront.setPower(0);
              shooterBack.setPower(0);
            }
            
             if (gamepad1.right_bumper)//Shooter servo 
             {
                shooterServo.setPosition(0.5);
             }
             else 
             {
                shooterServo.setPosition(0);
             }

             //Intake
             if (gamepad1.right_trigger > 0.2)
             {
               intake.setPower(0.3);
             }
             if (gamepad1.y)
             {
               intake.setPower(-0.3);
             }
             else
               intake.setPower(0.0);
             }
            
             //wobble claw open/close
             if (gamepad1.left_bumper)
             {
               wobbleClaw.setPosition(0.5); // might need to change values but this should be open
             }
             else
             {
               wobbleClaw.setPosition(0); // close claw
             }
             */

            /*
             //wobble arm to be continued...I need to commit it
             if 

             initialize to stowed position 

             hit trigger go to down position 
             hit trigger 2x to lift above wall

             one servo will start @ 0 and one servo will start @ 1 (stowed position)

             e position would have same changes applies so +- 0.4
            */

            
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
       }
    }
