package core.domain.application;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

@Entity
@Table(name = "APPLICATION")

public class Application implements AggregateRoot<IdApplication> {


        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private IdApplication idApplication;

         @Column(name = "RANK")
        private Rank rank;


        @Column(name = "SUBMISSION_DATE")
        private SubmissionDate submissionDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "STATUS")
        private Status status;

        @Column(name = "APPLICATION_DATA_FILE")
        private ApplicationDataFile applicationDataFile;

        @Column(name = "FILES_ATTACHED_CONTENT")
        private FilesAttachedContent filesAttachedContent;

        @Column(name = "EMAIL_FILES_ATTACHED")
        private EmailFilesAttached emailFilesAttached;
        @Column(name = "EMAIL_CONTENT_FILE")
        private EmailContentFile emailContentFile;


    public Application(  IdApplication idApplication ,Rank rank, SubmissionDate submissionDate, Status status, ApplicationDataFile applicationDataFile, FilesAttachedContent filesAttachedContent, EmailFilesAttached emailFilesAttached, EmailContentFile emailContentFile) {
        this.idApplication = idApplication;
        this.rank = rank;
        this.submissionDate = submissionDate;
        this.status = status;
        this.applicationDataFile = applicationDataFile;
        this.filesAttachedContent = filesAttachedContent;
        this.emailFilesAttached = emailFilesAttached;
        this.emailContentFile = emailContentFile;
    }


        protected Application() {
            // for ORM only
        }

        public boolean sameAs(final Object other) {
            if (this == other) {
                return true;
            }

            if (!(other instanceof Application)) {
                return false;
            }

            final Application that = (Application) other;

            return rank.equals(that.rank) && submissionDate.equals(that.submissionDate)
                    && status.equals(that.status) && applicationDataFile.equals(that.applicationDataFile)
                    && filesAttachedContent.equals(that.filesAttachedContent) && emailFilesAttached.equals(that.emailFilesAttached)
                    && emailContentFile.equals(that.emailContentFile);
        }


    public int compareTo(IdApplication other) {
        return AggregateRoot.super.compareTo(other);
    }


    @Override
    public IdApplication identity() {
        return null;
    }


    public boolean hasIdentity(IdApplication id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}


