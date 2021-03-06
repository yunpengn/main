package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_TAG_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String TAG_VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    /**
     * Validates given tag name.
     *
     * @throws IllegalValueException if the given tag name string is invalid.
     */
    public Tag(String name) throws IllegalValueException {
        requireNonNull(name);
        String trimmedName = name.trim();

        if (!isValidTagName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_TAG_CONSTRAINTS);
        }
        this.tagName = trimmedName;

        // Adds an entry in TagColorManager if there is no entry yet.
        if (!TagColorManager.contains(this)) {
            TagColorManager.setColor(this);
        }
    }

    public Tag(String name, String color) throws IllegalValueException {
        requireNonNull(name, color);
        String trimmedName = name.trim();

        if (!isValidTagName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_TAG_CONSTRAINTS);
        }
        this.tagName = trimmedName;

        TagColorManager.setColor(this, color);
    }

    //@@author low5545
    /**
     * Creates a copy of the given Tag.
     */
    public Tag(Tag tag) {
        requireNonNull(tag);
        this.tagName = tag.tagName;

        // Adds an entry in TagColorManager if there is no entry yet.
        if (!TagColorManager.contains(this)) {
            TagColorManager.setColor(this);
        }
    }
    //@@author

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(TAG_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && this.tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
