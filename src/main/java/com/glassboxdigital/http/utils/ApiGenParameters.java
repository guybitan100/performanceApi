package com.glassboxdigital.http.utils;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ApiGenParameters {

    @Parameter(names = {"-f", "-templateFile"}, description = "The fiddler template file", required = true)
    private String templateFile;
    public String getTemplateFile() {
        return templateFile;
    }
}
