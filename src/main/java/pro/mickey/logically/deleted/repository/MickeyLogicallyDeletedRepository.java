package pro.mickey.logically.deleted.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.mickey.logically.deleted.repository.model.MickeyLogicallyDeletedEntity;

public interface MickeyLogicallyDeletedRepository extends JpaRepository<MickeyLogicallyDeletedEntity, Integer> {

}