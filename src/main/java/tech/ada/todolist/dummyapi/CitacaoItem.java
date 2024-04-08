package tech.ada.todolist.dummyapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitacaoItem {
    private Long id;
    private String quote;
    private String author;
}
