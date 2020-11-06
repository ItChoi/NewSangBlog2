package com.blog.newsangblog2.common.utils;

import com.blog.newsangblog2.common.enumeration.ResourceType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FileUtils {
    public static String[] ALLOWED_EXTS = {
            "gif", "jpeg", "jpg",
            "png", "svg", "blob"
    };

    public static String[] ALLOWED_MIMETYPE = {
            "image/gif",
            "image/jpeg",
            "image/pjpeg",
            "image/x-png",
            "image/png",
            "image/svg+xml"
    } ;


    public static List<String> getResourceByType(String type) {
        return Stream.of(ResourceType.values())
                .filter(resource -> type.equals(resource.getType()))
                .map(resource -> resource.name())
                .collect(Collectors.toList());
    }

}
