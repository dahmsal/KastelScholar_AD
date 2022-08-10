package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.output.CreateBibliography;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.ParameterException;


import java.util.*;

/**
 * Command: print bibliography
 * Create a bibliography output in a given style (acm|apa) from a set of publications
 * @author uppyo
 * @version 1.0
 */
public class PrintBibliography extends Command {
    private static final String PATTERN = "print bibliography";
    private final DatabaseProvider databaseProvider;
    private final Parameter listId = ScholarParameter.idParameter().useAsList().build();
    private final Parameter style = ScholarParameter.bibStyleParameter().useAsField(List.of(listId)).build();
    private final List<Parameter> parameters;

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public PrintBibliography(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(style);
    }

    @Override
    public String getPattern() {
        return PATTERN;
    }

    @Override
    public List<Parameter> getParams() {
        return this.parameters;
    }

    @Override
    public Result exec(Dictionary<Parameter, List<Object>> parameterDict) {
        String style = (String) parameterDict.get(this.style).get(0);
        Set<Publication> publications = new HashSet<>();
        String result;
        try {
            for (Object id : parameterDict.get(listId)) {
                Publication givenPublication
                        = this.databaseProvider.getPublicationDatabase().getPublication((String) id);
                if (!givenPublication.isValid()) {
                    throw new ParameterException("a given publication was invalid");
                }
                publications.add(givenPublication);
            }
            result = CreateBibliography.getBibliography(style, publications);
        } catch (IdentifierException | ParameterException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true, result);
    }
}