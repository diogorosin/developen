CREATE TABLE "City" (

	"identifier" INTEGER DEFAULT NEXTVAL('CitySequence') NOT NULL,
	
	"denomination" TEXT NOT NULL,
	
	"state" INTEGER NOT NULL

);
