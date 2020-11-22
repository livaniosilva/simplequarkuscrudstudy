package org.acme.quarkus.sample;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@DBRider
@QuarkusTest
@QuarkusTestResource(DataBaseLifeCycle.class)
public class ProdutoTest {

    @Test
    @DataSet("produtos.yml")
    public void testeUm() {
        Assert.assertEquals(1, Produto.count());
    }

}