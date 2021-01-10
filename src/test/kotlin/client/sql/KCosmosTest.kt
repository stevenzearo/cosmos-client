package client.sql

import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * @author steve
 */
class KCosmosTest {

    @Test
    fun cosmosTest() {
        val config = KCosmosSQLConfig(
            host = "https://demo-root.documents.azure.com:443/",
            key = "q9DVHFkvg27dITRqrcTJQZ7GxJVDk5vs82AIeqIfzkjOILy8ixusYZBU7agejhKD7jP0s13CAMzsoUiOq9jjEA==",
            database = "demo-db",
            container = "family"
        )

        runBlocking {
            val kCosmos = KCosmos(config)
            config.database?.let { kCosmos.connectToDatabase(it) }
            config.container?.let { kCosmos.connectToContainer(it) }
            kCosmos.createContainer("students", "/student/name")
            kCosmos.connectToContainer("students")
            val s1 = Student("stu-001", "steve", 23, Gender.MALE, 86.5f)
            kCosmos.create(s1)
            val list = kCosmos.query("select * from students", Student::class.java)
            println(list.size)
//            assertFalse { list.isEmpty() }
        }
    }
}