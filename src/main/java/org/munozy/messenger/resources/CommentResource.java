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

import org.munozy.messenger.model.Comment;
import org.munozy.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = new CommentService();

	/**
	 * GET http://localhost:8080/messenger/webapi/messages/[messageId]/comments
	 * 
	 * @param messageId
	 * @return
	 */
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	/**
	 * GET http://localhost:8080/messenger/webapi/messages/[messageId]/comments/[commentId]
	 * 
	 * @param messageId
	 * @param commentId
	 */
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}

	/**
	 * POST http://localhost:8080/messenger/webapi/messages/[messageId]/comments	
	 * 
	 * @param messageId
	 * @param comment
	 * @return
	 */
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}

	/**
	 * PUT http://localhost:8080/messenger/webapi/messages/[messageId]/comments/[commentId]
	 * 
	 * @param messageId
	 * @param id
	 * @param comment
	 * @return
	 */
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId,
			Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	/**
	 * DELETE http://localhost:8080/messenger/webapi/messages/[messageId]/comments/[commentId]
	 * @param messageId
	 * @param commentId
	 */
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}
}
