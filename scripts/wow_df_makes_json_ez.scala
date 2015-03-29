val sqlContext = new org.apache.spark.sql.SQLContext(sc)

val jsonDf = sqlContext.jsonFile("data/sp500IndexPEAndDiv.json")

jsonDf.show()

