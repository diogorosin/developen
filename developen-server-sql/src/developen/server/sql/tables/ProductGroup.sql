CREATE TABLE "ProductGroup" (

	"identifier" INTEGER DEFAULT NEXTVAL('ProductGroupSequence') NOT NULL,

	"denomination" TEXT NOT NULL,

        "shortDenomination" TEXT NOT NULL,

        "productMark" BOOLEAN NOT NULL,

        "productLine" BOOLEAN NOT NULL,

        "productModel" BOOLEAN NOT NULL,

        "productDetail" BOOLEAN NOT NULL,

        "productFinish" BOOLEAN NOT NULL,

        "widthValue" BOOLEAN NOT NULL,

        "heightValue" BOOLEAN NOT NULL,

        "lengthValue" BOOLEAN NOT NULL,

        "contentValue" BOOLEAN NOT NULL,

        "grossWeightValue" BOOLEAN NOT NULL,

        "netWeightValue" BOOLEAN NOT NULL

);
