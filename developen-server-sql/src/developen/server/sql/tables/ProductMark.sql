CREATE TABLE "ProductMark" (

	"identifier" INTEGER DEFAULT NEXTVAL('ProductMarkSequence') NOT NULL,

	"denomination" TEXT NOT NULL,

        "shortDenomination" TEXT NOT NULL

);
