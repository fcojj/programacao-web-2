package tech.ada.todolist.dummyapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitacoesService {
    private final CitacoesDummyJsonApi citacoesDummyJsonApi;



    public List<CitacaoItemDto>  pegarCitacoes() {

        CitacoesDummyJson resposta = citacoesDummyJsonApi.pegarCitacoes(30L,0L);

        List<CitacaoItemDto> listaCitacaoItemDto = resposta.getQuotes().stream().map(quote -> new CitacaoItemDto(quote.getQuote(),quote.getAuthor())).toList();

        return listaCitacaoItemDto;
   }
}
