package org.munozy.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.munozy.messenger.model.Profile;
import org.munozy.messenger.service.ProfileService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService profileService = new ProfileService();

	/**
	 * GET http://localhost:8080/messenger/webapi/profiles
	 * 
	 * @return
	 */
	@GET
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}
	
	/**
	 * GET http://localhost:8080/messenger/webapi/profiles/[profileName]
	 * 
	 * @param profileName
	 * @return
	 */
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}
	
	/**
	 * POST http://localhost:8080/messenger/webapi/profiles
	 * 
	 * @param profile
	 * @return
	 */
	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	/**
	 * PUT http://localhost:8080/messenger/webapi/profiles/[profileName]
	 * 
	 * @param profileName
	 * @param profile
	 * @return
	 */
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	/**
	 * DELETE http://localhost:8080/messenger/webapi/profiles/[profileName]
	 * 
	 * @param profileName
	 */
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}
}
