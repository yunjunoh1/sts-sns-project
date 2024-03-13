package com.cos.photogramstart.web.dto.subscribe;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	
	private int id;
	private String username;
	private String profileImageUrl;
	private Integer subscribeState;
	private Integer equalUserState;

}
