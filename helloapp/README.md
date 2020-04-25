### Maven 打war包
	Maven检查所有SNAPSHOT依赖更新，确保集成基于最新的状态
	mnv -U
	
	跳过单元测试
	mvn -U -Dmaven.test.skip=true clean package

	指定配置文件，打成war包
	mvn -U -Dmaven.test.skip=true clean package -P prod

	指定pom.xml路径
	mvn -f helloapp/pom.xml -U -Dmaven.test.skip=true clean package -P prod

	远程仓库的更新（跳过测试）
	mvn clean package deploy -Dmaven.test.skip=true

### git
    git强制覆盖：
    git fetch --all
    git reset --hard origin/master
    git pull
    
    git强制覆盖本地命令（单条执行）：
    git fetch --all && git reset --hard origin/master && git pull