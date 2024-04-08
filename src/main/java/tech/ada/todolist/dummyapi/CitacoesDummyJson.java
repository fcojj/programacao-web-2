package tech.ada.todolist.dummyapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CitacoesDummyJson {

    private List<CitacaoItem> quotes;
    private Long total;
    private Long skip;
    private Long limit;
}
