package com.example.basicproject.service.backend.impl;

import com.example.basicproject.dao.FileDao;
import com.example.basicproject.dao.domain.File;
import com.example.basicproject.http.QiniuApiHelper;
import com.example.basicproject.service.backend.FileService;
import com.example.basicproject.utils.DownloadUtils;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.MD5Util;
import com.example.basicproject.utils.UserHelperUtil;
import com.qiniu.storage.model.DefaultPutRet;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;

@Service
public class FileServiceImpl implements FileService {

    private final static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    private FileDao fileDao;

    private QiniuApiHelper qiniuApiHelper;

    @Autowired
    public void setQiniuApiHelper(QiniuApiHelper qiniuApiHelper) {
        this.qiniuApiHelper = qiniuApiHelper;
    }

    @Autowired
    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadImg(MultipartFile file,String storageLocation) throws IOException {
        String md5;
        File fileInstance;
        if (File.LOCAL_STORAGE_LOCATION.equals(storageLocation)){
            byte[] content = file.getBytes();
            md5 = MD5Util.getImageHash(file.getInputStream());
            fileInstance = File.create(file.getOriginalFilename(), content, md5,storageLocation);
        }else if (File.QN_STORAGE_LOCATION.equals(storageLocation)){
            DefaultPutRet defaultPutRet = qiniuApiHelper.uploadFile(file.getInputStream(), null);
            md5=defaultPutRet.hash;
            fileInstance = File.create(file.getOriginalFilename(), null, md5,storageLocation);
        }else{
            throw new RuntimeException("不存在的存储位置");
        }

        Long id = fileDao.selectIdByMD5(md5);
        if (id!=null){
            return IdUtil.encode(BigInteger.valueOf(id));
        }
        UserHelperUtil.fillEditInfo(fileInstance);
        UserHelperUtil.fillCreateInfo(fileInstance);
        fileDao.insert(fileInstance);
        return IdUtil.encode(BigInteger.valueOf(fileInstance.getId()));
    }

    @Override
    public void showImg(String id, HttpServletResponse response) throws IOException {
        File file = fileDao.selectByPrimaryKey(IdUtil.decode(id).longValue());
        Assert.notNull(file,"图片不存在");

        byte[] bytes;
        if (File.LOCAL_STORAGE_LOCATION.equals(file.getStorageLocation())){
            bytes = file.getData();
        }else{
            bytes = qiniuApiHelper.downloadFile(file.getMd5());
        }
        DownloadUtils.showPic(response, new ByteArrayInputStream(bytes));
    }
}
