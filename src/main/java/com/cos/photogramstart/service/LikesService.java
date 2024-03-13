package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.likes.LikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesService {
	
	private final LikesRepository likesRepository;
	
	@Transactional
	public void like(int imageId, int principalid) {
		likesRepository.mLikes(imageId, principalid);
	}
	
	@Transactional
	public void unlike(int imageId, int principalid) {
		likesRepository.mUnLikes(imageId, principalid);
	}
}
