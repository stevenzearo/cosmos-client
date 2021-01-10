package client.sql;

import junit.framework.TestCase;

import java.util.List;

/**
 * @author steve
 */
public class CosmosTest extends TestCase {

    public void testQuery() {
        Cosmos cosmos = new Cosmos(new CosmosSQLConfig());

        List<String> query = cosmos.query("SELECT f.id FROM family f");
        assertFalse(query.isEmpty());
        cosmos.close();
    }
}