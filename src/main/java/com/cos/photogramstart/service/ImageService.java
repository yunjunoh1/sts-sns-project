package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Transactional(readOnly = true)
	public List<Image> popular(){
		return imageRepository.mPopular();
	}
	
	@Transactional(readOnly = true) 
	public Page<Image> imgStory(int principalid, Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalid, pageable);
		
		images.forEach((image)->{
			
			image.setLikeCount(image.getLikes().size());
			
			image.getLikes().forEach((like) ->{
				if(like.getUser().getId() == principalid) { // 해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한 것인지 비교
					image.setLikeState(true);
				}
			});
		});
		
		return images;
	}
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional 
	public void imgUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); 
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); // 1.jpg
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // 1696a9c9-dad4-4eea-b16b-639e57897a61_KakaoTalk_20240112_223450891.jpg
		imageRepository.save(image);
		
	
	}
}
