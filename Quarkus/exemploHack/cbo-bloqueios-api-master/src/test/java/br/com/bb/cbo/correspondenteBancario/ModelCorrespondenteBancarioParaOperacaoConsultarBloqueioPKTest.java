package br.com.bb.cbo.correspondenteBancario;

import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPKTest {

    @Test
    public void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK.class,
        		new ValueBuilder<ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK>() {
            public ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK build() {
            	
            	ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK.getSerialversionuid();
            	
            	ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK correspondenteBancarioParaOperacaoConsultarBloqueioPK;
            	
            	correspondenteBancarioParaOperacaoConsultarBloqueioPK = 
            			new ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK(22, 1);
            	
            	correspondenteBancarioParaOperacaoConsultarBloqueioPK.hashCode();
            	
            	correspondenteBancarioParaOperacaoConsultarBloqueioPK.equals(
            			new ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK());
            	
                return correspondenteBancarioParaOperacaoConsultarBloqueioPK;
            }
        });
        tester.testAll(ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK.class);
    }
    
}
