CREATE TABLE "Macro" (

	"identifier" INTEGER DEFAULT NEXTVAL('MacroSequence') NOT NULL,

	"denomination" TEXT NOT NULL,

        "active" BOOLEAN NOT NULL DEFAULT TRUE,

        "icms" BOOLEAN NOT NULL DEFAULT TRUE,

        "ipi" BOOLEAN NOT NULL DEFAULT TRUE,

        "pisCofins" BOOLEAN NOT NULL DEFAULT TRUE,

        "iss" BOOLEAN NOT NULL DEFAULT FALSE,

        "stock" BOOLEAN NOT NULL DEFAULT TRUE,

        "finance" BOOLEAN NOT NULL DEFAULT TRUE,

        "fiscalDocumentType" VARCHAR(2) NOT NULL

);
