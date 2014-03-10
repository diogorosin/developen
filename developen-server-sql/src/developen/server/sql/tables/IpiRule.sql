CREATE TABLE "IpiRule" (

	"ipi" INTEGER NOT NULL,

	"cfop" INTEGER NOT NULL,

        "rule" INTEGER NOT NULL DEFAULT 1,

        "cst" VARCHAR(2),

        "ipiAliquot" NUMERIC(5,2) DEFAULT 0,

        "ipiStaff" NUMERIC(14,4) DEFAULT 0

);
