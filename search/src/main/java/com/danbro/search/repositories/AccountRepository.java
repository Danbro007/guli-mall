package com.danbro.search.repositories;

import java.util.List;
import com.danbro.search.entity.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Classname UserRepository
 * @Description TODO
 * @Date 2021/2/15 17:59
 * @Created by Administrator
 */
public interface AccountRepository extends ElasticsearchRepository<Account, Long> {
    List<Account> findByLastname(String lastName);

    List<Account> findByBalanceBefore(Long balance);
}
