import org.apache.spark.sql._
// This sample assumes that the data files needed are available at the relative path
 val sp500Cos = sc.textFile("data/constituents-financials.csv")

 def isHeader(line: String) :Boolean = {
  line.contains("Symbol,Name,Sector,Price,")
 }
                              
// You need to get the headers and use them for the schema
 val header = sp500Cos.filter(isHeader(_)).collect()(0).split(",")
// Get the SQL Context 
val sqlContext = new org.apache.spark.sql.SQLContext(sc)

//Automated Schema creation - we assume they are all nullable Strings
val schema = StructType(header.map(fieldName => StructField(fieldName, StringType, true)))

// Get just the data
val sp500Lines = sp500Cos.filter(!isHeader(_))

// Make Rows
val rowRDD = sp500Lines.map(p=>{Row.fromSeq(p.split(","))})

// Finally apply the schema and get a temp table from which to query.
val sp500SchemaRDD = sqlContext.applySchema(rowRDD, schema)

sp500SchemaRDD.registerTempTable("sp500")

// Now you can query your data.
sqlContext.sql("SELECT Symbol FROM sp500").foreach(println)

// Aggregation is available
sqlContext.sql("SELECT COUNT(Symbol) FROM sp500").foreach(println)
// Even though we said everything is a String in the schema - well...this works.
sqlContext.sql("SELECT SUM(Price) FROM sp500").foreach(println)
// And of course this doesn't - well it fails nicely
sqlContext.sql("SELECT SUM(Symbol) FROM sp500").foreach(println)
