package be.gestatech.bookstore.web.view.mapper.auth;

import be.gestatech.bookstore.web.view.dto.auth.TokenType;

import javax.inject.Named;

@Named
public class TokenTypeMapper {

    public TokenType mapToWeb(be.gestatech.bookstore.domain.auth.entity.TokenType source) {
        return mapToWeb(source.name());
    }

    private TokenType mapToWeb(String actualTokenType) {
        TokenType webTokenType = null;
        for (TokenType tokenType: TokenType.values()) {
            if (tokenType.name().equalsIgnoreCase(actualTokenType)) {
                webTokenType = tokenType;
            }
        }
        return webTokenType;
    }

    public be.gestatech.bookstore.domain.auth.entity.TokenType mapToBusiness(TokenType source) {
        return mapToBusiness(source.name());
    }

    private be.gestatech.bookstore.domain.auth.entity.TokenType mapToBusiness(String actualTokenType) {
        be.gestatech.bookstore.domain.auth.entity.TokenType businessTokenType = null;
        for (be.gestatech.bookstore.domain.auth.entity.TokenType tokenType: be.gestatech.bookstore.domain.auth.entity.TokenType.values()) {
            if (tokenType.name().equalsIgnoreCase(actualTokenType)) {
                businessTokenType = tokenType;
            }
        }
        return businessTokenType;
    }

}
