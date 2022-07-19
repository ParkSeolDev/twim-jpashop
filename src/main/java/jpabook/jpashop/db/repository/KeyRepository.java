package jpabook.jpashop.db.repository;

import jpabook.jpashop.db.entity.Twimkey;
import jpabook.jpashop.db.mapper.TwimkeyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class KeyRepository {
    private final EntityManager em;
    private final TwimkeyMapper twimkeyMapper;

//    @Transactional
//    public Twimkey save(Map<String, String> inputKeyMap){
//        Twimkey twimKey = new Twimkey();
//        twimKey.setTwimmap(inputKeyMap);
//        em.persist(twimKey);
//        return twimKey;
//    }

    @Transactional
    public Twimkey save(Map<String, String> inputKeyMap){
        Twimkey twimKey = new Twimkey();
        twimKey = twimkeyMapper.toEntity(inputKeyMap);
        em.persist(twimKey);
        return twimKey;
    }

    public String getPrivateKey (String publicKey) {

//        Twimkey twimkey = em.createQuery("select t.privatekey from Twimkey t where t.publickey=:publicKey", Twimkey.class)
//                .setParameter("publicKey", publicKey)
//                .getSingleResult();
//        return twimkey.getPrivatekey();

        Twimkey twimkey = (Twimkey) em.createNativeQuery("select id, privatekey, publickey from twimkey where publickey=:publicKey", Twimkey.class)
                .setParameter("publicKey", publicKey)
                .getSingleResult();
        return twimkey.getPrivatekey();
    }
}
