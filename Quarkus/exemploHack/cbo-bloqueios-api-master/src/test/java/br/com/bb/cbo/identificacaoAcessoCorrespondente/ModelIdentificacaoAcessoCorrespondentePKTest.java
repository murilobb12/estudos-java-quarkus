package br.com.bb.cbo.identificacaoAcessoCorrespondente;
import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelIdentificacaoAcessoCorrespondentePKTest {
    @Test
    public void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(ModelIdentificacaoAcessoCorrespondentePK.class, new ValueBuilder<ModelIdentificacaoAcessoCorrespondentePK>() {
            public ModelIdentificacaoAcessoCorrespondentePK build() {
            	
            	ModelIdentificacaoAcessoCorrespondentePK.getSerialversionuid();
            	
            	ModelIdentificacaoAcessoCorrespondentePK modelIdentificacaoAcessoCorrespondentePK;
            	
            	modelIdentificacaoAcessoCorrespondentePK = new ModelIdentificacaoAcessoCorrespondentePK(1, "F1220222");
            	
            	modelIdentificacaoAcessoCorrespondentePK.hashCode();
            	
            	modelIdentificacaoAcessoCorrespondentePK.equals(new ModelIdentificacaoAcessoCorrespondentePK());
            	
                return modelIdentificacaoAcessoCorrespondentePK;
            }
        });
        tester.testAll(ModelIdentificacaoAcessoCorrespondentePK.class);
    }
}
