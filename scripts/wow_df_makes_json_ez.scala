val sqlContext = new org.apache.spark.sql.SQLContext(sc)

val df = sqlContext.jsonFile("data/sp500IndexPEAndDiv.json")

df.show()