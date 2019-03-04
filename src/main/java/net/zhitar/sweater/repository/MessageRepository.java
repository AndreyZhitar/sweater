package net.zhitar.sweater.repository;

import net.zhitar.sweater.domain.Message;
import net.zhitar.sweater.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Page<Message> findAll(Pageable pageable);

    Page<Message> findByAuthor(User user, Pageable pageable);

    Page<Message> findByTag(String tag, Pageable pageable);
}
