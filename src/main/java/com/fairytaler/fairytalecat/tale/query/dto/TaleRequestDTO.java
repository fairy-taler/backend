package com.fairytaler.fairytalecat.tale.query.dto;

import com.fairytaler.fairytalecat.tale.domain.model.TalePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaleRequestDTO {
    private String title;
<<<<<<< HEAD
    private List<TalePage> pages;
=======
    private String createAt;
    private List<TalePage> pages;

>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
    public TaleRequestDTO() {}

}
