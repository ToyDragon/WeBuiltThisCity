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

Setup
-----
There are eclipse 4 projects in this repo, so eclipse is required. The Android SDK and the OUYA SDK are both required for this project.

####Setting up the Workspace
It is recomended to make a fresh workspace for WeBuiltThisCity.
The LaunchNonAndroid and WeBuiltThisCity projects are both regular Java Projects, and can be imported inside of eclipse by using File >> Import >> General >> Existing Projects into Workspace. Select the appropriate root directory of the git repository and select LaunchNonAndroid and WeBuiltThiscity, then click finish. The LaunchAndroid project and the WeBuiltThisCityAndroidLibrary is an android project, and can be imported with File >> Import >> Android >> Existing Android Code into Workspace. Once imported, the WeBuiltthisCityAndroidLibrary project must be explicitly marked as a library. This can be done by right clicking and going to Properties >> Android, and checking the "is Library" box.
