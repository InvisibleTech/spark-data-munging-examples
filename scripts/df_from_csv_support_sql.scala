import org.apache.spark.sql._
import org.apache.spark.sql.types._
// This sample assumes that the data files needed are available at the relative path
 val sp500financials = sc.textFile("data/sp500-financials.csv")

 def isHeader(line: String) :Boolean = {
  line.contains("Symbol,Name,Sector,")
 }
                              
// You need to get the headers and use them for the schema
// NOTE: you need to see how to deal with names that have commas.
val header = sp500financials.filter(isHeader(_)).collect()(0).split(",")
// Get the SQL Context 
val sqlContext = new org.apache.spark.sql.SQLContext(sc)

// Simplistic Schema creation - we assume they are all nullable Strings and you notice here
// that we don't have the nice type inferencing for free like with JSON.
val schema = StructType(header.map(fieldName => StructField(fieldName, StringType, true)))

// Get just the data
val sp500Lines = sp500financials.filter(!isHeader(_))

// Make Rows and don't break quoted string values.
val rowRDD = sp500Lines.map(p=>{Row.fromSeq(p.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))})

// Now we can make a DataFrame and get back to SQL processing in Spark
val sp500financialsDF = sqlContext.createDataFrame(rowRDD, schema)

sp500financialsDF.registerTempTable("sp500fin")

val avgEPS = sqlContext.sql("SELECT AVG(`Earnings/Share`) FROM sp500fin")
val avgEPSNamed = sqlContext.sql("SELECT AVG(`Earnings/Share`) as AvgEPS FROM sp500fin")
val avgEPSProg = sp500financialsDF.agg(avg(sp500financialsDF.col("Earnings/Share")))
