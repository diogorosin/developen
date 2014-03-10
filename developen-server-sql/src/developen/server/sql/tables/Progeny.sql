CREATE TABLE "Progeny" (

	"identifier" INTEGER NOT NULL,
	
	"denomination" TEXT NOT NULL,

        "shortDenomination" TEXT NOT NULL,

	"active" BOOLEAN NOT NULL DEFAULT TRUE,
 
        "progenyType" VARCHAR(2) NOT NULL,

	"price" NUMERIC(12,2) NOT NULL DEFAULT 0,

        "icms" INTEGER,

        "pisCofins" INTEGER 

);
