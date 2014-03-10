CREATE TABLE "ProductModel" (

	"identifier" INTEGER DEFAULT NEXTVAL('ProductModelSequence') NOT NULL,

	"denomination" TEXT NOT NULL,

        "shortDenomination" TEXT NOT NULL

);
