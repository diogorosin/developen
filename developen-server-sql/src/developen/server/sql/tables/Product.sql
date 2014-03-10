CREATE TABLE "Product" (

	"progeny" INTEGER NOT NULL,

        "productGroup" INTEGER,

        "productMark" INTEGER,

        "productLine" INTEGER,

        "productModel" INTEGER,

        "productDetail" INTEGER,

        "productFinish" INTEGER,

        "widthValue" NUMERIC(14,4) DEFAULT 0,

        "widthUnit" INTEGER,

        "heightValue" NUMERIC(14,4) DEFAULT 0,

        "heightUnit" INTEGER,

        "lengthValue" NUMERIC(14,4) DEFAULT 0,

        "lengthUnit" INTEGER,

        "contentValue" NUMERIC(14,4) DEFAULT 0,

        "contentUnit" INTEGER,

        "grossWeightValue" NUMERIC(14,4) DEFAULT 0,

        "grossWeightUnit" INTEGER,

        "netWeightValue" NUMERIC(14,4) DEFAULT 0,

        "netWeightUnit" INTEGER,

        "ipi" INTEGER 

);
