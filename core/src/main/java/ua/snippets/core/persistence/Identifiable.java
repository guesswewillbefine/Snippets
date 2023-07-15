package ua.snippets.core.persistence;

public interface Identifiable<I> {
    I getId();
    boolean isNew();
}
