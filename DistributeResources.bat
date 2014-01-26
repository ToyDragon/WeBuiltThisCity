@echo off
xcopy /s/e/y "Resources/Images" "LaunchAndroid/res/drawable-mdpi"
xcopy /s/e/y "Resources/Images" "LaunchNonAndroid/bin"
xcopy /s/e/y "Resources/Sounds" "LaunchNonAndroid/bin"
xcopy /s/e/y "Resources/Other" "LaunchNonAndroid/bin"
echo Done!
pause