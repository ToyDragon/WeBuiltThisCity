WeBuiltThisCity
===============

RamDev tower defense game

This needs to be playable, or at least presentable by February 22nd.

Multi-Platform
--------------
This game will be able to be ran on android mobile devices, pc's, and the Ouya console. Due to the difficulty of user input, development will not be targetted at mobile. 

There are three seperate launch components, one for each platform of development. These provide a standardized hardware interface with the platforms. The game itself is the project "WeBuiltThisCity," this is where everything not specific to any system will be located.

Before running the android launcher, run the android library updater batch file. Alternatively, copy all of the source code from the WeBuiltThisCity project into the WeBuiltThisCityAndroidLibrary project and compile it in eclipse. Then move the webuiltthiscityandroidlibrary.jar from the bin folder into the AndroidLauncher/libs folder.

Resources
---------
All resources (IE graphics or data files) should be saved in the Resources folder using all lower case names and should be referenced through the appropriate methods in the MachineInterface or GraphicsInterface. After adding resources, run the resource distributer batch file, or copy them to the appropriate locations in each launcher environment.
