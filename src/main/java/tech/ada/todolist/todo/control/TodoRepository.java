package tech.ada.todolist.todo.control;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.todolist.todo.entity.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    //JPA Query Method -> metodo criado pelo Spring Data JPA
    //List<Todo> findByDescricaoContaining(String descricao);

    //Query native
    //@Query(value= "SELECT * FROM TODOSCHEMA.todo t WHERE t.desc LIKE %:descricao%", nativeQuery = true)
    //List<Todo> pegaTodosPorDescricao(String descricao);

    //JPQL query-> Java Persistence Query Language
    //@Query("select t from Todo t where t.descricao like %:desc%")
    //List<Todo> pegaTodosPorDescricao(String desc);

    //JPQL query-> Java Persistence Query Language com bind parameter usando ordem dos argumentos
    //@Query("select t from Todo t where t.descricao like %?1%")
    //List<Todo> pegaTodosPorDescricao(String descricao);

    //JPQL query-> Java Persistence Query Language com bind parameter usando @Param
    @Query("select t from Todo t where t.descricao like %:desc%")
    List<Todo> pegaTodosPorDescricao(@Param("desc") String descricao);



    //List<Todo> findDistinctByDescricaoAndConcluido(String descricao, Boolean concluido);

    //List findByPrecoLessThan(23.5);

}
