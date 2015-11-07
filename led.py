#!/usr/bin/python
import sys
from sense_hat import SenseHat
from time import sleep

sense = SenseHat()

# Rotation set to vertical (down to raspi logo)
sense.set_rotation(270)

# Global variables 
# Colours
X = [255, 0, 0]  # Red
O = [0, 0, 0]  # Black
W = [255, 255, 255] # White
G = [20, 171, 20] # Green
B = [16, 63, 118] # Blue

def message( str ):
	sense.show_message(str,text_colour=X, back_colour=O)
	return;

def downArrow():
	pixelArray = [
		O, O, O, X, X, O, O, O,
		O, O, O, X, X, O, O, O,
		O, O, O, X, X, O, O, O,
		O, O, O, X, X, O, O, O,
		O, O, O, X, X, O, O, O,
		O, X, X, X, X, X, X, O,
		O, O, X, X, X, X, O, O,
		O, O, O, X, X, O, O, O
	]
	i = 0
	while (i<=3):	
		sense.set_pixels(pixelArray)
		sleep(1)
		sense.clear(0,0,0)
		sleep(1)
		i=i+1
	sense.set_pixels(pixelArray)
	return;

def upArrow():
        pixelArray = [
                O, O, O, X, X, O, O, O,
                O, O, X, X, X, X, O, O,
                O, X, X, X, X, X, X, O,
                O, O, O, X, X, O, O, O,
                O, O, O, X, X, O, O, O,
                O, O, O, X, X, O, O, O,
                O, O, O, X, X, O, O, O,
                O, O, O, X, X, O, O, O
        ]
	
	i=0
	while (i<=3):
		sense.set_pixels(pixelArray)
                sleep(1)
                sense.clear(0,0,0)
                sleep(1)
		i=i+1
        sense.set_pixels(pixelArray)
        return;

def showTemperature():
	temperature = sense.get_temperature()
	sense.show_message("%.1f C" % temperature,text_colour=X, back_colour=O)
	return;

def showHumidity():
	humidity = sense.get_humidity()
        sense.show_message("%.1f rH" % humidity,text_colour=B, back_colour=O)
        return;

def showDrop():
	pixelArray = [
                O, O, O, B, B, O, O, O,
                O, O, B, B, B, B, O, O,
                O, O, B, B, B, B, O, O,
                O, B, B, B, B, B, B, O,
                O, B, B, B, B, B, B, O,
                B, B, B, B, B, B, B, B,
                B, B, B, B, B, B, B, B,
                O, B, B, B, B, B, B, O
        ]

	i=0
        while (i<=3):
                sense.set_pixels(pixelArray)
                sleep(1)
                sense.clear(0,0,0)
                sleep(1)
                i=i+1
        sense.set_pixels(pixelArray)
        return;

def showOK():
        pixelArray = [
                O, O, O, O, O, O, O, O,
                O, O, O, O, O, O, O, O,
                O, O, O, O, O, O, O, G,
                O, O, O, O, O, O, G, O,
                O, O, O, O, O, G, O, O,
                G, O, O, O, G, O, O, O,
                O, G, O, G, O, O, O, O,
                O, O, G, O, O, O, O, O
        ]

	i=0
	while (i<=2):
                sense.set_pixels(pixelArray)
                sleep(0.3)
                sense.clear(0,0,0)
                sleep(0.3)
                i=i+1
        return;

def showFail():
        pixelArray = [
                X, O, O, O, O, O, O, X,
                O, X, O, O, O, O, X, O,
                O, O, X, O, O, X, O, O,
                O, O, O, X, X, O, O, O,
                O, O, O, X, X, O, O, O,
                O, O, X, O, O, X, O, O,
                O, X, O, O, O, O, X, O,
                X, O, O, O, O, O, O, X
        ]

        i=0
        while (i<=2):
                sense.set_pixels(pixelArray)
                sleep(0.3)
                sense.clear(0,0,0)
                sleep(0.3)
                i=i+1
        return;

def clearAll():
	sense.clear(0,0,0)
	return;

if len(sys.argv)<=1:
	clearAll()
elif sys.argv[1]=="temp":
	showTemperature()
elif sys.argv[1]=="hum":
	showHumidity()
elif sys.argv[1]=="msg":
	message(sys.argv[2])
elif sys.argv[1]=="up":
	upArrow()
elif sys.argv[1]=="down":
	downArrow()
elif sys.argv[1]=="drop":
	showDrop()
elif sys.argv[1]=="ok":
	showOK()
elif sys.argv[1]=="fail":
	showFail()
elif sys.argv[1]=="show":
	sense.show_message("Temperature: ",text_colour=X, back_colour=O)
	showTemperature()
	sense.show_message("Humidity: ",text_colour=X, back_colour=O)
	showHumidity()
else:
	clearAll()
