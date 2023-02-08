package br.com.bb.cbo.resources;

import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.bb.iib.curio.utils.exceptions.CurioNegocioException;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.requisicao.DadosRequisicaoConsultaDadosBasicosCodigoCliente;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.resposta.Controle;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.resposta.DadosRespostaConsultaDadosBasicosCodigoCliente;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.resposta.Saida;
import io.quarkus.test.Mock;

@Mock
@RequestScoped
@RestClient
public class MockInterfaceConsumidor implements InterfaceConsumidor {

	@Override
	public DadosRespostaConsultaDadosBasicosCodigoCliente executarOperacao(DadosRequisicaoConsultaDadosBasicosCodigoCliente requisicao) 
			throws CurioNegocioException {
		DadosRespostaConsultaDadosBasicosCodigoCliente resposta = new DadosRespostaConsultaDadosBasicosCodigoCliente();
		
		Controle controle = new Controle();
		
		Saida saida = new Saida();
		saida.setNome("NOME DO OPERADOR");
		saida.setCpfCgc(12345678911L);
		
		resposta.setControle(controle);
		resposta.setSaida(saida);
		
		return resposta;
	}
}
