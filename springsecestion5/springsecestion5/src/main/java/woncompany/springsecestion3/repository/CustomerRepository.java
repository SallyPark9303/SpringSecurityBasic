package woncompany.springsecestion3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import woncompany.springsecestion3.model.Customer;

import java.util.List;

//CrudRepository: Spring Data JPA 프레임워크에 존재하는 것들 중 하나로 CRUD 연산을 위한 코드가 자동으로 생성
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    //  findBy: Spring Data JPA 에게 Select 쿼리를 반환해 오라는 것
    // Spring Data JPA 는 어떤 작업 조건으로 검색할거냐고 물음 : findBy 뒤에 email 열 이라고 알려줌
    List<Customer> findByEmail(String email);
}
