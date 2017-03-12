:: Beginning of hub batch file
set SERVER_VERSION=3.0.1
set TASK_NAME=SeleniumServerNode3
set HUB_PORT=5556
set REGISTER_IP=localhost:4444
set IE_DRIVER=d:\_webdriver\iedriver\IEDriverServer.exe
java -Dwebdriver.ie.driver=%IE_DRIVER% -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%REGISTER_IP%/grid/register -browser "browserName=internet explorer,version=11,maxinstance=1,platform=WINDOWS" -port %HUB_PORT%
:: End of hub batch file
pause
