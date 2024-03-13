package com.cos.photogramstart.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Integer>{
	
	@Modifying
	@Query(value="INSERT INTO likes(imageId, userId, createDate) VALUES(:imageId, :principalid, now())", nativeQuery = true)
	int mLikes(int imageId, int principalid);
	
	@Modifying
	@Query(value="DELETE FROM likes WHERE imageId = :imageId AND userId = :principalid", nativeQuery = true)
	int mUnLikes(int imageId, int principalid);
}
