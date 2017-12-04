package be.gestatech.bookstore.web.view.jsf;

import be.gestatech.bookstore.service.api.ProgrammingLanguageService;
import be.gestatech.bookstore.service.dto.ProgrammingLanguage;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class ProgrammingLanguageTableBean {

    @Inject
    private ProgrammingLanguageService programmingLanguageService;

    public List<ProgrammingLanguage> getAll() {
        return programmingLanguageService.getAll();
    }
}
