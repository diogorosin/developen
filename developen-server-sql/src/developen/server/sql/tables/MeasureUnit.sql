CREATE TABLE "MeasureUnit" (

	"identifier" INTEGER DEFAULT NEXTVAL('MeasureUnitSequence') NOT NULL,

	"denomination" TEXT NOT NULL,
	        
	"acronym" TEXT NOT NULL,

	"group" INTEGER NOT NULL

);
