package com.fairytaler.fairytalecat.community.query.application.service;

import com.fairytaler.fairytalecat.common.paging.Pagenation;
import com.fairytaler.fairytalecat.community.domain.model.Comment;
import com.fairytaler.fairytalecat.community.domain.model.Forum;
import com.fairytaler.fairytalecat.community.domain.model.Notice;
import com.fairytaler.fairytalecat.community.query.application.dao.CommentQueryDAO;
import com.fairytaler.fairytalecat.community.query.application.dao.ForumQueryDAO;
import com.fairytaler.fairytalecat.community.query.application.dto.CommentResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.dto.ForumResponseDTO;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.ProfileRepository;
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

    static private ProfileRepository profileRepository;
    static private TokenProvider tokenProvider;
    public ForumQueryService(ForumQueryDAO forumQueryDao, CommentQueryDAO commentQueryDAO, MemberInfoRepository memberInfoRepository,
                             TokenProvider tokenProvider, ProfileRepository profileRepository){
        this.forumQueryDao = forumQueryDao;
        this.commentQueryDAO = commentQueryDAO;
        this.memberInfoRepository = memberInfoRepository;
        this.tokenProvider = tokenProvider;
        this.profileRepository = profileRepository;
    }
    public ForumResponseDTO getForum(String accessToken, Long forumCode){
        /* ????????? ?????? */
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
        /* ????????? ?????? */
        Member writer = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode()));
        String memberCode = tokenProvider.getUserCode(accessToken);
        forumResponseDTO.setProfileUrl(profileRepository.findByMemberCode(writer.getMemberCode()).getImgUrl());
        forumResponseDTO.setNickname(writer.getNickname());
        if(Long.parseLong(memberCode) == writer.getMemberCode()){
            forumResponseDTO.setMyForum(true);
        }
        else{
            forumResponseDTO.setMyForum(false);
        }
        /* ?????? ????????? ?????? */
        String memberId = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getMemberId();
        forumResponseDTO.setMemberId(memberId);

        return forumResponseDTO;
}
    public List<CommentResponseDTO> getCommentInForum(String accessToken, Long forumCode){
        List<Comment> comments = commentQueryDAO.findByForumCode(forumCode);
        List<CommentResponseDTO> commentDTOList = new ArrayList<>();

        for(Comment comment : comments){
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
            commentResponseDTO.setContent(comment.getContent());
            commentResponseDTO.setCreateDate(comment.getCreateDate());
            /* ????????? ???????????? ?????? */
            Member commenter = memberInfoRepository.findByMemberCode(Long.parseLong(comment.getMemberCode()));
            String memberCode = tokenProvider.getUserCode(accessToken);
            commentResponseDTO.setNickname(commenter.getNickname());
            commentResponseDTO.setMemberId(commenter.getMemberId());
            commentResponseDTO.setProfileUrl(profileRepository.findByMemberCode(commenter.getMemberCode()).getImgUrl());
            if(Long.parseLong(memberCode) == commenter.getMemberCode()){
                commentResponseDTO.setMyComment(true);
            }
            else{
                commentResponseDTO.setMyComment(false);
            }
            commentDTOList.add(commentResponseDTO);
        }
        return commentDTOList;
    }
    public Page<Forum> getForumListWidthPaging(Pageable pageable){
        Page<Forum> forums = forumQueryDao.findAll(pageable);

        if(forums == null){
//            throw new NoMemberException(); //?????? ??????
            System.out.println("?????? ????????? ???????????? ????????????.");
        }
        return forums;
    }
//    public Page<Forum> getForumListByCategoryWidthPaging(String category, Pageable pageable){
//        Page<Forum> forums = forumQueryDao.findByCategory(category,pageable);
//
//        if(forums == null){
////            throw new NoMemberException(); //?????? ??????
//            System.out.println("?????? ????????? ???????????? ????????????.");
//        }
//        return forums;
//    }
//    public Page<Forum> getForumListByMemberCodeWidthPaging(String accessToken, Pageable pageable){
//        String memberCode = tokenProvider.getUserCode(accessToken);
//        Page<Forum> forums = forumQueryDao.findByMemberCode(memberCode,pageable);
//
//        if(forums == null){
////            throw new NoMemberException(); //?????? ??????
//            System.out.println("?????? ????????? ???????????? ????????????.");
//        }
//        return forums;
//    }

    public Pagenation<ForumResponseDTO> getForumListWidthPaging2(Pageable pageable){
        Page<Forum> forums = forumQueryDao.findAll(pageable);
        List<ForumResponseDTO> forumResponseDTOList = new ArrayList<>();

        /* ????????? ???????????? ?????? ?????? DTO ????????? ?????????*/
        for(Forum forum : forums.getContent()){
            /* ??????????????? ????????? ??????????????? DTO ?????? ??????*/
            ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
            forumResponseDTO.setTitle(forum.getTitle());
            forumResponseDTO.setCreateDate(forum.getCreateDate());
            forumResponseDTO.setCategory(forum.getCategory());
            forumResponseDTO.setForumCode(forum.getForumCode());
            forumResponseDTO.setMemberCode(forum.getMemberCode());

            /* ????????? ???????????? */
            String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
            forumResponseDTO.setNickname(nickname);
            forumResponseDTOList.add(forumResponseDTO);

            /* ?????? ????????? ?????? */
            String memberId = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getMemberId();
            forumResponseDTO.setMemberId(memberId);


        }
        /* ????????? ???????????? ????????? ??????*/
        Pagenation<ForumResponseDTO> pages = new Pagenation<ForumResponseDTO>();
        pages.setTotalPages(forums.getTotalPages());
        pages.setTotalElement(forums.getTotalElements());
        pages.setSize(forums.getSize());
        pages.setNumber(forums.getNumber());
        pages.setContent(forumResponseDTOList);

        if(forums == null){
//            throw new NoMemberException(); //?????? ??????
            System.out.println("?????? ????????? ???????????? ????????????.");
        }
        return pages;
    }

    public Pagenation<ForumResponseDTO> getForumListByMemberCodeWidthPaging(String accessToken, Pageable pageable){
        String memberCode = tokenProvider.getUserCode(accessToken);
        Page<Forum> forums = forumQueryDao.findByMemberCode(memberCode,pageable);

        List<ForumResponseDTO> forumResponseDTOList = new ArrayList<>();
        /* ????????? ???????????? ?????? ?????? DTO ????????? ?????????*/
        for(Forum forum : forums.getContent()){
            /* ??????????????? ????????? ??????????????? DTO ?????? ??????*/
            ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
            forumResponseDTO.setTitle(forum.getTitle());
            forumResponseDTO.setCreateDate(forum.getCreateDate());
            forumResponseDTO.setCategory(forum.getCategory());
            forumResponseDTO.setForumCode(forum.getForumCode());

            /* ????????? ???????????? */
            String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
            forumResponseDTO.setNickname(nickname);
            forumResponseDTOList.add(forumResponseDTO);
            System.out.println(forumResponseDTO);
            /* ?????? ????????? ?????? */
            String memberId = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getMemberId();
            forumResponseDTO.setMemberId(memberId);
        }
        /* ????????? ???????????? ????????? ??????*/
        Pagenation<ForumResponseDTO> pages = new Pagenation<ForumResponseDTO>();
        pages.setTotalPages(forums.getTotalPages());
        pages.setTotalElement(forums.getTotalElements());
        pages.setSize(forums.getSize());
        pages.setNumber(forums.getNumber());
        pages.setContent(forumResponseDTOList);

        if(forums == null){
//            throw new NoMemberException(); //?????? ??????
            System.out.println("?????? ????????? ???????????? ????????????.");
        }
        return pages;
    }

    public Pagenation<ForumResponseDTO>  getForumListByCategoryWidthPaging(String category, Pageable pageable){
        Page<Forum> forums = forumQueryDao.findByCategory(category,pageable);
        List<ForumResponseDTO> forumResponseDTOList = new ArrayList<>();

        /* ????????? ???????????? ?????? ?????? DTO ????????? ?????????*/
        for(Forum forum : forums.getContent()){
            /* ??????????????? ????????? ??????????????? DTO ?????? ??????*/
            ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
            forumResponseDTO.setTitle(forum.getTitle());
            forumResponseDTO.setCreateDate(forum.getCreateDate());
            forumResponseDTO.setCategory(forum.getCategory());
            forumResponseDTO.setForumCode(forum.getForumCode());

            /* ????????? ???????????? */
            String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
            forumResponseDTO.setNickname(nickname);
            forumResponseDTOList.add(forumResponseDTO);
            System.out.println(forumResponseDTO);

            /* ?????? ????????? ?????? */
            String memberId = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getMemberId();
            forumResponseDTO.setMemberId(memberId);
        }
        /* ????????? ???????????? ????????? ??????*/
        Pagenation<ForumResponseDTO> pages = new Pagenation<ForumResponseDTO>();
        pages.setTotalPages(forums.getTotalPages());
        pages.setTotalElement(forums.getTotalElements());
        pages.setSize(forums.getSize());
        pages.setNumber(forums.getNumber());
        pages.setContent(forumResponseDTOList);

        if(forums == null){
//            throw new NoMemberException(); //?????? ??????
            System.out.println("?????? ????????? ???????????? ????????????.");
        }
        return pages;
    }

    public Pagenation<ForumResponseDTO> searchForumByTitle(String title,Pageable pageable){
        Page<Forum> forums = forumQueryDao.findByTitleContaining(title, pageable);
        List<ForumResponseDTO> forumResponseDTOList = new ArrayList<>();

        /* ????????? ???????????? ?????? ?????? DTO ????????? ?????????*/
        for(Forum forum : forums.getContent()){
            /* ??????????????? ????????? ??????????????? DTO ?????? ??????*/
            ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
            forumResponseDTO.setTitle(forum.getTitle());
            forumResponseDTO.setCreateDate(forum.getCreateDate());
            forumResponseDTO.setCategory(forum.getCategory());
            forumResponseDTO.setForumCode(forum.getForumCode());
            forumResponseDTO.setMemberCode(forum.getMemberCode());

            /* ????????? ???????????? */
            String nickname = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getNickname();
            forumResponseDTO.setNickname(nickname);
            forumResponseDTOList.add(forumResponseDTO);

            /* ?????? ????????? ?????? */
            String memberId = memberInfoRepository.findByMemberCode(Long.parseLong(forum.getMemberCode())).getMemberId();
            forumResponseDTO.setMemberId(memberId);


        }
        /* ????????? ???????????? ????????? ??????*/
        Pagenation<ForumResponseDTO> pages = new Pagenation<ForumResponseDTO>();
        pages.setTotalPages(forums.getTotalPages());
        pages.setTotalElement(forums.getTotalElements());
        pages.setSize(forums.getSize());
        pages.setNumber(forums.getNumber());
        pages.setContent(forumResponseDTOList);

        if(forums == null){
//            throw new NoMemberException(); //?????? ??????
            System.out.println("?????? ????????? ???????????? ????????????.");
        }
        return pages;
    }

}
