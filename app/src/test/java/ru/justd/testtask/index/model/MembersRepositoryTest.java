package ru.justd.testtask.index.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import ru.justd.testtask.index.model.remote.FetchMemebersResponse;
import rx.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by defuera on 20/04/2017.
 */
public class MembersRepositoryTest {

    private MembersRepository repository;
    private RetrofitMembersDataSource remote;
    private MemoryCacheMembersDataSource local = new MemoryCacheMembersDataSource();

    private final String TEST_CASE_VALUE_1 = "one";

    @Before
    public void setup() {
        remote = mock(RetrofitMembersDataSource.class);
        when(remote.fetchMembers()).then(createTestResponse(TEST_CASE_VALUE_1));

        repository = new MembersRepository(
                remote,
                local
        );
    }

    private Answer<Single<FetchMemebersResponse>> createTestResponse(final String value) {

        return invocation -> {
            List<Department> list = new ArrayList<>();
            list.add(new Department(value, null));
            return Single.just(new FetchMemebersResponse(list));
        };
    }

    /**
     * Assure data is loaded from cache if available
     */
    @Test
    public void loadPrices() {
        //1) data should be loaded from network and cached
        List<Department> remoteDepartments1 = repository.fetchMembers().toBlocking().value();
        Assert.assertEquals(TEST_CASE_VALUE_1, remoteDepartments1.get(0).name);

        //2) data should be loaded from cache
        List<Department> cachedDepartments1 = repository.fetchMembers().toBlocking().value();
        Assert.assertEquals(TEST_CASE_VALUE_1, cachedDepartments1.get(0).name);

        //check data is loaded from remote just one time
        verify(remote, times(1)).fetchMembers();
    }

}