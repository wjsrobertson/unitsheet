package org.unitsheet.reflection;

import org.unitsheet.types.ObjectConverter;

import java.util.Set;

import static org.unitsheet.utils.Collections.setOf;

public class ValueResolverFactory {

    private final Set<ValueResolver> resolvers;

    public ValueResolverFactory(ObjectConverter objectConverter) {
        resolvers = setOf(
                new ReadCellValueResolver(objectConverter),
                new ReadColumnValueResolver(objectConverter)
        );
    }

    public Set<ValueResolver> getResolvers() {
        return resolvers;
    }
}
