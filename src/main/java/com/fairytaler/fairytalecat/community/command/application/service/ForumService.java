package com.fairytaler.fairytalecat.community.command.application.service;

import com.fairytaler.fairytalecat.community.command.application.dao.CommentDAO;
import com.fairytaler.fairytalecat.community.command.application.dao.ForumDAO;
import com.fairytaler.fairytalecat.community.command.application.dto.CommentRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.ForumRequestDTO;
import com.fairytaler.fairytalecat.community.domain.model.Comment;
import com.fairytaler.fairytalecat.community.domain.model.Forum;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    static private ForumDAO forumDAO;
    static private CommentDAO commentDAO;
    static private TokenProvider tokenProvider;

    public ForumService(ForumDAO forumDAO, TokenProvider tokenProvider, CommentDAO commentDAO){
        this.forumDAO = forumDAO;
        this.tokenProvider=tokenProvider;
        this.commentDAO = commentDAO;
    }

    public Forum insertForum(String accessToken, ForumRequestDTO forumRequestDTO){
        /* 데이터 생성 */
        Forum forum = new Forum();
        forum.setTitle(forumRequestDTO.getTitle());
        forum.setContent(forumRequestDTO.getContent());
        forum.setCategory(forumRequestDTO.getCategory());
        forum.setCreateDate(new Date());

        /* 사용자 정보 (작성자) 가져와서 넣기 */
        String memberCode = tokenProvider.getUserCode(accessToken);
        forum.setMemberCode(memberCode);

        forumDAO.save(forum);

        return forum;
    }

    public String insertComment(String accessToken, CommentRequestDTO commentRequestDTO){

        /* 데이터 생성 */
        Comment comment = new Comment();

        comment.setContent(commentRequestDTO.getContent());
        comment.setCreateDate(new Date());
        comment.setMemberCode(tokenProvider.getUserCode(accessToken));
        comment.setForumCode(commentRequestDTO.getForumCode());

        /* 사용자 정보 (작성자) 가져와서 넣기 */
        comment.setMemberCode(tokenProvider.getUserCode(accessToken));
        System.out.println(comment.getMemberCode());
        commentDAO.save(comment);

        return commentRequestDTO.getContent();
    }

    public Forum updateForum(String accessToken, ForumRequestDTO forumRequestDTO){
        Optional<Forum> oForum = forumDAO.findById(forumRequestDTO.getForumCode());

        /* 데이터 삽입 */
        try{
            Forum forum = oForum.get();
            if(tokenProvider.getUserCode(accessToken) != forum.getMemberCode()){
                throw new Exception();
            }
            forum.setTitle(forumRequestDTO.getTitle());
            forum.setContent(forumRequestDTO.getContent());

            forumDAO.save(forum);
            return forum;
        }
        catch (Exception exception){
            return null;
        }
    }
    public Comment updateComment(String accessToken, CommentRequestDTO commentRequestDTO){
        Optional<Comment> oComment = commentDAO.findById(commentRequestDTO.getCommentCode());

        /* 데이터 삽입 */
        try{
            Comment comment = oComment.get();
            if(tokenProvider.getUserCode(accessToken) != comment.getMemberCode()){
                throw new Exception();
            }
            comment.setContent(commentRequestDTO.getContent());

            commentDAO.save(comment);
            return comment;
        }
        catch (Exception exception){
            return null;
        }
    }
    public Long deleteComment(String accessToken, Long commentCode){
        Optional<Comment> oComment = commentDAO.findById(commentCode);
        if(oComment.isPresent()) {
            commentDAO.delete(oComment.get());
            return commentCode;
        }
        return null;
    }
    public Long deleteForum(String accessToken, Long forumCode){

        List<Comment> commentList = commentDAO.findByForumCode(forumCode);
        for(Comment comment : commentList){
            commentDAO.delete(comment);
        }
        /* 게시글 삭제 */
        Optional<Forum> oForum = forumDAO.findById(forumCode);
        if(oForum.isPresent()) {
            forumDAO.delete(oForum.get());
            return forumCode;
        }

        return null;
    }
}
