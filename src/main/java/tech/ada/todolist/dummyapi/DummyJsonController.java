package tech.ada.todolist.dummyapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequestMapping("/v1/")
@RestController
@RequiredArgsConstructor
public class DummyJsonController {

    private final CitacoesService citacoesService;

    @GetMapping("/citacoes")
    public List<CitacaoItemDto> getCitacoes(){

        var respostaDto = citacoesService.pegarCitacoes();

        return respostaDto;
    }

}
