package org.munozy.messenger.resources;

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
import javax.ws.rs.core.MediaType;

import org.munozy.messenger.model.Message;
import org.munozy.messenger.resources.beans.MessageFilterBean;
import org.munozy.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	/**
	 * GET http://localhost:8080/messenger/webapi/messages?year=[value] 
	 * or 
	 * GET http://localhost:8080/messenger/webapi/messages?start=[value]&size=[value] 
	 * or
	 * GET http://localhost:8080/messenger/webapi/messages
	 * 
	 * @param messageFilterBean
	 * @return
	 */
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
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
	 * @param messageId
	 * @return
	 */
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId) {
		return messageService.getMessage(messageId);
	}

	/**
	 * POST http://localhost:8080/messenger/webapi/messages
	 * 
	 * @param message
	 * @return
	 */
	@POST
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
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
