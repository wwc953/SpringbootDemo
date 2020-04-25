#!/bin/bash
projectName=helloapp
#备份路径
warbak_dir=/home/work/warbak
#安装路径
#webapp_dir=/home/work/8tomcat/webapps
#tomcat版本
tomcat_version=apache-tomcat-8.5.23
#绝对路径
absolute_path=`pwd`
absolute_tomcat_path=$absolute_path/$tomcat_version

echo "build.sh begin............"

# grep -w 精确匹配
PID=$(ps -ef | grep java | grep -w $absolute_tomcat_path | grep -v grep | awk '{print $2}')
if [ $PID ]; then
    echo "停止进程：kill -9 $PID"
	kill -9 $PID
else
    echo "无历史进程...."
fi

#rm -rf releas
rm -rf $tomcat_version

#mkdir releas
#打包
#mvn -f $projectName/pom.xml -U -Dmaven.test.skip=true clean package
mvn -U -Dmaven.test.skip=true clean package -P prod

#tar -zxvf ./soft/$tomcat_version.tar.gz -C ./
tar -zxf ./soft/$tomcat_version.tar.gz -C ./
if [ $? -ne 0 ]; then
	echo "$tomcat_version.tar.gz 解压出错!!!"
	exit;
fi
#cp -r $projectName/target/*.war releas
cp -r target/*.war $tomcat_version/webapps

#######添加war
#cp -r releas/*.war $webapp_dir

#####移除war
#mkdir -p $warbak_dir
#cp $webapp_dir/$projectName.war $warbak_dir/$projectName.war_$(date "+%Y%m%d%H%M%S")
#rm -f $webapp_dir/$projectName.war

dos2unix *.sh

sh ./start.sh