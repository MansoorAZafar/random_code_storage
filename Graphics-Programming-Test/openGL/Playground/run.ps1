time make

$size = du -sh playground.exe
echo "Executable Size: $size"

./playground.exe
make clean
pause
cls
