package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
	
	
	@Modifying 
	@Query(value="INSERT INTO SUBSCRIBE (fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId); 
	
	@Modifying
	@Query(value="DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromuserId =:principalid AND toUserId = :pageUserId", nativeQuery = true)
	int mSubscribeState(int principalid, int pageUserId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
	int mSubscribeCount(int pageUserId);

}
