package be.gestatech.bookstore.service.api;

import be.gestatech.bookstore.service.dto.ProgrammingLanguage;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProgrammingLanguageService {

    List<ProgrammingLanguage> getAll();
}
