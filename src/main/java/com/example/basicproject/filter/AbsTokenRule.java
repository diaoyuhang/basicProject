package com.example.basicproject.filter;

import com.example.basicproject.constant.BaseConstant;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class AbsTokenRule implements Filter {
    private Set<String> excludedAccuratePaths;

    private Set<Pattern> excludedVaguePaths;

    @Override
    public void init(FilterConfig filterConfig) {
        String paths = filterConfig.getInitParameter("excludedAccuratePaths");
        excludedAccuratePaths = new HashSet<>(Arrays.asList(paths.split(BaseConstant.COMMA_SEPARATOR)));

        String vaguePaths = filterConfig.getInitParameter("excludedVaguePaths");
        excludedVaguePaths = new HashSet<>();
        for (String path : vaguePaths.split(BaseConstant.COMMA_SEPARATOR)) {
            excludedVaguePaths.add(Pattern.compile(path));
        }
    }


    public Boolean isAuthWhiteList(String uri) {
        if (excludedAccuratePaths.contains(uri)) {
            return true;
        }

        for (Pattern pattern : excludedVaguePaths) {
            if (pattern.matcher(uri).matches()) {
                return true;
            }
        }

        return false;
    }

}
