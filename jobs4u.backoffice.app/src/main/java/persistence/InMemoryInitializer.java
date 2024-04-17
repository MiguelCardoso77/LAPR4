package persistence;

import app.Jobs4uBootstrapper;

final class InMemoryInitializer {

    private static class LazyHolder {
        private static final InMemoryInitializer INSTANCE = new InMemoryInitializer();

        private LazyHolder() {
        }
    }

    private boolean initialized;

    private InMemoryInitializer() {
        // ensure no public instantiation
    }

    private synchronized void initialize() {
        if (!initialized) {
            // to ensure some default test data is available, specially when using
            // in memory persistence
            new Jobs4uBootstrapper().execute();
            new ECafeteriaDemoBootstrapper().execute();
            initialized = true;
        }
    }

    public static void init() {
        LazyHolder.INSTANCE.initialize();
    }
}

