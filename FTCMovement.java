package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * In this version of FTC Movement that I(Gordon) have created, I am allowing the Right stick to
 * control the movement of the Robot.
 *
 */

@TeleOp(name="FTC Movement", group="Linear Opmode")  // @Autonomous(...) is the other common choice

public class FTCMovement extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftMotor = null;
    DcMotor rightMotor = null;
    //For the controlling of the movement, we will use the right stick - G
    double vertPow = -gamepad1.right_stick_y;
    double turnPow = -gamepad1.right_stick_x;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Please name the motors motor_1 and motor_2 as shown (Or if you have a different name, change the code)
        leftMotor  = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE); //This can be changed to FORWARD if necessary

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            vertPow = Range.clip(vertPow, -1, 1);
            turnPow = Range.clip(turnPow, -1, 1);

            //THIS HAS NOT BEEN TESTED FOR ITS DIRECTIONS. But this can be edited after test runs.
            if (turnPow != 0) {
                leftMotor.setPower(-turnPow);
                rightMotor.setPower(turnPow);
            }
            else {
                leftMotor.setPower(vertPow);
                rightMotor.setPower(vertPow);
            }

        }
    }
}
