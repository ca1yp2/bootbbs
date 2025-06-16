package net.ca1yps.bootbbs.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.ca1yps.bootbbs.entity.FileEntity;
import net.ca1yps.bootbbs.service.FileService;
import net.ca1yps.bootbbs.util.FileUploadUtil;

@RestController
public class FileController {

    private final FileService fService;
    private final String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/upload";

    public FileController(FileService fService){
        this.fService = fService;
    }
    
    @PostMapping("/upload")
    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("fileKey") Long fileKey)throws IOException{

        // 저장
        FileEntity entity = FileUploadUtil.saveFile(fileKey, file, uploadPath);

        // db 저장
        fService.save(entity);

        Map<String, Object> data = new HashMap<>();
        data.put("fileId", entity.getId());
        data.put("nfilename", entity.getNewFilename());
        data.put("filesize", entity.getFileSize());
        data.put("fileUrl", "/upload/" + entity.getNewFilename());
        data.put("ext", entity.getExt());
        return data;

    }

    @PostMapping("/fileDelete")
    public String fileDelete(@RequestParam("filename") String filename){
        boolean deleted = fService.deleteFile(filename);
        return deleted ? "1" : "0";
    }

    @GetMapping("/download/{fileid}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileid") long fileid) throws IOException{
        //실제 파일의 저장 경로
        Optional<FileEntity> ofile = fService.getFileById(fileid);
        if(ofile.isPresent()){
            FileEntity file = ofile.get();
            Path filePath = Paths.get(uploadPath, file.getNewFilename());
            Resource resource = new UrlResource(filePath.toUri());
            String encodedFilename = URLEncoder.encode(file.getOldFilename(), StandardCharsets.UTF_8);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"").body(resource);
        }
        //다운로드 파일이 없으면 404 응답
        return ResponseEntity.notFound().build();
    }

}
