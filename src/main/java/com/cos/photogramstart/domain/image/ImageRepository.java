package com.cos.photogramstart.domain.image;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer>{
	
	@Query(value="SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalid) ORDER BY id DESC", nativeQuery = true)
	Page<Image> mStory(int principalid, Pageable pageable);
	
	@Query(value="SELECT i.* FROM image i INNER JOIN(SELECT imageid, COUNT(imageid) likeCount FROM likes GROUP BY imageid) c on i.id = c.imageid ORDER BY likecount DESC", nativeQuery = true)
	List<Image> mPopular();
}
