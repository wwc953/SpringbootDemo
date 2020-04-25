#!/bin/bash

#tomcat版本
tomcat_version=apache-tomcat-8.5.23
#绝对路径
absolute_path=`pwd`
absolute_tomcat_path=$absolute_path/$tomcat_version
user=$(whoami)

echo "$user start tomcat....."

#sh $absolute_tomcat_path/bin/startup.sh
nohup $absolute_tomcat_path/bin/startup.sh >/dev/null 2>&1 &

if [ $? -eq 0 ]; then
	PID=$(ps -ef | grep java | grep -w $absolute_tomcat_path | grep -v grep | awk '{print $2}')
    echo $PID > $1/tomcat.pid
else
    echo "start tomcat error!!!"
fi

