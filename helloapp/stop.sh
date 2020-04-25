#!/bin/bash
#tomcat版本
tomcat_version=apache-tomcat-8.5.23
#绝对路径
absolute_path=`pwd`
absolute_tomcat_path=$absolute_path/$tomcat_version
user=$(whoami)

PID=$(ps -ef | grep -w $absolute_tomcat_path | grep -v grep | awk '{print $2}')
if [ -z ${PID} ]; then
    echo "no tomcat process"
else
    kill -9 $PID
    echo "${user} kill -9 ${PID}"
    > ${absolute_tomcat_path}/tomcat.pid
fi


