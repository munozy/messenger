package org.munozy.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.munozy.messenger.database.util.URIUtil;
import org.munozy.messenger.model.Message;
import org.munozy.messenger.resources.beans.MessageFilterBean;
import org.munozy.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	/**
	 * GET http://localhost:8080/messenger/webapi/messages?year=[value] or GET
	 * http://localhost:8080/messenger/webapi/messages?start=[value]&size=[value] or
	 * GET http://localhost:8080/messenger/webapi/messages
	 * 
	 * HEADERS: Accept: application/json
	 * 
	 * @param messageFilterBean
	 * @return
	 */
	@GET
	public List<Message> getJSONMessages(@BeanParam MessageFilterBean messageFilterBean) {
		return getMessages(messageFilterBean);
	}
	
	/**
	 * GET http://localhost:8080/messenger/webapi/messages?year=[value] or GET
	 * http://localhost:8080/messenger/webapi/messages?start=[value]&size=[value] or
	 * GET http://localhost:8080/messenger/webapi/messages
	 * 
	 * HEADERS: Accept: txt/xml
	 * 
	 * @param messageFilterBean
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessages(@BeanParam MessageFilterBean messageFilterBean) {
		return getMessages(messageFilterBean);
	}
	
	private List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
		if (messageFilterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(messageFilterBean.getYear());
		} else if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());

		}
		return messageService.getAllMessages();
	}

	/**
	 * GET http://localhost:8080/messenger/webapi/messages/[messageId]
	 * 
	 * HEADERS: Accept: application/json
	 * 
	 * @param messageId
	 * @param uriInfo
	 * 
	 * @return
	 */
	@GET
	@Path("/{messageId}")
	public Message getJSONMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		return getMessage(messageId, uriInfo);
	}
	
	/**
	 * GET http://localhost:8080/messenger/webapi/messages/[messageId]
	 * 
	 * HEADERS: Accept: txt/xml
	 * 
	 * @param messageId
	 * @param uriInfo
	 * 
	 * @return
	 */
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.TEXT_XML)
	public Message getXMLMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		return getMessage(messageId, uriInfo);
	}
	
	private Message getMessage(long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLink(URIUtil.getUriForSelf(uriInfo, message), "self");
		message.addLink(URIUtil.getUriForProfile(uriInfo, message), "profile");
		message.addLink(URIUtil.getUriForComments(uriInfo, message), "comments");

		return message;

	}

	/**
	 * POST http://localhost:8080/messenger/webapi/messages
	 * 
	 * HEADERS: Content-Type: application/json
	 * 
	 * @param message
	 * @param uriInfo
	 * @return
	 */
	@POST
	public Response addJSONMessage(Message message, @Context UriInfo uriInfo) {
		return addMessage(message, uriInfo);
	}
	
	/**
	 * POST http://localhost:8080/messenger/webapi/messages
	 * HEADERS: Content-Type: txt/xml
	 * 
	 * @param message
	 * @param uriInfo
	 * @return
	 */
	@POST
	@Consumes(MediaType.TEXT_XML)
	public Response addXMLMessage(Message message, @Context UriInfo uriInfo) {
		return addMessage(message, uriInfo);
	}
	
	private Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		// Sending Status Code (201 - Create new resource) and
		// Location (http://localhost:8080/messenger/webapi/messages/[id]) Header
		return Response.created(uri).entity(newMessage).build();
	}

	/**
	 * PUT http://localhost:8080/messenger/webapi/messages/[messageId]
	 * 
	 * @param messageId
	 * @param message
	 * @return
	 */
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	/**
	 * DELETE http://localhost:8080/messenger/webapi/messages/[messageId]
	 * 
	 * @param id
	 */
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}

	/**
	 * http://localhost:8080/messenger/webapi/messages/[messageId]/comments
	 * 
	 * @return
	 */
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
