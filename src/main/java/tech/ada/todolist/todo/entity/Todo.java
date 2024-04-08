package tech.ada.todolist.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desc", nullable = false)
    private String descricao;

    @Column
    private String titulo;

    @Column
    private String prioridade;

    @Column(nullable = false)
    private Boolean concluido;
}
