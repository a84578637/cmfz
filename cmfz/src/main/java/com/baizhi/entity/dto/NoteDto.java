package com.baizhi.entity.dto;

import com.baizhi.entity.fNote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private Integer total;
    private List<fNote> rows;
}
