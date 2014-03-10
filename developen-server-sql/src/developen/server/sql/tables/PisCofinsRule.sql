CREATE TABLE "PisCofinsRule" (

	"pisCofins" INTEGER NOT NULL,

	"cfop" INTEGER NOT NULL,

        "rule" INTEGER NOT NULL DEFAULT 1,

        "pisCst" VARCHAR(2),

        "cofinsCst" VARCHAR(2),

        "pisCumulative" NUMERIC(14,4) DEFAULT 0,

        "pisNonCumulative" NUMERIC(14,4) DEFAULT 0,

        "cofinsCumulative" NUMERIC(14,4) DEFAULT 0,

        "cofinsNonCumulative" NUMERIC(14,4) DEFAULT 0

);
