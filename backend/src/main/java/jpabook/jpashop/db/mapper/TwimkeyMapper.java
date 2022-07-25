package jpabook.jpashop.db.mapper;

import jpabook.jpashop.db.entity.Twimkey;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
public class TwimkeyMapper {
    public Twimkey toEntity(Map<String, String> inputKeyMap) {
        Iterator<Map.Entry<String, String>> keys = inputKeyMap.entrySet().iterator();
        while (keys.hasNext()) {
            Map.Entry entry = (Map.Entry) keys.next();
            return Twimkey.builder()
                    .publickey((String) entry.getKey())
                    .privatekey((String) entry.getValue())
                    .build();

        }
        return null;
    }
}

