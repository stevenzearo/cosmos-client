package client.sql;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<String> query(String sql) {
        CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        queryOptions.setQueryMetricsEnabled(true);

        return
        cosmosClient.getDatabase(cosmosSQLConfig.database)
            .getContainer(cosmosSQLConfig.container)
            .queryItems("SELECT f.id FROM family f", queryOptions, String.class).streamByPage().flatMap(tFeedResponse -> tFeedResponse.getResults().stream()).collect(Collectors.toList());
    }

    public void close() {
        cosmosClient.close();
    }
}
