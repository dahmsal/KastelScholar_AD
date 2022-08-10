package edu.kit.informatik.ui.output;

import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.data.objects.venue.Series;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateBibliography {
    private static final String PROCEEDINGS = "In Proceedings of";

    public static String getBibliography(String style, Set<Publication> publications)
            throws ParameterException, IdentifierException {
        List<Publication> sortedPublications = publications.stream().sorted().collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        if (style.equals("acm")) {
            return acmBuilder(sortedPublications);
        }
        if (style.equals("apa")) {
            return apaBuilder(sortedPublications);
        }
        else {
            throw new ParameterException("bib style could not be found");
        }
    }

    private static String getAuthorList(String style, List<Author> authors) throws IdentifierException {
        StringBuilder stringBuilder = new StringBuilder();
        if (style.equals("acm")) {
            for (Author author:authors) {
                stringBuilder.append(author.getId());
                stringBuilder.append(UtilStrings.getComma());
                stringBuilder.append(UtilStrings.getWhitespace());
            }
            //remove trailing comma
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(UtilStrings.getComma()));
            //replace last comma with "and"
            int lastComma = stringBuilder.lastIndexOf(UtilStrings.getComma());
            stringBuilder.replace(lastComma, lastComma + 1, UtilStrings.getWhitespace() + UtilStrings.getAnd());
            return stringBuilder.toString().trim();
        }
        if (style.equals("apa")){
            for (Author author:authors) {
                stringBuilder.append(author.getLastName());
                stringBuilder.append(UtilStrings.getComma()).append(UtilStrings.getWhitespace());
                stringBuilder.append(author.getFirstLetter()).append(UtilStrings.getDot());
                stringBuilder.append(UtilStrings.getComma()).append(UtilStrings.getWhitespace());
            }
            //remove trailing comma
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(UtilStrings.getComma()));
            //replace last comma with "&"
            int lastComma = stringBuilder.lastIndexOf(UtilStrings.getComma());
            stringBuilder.replace(lastComma, lastComma + 1
                    , UtilStrings.getWhitespace() + UtilStrings.getAndSymbol());
            return stringBuilder.toString().trim();
        }
        throw new IdentifierException("style could not be found in author list creator");
    }


    private static String acmBuilder(List<Publication> sortedPublications) throws IdentifierException {
        StringBuilder stringBuilder = new StringBuilder();
        String style = "acm";
        int bibID = 1;
        for (Publication publication:sortedPublications) {
            stringBuilder.append(UtilStrings.getBracketInt(bibID));
            stringBuilder.append(UtilStrings.getWhitespace());
            stringBuilder.append(getAuthorList(style, publication.getAuthors()));
            stringBuilder.append(UtilStrings.getDot()).append(UtilStrings.getWhitespace());
            if (publication.getVenue() instanceof Series) {
                stringBuilder.append(publication.getTitle());
                stringBuilder.append(UtilStrings.getDot()).append(UtilStrings.getWhitespace());
                stringBuilder.append(PROCEEDINGS).append(UtilStrings.getWhitespace());
                stringBuilder.append(publication.getVenue().getName());
                stringBuilder.append(UtilStrings.getComma()).append(UtilStrings.getWhitespace());
                stringBuilder.append(publication.getYear());
                stringBuilder.append(UtilStrings.getComma()).append(UtilStrings.getWhitespace());
                stringBuilder.append(((Series) publication.getVenue()).getLocation(publication.getYear()));
                stringBuilder.append(UtilStrings.getDot());
            } else {
                stringBuilder.append(publication.getYear());
                stringBuilder.append(UtilStrings.getDot()).append(UtilStrings.getWhitespace());
                stringBuilder.append(publication.getTitle());
                stringBuilder.append(UtilStrings.getDot()).append(UtilStrings.getWhitespace());
                stringBuilder.append(publication.getVenue().getName()).append(UtilStrings.getDot());
            }
            bibID++;
        }
        return stringBuilder.toString();
    }

    private static String apaBuilder(List<Publication> sortedPublications) throws IdentifierException {
        StringBuilder stringBuilder = new StringBuilder();
        String style = "apa";
        for (Publication publication:sortedPublications) {
            stringBuilder.append(getAuthorList(style, publication.getAuthors())).append(UtilStrings.getWhitespace());
            stringBuilder.append(UtilStrings.getQQInt(publication.getYear())).append(UtilStrings.getDot());
            stringBuilder.append(UtilStrings.getWhitespace());
            stringBuilder.append(publication.getTitle()).append(UtilStrings.getDot());
            stringBuilder.append(UtilStrings.getWhitespace());
            stringBuilder.append(publication.getVenue().getName());
            if (publication.getVenue() instanceof Series) {
                stringBuilder.append(UtilStrings.getWhitespace());
                stringBuilder.append(((Series) publication.getVenue()).getLocation(publication.getYear()));
            }
            stringBuilder.append(UtilStrings.getDot());
        }
        return stringBuilder.toString();
    }


}
