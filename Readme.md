/********************
 *	CLIENT DEV MODE	*
 ********************/

# SHELL SCREEN RESOLUTION 

The 'screen' MUST be composed of a 25 row by 80 column matrix of character locations.
Manually check it into your shell settings.
Without this configuration the gotoxy() goes wrong.

Possibility modify this configuration by hand currently

# DO NOT COMPILE INTO EMBEDDED IDE CONSOLE

Running the application into the embbeded IDE console will not activate the RAW mode.
In other word you will be force to press enter at each input.

How to launch start the app into the native shell

# External Tools Configuration

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
	
# Compile and run the client

Make sure there is jna-4.2.1.jar as Maven dependency
By cloning the master branch it's guaranteed 100% error free

Run the External_Console you previously set :
	pwd
	/home/X/workspace/MultiTetris/src/main

Copy/Paste
	java -cp ~/.m2/repository/net/java/dev/jna/jna/4.2.1/jna-4.2.1.jar:../../target/classes: fr.esiea.ga.tetris.client.Tetris
	
	~/.m2/repository/net/java/dev/jna/jna/4.2.1/jna-4.2.1.jar
If the jar isn't at this location localy on your computer find it and replace this path with yours

/********************
 *	SERVER DEV MODE	*
 ********************/