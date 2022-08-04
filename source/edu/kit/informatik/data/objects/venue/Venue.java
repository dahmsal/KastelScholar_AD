package edu.kit.informatik.data.objects.venue;

import java.util.List;
import java.util.Set;

public abstract class Venue {

    public abstract String getName();

    public abstract Set<String> getKeywords();

    public abstract void addKeywords(List<String> keywords);

}
