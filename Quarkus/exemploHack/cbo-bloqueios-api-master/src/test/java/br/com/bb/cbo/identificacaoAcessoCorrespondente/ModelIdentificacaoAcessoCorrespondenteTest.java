package br.com.bb.cbo.identificacaoAcessoCorrespondente;
import java.time.LocalDate;
import java.time.LocalTime;

import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelIdentificacaoAcessoCorrespondenteTest {
    @Test
    public void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(ModelIdentificacaoAcessoCorrespondente.class, new ValueBuilder<ModelIdentificacaoAcessoCorrespondente>() {
            public ModelIdentificacaoAcessoCorrespondente build() {
            	
            	ModelIdentificacaoAcessoCorrespondente modelIdentificacaoAcessoCorrespondente =
            			new ModelIdentificacaoAcessoCorrespondente(
            					1, "", 1, 1, 1, "", null, null, 1, 1, 1, null, null, null, null, null);
            	
            	modelIdentificacaoAcessoCorrespondente = new ModelIdentificacaoAcessoCorrespondente();
                
                return modelIdentificacaoAcessoCorrespondente;
            }
        });
        tester.setTypeExclusions(LocalDate.class, LocalTime.class);
        tester.testAll(ModelIdentificacaoAcessoCorrespondente.class);
    }
}
