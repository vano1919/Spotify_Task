package org.epam.enumeration.pageLinksEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LinksEnum {
    LOGIN_PAGE_URL("https://accounts.spotify.com/en/login");

    private final String url;
}