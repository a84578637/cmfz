package com.baizhi.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

public class VideoUtil {
    public static String getTail(MultipartFile file){
        String originalFilename = file.getOriginalFilename();

        String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        return substring;
    }
    public static String getPath(StorePath path){
        String group = path.getGroup();
        String path2 = path.getPath();
        return group+"/"+path2;
    }
    public static String  getDuration(String filePath) {
        File file = new File(filePath);
        MP3AudioHeader audioHeader = null;
        Integer min = 0;
        Integer otherSeconds = 0;
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            audioHeader = (MP3AudioHeader)f.getAudioHeader();
            Integer seconds = audioHeader.getTrackLength();
            min = seconds/60;
            otherSeconds = seconds%60;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return min+"分"+otherSeconds+"秒";
    }
    public static String getFileSize(String path) {
        String resourceSizeMb = null;
        try {
            // 指定路径即可
            File f = new File(path);

            FileInputStream fis = new FileInputStream(f);

            DecimalFormat df = new DecimalFormat("#.##");


            if((double)((double) fis.available() / 1024) > 1000) {
                resourceSizeMb = df.format((double)((double) fis.available() / 1024 / 1024)) + "MB";
            } else {
                resourceSizeMb= df.format((double)((double) fis.available() / 1024)) + "KB";
            }

        } catch (Exception e) {
            e.printStackTrace();
            resourceSizeMb = null;
        }
        return resourceSizeMb;
    }

}
