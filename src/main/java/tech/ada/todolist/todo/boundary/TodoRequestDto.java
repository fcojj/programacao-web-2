package tech.ada.todolist.todo.boundary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoRequestDto {

    @NotNull(message="descricao é obrigatorio")
    private String descricao; // -> comprar massa para pastel

    @NotBlank(message="titulo é obrigatorio")
    private String titulo;// -> Coisas que preciso fazer hoje

    @NotNull(message="prioridade é obrigatorios")
    private PrioridadeEnum prioridade; // -> "BAIXO" BAIXINHO

    @NotNull(message = "concluido é obrigatório")
    private Boolean concluido; // -> false
}
