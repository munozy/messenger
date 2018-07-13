package org.munozy.messenger.database.util;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import org.munozy.messenger.model.Message;
import org.munozy.messenger.resources.CommentResource;
import org.munozy.messenger.resources.MessageResource;
import org.munozy.messenger.resources.ProfileResource;

public class URIUtil {
	private URIUtil() {	}
	
	public static String getUriForComments(UriInfo uriInfo, Message message) {
		// http://localhost:8080/messenger/webapi + /messages + /[messageId] + /comments
		URI uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource").path(CommentResource.class)
				.resolveTemplate("messageId", message.getId()).build();
		return uri.toString();
	}

	public static  String getUriForProfile(UriInfo uriInfo, Message message) {
		// http://localhost:8080/messenger/webapi + /profiles + /[messageAuthor]
		URI uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build();
		return uri.toString();
	}

	public static String getUriForSelf(UriInfo uriInfo, Message message) {
		// http://localhost:8080/messenger/webapi + /messages + /[messageId]
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId()))
				.build().toString();
		return uri;
	}
}
