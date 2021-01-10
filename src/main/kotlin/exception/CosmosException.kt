package exception

/**
 * @author steve
 */
open class CosmosException(errorCode: String = "COSMOS_EXCEPTION", message: String,  cause: Throwable): Exception(message, cause) {
    constructor(errorCode: String, message: String): this(errorCode, message, Exception(message))
    constructor(errorCode: String): this(errorCode, "Cosmos Exception", Exception("Cosmos Exception"))
}