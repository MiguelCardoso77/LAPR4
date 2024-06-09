package core.persistence;

import infrastructure.Application;
import eapli.framework.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Represents the persistence context in the Jobs4U system.
 * This class provides a method to get a RepositoryFactory instance.
 * It is marked as a utility class, meaning it's not meant to be instantiated or subclassed.
 *
 * @author Miguel Cardoso
 */
@Utility
public final class PersistenceContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceContext.class);
    private static RepositoryFactory theFactory;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private PersistenceContext() {
        // ensure utility
    }

    /**
     * Returns a RepositoryFactory instance.
     * If the instance is null, it tries to create a new one using reflection.
     * If the creation fails, it logs the error and throws an IllegalStateException.
     *
     * @return A RepositoryFactory instance.
     */
    public static RepositoryFactory repositories() {
        if (theFactory == null) {
            final String factoryClassName = Application.settings().getRepositoryFactory();
            try {
                theFactory = (RepositoryFactory) Class.forName(factoryClassName).getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                     | IllegalArgumentException | InvocationTargetException | ClassNotFoundException ex) {
                LOGGER.error("Unable to dynamically load the Repository Factory", ex);
                throw new IllegalStateException("Unable to dynamically load the Repository Factory: " + factoryClassName, ex);
            }
        }
        return theFactory;
    }
}
