package cn.ganxq.dbcontrol.common.music;

import cn.ganxq.dbcontrol.entity.UploadMusic;
import cn.ganxq.dbcontrol.service.IUploadMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.logging.*;

@RestController
@RequestMapping("/upload")
public class Upload {

    private static final String TARGET_DIRECTORY = "C:/project/db/music";
    private static final Logger logger = Logger.getLogger(Upload.class.getName());

    @Autowired
    private IUploadMusicService uploadMusicService; // 注入 IUploadMusicService

    @PostMapping("/music")
    public ResponseEntity<String> uploadMusicFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("musicName") String musicName,
            @RequestParam("uploadUser") String uploadUser) {

        // 检查文件是否为空
        if (file.isEmpty()) {
            logger.warning("No file provided.");
            return ResponseEntity.badRequest().body("No file uploaded.");
        }

        // 获取文件名并校验类型
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".mp3")) {
            logger.warning("Invalid file type. Only .mp3 files are supported.");
            return ResponseEntity.badRequest().body("Invalid file type. Only .mp3 files are supported.");
        }

        // 创建用户文件夹
        File userDir = new File(TARGET_DIRECTORY, uploadUser);
        if (!userDir.exists()) {
            if (!userDir.mkdirs()) {
                logger.warning("Failed to create user directory: " + userDir.getAbsolutePath());
                return ResponseEntity.status(500).body("Failed to create user directory.");
            }
            logger.info("Created user directory: " + userDir.getAbsolutePath());
        }

        // 处理文件名冲突
        File targetFile = new File(userDir, fileName);
        if (targetFile.exists()) {
            String baseName = getBaseName(fileName);
            String extension = getExtension(fileName);
            targetFile = new File(userDir, baseName + "_new" + extension);
            logger.info("Renaming the file to avoid conflict: " + targetFile.getName());
        }

        // 保存文件
        try {
            file.transferTo(targetFile);
            logger.info("File uploaded successfully: " + targetFile.getAbsolutePath());

            // 插入文件信息到数据库
            UploadMusic uploadMusic = new UploadMusic();
            uploadMusic.setMusicName(musicName);
            uploadMusic.setUploadUser(uploadUser);
            boolean saved = uploadMusicService.save(uploadMusic);  // 使用 MyBatis-Plus 插入数据库
            if (saved) {
                logger.info("Music info saved to database successfully.");
            } else {
                logger.warning("Failed to save music info to database.");
            }

            return ResponseEntity.ok("File uploaded and information saved successfully!");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error uploading file: ", e);
            return ResponseEntity.status(500).body("Error uploading file.");
        }
    }

    @DeleteMapping("/music/{musicFile}")
    public ResponseEntity<String> deleteMusicFile(@PathVariable("musicFile") String fileName,
                                                  @RequestParam("uploadUser") String uploadUser) {
        // 校验文件类型
        if (fileName == null || !fileName.toLowerCase().endsWith(".mp3")) {
            logger.warning("Invalid file type. Only .mp3 files are supported.");
            return ResponseEntity.badRequest().body("Invalid file type. Only .mp3 files are supported.");
        }

        // 定位到用户文件目录
        File userDir = new File(TARGET_DIRECTORY, uploadUser);
        File targetFile = new File(userDir, fileName);

        if (!targetFile.exists()) {
            logger.warning("File not found: " + targetFile.getAbsolutePath());
            return ResponseEntity.status(404).body("File not found.");
        }

        // 删除文件并返回响应
        if (targetFile.delete()) {
            logger.info("File deleted successfully: " + targetFile.getAbsolutePath());

            // 删除数据库中的文件信息
            boolean deleted = uploadMusicService.removeById(fileName);  // 使用 MyBatis-Plus 删除文件数据
            if (deleted) {
                logger.info("Music info deleted from database successfully.");
            } else {
                logger.warning("Failed to delete music info from database.");
            }

            return ResponseEntity.ok("File deleted successfully!");
        } else {
            logger.warning("Failed to delete the file: " + targetFile.getAbsolutePath());
            return ResponseEntity.status(500).body("Failed to delete the file.");
        }
    }

    private String getBaseName(String originalFileName) {
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return originalFileName;
        }
        return originalFileName.substring(0, dotIndex);
    }

    private String getExtension(String originalFileName) {
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return originalFileName.substring(dotIndex);
    }
}
