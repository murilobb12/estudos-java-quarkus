package br.com.bb.cbo.gestorRedeCorrespondenteBancario;
import org.agileware.test.PropertiesTester;
import org.agileware.test.ValueBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ModelGestorRedeCorrespondenteBancarioPKTest {
    @Test
    public void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(ModelGestorRedeCorrespondenteBancarioPK.class, new ValueBuilder<ModelGestorRedeCorrespondenteBancarioPK>() {
            public ModelGestorRedeCorrespondenteBancarioPK build() {
            	
            	ModelGestorRedeCorrespondenteBancarioPK.getSerialversionuid();
            	
            	ModelGestorRedeCorrespondenteBancarioPK gestorRedeCorrespondenteBancarioPK;
            	
            	gestorRedeCorrespondenteBancarioPK = new ModelGestorRedeCorrespondenteBancarioPK(1, 0);
            	
            	gestorRedeCorrespondenteBancarioPK.hashCode();
            	
            	gestorRedeCorrespondenteBancarioPK.equals(new ModelGestorRedeCorrespondenteBancarioPK());
            	
                return gestorRedeCorrespondenteBancarioPK;
            }
        });
        tester.testAll(ModelGestorRedeCorrespondenteBancarioPK.class);
    }
}
