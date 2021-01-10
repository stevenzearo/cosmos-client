package exception

/**
 * @author steve
 */
class CosmosConnectionException(errorCode: String = "COSMOS_CONNECT_EXCEPTION", message: String, cause: Throwable): CosmosException(errorCode, message, cause) {
    constructor(errorCode: String, message: String): this(errorCode, message, Exception(message))
    constructor(errorCode: String): this(errorCode, "Cosmos Connect Exception", Exception("Cosmos Connect Exception"))
}