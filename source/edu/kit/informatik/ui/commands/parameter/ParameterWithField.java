package edu.kit.informatik.ui.commands.parameter;

import java.util.List;

/**
 * Extension of the Parameter class, adds functionality to return field of parameters
 * @author uppyo
 * @version 1.0
 */
public class ParameterWithField extends Parameter {
    private final List<Parameter> parameterField;

    /**
     * Initialise Parameter super() and the parameter field
     * @param builder builder-pattern is used to create parameters
     * @param parameterField parameters for the parameter-field functionality
     */
    public ParameterWithField(ParameterBuilder builder, List<Parameter> parameterField) {
        super(builder);
        this.parameterField = parameterField;
    }

    /**
     * Get the parameter-field of the parameter
     * @return list of parameters in field
     */
    public List<Parameter> getParameterField() {
        return parameterField;
    }

}
