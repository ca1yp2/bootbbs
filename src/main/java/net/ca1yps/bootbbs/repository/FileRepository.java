package net.ca1yps.bootbbs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.ca1yps.bootbbs.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    //파일 목록
    List<FileEntity> findByBid(long bid);

    //bid가 업데이트 되지 않은(밀리초가 아직 들어있는) 쓰레기 파일 목록
    @Query("SELECT f FROM FileEntity f Where f.bid > :minTimestamp And bid < :beforeTime")  //bid가 10000000000000 보다 크고 오늘 0시보다는 작게
    List<FileEntity> findByTrashFile(@Param("minTimestamp") Long minTimestamp, @Param("beforeTime") Long beforeTime);

    //파일 이름으로 삭제
    Optional<FileEntity> findByNewFilename(String newFilename);
}
