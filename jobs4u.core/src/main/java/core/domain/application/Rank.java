package core.domain.application;

import eapli.framework.domain.model.ValueObject;

public class Rank  implements ValueObject, Comparable<Rank>{


        private int number;

        public Rank(final int Rank) {
            if (Rank < 0) {
                throw new IllegalArgumentException("Rank should be a positive number");
            }

            this.number = Rank;
        }

        protected Rank() {
            // for ORM
        }

        public static core.domain.application.Rank valueOf(final int Rank) {
            return new core.domain.application.Rank(Rank);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof core.domain.application.Rank)) {
                return false;
            }

            final core.domain.application.Rank that = (core.domain.application.Rank) o;
            return this.number == that.number;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(this.number);
        }

        @Override
        public String toString() {
            return Integer.toString(this.number);
        }

        @Override
        public int compareTo(final core.domain.application.Rank arg0) {
            return Integer.compare(number, arg0.number);
        }
    }



