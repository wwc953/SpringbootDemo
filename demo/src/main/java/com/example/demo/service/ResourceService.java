package com.example.demo.service;

import com.example.demo.dao.ResourceMapper;
import com.example.demo.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author wangweichun
 * @create 2020-05-09 17:11
 **/
@Transactional
@Service
public class ResourceService {

    @Value("${upload.path}")
    private String filePath;

    /**
     * 有效
     */
    public static final String VALID = "1";

    /**
     * 无效
     */
    public static final String INVALID = "0";

    @Autowired
    ResourceMapper resourceMapper;

    /**
     * 添加资源
     *
     * @param fileName 文件名称
     * @return 成功数量
     */
    public int addResource(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return -1;
        }

        Resource resource = new Resource();
        resource.setName(fileName);
        resource.setPath(filePath + File.separator + fileName);
        resource.setCreateBy("wang");
        resource.setCreateTime(new Date());
        resource.setUpdateBy("wang");
        resource.setUpdateTime(new Date());
        resource.setStatus(VALID);

        return resourceMapper.insert(resource);
    }

    /**
     * 根据id查询资源信息
     * @param id 资源id
     * @return 资源对象
     */
    public Resource getResourceById(Integer id) {
        if (id < 0) {
            return null;
        }
        return resourceMapper.selectByPrimaryKey(id);
    }

}
