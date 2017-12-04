package be.gestatech.bookstore.domain.auth.entity;

public enum Role {
    // Viewing stuff.
    VIEW_USER_PAGES,
    VIEW_ADMIN_PAGES,

    // Editing stuff.
    EDIT_OWN_PROFILE,
    EDIT_PROFILES,

    // Actions.
    ACCESS_API;
}
