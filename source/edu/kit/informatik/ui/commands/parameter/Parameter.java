package edu.kit.informatik.ui.commands.parameter;

import edu.kit.informatik.util.DataType;

import java.util.List;
import java.util.Objects;

/**
 * Description of a parameter
 * A parameter must have a regex-pattern
 * @author uppyo
 * @version 1.0
 */
public class Parameter {
    private final List<Parameter> alternativeParameters;
    private final boolean asList;
    private final boolean asField;
    private final String pattern;
    private final DataType type;

    public Parameter(ParameterBuilder builder) {
        this.asList = builder.asList;
        this.pattern = builder.pattern;
        this.type = builder.type;
        this.asField = builder.asField;
        this.alternativeParameters = builder.alternativeParameters;
    }

    public boolean isAsList() {
        return asList;
    }

    public boolean isAsField() {
        return asField;
    }

    public String getPattern() {
        return this.pattern;
    }

    public DataType getType() { return this.type; }

    public List<Parameter> getAlternativeParameters() {
        return this.alternativeParameters;
    }


    public static class ParameterBuilder {
        private boolean asList;
        private boolean asField;
        private String pattern;
        private DataType type;
        private List<Parameter> parameterField;
        private List<Parameter> alternativeParameters;

        public ParameterBuilder() {
            this.alternativeParameters = null;
            this.asList = false;
            this.asField = false;
            this.pattern = "";
            this.type = DataType.STRING;
        }

        public ParameterBuilder setType(DataType type) {
            this.type = type;
            return this;
        }

        public ParameterBuilder useAsList() {
            this.asList = true;
            return this;
        }

        public ParameterBuilder useAsField(List<Parameter> parameterField) {
            this.asField = true;
            this.parameterField = parameterField;
            return this;
        }

        public ParameterBuilder pattern(ParameterPattern givenParameterPattern) {
            this.pattern = givenParameterPattern.getPattern();
            return this;
        }

        public ParameterBuilder specialPattern(String specialPattern) {
            this.pattern = specialPattern;
            Class type = String.class;
            Object test = type.cast(specialPattern);
            return this;
        }

        public ParameterBuilder alternativeParameterList(List<Parameter> alternativeParameters) {
            this.alternativeParameters = alternativeParameters;
            return this;
        }

        public Parameter build() {
            if (this.asField) {
                return new ParameterWithField(this, this.parameterField);
            }
            return new Parameter(this);
        }
    }

}
