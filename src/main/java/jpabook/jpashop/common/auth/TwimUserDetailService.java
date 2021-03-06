package jpabook.jpashop.common.auth;

import jpabook.jpashop.api.service.UserService;
import jpabook.jpashop.db.entity.User;
import jpabook.jpashop.db.repository.UserFindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Component
public class TwimUserDetailService implements UserDetailsService{
    @Autowired
    UserFindRepository userFindRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFindRepository.findByUsername(username);
        if(user != null) {
            TwimUserDetails userDetails = new TwimUserDetails(user);
            return userDetails;
        }
        return null;
    }
}
