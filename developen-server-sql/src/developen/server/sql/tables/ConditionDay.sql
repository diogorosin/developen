CREATE TABLE "ConditionDay" (
	
	"condition" INTEGER NOT NULL,
		        
	"day" INTEGER NOT NULL,

        "fixedPercentage" BOOLEAN NOT NULL DEFAULT FALSE,
			        
        "valuePercentage" DECIMAL(5,2) DEFAULT 0
				        
);
