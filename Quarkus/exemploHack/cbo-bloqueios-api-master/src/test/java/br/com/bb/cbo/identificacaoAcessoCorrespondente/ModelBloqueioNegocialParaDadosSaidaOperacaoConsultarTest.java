package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.time.LocalDate;

import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelBloqueioNegocialParaDadosSaidaOperacaoConsultarTest {
    @Test
    public void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();

        tester.addMapping(ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar.class,
        		new ValueBuilder<ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar>() {
            public ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar build() {
            	
            	ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar modelBloqueioNegocialParaDadosSaidaOperacaoConsultar =
            			new ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar();
            	
            	modelBloqueioNegocialParaDadosSaidaOperacaoConsultar = 
            			new ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar(
            					1, "", 1, "", "", null, null, null, null, 1, 1L, "", 1, "", 1, "");
            	
                return modelBloqueioNegocialParaDadosSaidaOperacaoConsultar;
            }
        });
        tester.setTypeExclusions(LocalDate.class);
        tester.testAll(ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar.class);
    }
}
