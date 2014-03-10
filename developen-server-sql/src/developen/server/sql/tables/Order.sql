CREATE TABLE "Order" (

	"identifier" INTEGER DEFAULT NEXTVAL('OrderSequence') NOT NULL,
	
        "from" INTEGER NOT NULL,

	"to" INTEGER NOT NULL

);
