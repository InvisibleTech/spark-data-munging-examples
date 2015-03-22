package org.invisibletech

import org.scalatest.Assertions
import org.junit.Test
import javax.sql.rowset.RowSetMetaDataImpl
import java.sql.ResultSetMetaData
import java.sql.Types

class MetaDataFacadeTest extends Assertions {
  @Test def shouldProduceEmptyListOfNames_Given_EmptyMetaData() = {
  assert(MetaDataFacade.names(new RowSetMetaDataImpl()) === Array[String]())
  }
  
  @Test def shouldReturnName_Given_SingltoneMetaData() = {
    val singletonMeta = new RowSetMetaDataImpl()
    singletonMeta.setColumnCount(1)
    singletonMeta.setNullable(1, ResultSetMetaData.columnNullable)
    singletonMeta.setColumnName(1, "Goku")
    singletonMeta.setColumnType(1, Types.VARCHAR)
    
    assert(MetaDataFacade.names(singletonMeta) === Array[String]("Goku"))
  }
  
  @Test def shouldReturnNames_Given_MultipleMetaData() = {
    val singletonMeta = new RowSetMetaDataImpl()
    singletonMeta.setColumnCount(2)

    singletonMeta.setNullable(1, ResultSetMetaData.columnNullable)
    singletonMeta.setColumnName(1, "Goku")
    singletonMeta.setColumnType(1, Types.VARCHAR)

    singletonMeta.setNullable(2, ResultSetMetaData.columnNullable)
    singletonMeta.setColumnName(2, "Majin Bu")
    singletonMeta.setColumnType(2, Types.DOUBLE)
    
    assert(MetaDataFacade.names(singletonMeta) === Array[String]("Goku", "Majin Bu"))
  }
}