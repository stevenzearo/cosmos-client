package client.sql;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.util.CosmosPagedIterable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class Cosmos {
    private final CosmosClient cosmosClient;
    private final CosmosSQLConfig cosmosSQLConfig;

    public Cosmos(CosmosSQLConfig config) {
        this.cosmosSQLConfig = config;
        this.cosmosClient = new CosmosClientBuilder()
            .endpoint(config.host)
            .key(config.key)
            .preferredRegions(List.of("West US"))
            .consistencyLevel(ConsistencyLevel.EVENTUAL)
            .contentResponseOnWriteEnabled(true)
            .buildClient();
    }

    public <T> List<T> query(String sql, Class<T> tClass) {
        CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        queryOptions.setQueryMetricsEnabled(true);

        CosmosPagedIterable<T> queryItems = cosmosClient.getDatabase(cosmosSQLConfig.database)
            .getContainer(cosmosSQLConfig.container)
            .queryItems(sql, queryOptions, tClass);
        ArrayList<T> results = new ArrayList<>();
        queryItems.handle(stringFeedResponse -> results.addAll(stringFeedResponse.getResults()));
        return results;
    }
}
