@echo off
xcopy /s/e/y "WeBuiltThisCity/src" "WeBuiltThisCityAndroidLibrary/src"
echo Refresh the Android Library project...
pause
xcopy /y "WeBuiltThisCityAndroidLibrary/bin" "LaunchAndroid/libs"
echo Done!
pause