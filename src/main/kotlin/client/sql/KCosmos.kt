package client.sql

import com.azure.cosmos.*
import com.azure.cosmos.models.CosmosQueryRequestOptions
import exception.CosmosConnectionException

/**
 * @author steve
 */
class KCosmos(config: KCosmosSQLConfig) {
    private var cosmosClient: CosmosAsyncClient? = null
    private var cosmosSQLConfig: KCosmosSQLConfig? = null
    private var database: CosmosAsyncDatabase? = null
    private var container: CosmosAsyncContainer? = null

    init {
        cosmosSQLConfig = config
        cosmosClient = CosmosClientBuilder()
            .endpoint(config.host)
            .key(config.key)
            .preferredRegions(arrayListOf("West US"))
            .consistencyLevel(ConsistencyLevel.EVENTUAL)
            .contentResponseOnWriteEnabled(true)
            .buildAsyncClient()
    }

    suspend fun connectToDatabase(databaseId: String) {
        database = cosmosClient!!.getDatabase(databaseId)
    }

    suspend fun createContainer(containerId: String, partitionKeyPath: String) {
        checkDatabase()
        database!!.createContainer(containerId, partitionKeyPath)
    }

    suspend fun connectToContainer(containerId: String) {
        container = database!!.getContainer(containerId)
    }

    suspend fun showDatabases(): List<String> {
        return listOf("")
    }

    suspend fun showContainer(): List<String> {
        return listOf("")
    }

    suspend fun <T> query(sql: String, entityClass: Class<T>): List<T> {
        checkContainer()

        var results = arrayListOf<T>()

        val queryOptions = CosmosQueryRequestOptions()
        queryOptions.isQueryMetricsEnabled = true
        container!!
            .queryItems(sql, queryOptions, entityClass)
            .handle {
                results = it.results as ArrayList<T>
            }
        return results
    }

    suspend fun <T> create(t: T) {
        checkContainer()
        container!!.createItem(t)
    }

    fun close() {
        cosmosClient!!.close()
    }

    @Throws(CosmosConnectionException::class)
    private fun checkClient() {
        if (cosmosClient == null) {
            throw CosmosConnectionException(errorCode = "CLIENT_NOT_INITIALIZED")
        }
    }

    @Throws(CosmosConnectionException::class)
    private fun checkDatabase() {
        checkClient()
        if (database == null) {
            throw CosmosConnectionException(errorCode = "DATA_BASE_NOT_INITIALIZED")
        }
    }

    @Throws(CosmosConnectionException::class)
    private fun checkContainer() {
        checkDatabase()
        if (container == null) {
            throw CosmosConnectionException("CONTAINER_NOT_INITIALIZED")
        }
    }
}