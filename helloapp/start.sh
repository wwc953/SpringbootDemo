#!/bin/bash
#tomcat版本
tomcat_version=apache-tomcat-8.5.23

echo "start tomcat....."

sh $tomcat_version/bin/startup.sh


if [ $? -eq 0 ]; then
	PID=$(ps -ef | grep java | grep -v grep | awk '{print $2}')
    echo $PID > $1/tomcat.pid
else
    echo "start tomcat error!!!"
fi

