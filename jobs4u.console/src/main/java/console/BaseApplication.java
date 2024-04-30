package console;

import infrastructure.Application;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BaseApplication is an abstract class providing common functionalities and structure for console-based applications.
 * It handles setup and cleanup of event handlers, printing headers and footers, and provides hooks for subclasses to implement
 * application-specific behavior.
 * <p>
 * This class uses an in-process event dispatcher and logging for error handling.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings("squid:S106")
public abstract class BaseApplication {
    final EventDispatcher dispatcher = InProcessPubSub.dispatcher();
    protected static final String SEPARATOR_HR = "=====================================";
    private static final Logger LOGGER = LogManager.getLogger(BaseApplication.class);

    /**
     * Runs the application with the provided command line arguments.
     *
     * @param args the command line arguments
     */
    public void run(final String[] args) {
        printHeader();

        try {
            setupEventHandlers();

            doMain(args);

            printFooter();
        } catch (final Exception e) {
            System.out.println("Something unexpected has happened and the application will terminate. Please check the logs.\n");
            LOGGER.error(e);
        } finally {
            clearEventHandlers();
        }

        System.exit(0);
    }

    /**
     * Prints the footer message.
     */
    protected void printFooter() {
        System.out.println("\n");
        System.out.println(SEPARATOR_HR);
        System.out.println(appGoodbye());
        System.out.println(SEPARATOR_HR);
    }

    /**
     * Prints the header message.
     */
    protected void printHeader() {
        System.out.println(SEPARATOR_HR);
        System.out.println(appTitle() + " " + Application.VERSION);
        System.out.println(Application.COPYRIGHT);
        System.out.println(SEPARATOR_HR);
    }

    /**
     * Clears the event handlers.
     */
    private void clearEventHandlers() {
        try {
            doClearEventHandlers(dispatcher);

            dispatcher.shutdown();
        } catch (final Exception e) {
            LOGGER.error("Unable to cleanup event handlers", e);
        }
    }

    /**
     * Sets up the event handlers.
     */
    private void setupEventHandlers() {
        doSetupEventHandlers(dispatcher);
    }

    /**
     * Abstract method to be implemented by subclasses, representing the main logic of the application.
     *
     * @param args the command line arguments
     */
    protected abstract void doMain(final String[] args);

    /**
     * Abstract method to be implemented by subclasses, returning the title of the application.
     *
     * @return the title of the application
     */
    protected abstract String appTitle();

    /**
     * Abstract method to be implemented by subclasses, returning the goodbye message of the application.
     *
     * @return the goodbye message of the application
     */
    protected abstract String appGoodbye();

    /**
     * Method to clear the event handlers.
     * This method does nothing by default. Subclasses can override it to provide custom logic for clearing event handlers.
     *
     * @param dispatcher the event dispatcher
     */
    protected void doClearEventHandlers(final EventDispatcher dispatcher) {
        LOGGER.info("Clearing event handlers for dispatcher: {}", dispatcher);
    }

    /**
     * Abstract method to be implemented by subclasses, setting up the event handlers.
     *
     * @param dispatcher the event dispatcher
     */
    protected abstract void doSetupEventHandlers(EventDispatcher dispatcher);
}
