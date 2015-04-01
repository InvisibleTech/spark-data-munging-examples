val jsonDf = sqlContext.jsonFile("data/sp500IndexPEAndDiv.json")

jsonDf.show()

