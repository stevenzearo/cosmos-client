package client.sql;

/**
 * @author steve
 */
public class CosmosSQLConfigBuilder {
    private final CosmosSQLConfig cosmosSQLConfig;

    public CosmosSQLConfigBuilder(String host, String key) {
        this.cosmosSQLConfig = new CosmosSQLConfig();
        cosmosSQLConfig.host = host;
        cosmosSQLConfig.key = key;
    }

    public CosmosSQLConfigBuilder setDataBase(String dataBase) {
        cosmosSQLConfig.database = dataBase;
        return this;
    }

    public CosmosSQLConfigBuilder setContainer(String container) {
        cosmosSQLConfig.container = container;
        return this;
    }

    public CosmosSQLConfig build(){
        return cosmosSQLConfig;
    }
}
