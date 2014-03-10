CREATE TABLE "IcmsRule" (

	"icms" INTEGER NOT NULL,

	"from" INTEGER NOT NULL,

        "to" INTEGER NOT NULL,

        "rule" INTEGER NOT NULL DEFAULT 1,

        "cst" VARCHAR(2),

        "csosn" VARCHAR(3),

        "icmsAliquot" NUMERIC(5,2) DEFAULT 0,

        "icmsReduction" NUMERIC(7,4) DEFAULT 0,

        "icmsAliquotReduced" NUMERIC(5,2) DEFAULT 0,

        "icmsAliquotCreditReusable" NUMERIC(5,2) DEFAULT 0,

        "icmsSTMarckup" NUMERIC(5,2) DEFAULT 0,

        "icmsSTReduction" NUMERIC(7,4) DEFAULT 0,

        "icmsSTStaff" NUMERIC(14,4) DEFAULT 0,

        "cfopGroup" INTEGER NOT NULL DEFAULT 1

);
