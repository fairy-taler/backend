package com.fairytaler.fairytalecat.community.command.application.service;

import com.fairytaler.fairytalecat.community.command.application.dao.NoticeDAO;
import com.fairytaler.fairytalecat.community.command.application.dto.NoticeRequestDTO;
import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import com.fairytaler.fairytalecat.community.query.application.dao.NoticeQueryDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class NoticeService {

    NoticeDAO noticeDAO;

    public NoticeService(NoticeDAO noticeDAO){
        this.noticeDAO = noticeDAO;
    }

    @Transactional
    public Long registNotice(NoticeRequestDTO noticeRequestDTO) {
        /* 데이터 생성 */
        Notice notice = new Notice();
        notice.setTitle(noticeRequestDTO.getTitle());
        notice.setContent(noticeRequestDTO.getContent());
        notice.setCreateDate(new Date());
        notice.setPublic(true);

        noticeDAO.save(notice);

        return notice.getNoticeCode();
    }
    public Notice updateNotice(NoticeRequestDTO noticeRequestDTO){
        Optional<Notice> oNotice = noticeDAO.findById(noticeRequestDTO.getNoticeCode());

        /* 데이터 삽입 */
        Notice notice = oNotice.get();

        notice.setTitle(noticeRequestDTO.getTitle());
        notice.setContent(noticeRequestDTO.getContent());

        noticeDAO.save(notice);

        return notice;
    }
    public Notice updateNoticeToPublic(Long noticeCode, boolean isPublic){
        Optional<Notice> oNotice = noticeDAO.findById(noticeCode);

        /* 데이터 삽입 */
        Notice notice = oNotice.get();

        notice.setPublic(isPublic);

        noticeDAO.save(notice);

        return notice;
    }
    public Long deleteNotice(Long noticeCode){
        Optional<Notice> oNotice = noticeDAO.findById(noticeCode);
        if(oNotice.isPresent()) {
            noticeDAO.delete(oNotice.get());
<<<<<<< HEAD
<<<<<<< HEAD
            return noticeCode;
        }

        return null;
=======
        }

        return noticeCode;
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
=======
        }

        return noticeCode;
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
    }
}
