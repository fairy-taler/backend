package com.fairytaler.fairytalecat.community.query.application.service;

import com.fairytaler.fairytalecat.common.paging.Pagenation;
import com.fairytaler.fairytalecat.community.domain.model.Comment;
import com.fairytaler.fairytalecat.community.domain.model.Forum;
import com.fairytaler.fairytalecat.community.query.application.dao.CommentQueryDAO;
import com.fairytaler.fairytalecat.community.query.application.dao.ForumQueryDAO;
import com.fairytaler.fairytalecat.community.query.application.dto.CommentResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.dto.ForumResponseDTO;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        /* 게시판 조회 */
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
        forumResponseDTO.setCreateDate(forum.getCreateDate());
        forumResponseDTO.setMemberCode(forum.getMemberCode());
        /* 닉네임 조회 */
        String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
        forumResponseDTO.setNickname(nickname);

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
//    public Page<Forum> getForumListByCategoryWidthPaging(String category, Pageable pageable){
//        Page<Forum> forums = forumQueryDao.findByCategory(category,pageable);
//
//        if(forums == null){
////            throw new NoMemberException(); //예외 처리
//            System.out.println("해당 번호의 게시판이 없습니다.");
//        }
//        return forums;
//    }
//    public Page<Forum> getForumListByMemberCodeWidthPaging(String accessToken, Pageable pageable){
//        String memberCode = tokenProvider.getUserCode(accessToken);
//        Page<Forum> forums = forumQueryDao.findByMemberCode(memberCode,pageable);
//
//        if(forums == null){
////            throw new NoMemberException(); //예외 처리
//            System.out.println("해당 번호의 게시판이 없습니다.");
//        }
//        return forums;
//    }

    public Pagenation<ForumResponseDTO> getForumListWidthPaging2(Pageable pageable){
        Page<Forum> forums = forumQueryDao.findAll(pageable);
        List<ForumResponseDTO> forumResponseDTOList = new ArrayList<>();

        /* 원하는 데이터를 갖고 있는 DTO 리스트 만들기*/
        for(Forum forum : forums.getContent()){
            /* 엔티티에서 데이터 가져오와서 DTO 정보 저장*/
            ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
            forumResponseDTO.setTitle(forum.getTitle());
            forumResponseDTO.setCreateDate(forum.getCreateDate());
            forumResponseDTO.setCategory(forum.getCategory());
            forumResponseDTO.setForumCode(forum.getForumCode());

            /* 닉네임 가져오기 */
            String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
            forumResponseDTO.setNickname(nickname);
            forumResponseDTOList.add(forumResponseDTO);
            System.out.println(forumResponseDTO);
        }
        /* 페이지 네이션에 데이터 셋팅*/
        Pagenation<ForumResponseDTO> pages = new Pagenation<ForumResponseDTO>();
        pages.setTotalPages(forums.getTotalPages());
        pages.setTotalElement(forums.getTotalElements());
        pages.setSize(forums.getSize());
        pages.setNumber(forums.getNumber());
        pages.setContent(forumResponseDTOList);

        if(forums == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 게시판이 없습니다.");
        }
        return pages;
    }

    public Pagenation<ForumResponseDTO> getForumListByMemberCodeWidthPaging(String accessToken, Pageable pageable){
        String memberCode = tokenProvider.getUserCode(accessToken);
        Page<Forum> forums = forumQueryDao.findByMemberCode(memberCode,pageable);

        List<ForumResponseDTO> forumResponseDTOList = new ArrayList<>();
        /* 원하는 데이터를 갖고 있는 DTO 리스트 만들기*/
        for(Forum forum : forums.getContent()){
            /* 엔티티에서 데이터 가져오와서 DTO 정보 저장*/
            ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
            forumResponseDTO.setTitle(forum.getTitle());
            forumResponseDTO.setCreateDate(forum.getCreateDate());
            forumResponseDTO.setCategory(forum.getCategory());
            forumResponseDTO.setForumCode(forum.getForumCode());

            /* 닉네임 가져오기 */
            String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
            forumResponseDTO.setNickname(nickname);
            forumResponseDTOList.add(forumResponseDTO);
            System.out.println(forumResponseDTO);
        }
        /* 페이지 네이션에 데이터 셋팅*/
        Pagenation<ForumResponseDTO> pages = new Pagenation<ForumResponseDTO>();
        pages.setTotalPages(forums.getTotalPages());
        pages.setTotalElement(forums.getTotalElements());
        pages.setSize(forums.getSize());
        pages.setNumber(forums.getNumber());
        pages.setContent(forumResponseDTOList);

        if(forums == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 게시판이 없습니다.");
        }
        return pages;
    }

    public Pagenation<ForumResponseDTO>  getForumListByCategoryWidthPaging(String category, Pageable pageable){
        Page<Forum> forums = forumQueryDao.findByCategory(category,pageable);
        List<ForumResponseDTO> forumResponseDTOList = new ArrayList<>();

        /* 원하는 데이터를 갖고 있는 DTO 리스트 만들기*/
        for(Forum forum : forums.getContent()){
            /* 엔티티에서 데이터 가져오와서 DTO 정보 저장*/
            ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
            forumResponseDTO.setTitle(forum.getTitle());
            forumResponseDTO.setCreateDate(forum.getCreateDate());
            forumResponseDTO.setCategory(forum.getCategory());
            forumResponseDTO.setForumCode(forum.getForumCode());

            /* 닉네임 가져오기 */
            String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
            forumResponseDTO.setNickname(nickname);
            forumResponseDTOList.add(forumResponseDTO);
            System.out.println(forumResponseDTO);
        }
        /* 페이지 네이션에 데이터 셋팅*/
        Pagenation<ForumResponseDTO> pages = new Pagenation<ForumResponseDTO>();
        pages.setTotalPages(forums.getTotalPages());
        pages.setTotalElement(forums.getTotalElements());
        pages.setSize(forums.getSize());
        pages.setNumber(forums.getNumber());
        pages.setContent(forumResponseDTOList);

        if(forums == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 게시판이 없습니다.");
        }
        return pages;
    }

}
