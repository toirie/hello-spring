package jp.co.chronos.hello.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, MessagePrimaryKey> {
}
