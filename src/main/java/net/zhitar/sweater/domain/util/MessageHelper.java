package net.zhitar.sweater.domain.util;

import net.zhitar.sweater.domain.User;

public abstract class MessageHelper {

    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}
