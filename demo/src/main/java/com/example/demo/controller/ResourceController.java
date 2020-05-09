package com.example.demo.controller;

import com.example.demo.model.Resource;
import com.example.demo.service.ResourceService;
import com.example.demo.utils.DateUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangweichun
 * @create 2020-05-09 17:18
 **/
@RestController
public class ResourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Value("${upload.path}")
    private String filePath;

    @Autowired
    ResourceService resourceService;

    /**
     * 文件类型 黑名单
     */
    private List<String> blackList = new ArrayList<String>() {{
        add("jsp");
        add("sh");
        add("");
    }};

    /**
     * 多文件上传
     *
     * @param request resquest
     * @return
     */
    @PostMapping("/uploadFiles")
    public String uploadFiles(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (CollectionUtils.isEmpty(files)) {
            return "no files";
        }
        try {
            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                String fileName = file.getOriginalFilename();
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (file.isEmpty() || blackList.contains(fileType)) {
                    return "upload error";
                }
                //重命名
                fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + DateUtils.getTime() + "." + fileType;
                File dest = new File(filePath + File.separator + fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                file.transferTo(dest);

                resourceService.addResource(fileName);
            }
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            return "upload error";
        }
        return "upload success";
    }

    /**
     * 文件下载
     *
     * @param id       文件id
     * @param response response
     * @return
     */
    @GetMapping("/download")
    public String downloadFile(@RequestParam(value = "id") Integer id, HttpServletResponse response) {

        Resource resource = resourceService.getResourceById(id);
        if (resource == null) {
            return "no resource";
        }

        //设置文件路径
        File file = new File(resource.getPath());
        if (!file.exists()) {
            return "file not exist";
        }

        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + resource.getName());
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            return "download success";
        } catch (Exception e) {
            LOGGER.error("下载异常,{}", e);
        } finally {
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(fis);
        }

        return "download error";
    }

}
