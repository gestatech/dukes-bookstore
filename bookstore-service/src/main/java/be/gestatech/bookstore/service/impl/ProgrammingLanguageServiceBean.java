package be.gestatech.bookstore.service.impl;

import be.gestatech.bookstore.service.api.ProgrammingLanguageService;
import be.gestatech.bookstore.service.dto.ProgrammingLanguage;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProgrammingLanguageServiceBean implements ProgrammingLanguageService {
    @Override
    public List<ProgrammingLanguage> getAll() {
        List<ProgrammingLanguage> result = new ArrayList<>();
        ProgrammingLanguage java = new ProgrammingLanguage("java","James Gosling", 1995);
        ProgrammingLanguage cLang = new ProgrammingLanguage("c"," Dennis Ritchie", 1972);
        result.add(java);
        result.add(cLang);
        return result;
    }
}
