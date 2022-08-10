package edu.kit.informatik.ui.commands.parameter;

import edu.kit.informatik.util.DataType;

import java.util.List;

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
    private final boolean hasSpaceDelimiter;
    private final String pattern;
    private final DataType type;

    /**
     * A parameter gets created by a builder pattern, the builder initialises the attributes of the parameter
     * @param builder use a parameter-builder to create parameters
     */
    public Parameter(ParameterBuilder builder) {
        this.asList = builder.asList;
        this.pattern = builder.pattern;
        this.type = builder.type;
        this.asField = builder.asField;
        this.hasSpaceDelimiter = builder.hasSpaceDelimiter;
        this.alternativeParameters = builder.alternativeParameters;
    }

    /**
     * Does the parameter allow a list of input arguments
     * @return true if the parameter can be used as list
     */
    public boolean isAsList() {
        return asList;
    }

    /**
     * Does the parameter have a field of parameters
     * @return true if the parameter has a field of other parameters
     */
    public boolean isAsField() {
        return asField;
    }

    /**
     * Does the parameter overwrite the standard delimiter "," with a whitespace
     * This case only exists in the jaccard-command
     * @return true if the delimiter of the parser has to be overwritten
     */
    public boolean hasSpaceDelimiter() {
        return hasSpaceDelimiter;
    }

    /**
     * get the regex-pattern of the parameter (note: has to be compiled before use)
     * @return String regex-pattern of parameter
     */
    public String getPattern() {
        return this.pattern;
    }

    /**
     * Returns the data-type of the parameter, default is String
     * @return specified data-type, used for safe casting
     */
    public DataType getType() { return this.type; }

    /**
     * Get alternative parameters, if the parameter can be replaced by another parameter
     * @return list of alternative parameters
     */
    public List<Parameter> getAlternativeParameters() {
        return this.alternativeParameters;
    }

    /**
     * Builder for parameters, the pattern enables addition optional functionality to a parameter
     */
    public static class ParameterBuilder {
        private boolean asList;
        private boolean asField;
        private boolean hasSpaceDelimiter;
        private String pattern;
        private DataType type;
        private List<Parameter> parameterField;
        private final List<Parameter> alternativeParameters;

        /**
         * Initialises all parameters to default-values
         */
        public ParameterBuilder() {
            this.alternativeParameters = null;
            this.asList = false;
            this.asField = false;
            this.hasSpaceDelimiter = false;
            this.pattern = "";
            this.type = DataType.STRING;
        }

        /**
         * Set the data-type of parameter
         * @param type data-type, default is String
         * @return the updated parameter-builder
         */
        public ParameterBuilder setType(DataType type) {
            this.type = type;
            return this;
        }

        /**
         * Set the parameter to a list-compatible parameter
         * @return the updated parameter-builder
         */
        public ParameterBuilder useAsList() {
            this.asList = true;
            return this;
        }

        /**
         * Enable the parameter-field functionality
         * @param parameterField parameters that are part of the field
         * @return the updated parameter-builder
         */
        public ParameterBuilder useAsField(List<Parameter> parameterField) {
            this.asField = true;
            this.parameterField = parameterField;
            return this;
        }

        /**
         * Set the regex-pattern to one of the pre-defined patterns
         * @param givenParameterPattern a pre-defined regex-pattern (see ParameterPattern Enum)
         * @return the updated parameter-builder
         */
        public ParameterBuilder pattern(ParameterPattern givenParameterPattern) {
            this.pattern = givenParameterPattern.getPattern();
            return this;
        }

        /**
         * Set the regex pattern to a non-standard pattern
         * @param specialPattern pattern as String
         * @return the updated parameter-builder
         */
        public ParameterBuilder specialPattern(String specialPattern) {
            this.pattern = specialPattern;
            return this;
        }

        /**
         * Set the flag for a delimiter overwrite
         * @return the updated parameter-builder
         */
        public ParameterBuilder hasSpaceDelimiter() {
            this.hasSpaceDelimiter = true;
            return this;
        }

        /**
         * Build the parameter
         * @return the final parameter
         */
        public Parameter build() {
            if (this.asField) {
                return new ParameterWithField(this, this.parameterField);
            }
            return new Parameter(this);
        }
    }

}
