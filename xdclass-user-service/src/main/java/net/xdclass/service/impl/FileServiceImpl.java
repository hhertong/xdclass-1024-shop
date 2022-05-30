package net.xdclass.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.OSSConfig;
import net.xdclass.service.FileService;
import net.xdclass.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String uploadUserImg(MultipartFile file) {

        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String originalFilename = file.getOriginalFilename();

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String folder = dtf.format(ldt);
        String fileName = CommonUtil.generateUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newFileName = "user/" + folder + "/" + fileName + extension;

        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketname, newFileName, file.getInputStream());

            //返回访问路径
            if (null != putObjectResult) {

                String imgUrl = "https://" + bucketname + "." + endpoint + "/" + newFileName;
                return imgUrl;
            }
        } catch (IOException e) {
            log.error("文件上传失败:{ }", e);
        } finally {
            ossClient.shutdown();
        }

        return null;
    }
}
