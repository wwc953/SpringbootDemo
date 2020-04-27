### Maven jar
	
	指定配置文件，打成war包
	mvn -U -Dmaven.test.skip=true clean package -P prod

### Mybatis
    1.Mapper insert 成功后可以获取id
    <insert id="insert" parameterType="com.example.demo.model.MyTest" 
        useGeneratedKeys="true" keyProperty="id">
        .....
    </insert>
    
    