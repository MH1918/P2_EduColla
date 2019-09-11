package com.coll.dao;

import com.coll.model.ProfilePicture;

public interface ProfilePicDAO {
	
	public void save(ProfilePicture profilePicture);
	
	public ProfilePicture getProfilePic(String username);
}
