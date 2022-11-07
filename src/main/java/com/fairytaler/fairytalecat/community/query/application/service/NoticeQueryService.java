package com.fairytaler.fairytalecat.community.query.application.service;

import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import com.fairytaler.fairytalecat.community.query.application.dao.NoticeQueryDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoticeQueryService {
<<<<<<< HEAD
    private NoticeQueryDAO noticeQueryDao;

    public NoticeQueryService(NoticeQueryDAO noticeQueryDao){
        this.noticeQueryDao = noticeQueryDao;
    }
    public Optional<Notice> getNotice(Long noticeCode){
        /* 공지사항 조회 */
        Optional<Notice> notice = noticeQueryDao.findByNoticeCode(noticeCode);
=======
    private NoticeQueryDAO noticeDao;

    public NoticeQueryService(NoticeQueryDAO noticeDao){
        this.noticeDao = noticeDao;
    }
    public Optional<Notice> getNotice(Long noticeCode){
        /* 공지사항 조회 */
        Optional<Notice> notice = noticeDao.findByNoticeCode(noticeCode);
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
        if(notice == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 공지사항이 없습니다.");
        }
        return notice;
    }

    public Page<Notice> getNoticeListWidthPaging(Pageable pageable){
<<<<<<< HEAD
        Page<Notice> notice = noticeQueryDao.findAll(pageable);
=======
        Page<Notice> notice = noticeDao.findAll(pageable);
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9

        if(notice == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 공지사항이 없습니다.");
        }
        return notice;
    }
}
