# Joystick2PPM

This is a port of Flytron's Compufly to Java for cross platform use. There have also been enhancements made to the original to support multiple usb sources. The goal is to make a versatile computer based system to control RC Land, Sea, Aircraft and other projects. Use the software with the PiKoder/USB (interface from USB2PPM, please refer to http://www.pikoder.com/PiKoder_USB_EN.html for more information) hardware solution to get PPM to a RC transmitter. The Current version is set up for Windows with a batch file to launch. We are working on getting that into a universal .jar

Should work with v1 and v2 but please note that this software is only tested in combination with the PiKoder/USB.

Comparing to the joystick-to-ppm version this build uses the latest availabale libs for jinput and rxtx. The build habe been cleaned up also.

To get started:

Run start.x86.bat for x86

Run start.x64.bat for x64

Requirements:

PiKoder USB2PPM: http://www.pikoder.com/PiKoder_USB_EN.html

Windows 7 USB drivers : http://www.flytron.com/pdf/CP210x_VCP_Win_XP_S2K3_Vista_7.zip


Notes:

1. This porject is based on joystick-to-ppm - please see the respective repository for more information.