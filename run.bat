@echo off
REM ================================================
REM Script para compilar e executar o projeto JavaFX
REM ================================================

REM Caminhos
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
set JAVAFX_LIB="G:\A3-PROGRAMA--O-DE-SOLU--ES-COMPUTACIONAIS\javafx-sdk-24.0.2\lib"
set PROJECT_DIR="G:\A3-PROGRAMA--O-DE-SOLU--ES-COMPUTACIONAIS\Gerenciamento_de_Usuarios"
set BIN_DIR=%PROJECT_DIR%\bin
set LIB_DIR=%PROJECT_DIR%\lib
set MYSQL_JAR=%PROJECT_DIR%\mysql-connector-j-9.4.0.jar

REM ================================================
echo Limpando arquivos antigos...
if exist %BIN_DIR% rmdir /s /q %BIN_DIR%
mkdir %BIN_DIR%

REM ================================================
echo Compilando os arquivos Java...
javac --module-path %JAVAFX_LIB% --add-modules javafx.controls,javafx.fxml ^
  -d %BIN_DIR% ^
  %PROJECT_DIR%\*.java

if errorlevel 1 (
    echo ERRO na compilação!
    pause
    exit /b
)

REM ================================================
echo Criando arquivo JAR...
cd %BIN_DIR%
jar --create --file Gerenciamento_de_Usuarios.jar ^
    -e MainApp ^
    *.class

REM Inclui dependência do MySQL
jar uf Gerenciamento_de_Usuarios.jar -C %PROJECT_DIR% config.properties
jar uf Gerenciamento_de_Usuarios.jar -C %PROJECT_DIR% ConfigDbView.fxml
jar uf Gerenciamento_de_Usuarios.jar -C %PROJECT_DIR% UsuarioView.fxml

REM ================================================
echo Executando o programa...
java --module-path %JAVAFX_LIB% --add-modules javafx.controls,javafx.fxml ^
 -cp Gerenciamento_de_Usuarios.jar;%MYSQL_JAR% MainApp

pause
