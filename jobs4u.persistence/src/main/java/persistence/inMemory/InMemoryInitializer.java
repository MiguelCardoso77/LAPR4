package persistence.inMemory;

import bootstrappers.Jobs4UBootstrapper;

final class InMemoryInitializer {

    private static class LazyHolder {
        private static final InMemoryInitializer INSTANCE = new InMemoryInitializer();

        private LazyHolder() {
        }
    }

    private InMemoryInitializer() {
        // to ensure some default test data is available, specially when using
        // in memory persistence
        new Jobs4UBootstrapper().execute();
    }

    private void initialize() {
        // nothing to do; data has already been initialized in the singleton
        // constructor.
    }

    public static void init() {
        LazyHolder.INSTANCE.initialize();
    }
}
