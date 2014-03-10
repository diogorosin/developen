CREATE TABLE "ProductDetail" (

	"identifier" INTEGER DEFAULT NEXTVAL('ProductDetailSequence') NOT NULL,

	"denomination" TEXT NOT NULL,

        "shortDenomination" TEXT NOT NULL

);
