// To make this work you need to start spark shell like this:
// ../bin/spark-shell --driver-class-path ../lib/postgresql-9.3-1103.jdbc41.jar
val all = sqlContext.jdbc("jdbc:postgresql://localhost/sparkdemo?user=<replace>", "sp500")

// This fails right now, I think since names of columns are not properly escaped.
all.show

all.registerTempTable("sp500")

// Run a selection to get a subset of the data.
val above50 = sqlContext.sql("SELECT * FROM sp500 WHERE price > 50")

// And another...
val notHealthCare = sqlContext.sql("SELECT * FROM sp500 WHERE sector <> 'Health Care' ")

// How about membership?  With projections.
val itAndIndustry = sqlContext.sql("SELECT symbol, name, price FROM sp500 WHERE sector IN ('Industrials', 'Information Technology')")

// Some more tricks

val fieldNames = all.schema.fieldNames
val row1Data = all.take(1)(0).toSeq
val namedData = (fieldNames zip row1Data).toMap