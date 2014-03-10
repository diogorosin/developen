CREATE TABLE "Country" (

	"identifier" INTEGER DEFAULT NEXTVAL('CountrySequence') NOT NULL,
	
	"denomination" TEXT NOT NULL,

	"acronym" VARCHAR(3) NOT NULL

);
