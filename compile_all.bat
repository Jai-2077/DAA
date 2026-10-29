@echo off
setlocal
set FAIL=0
for /d %%D in (*) do (
    if exist "%%D\*.java" (
        echo === %%D ===
        pushd "%%D"
        javac *.java
        if errorlevel 1 set FAIL=1
        popd
    )
)
echo.
if "%FAIL%"=="1" (echo SOME FILES FAILED) else (echo ALL COMPILED OK)
pause