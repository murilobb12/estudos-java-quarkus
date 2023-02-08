package aulas.Tecnicas.Stream.CriaNomeOptional.Main;

import aulas.Tecnicas.Stream.CriaNomeOptional.Interfaces.MiddleName;
import aulas.Tecnicas.Stream.CriaNomeOptional.Interfaces.Name;

public class CreateName implements Name {

    @Override
    public MiddleName name(String name) {
        return new CreateMiddleName(name);
    }
}
