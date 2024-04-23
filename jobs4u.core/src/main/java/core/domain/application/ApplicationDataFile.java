package core.domain.application;

import eapli.framework.domain.model.ValueObject;

public class ApplicationDataFile  implements ValueObject {
        private String applicationDataFile;

        public ApplicationDataFile(final String applicationDataFile) {
            if (applicationDataFile == null || applicationDataFile.isEmpty()) {
                throw new IllegalArgumentException("applicationDataFile should neither be null nor empty");
            }

            this.applicationDataFile = applicationDataFile;
        }

        protected ApplicationDataFile() {
            // for ORM
        }

        public static ApplicationDataFile valueOf(final String applicationDataFile) {
            return new ApplicationDataFile(applicationDataFile);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ApplicationDataFile)) {
                return false;
            }

            final ApplicationDataFile that = (ApplicationDataFile) o;
            return this.applicationDataFile.equals(that.applicationDataFile);
        }

        @Override
        public int hashCode() {
            return this.applicationDataFile.hashCode();
        }

        @Override
        public String toString() {
            return applicationDataFile;
        }
    }


