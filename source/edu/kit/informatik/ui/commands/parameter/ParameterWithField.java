package edu.kit.informatik.ui.commands.parameter;

import java.util.List;

public class ParameterWithField extends Parameter {
    private final List<Parameter> parameterField;

    public ParameterWithField(ParameterBuilder builder, List<Parameter> parameterField) {
        super(builder);
        this.parameterField = parameterField;
    }

    public List<Parameter> getParameterField() {
        return parameterField;
    }
}
