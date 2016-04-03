Angoso Stefan
Guidis Antoine

# FEATURES

Menu
Solo mode
Special character
Time
Rotation

Client - Server communication OK
Implementation into Multi mode NON OK

# CLIENT DEV MODE

* SET SHELL SCREEN RESOLUTION 

Right click > Profile > Profile Preference
The have a better experience, set the Font to : FreeMono 12
The 'screen' MUST be composed of a 25 row by 80 column matrix of character locations.
Manually check it into your shell settings.
Without this configuration the gotoxy() goes wrong.

Possibility modify this configuration by hand currently

* DO NOT COMPILE INTO EMBEDDED IDE CONSOLE

	git clone git@github.com:AngosoStefan/MultiTetris.git
	cd MultiTetris
	mvn clean install
	cd src/main
	./Tetris

OR

Import Maven Project

Running the application into the embbeded IDE console will not activate the RAW mode.
In other word you will be force to press enter at each input.

How to launch start the app into the native shell

* External Tools Configuration

	# Main
	
	Name					: External_Console
	Location 				: /usr/bin/gnome-terminal
	Working Directory 	: ${workspace_loc:/MultiTetris/src/main}

	# Build

	Build before launch
	Specific projects
	Include referenced projects

	# Common

	Untick Allocate console
	
* Play

Play with z q d key and press p for exit current game to return to menu 

# SERVER DEV MODE

Not working.
But you should be in MultiTetris/src/main
./GameServer
./Tetris
./Tetris
And start MultiPlayer Game on both "./Tetris"
Correct implementation of ArrayBlockingQueue<NetworkMessage>
