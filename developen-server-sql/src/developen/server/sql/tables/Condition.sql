CREATE TABLE "Condition" (

	"identifier" INTEGER DEFAULT NEXTVAL('ConditionSequence') NOT NULL,

	"denomination" TEXT NOT NULL,
	        
	"active" BOOLEAN NOT NULL DEFAULT TRUE

);
