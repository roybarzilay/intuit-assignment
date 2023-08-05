package com.example.assignment.service.data.load;


import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseDataLoadService {

    private static final String FILE_PATTERN = "classpath:/data/*.%s";

    public List<String> getResourceUrls(String fileType) throws IOException {

        String locationPattern = String.format(FILE_PATTERN, fileType);
        ClassLoader classLoader = BaseDataLoadService.class.getClassLoader();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);

        Resource[] resources = resolver.getResources(locationPattern);

        return Arrays.stream(resources)
                .map(this::toURL)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private String toURL(Resource resource) {
        try {
            if (resource == null) {
                return null;
            }
            return resource.getURL().toExternalForm();
        } catch (IOException e) {
            return null;
        }
    }

}
