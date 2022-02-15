# MechanismTester
Individual mechanism tester for FRC (WPILib 2022)

Change motor controller IDs, servo IDs, pneumatics hub ports, etc. in Constants

Left joystick: Test a motor with variable power (forward and reverse) and no limit control
A button: Test a motor with 25% power forward
Y button: Test a motor with 50% power forward
X button: Test a motor with 75% power forward
B button: Test a motor with 100% power forward

Left bumper: Test a solenoid's "off" state
Right bumper: Test a solenoid's "on" state

Left stick button: Test a motor going forward until an amperage limit
Right stick button: Test a motor going forward until it hits a limit switch

Left logo button: Test a motor going forward until hitting a soft limit (or button release)
Right logo button: Test a motor going reverse until hitting a soft limit (or button release)
