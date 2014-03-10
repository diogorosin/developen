CREATE TABLE "Subject" (

     "identifier" INTEGER DEFAULT NEXTVAL('SubjectSequence') NOT NULL,

     "denomination" TEXT NOT NULL,

     "active" BOOLEAN NOT NULL DEFAULT TRUE,

     "rule" INTEGER NOT NULL DEFAULT 1

);
