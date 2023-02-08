package br.com.bb.cbo.correspondenteBancario;

import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioTest {

	@Test
	public void testProperties() throws Exception {
		PropertiesTester tester = new PropertiesTester();

		tester.addMapping(ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio.class,
				new ValueBuilder<ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio>() {
			public ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio build() {
				
				ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio correspondenteBancarioParaOperacaoConsultarBloqueio =
						new ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio();
				
				correspondenteBancarioParaOperacaoConsultarBloqueio = new ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio(
						202, 22, "TESTE");
				
				return correspondenteBancarioParaOperacaoConsultarBloqueio;
			}
		});
		tester.testAll(ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio.class);
	}
}
