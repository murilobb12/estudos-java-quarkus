package br.com.bb.cbo.identificacaoAcessoCorrespondente;
import java.time.LocalDate;
import java.time.LocalTime;

import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelHistoricoIdentificacaoAcessoCorrespondenteTest {
    @Test
    public void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(ModelHistoricoIdentificacaoAcessoCorrespondente.class,
        		new ValueBuilder<ModelHistoricoIdentificacaoAcessoCorrespondente>() {
            public ModelHistoricoIdentificacaoAcessoCorrespondente build() {
            	
            	ModelHistoricoIdentificacaoAcessoCorrespondente modelHistoricoIdentificacaoAcessoCorrespondente =
            			new ModelHistoricoIdentificacaoAcessoCorrespondente(
            					1, "", null, 1, 1, 1, "", null, null, 1, 1, 1, null, null, 1, 1, "");
            	
            	modelHistoricoIdentificacaoAcessoCorrespondente =
            			new ModelHistoricoIdentificacaoAcessoCorrespondente();
                
                return modelHistoricoIdentificacaoAcessoCorrespondente;
            }
        });
        tester.setTypeExclusions(LocalDate.class, LocalTime.class);
        tester.testAll(ModelHistoricoIdentificacaoAcessoCorrespondente.class);
    }
}
