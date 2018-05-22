package org.munozy.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.munozy.messenger.database.DatabaseClass;
import org.munozy.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		profiles.put("MunozY", new Profile(1, "MunozY", "Yupanqui", "Munoz"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		Profile profile = profiles.get(profileName);
		if (profile == null) {
			throw new RuntimeException("Profile with profileName " + profileName + " not found");
		}
		return profile;
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
