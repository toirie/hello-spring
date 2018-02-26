package jp.co.chronos.hello.domain;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("todoService")
@AllArgsConstructor
public class HelloServiceImpl implements HelloService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public String getMessage() {
        MessagePrimaryKey primaryKey = new MessagePrimaryKey("1");
        Message message = messageRepository.findOne(primaryKey);
        return message.getMessage();
    }
}
