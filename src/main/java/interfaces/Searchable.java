package interfaces;

/**
 * Generic interface for search functionality across different platforms.
 * Implement this in page classes where search actions are needed.
 */
public interface Searchable {
    /**
     * Performs a search operation
     *
     * @param searchTerm The text or item to search for
     */
    void search(String searchTerm);
}