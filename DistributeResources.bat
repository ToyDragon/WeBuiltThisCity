@echo off
echo -------
echo Android
echo -------
echo
xcopy /s/e/y "Resources/Images" "LaunchAndroid/res/drawable-mdpi"
xcopy /s/e/y "Resources/Sounds" "LaunchAndroid/assets/Sounds"
xcopy /s/e/y "Resources/Other" "LaunchAndroid/assets/Other"
echo ----------
echo NonAndroid
echo ----------
xcopy /s/e/y "Resources/Images" "LaunchNonAndroid/Resources/Images"
xcopy /s/e/y "Resources/Sounds" "LaunchNonAndroid/Resources/Sounds"
xcopy /s/e/y "Resources/Other" "LaunchNonAndroid/Resources/Other"
echo ----
echo OUYA
echo ----
echo Ouya isnt made yet ya goof!
echo Done!
pause