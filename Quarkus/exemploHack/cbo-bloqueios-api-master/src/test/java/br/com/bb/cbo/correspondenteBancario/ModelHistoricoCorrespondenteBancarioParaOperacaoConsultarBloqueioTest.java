package br.com.bb.cbo.correspondenteBancario;

import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueioTest {

	@Test
	public void testProperties() throws Exception {
		PropertiesTester tester = new PropertiesTester();

		tester.addMapping(ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio.class,
				new ValueBuilder<ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio>() {
			public ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio build() {
				
				ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio
				historicoCorrespondenteBancarioParaOperacaoConsultarBloqueio =
						new ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio();
				
				historicoCorrespondenteBancarioParaOperacaoConsultarBloqueio =
						new ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio(1, 1, 0);
				
				return historicoCorrespondenteBancarioParaOperacaoConsultarBloqueio;
			}
		});
		tester.testAll(ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio.class);
	}
}
