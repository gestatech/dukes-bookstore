package be.gestatech.bookstore.web.jsf;

import be.gestatech.bookstore.service.api.ProgrammingLanguageService;
import be.gestatech.bookstore.service.dto.ProgrammingLanguage;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name="prolangtableview")
@ViewScoped
public class ProgrammingLanguageTableBean {
    @EJB
    private ProgrammingLanguageService programmingLanguageService;

    public List<ProgrammingLanguage> getLangs() {
        return programmingLanguageService.getAllLangs();
    }
}
