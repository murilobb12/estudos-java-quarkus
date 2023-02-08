package br.com.bb.cbo.identificacaoAcessoCorrespondente;
import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelHistoricoIdentificacaoAcessoCorrespondentePKTest {
    @Test
    public void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(ModelHistoricoIdentificacaoAcessoCorrespondentePK.class,
        		new ValueBuilder<ModelHistoricoIdentificacaoAcessoCorrespondentePK>() {
            public ModelHistoricoIdentificacaoAcessoCorrespondentePK build() {
            	
            	ModelHistoricoIdentificacaoAcessoCorrespondentePK.getSerialversionuid();
            	
            	ModelHistoricoIdentificacaoAcessoCorrespondentePK modelHistoricoIdentificacaoAcessoCorrespondentePK;
            	
            	modelHistoricoIdentificacaoAcessoCorrespondentePK =
            			new ModelHistoricoIdentificacaoAcessoCorrespondentePK(1, "F1220222", null);
            	
            	modelHistoricoIdentificacaoAcessoCorrespondentePK.hashCode();
            	
            	modelHistoricoIdentificacaoAcessoCorrespondentePK.equals(new ModelHistoricoIdentificacaoAcessoCorrespondentePK());
            	
                return modelHistoricoIdentificacaoAcessoCorrespondentePK;
            }
        });
        tester.testAll(ModelHistoricoIdentificacaoAcessoCorrespondentePK.class);
    }
}
