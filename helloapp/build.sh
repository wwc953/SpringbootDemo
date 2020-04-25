#!/bin/bash
projectName=helloapp
#备份路径
warbak_dir=/home/work/warbak
#安装路径
#webapp_dir=/home/work/8tomcat/webapps
#tomcat版本
tomcat_version=apache-tomcat-8.5.23

rm -rf releas
mkdir releas
#打包
#mvn -f $projectName/pom.xml -U -Dmaven.test.skip=true clean package
mvn -U -Dmaven.test.skip=true clean package -P prod

tar -zxvf ./soft/$tomcat_version.tar.gz -C ./
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
sh ./start.sh $tomcat_version