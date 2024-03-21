package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Person;
import br.com.erudio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u From User u WHERE u.userName =: userName")//query jpql, apontando para o objeto user não tabela do banco
	User findByUsername(@Param("userName") String userName);
}


