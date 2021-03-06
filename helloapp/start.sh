#!/bin/bash
#tomcat版本
tomcat_version=apache-tomcat-8.5.23
#绝对路径
absolute_path=`pwd`
absolute_tomcat_path=$absolute_path/$tomcat_version
user=$(whoami)

echo "$user start tomcat....."

sh $absolute_tomcat_path/bin/startup.sh
#有问题，当时war使，tomcat会启动两次？？？
#第一次是一个进程，第二次是springboot项目的进程id？？？
#nohup $absolute_tomcat_path/bin/startup.sh >/dev/null 2>&1 &

if [ $? -eq 0 ]; then
	PID=$(ps -ef | grep -w $absolute_tomcat_path | grep -v grep | awk '{print $2}')
	if [ -z ${PID} ]; then
		echo "no PID,start error!!!"
	else
	    echo "$PID > $absolute_tomcat_path/tomcat.pid"
	    echo $PID > $absolute_tomcat_path/tomcat.pid
	fi
else
    echo "start tomcat error!!!"
fi

