@echo off
echo -------
echo Android
echo -------
mkdir "LaunchAndroid/res/drawable-mdpi"
mkdir "LaunchAndroid/assets/Sounds"
mkdir "LaunchAndroid/assets/Other"
xcopy /s/e/y "Resources/Images" "LaunchAndroid/res/drawable-mdpi"
xcopy /s/e/y "Resources/Sounds" "LaunchAndroid/assets/Sounds"
xcopy /s/e/y "Resources/Other" "LaunchAndroid/assets/Other"
echo ----------
echo NonAndroid
echo ----------
mkdir "LaunchNonAndroid/Resources/Images"
mkdir "LaunchNonAndroid/Resources/Sounds"
mkdir "LaunchNonAndroid/Resources/Other"
xcopy /s/e/y "Resources/Images" "LaunchNonAndroid/Resources/Images"
xcopy /s/e/y "Resources/Sounds" "LaunchNonAndroid/Resources/Sounds"
xcopy /s/e/y "Resources/Other" "LaunchNonAndroid/Resources/Other"
echo ----
echo OUYA
echo ----
mkdir "LaunchOUYA/res/drawable-mdpi"
mkdir "LaunchOUYA/assets/Sounds"
mkdir "LaunchOUYA/assets/Other"
xcopy /s/e/y "Resources/Images" "LaunchOUYA/res/drawable-mdpi"
xcopy /s/e/y "Resources/Sounds" "LaunchOUYA/assets/Sounds"
xcopy /s/e/y "Resources/Other" "LaunchOUYA/assets/Other"
echo Done!
pause