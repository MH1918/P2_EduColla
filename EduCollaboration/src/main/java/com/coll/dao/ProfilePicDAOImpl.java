package com.coll.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.ProfilePicture;

@Repository("profilePicDAO")
@Transactional
public class ProfilePicDAOImpl implements ProfilePicDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void save(ProfilePicture profilePicture) {
		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);
		session.flush();
	}

	public ProfilePicture getProfilePic(String username) {

		Session session=sessionFactory.openSession();
		ProfilePicture profilePic=(ProfilePicture)session.get(ProfilePicture.class, username);
		session.close();
		return profilePic;
	}
}