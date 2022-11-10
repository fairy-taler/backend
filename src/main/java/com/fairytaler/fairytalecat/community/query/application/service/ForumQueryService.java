package com.fairytaler.fairytalecat.community.query.application.service;

import com.fairytaler.fairytalecat.community.command.domain.model.Comment;
import com.fairytaler.fairytalecat.community.command.domain.model.Forum;
import com.fairytaler.fairytalecat.community.command.domain.model.Inquiry;
import com.fairytaler.fairytalecat.community.query.application.dao.CommentQueryDAO;
import com.fairytaler.fairytalecat.community.query.application.dao.ForumQueryDAO;
import com.fairytaler.fairytalecat.community.query.application.dto.CommentResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.dto.ForumResponseDTO;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.tale.domain.model.TTSTalePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForumQueryService {

    static private ForumQueryDAO forumQueryDao;
    static private CommentQueryDAO commentQueryDAO;
    static private MemberInfoRepository memberInfoRepository;

    static private TokenProvider tokenProvider;
    public ForumQueryService(ForumQueryDAO forumQueryDao, CommentQueryDAO commentQueryDAO, MemberInfoRepository memberInfoRepository,
                             TokenProvider tokenProvider){
        this.forumQueryDao = forumQueryDao;
        this.commentQueryDAO = commentQueryDAO;
        this.memberInfoRepository = memberInfoRepository;
        this.tokenProvider = tokenProvider;
    }

    public ForumResponseDTO getForum(Long forumCode){
        /* 공지사항 조회 */
        Optional<Forum> oForum = forumQueryDao.findById(forumCode);
        Forum forum;
        try{
            forum = oForum.get();
        }catch(Exception exception){
            return null;
         }
        ForumResponseDTO forumResponseDTO = new ForumResponseDTO();

        forumResponseDTO.setForumCode(forum.getForumCode());
        forumResponseDTO.setTitle(forum.getTitle());
        forumResponseDTO.setContent(forum.getContent());
        forumResponseDTO.setMemberCode(forum.getMemberCode());

        return forumResponseDTO;
    }

    public List<CommentResponseDTO> getCommentInForum(Long forumCode){
        List<Comment> comments = commentQueryDAO.findByForumCode(forumCode);
        List<CommentResponseDTO> commentDTOList = new ArrayList<>();

        for(Comment comment : comments){
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
            commentResponseDTO.setContent(comment.getContent());
            commentResponseDTO.setCreateDate(comment.getCreateDate());
            /* 닉네임 검색해서 넣기 */
            Member member = memberInfoRepository.findByMemberCode(Long.parseLong(comment.getMemberCode()));
            commentResponseDTO.setNickname(member.getNickname());

            commentDTOList.add(commentResponseDTO);
        }
        return commentDTOList;
    }
    public Page<Forum> getForumListWidthPaging(Pageable pageable){
        Page<Forum> forums = forumQueryDao.findAll(pageable);

        if(forums == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 게시판이 없습니다.");
        }
        return forums;
    }
    public Page<Forum> getForumListByCategoryWidthPaging(String category, Pageable pageable){
        Page<Forum> forums = forumQueryDao.findByCategory(category,pageable);

        if(forums == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 게시판이 없습니다.");
        }
        return forums;
    }
    public Page<Forum> getForumListByMemberCodeWidthPaging(String accessToken, Pageable pageable){
        String memberCode = tokenProvider.getUserCode(accessToken);
        Page<Forum> forums = forumQueryDao.findByMemberCode(memberCode,pageable);

        if(forums == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 게시판이 없습니다.");
        }
        return forums;
    }


}
