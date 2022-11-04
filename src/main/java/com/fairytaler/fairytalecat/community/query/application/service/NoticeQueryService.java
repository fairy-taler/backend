package com.fairytaler.fairytalecat.community.query.application.service;

import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import com.fairytaler.fairytalecat.community.query.application.dto.NoticeResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.dao.NoticeDAO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class NoticeQueryService {
    private NoticeDAO noticeDao;

    public NoticeQueryService(NoticeDAO noticeDao){
        this.noticeDao = noticeDao;
    }

    public NoticeResponseDTO selectNotice(int noticeCode){
        return new NoticeResponseDTO(noticeCode,"공지사항", "내용","Y", new Date());
    }

    public Optional<Notice> getNotice(Long noticeCode){
        /* 공지사항 조회 */
        Optional<Notice> notice = noticeDao.findByNoticeCode(noticeCode);
        if(notice == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 공지사항이 없습니다.");
        }
        return notice;
    }
}
