package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
@Disabled
public class autonomousCode extends LinearOpMode{

    // start encoders
    public void startEncoders() {
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // exit encoders
    public void exitEncoders() {
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // restart encoders
    public void resetEncoders() {
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // start encoders arm
    public void startEncodersA() {
        AM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LAM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // exit encoders arm
    public void exitEncodersA() {
        AM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LAM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // restart encoders arm
    public void resetEncodersA() {
        AM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LAM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // drive with encoders
    public void driveEncoders(double power, int target) {
        FR.setTargetPosition(target);
        FL.setTargetPosition(target);
        BR.setTargetPosition(target);
        BL.setTargetPosition(target);

        FR.setPower(power);
        FL.setPower(power);
        BR.setPower(power);
        BL.setPower(power);

        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FR.isBusy() || FL.isBusy()) {
        }

        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);
    }

    DcMotor FR, FL, BR, BL, AM, LAM;
    Servo CS;

    @Override
    public void runOpMode() throws InterruptedException {
        FR = hardwareMap.dcMotor.get("Front Right");
        FL = hardwareMap.dcMotor.get("Front Left");
        BR = hardwareMap.dcMotor.get("Back Right");
        BL = hardwareMap.dcMotor.get("Back Left");
        AM = hardwareMap.dcMotor.get("Arm Lift");
        LAM = hardwareMap.dcMotor.get("Linear Actuator");

        AM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LAM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        CS = hardwareMap.servo.get("Claw Servo");

        waitForStart();

        resetEncoders();startEncoders();
        double circumference = 3.14 * 4;
        double rotationsNeeded = 24 / circumference;
        int encoderDrivingTarget = (int) (rotationsNeeded * 537.7);

        driveEncoders(0.5, encoderDrivingTarget);
        exitEncoders();

        resetEncodersA();startEncodersA();
        int armRotation = (int) (0.75 * 3895.9);

        AM.setTargetPosition(armRotation);
        AM.setPower(0.75);
        while (AM.isBusy()) {
        }
        AM.setPower(0);



    }
}
