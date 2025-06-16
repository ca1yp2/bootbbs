package net.ca1yps.bootbbs.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.ca1yps.bootbbs.entity.FileEntity;
import net.ca1yps.bootbbs.repository.FileRepository;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final String uploadPath = "/upload/";

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    //파일 저장
    public FileEntity save(FileEntity file){
        return fileRepository.save(file);
    }

    //파일 아이디로 가져오기
    public Optional<FileEntity> getFileById(Long id){
        return fileRepository.findById(id);
    }

    //특정 게시물에 소속된 파일 가져오기
    public List<FileEntity> getFilesByBid(Long bid){
        return fileRepository.findByBid(bid);
    }

    //timestamp 등록된 bid 찾아서 id로 변경
    @Transactional
    public void updateBidByTempkey(Long tempBid, Long realBid){
        List<FileEntity> files = fileRepository.findByBid(tempBid);
        for(FileEntity file: files){
            file.setBid(realBid);
            fileRepository.save(file);
        }
    }

    @Transactional
    public boolean deleteFile(String nFilename){
        Optional<FileEntity> fileOpt = fileRepository.findByNewFilename(nFilename);
        if(fileOpt.isPresent()){
            FileEntity file = fileOpt.get();
            
            //실제 파일 삭제
            File realFile = new File(uploadPath + file.getNewFilename());
            if(realFile.exists()){
                realFile.delete();
            } 
            //DB 삭제
            fileRepository.deleteById(file.getId());
            return true;
        }
        return false;
    }

    //게시글 삭제 될 때 소속된 파일도 삭제
    @Transactional
    public void deleteFilesByBid(Long bid){
        List<FileEntity> files = fileRepository.findByBid(bid);
        for(FileEntity file: files){
            deleteFile(file.getNewFilename());
        }
    }

    //일정 시간이 되면 파일을 삭제해 주는 스케줄러
    @Scheduled(cron = "0 15 14 * * * ")
    public void scheduledFileCleanup(){
        int deletedCount = cleanUpUnlinkedOldFiles();
        System.out.println("🧹" + deletedCount + "개의 파일을 정리했습니다.");
    }

    public int cleanUpUnlinkedOldFiles(){
        long minTimestamp = 10000000000000L;

        //오늘 0시 가져오기
        LocalDate today = LocalDate.now();
        LocalDateTime midnight = today.atStartOfDay();
        long todayZero = midnight.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        List<FileEntity> trashFiles = fileRepository.findByTrashFile(minTimestamp, todayZero);
        int count = 0;
        for(FileEntity tfile : trashFiles){
            if(deleteFile(tfile.getNewFilename())){
                count++;
            }
        }
        return count;
    }
}
