package com.coll.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.coll.dao.ProfilePicDAO;
import com.coll.model.ProfilePicture;
import com.coll.model.UserDetail;

@RestController
public class ProfilePicRestController {
	
	@Autowired
	private ProfilePicDAO profilePicDAO;
	
	@RequestMapping(value="/doUpload", method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePic(@RequestParam(value="file") CommonsMultipartFile fileUpload, HttpSession session) {
		
		UserDetail user=(UserDetail)session.getAttribute("userDetail");
		
		if(user==null) {
			return new ResponseEntity<String>("Error Occured", HttpStatus.UNAUTHORIZED);
		}
		else {
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setUsername(user.getUsername());
			System.out.println(fileUpload.getBytes());
			profilePicture.setImage(fileUpload.getBytes());
			profilePicDAO.save(profilePicture);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{username}", method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@PathVariable("username")String username, HttpSession session) {
		
		UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		
		if(userDetail==null) {
			return null;
		}
		else {
			ProfilePicture profilePicture=profilePicDAO.getProfilePic(username);
			
			if(profilePicture!=null) {
				return profilePicture.getImage();
			}
			else {
				return null;
			}
		}
	}
}
