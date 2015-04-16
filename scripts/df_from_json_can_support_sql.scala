// SQLContext is the main tool for this demo, and is loaded in the shell.  SparkContext, a.k.a. "sc" in the shell
// loads files into RDDs of String.  Which you'd have to parse and map... blah, blah, blah.
sqlContext.tables

// You  can load it either way:
val jsonDf = sqlContext.load("data/sp500IndexPEAndDiv.json", "json")

val jsonDf = sqlContext.jsonFile("data/sp500IndexPEAndDiv.json")

// jsonDf has a DataFrame which has schema information.  If you prefer SQL to lots of
// codey bits you can do some cool things by registering a tempTable
jsonDf.registerTempTable("sp500PEnDiv")

val all =  sqlContext.sql("SELECT * FROM sp500PEnDiv")
all.show

// Now this json is a bit messy let's see some of the numeric data is string
val avgCPIAnon = sqlContext.sql("SELECT AVG(`Consumer Price Index`) FROM sp500PEnDiv")
// The name assigned to the result is generated but you can name it...
val avgCPINamed = sqlContext.sql("SELECT AVG(`Consumer Price Index`) as AvgCPI FROM sp500PEnDiv")
// For those who fear SQL there is also another to get to this data is programatically
val avgCPIProg = jsonDf.agg(avg(jsonDf.col("Consumer Price Index")))
// Personally I think SQL is more concise for use... but building a query programatically can allow 
// tool smithing that is better than bunch of string concats.