// This sample assumes that the data files needed are available at the relative path
 val sp500Cos = sc.textFile("data/constituents-financials.csv")

 // You can define functions to help you do your work 
 def isHeader(line: String) :Boolean = {
  line.contains("Symbol,Name,Sector,Price,")
 }
                              
// You need to remove the headers.

 val headerRemoved = sp500Cos.filterNot(x => isHeader(x))

 // At any point type :history to see what you'ev typed in the past to get it back to copy
 // or save in a file.

val splitLines = headerRemoved.map(_ split ",")

// we still haven't caused anything to happen yet so let's use an action, like takeSample(n)

val splitSample = splitLines.takeSample(true, 20)

// How big is it and what is it?
splitSample.size

splitSample.getClass

// If we try to simply dump out the contents like this we see that all the Arrays are strings
 splitSample.foreach(x => println(x))

 // This is because we did not use any of Spark's capabilities to support typed data.. 
 // we simply loaded and split the records on the delimiter, naively and in the end 
 // not efficiently.  Also, we didn't get into skipping the header record, which is easy
