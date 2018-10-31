import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.reactiverse.pgclient.PgClient;
import io.reactiverse.pgclient.PgConnection;
import io.reactiverse.pgclient.PgPool;
import io.reactiverse.pgclient.PgPoolOptions;
import io.reactiverse.pgclient.PgRowSet;
import io.reactiverse.pgclient.Tuple;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(VertxUnitRunner.class)
public class Reproducer {
    @Rule
    public PostgreSQLContainer postgres = new PostgreSQLContainer().withDatabaseName("test");

    @Test
    public void reproduce152(TestContext context) {
        Async async = context.async();
        PgPoolOptions options = new PgPoolOptions()
                .setPort(postgres.getMappedPort(5432))
                .setHost("localhost")
                .setDatabase("test")
                .setUser(postgres.getUsername())
                .setPassword(postgres.getPassword())
                .setMaxSize(1);

        PgPool client = PgClient.pool(options);
        client.getConnection(conAr -> {
            if (conAr.failed()) {
                context.fail(conAr.cause());
            }
            PgConnection con = conAr.result();
            con.exceptionHandler(context::fail);
            con.query("CREATE TABLE account(id INTEGER PRIMARY KEY)", ar -> {
                System.out.println(ar.succeeded());
                if (ar.failed()) {
                    context.fail(ar.cause());
                }
//                List<Tuple> batch = Arrays.asList(Tuple.of(1)); //uncomment this line and comment next to make test pass
                List<Tuple> batch = new ArrayList<>();
                con.preparedBatch("INSERT INTO account VALUES ($1)", batch, ar1 -> {
                    System.out.println(ar1.succeeded());
                    if (ar1.failed()) {
                        context.fail(ar1.cause());
                    }
                    System.out.println(ar1.result().rowCount());
                    assertEquals(1, ar1.result().rowCount());
                    async.complete();
                });
            });
        });

        async.awaitSuccess(20000);
    }
}