package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative OpMode")

public class PARSJRCODE extends OpMode
{
    
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor armmotor = null;
    private DcMotor acimotor = null;
    private Servo solservo = null;
    private Servo sagservo = null;
    private Servo servo3 = null;
    
    
    

    
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        armmotor = hardwareMap.get(DcMotor.class, "arm_motor");
        acimotor = hardwareMap.get(DcMotor.class, "aci_motor");
        solservo = hardwareMap.get(Servo.class, "left_servo");
        sagservo = hardwareMap.get(Servo.class, "right_servo");
        servo3 = hardwareMap.get(Servo.class, "servo3");

        
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        
        acimotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Encoder kullanarak PID kontrol
        acimotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // Motorun frenle durmasını sağlar
        armmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        
        telemetry.addData("Status", "Initialized");
    }

    
    @Override
    public void init_loop() {
    }

    
    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        
        double leftPower;
        double rightPower;
        
        
        
        
        double drive = -gamepad1.left_stick_y*-1.2;
        double turn  =  gamepad1.right_stick_x*1;
        leftPower    = Range.clip(drive + turn, -0.9, 0.9) ;
        rightPower   = Range.clip(drive - turn, -0.9, 0.9) ;
        
        
        

        // Send calculated power to wheels
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("Arm Position :", acimotor.getCurrentPosition());
        
        
        
      
        
        
        if(gamepad1.y){
            armmotor.setPower(-1);
        }
        else if(gamepad1.a){
            armmotor.setPower(1);
        }
        else {
            armmotor.setPower(0.0);
        }
        
        if(gamepad2.b){
            acimotor.setPower(1);
            
        }
        else if (gamepad2.x){
            acimotor.setPower(-1);
            
        }
        else{
            acimotor.setPower(0.0);
            
        }
        
        if(gamepad2.a){
            solservo.setPosition(-0.69);
            sagservo.setPosition(0.32);
            
            
        }
        else if (gamepad2.left_bumper){
            solservo.setPosition(0.25);
            
        }
        
        else if(gamepad2.right_bumper){
            sagservo.setPosition(0);
            
        }
        
        
        if(gamepad2.y){
            servo3.setPosition(0.25);
            
        }
        
       
        
    }
    }

