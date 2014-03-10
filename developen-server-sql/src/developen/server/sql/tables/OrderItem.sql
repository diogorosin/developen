CREATE TABLE "OrderItem" (

	"order" INTEGER NOT NULL,
	
        "progeny" INTEGER NOT NULL,

	"amount" NUMERIC(14,4) NOT NULL DEFAULT 0,

        "price" NUMERIC(12,2) NOT NULL DEFAULT 0,

        "value" NUMERIC(12,2) NOT NULL DEFAULT 0	

);
