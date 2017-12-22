package be.gestatech.bookstore.web.view.mapper.auth;

import be.gestatech.bookstore.web.view.dto.auth.LoginToken;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class LoginTokenMapper {

    @Inject
    private TokenTypeMapper tokenTypeMapper;

    public List<LoginToken> mapToWeb(List<be.gestatech.bookstore.domain.auth.entity.LoginToken> source) {
        List<LoginToken> loginTokens = null;
        if (Objects.nonNull(source)) {
            loginTokens = new ArrayList<>();
            if (Objects.nonNull(source)) {
                loginTokens.addAll(source.stream().map(this::mapToWeb).collect(Collectors.toList()));
            }
        }
        return loginTokens;
    }

    public LoginToken mapToWeb(be.gestatech.bookstore.domain.auth.entity.LoginToken source) {
        return mapToWeb(source, new LoginToken());
    }

    private LoginToken mapToWeb(be.gestatech.bookstore.domain.auth.entity.LoginToken source, LoginToken target) {
        LoginToken webLoginToken = null;
        if (Objects.nonNull(source)) {
            if (Objects.isNull(target)) {
                webLoginToken = new LoginToken();
            }
            webLoginToken.setCreated(source.getCreated());
            webLoginToken.setDescription(source.getDescription());
            webLoginToken.setExpiration(source.getExpiration());
            webLoginToken.setIpAddress(source.getIpAddress());
            webLoginToken.setTokenHash(source.getTokenHash());
            webLoginToken.setType(tokenTypeMapper.mapToWeb(source.getType()));
            //webLoginToken.setUser(source.getUser());
        }
        return webLoginToken;
    }

    public List<be.gestatech.bookstore.domain.auth.entity.LoginToken> mapToBusiness(List<LoginToken> source) {
        List<be.gestatech.bookstore.domain.auth.entity.LoginToken> loginTokens = null;
        if (Objects.nonNull(source)) {
            loginTokens = new ArrayList<>();
            if (Objects.nonNull(source)) {
                loginTokens.addAll(source.stream().map(this::mapToBusiness).collect(Collectors.toList()));
            }
        }
        return loginTokens;
    }

    public be.gestatech.bookstore.domain.auth.entity.LoginToken mapToBusiness(LoginToken source) {
        return mapToBusiness(source, new be.gestatech.bookstore.domain.auth.entity.LoginToken());
    }

    private be.gestatech.bookstore.domain.auth.entity.LoginToken mapToBusiness(LoginToken source, be.gestatech.bookstore.domain.auth.entity.LoginToken target) {
        be.gestatech.bookstore.domain.auth.entity.LoginToken businessLoginToken = null;
        if (Objects.nonNull(source)) {
            if (Objects.isNull(target)) {
                businessLoginToken = new be.gestatech.bookstore.domain.auth.entity.LoginToken();
            }
            businessLoginToken.setCreated(source.getCreated());
            businessLoginToken.setDescription(source.getDescription());
            businessLoginToken.setExpiration(source.getExpiration());
            businessLoginToken.setIpAddress(source.getIpAddress());
            businessLoginToken.setTokenHash(source.getTokenHash());
            businessLoginToken.setType(tokenTypeMapper.mapToBusiness(source.getType()));
            //businessLoginToken.setUser(source.getUser());
        }
        return businessLoginToken;
    }
}
