package com.guidelk.test;

import com.guidelk.tourism.entity.Customer;
import com.guidelk.tourism.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UnitTest {
        @Autowired
        CustomerRepository customerRepository;


        @Test
        public void getCustomerDataList() {
        List<Customer> customerList = this.customerRepository.findALL();
        Assertions.assertThat(customerList.size()).isGreaterThan(0);
}

}
