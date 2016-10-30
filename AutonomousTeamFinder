import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file is an autonomous robot that will meander its way through the
 * horrific jungle known as the playing field and the first colour it detects
 * shall be the team colour. WARNING: since it goes in straight lines and turns
 * when it touches a wall it MAY be inefficient as f. -G
 */

@Autonomous(name="Template: Iterative OpMode", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
public class AutonomousTeamFinder extends OpMode
{
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor leftMotor = null;
    DcMotor rightMotor = null;
    TouchSensor touchSensor;
    ColorSensor colorSensor;
    float hsvValues[] = {0F,0F,0F};
    boolean team = false;
    int teamColor = 0; //0 = unnamed, 1 = red, 2 = blue

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor  = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE); //This can be changed to FORWARD if necessary

        touchSensor = hardwareMap.touchSensor.get("sensor_touch");

        // get a reference to our ColorSensor object.
        colorSensor = hardwareMap.colorSensor.get("sensor_color");

        // Set the LED in the beginning
        colorSensor.enableLed(true);
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
            double light = opticalDistanceSensor.getLightDetected();
            while (!team) {
                if(!touchSensor.isPressed()) {
                    forward(1);
                }
                if (touchSensor.isPressed()) {
                    stop();
                    right(1,1000);
                }
                if (colorSensor.red()>colorSensor.blue() || colorSensor.red()>colorSensor.green()) {
                    team = true;
                    teamColor = 1;
                }
                if (colorSensor.blue()>colorSensor.red() || colorSensor.blue()>colorSensor.green()) {
                    team = true;
                    teamColor = 2;
                }
            }


        }

            public void forward(double power){
                leftMotor.setPower(power);
                rightMotor.setPower(power);
            }
            public void back(double power){
                leftMotor.setPower(-power);
                rightMotor.setPower(-power);
            }
            public void stop() {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
            public void left(double power){
                leftMotor.setPower(-power);
                rightMotor.setPower(power);
            }
            public void right(double power, long time) throws InterruptedException{
                leftMotor.setPower(power);
                rightMotor.setPower(-power);
                Thread.sleep(time);
            }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
