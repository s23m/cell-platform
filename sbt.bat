set SCRIPT_DIR=%~dp0
java -Xmx768m -XX:MaxPermSize=256m -Xss2m -Dsbt.log.noformat=true -jar "%SCRIPT_DIR%sbt-launch-0.11.2.jar" %*