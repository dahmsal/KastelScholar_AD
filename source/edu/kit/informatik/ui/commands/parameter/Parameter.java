package edu.kit.informatik.ui.commands.parameter;

/**
 * Description of a parameter
 * A parameter must have a regex-pattern
 * @author uppyo
 * @version 1.0
 */
public class Parameter {
    private final boolean asList;
    private final String pattern;
    public Parameter(ParameterBuilder builder) {
        this.asList = builder.asList;
        this.pattern = builder.pattern;
    }

    public boolean isAsList() {
        return asList;
    }

    public String getPattern() {
        return this.pattern;
    }

    public static class ParameterBuilder {
        private boolean asList;
        private String pattern;

        public ParameterBuilder() {
            this.asList = false;
        }

        public ParameterBuilder useAsList() {
            this.asList = true;
            return this;
        }

        public ParameterBuilder pattern(Pattern givenPattern) {
            this.pattern = givenPattern.getPattern();
            return this;
        }

        public ParameterBuilder specialPattern(String specialPattern) {
            this.pattern = specialPattern;
            return this;
        }

        public Parameter build() {
            Parameter parameter = new Parameter(this);
            return parameter;
        }
    }

}
