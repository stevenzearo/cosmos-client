package client.sql

/**
 * @author steve
 */
data class KCosmosSQLConfig(
    val host: String = "https://demo-root.documents.azure.com:443/",
    val key: String = "q9DVHFkvg27dITRqrcTJQZ7GxJVDk5vs82AIeqIfzkjOILy8ixusYZBU7agejhKD7jP0s13CAMzsoUiOq9jjEA==",
    var database: String? = "demo-db",
    var container: String? = "family"
)