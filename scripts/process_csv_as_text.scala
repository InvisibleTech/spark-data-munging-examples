// This sample assumes that the data files needed are available at the relative path
 val sp500Cos = sc.textFile("data/constituents-financials.csv")

 // At any point type :history to see what you'ev typed in the past to get it back to copy
 // or save in a file.

val splitLines = sp500Cos.map(_ split ",")

// we still haven't caused anything to happen yet so let's use an action, like takeSample(n)

val splitSample = splitLines.takeSample(true, 20)

// How big is it and what is it?
splitSample.size

splitSample.getClass

// If we try to simply dump out the contents like this we see that all the Arrays are strings
 splitSample.foreach(x => println(x))

 // This is because we did not use any of Spark's capabilities to support typed data.. 
 // we simply loaded and split the records on the delimiter, naively and in the end 
 // not efficiently.
