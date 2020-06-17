package com.blog.newsangblog2.common.utils;

import com.blog.newsangblog2.common.enumeration.ResourceType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    public static List<String> getResourceByType(String type) {

        return Stream.of(ResourceType.values())
                .filter(resource -> type.equals(resource.getType()))
                .map(resource -> resource.name())
                .collect(Collectors.toList());

    }
}