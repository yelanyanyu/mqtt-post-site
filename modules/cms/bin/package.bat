@echo off
rem /**
rem  * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [信息] 打包安装工程，生成jar包文件。
echo.

%~d0
cd %~dp0

cd ..
call mvn clean install -Dmaven.test.skip=true -Ppackage

cd bin
cmd /c msg %username% /time:0 /w "打包完成..."
pause