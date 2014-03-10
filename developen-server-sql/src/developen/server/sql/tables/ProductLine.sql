CREATE TABLE "ProductLine" (

	"identifier" INTEGER DEFAULT NEXTVAL('ProductLineSequence') NOT NULL,

	"denomination" TEXT NOT NULL,

        "shortDenomination" TEXT NOT NULL

);
